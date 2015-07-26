import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SafetyNet {
	private static final int Y = 550;
	private int WIDTH = 853;
	private int HEIGHT = 10;
	private int x = 0;
	private int xa = 0;
	private Game game;
	private Color FILLCOLOR;
	private int hitsLeft;
	private Boolean ALIVE = true;
	private Powerup thisParent;
	

	int racquetMods = 0;
	// paddleMods represents the modifications made to the paddle length
	// -1 means the size has been decreased once, 0 means no changes, 1 represents one size increase, etc

	public SafetyNet(Game game, Powerup parent) {
		this.game = game;
		setX(0);
		FILLCOLOR = Color.LIGHT_GRAY;
		hitsLeft = 2;
		thisParent = parent;
	}
	
	public void setRacquetMods(int num){
		racquetMods = num;
	}
	
	public int getX() {
		return x;
	}

	public void addRacquetMod(){
		racquetMods = racquetMods + 1;
	}
	
	public void SubtractRacquetMod(){
		racquetMods = racquetMods - 1;
	}
	
	public int getRacquetMods(){
		return racquetMods;
	}
	
	public void move() {
		if (getX() + xa > 0 && getX() + xa < game.getWidth() - getWIDTH())
			setX(getX() + xa);
	}

	public void paint(Graphics2D g) {
		if (ALIVE == true){	
			g.setColor(FILLCOLOR);
			g.fillRect(getX(), Y, getWIDTH(), HEIGHT);
		}
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void MousePressed(MouseEvent e){
	}
	
	public void MouseClicked(MouseEvent e){
	}
	
	public void keyPressed(KeyEvent e) {
	}

	public Rectangle getBounds() {
		return new Rectangle(getX(), Y, getWIDTH(), HEIGHT);
	}

	public int getTopY() {
		return Y;
	}


	public int getWIDTH() {
		return WIDTH;
	}


	public void setWIDTH(int num) {
		WIDTH = num;
	}

	void setX(int x) {
		this.x = x;
	}
	
	public void setColor(Color fill){
		FILLCOLOR = fill;
	}
	
	public void subtractHit(){
		hitsLeft -= 1;
	}
	
	public int getHitsLeft(){
		return hitsLeft;
	}
	
	public void deleteNet(){
		game.safetyList.remove(this);
		game.activePowerups.remove(this);
		game.hasSafetyNet = false;
		game.activePowerups.remove(thisParent);
	}
	
	public void checkHits(){
		subtractHit();
		
		if (hitsLeft <= 0){
			deleteNet();
			ALIVE = false;
			deleteNet();
		}else{
			colorNet();
		}
	}
	
	public void colorNet(){
		if (hitsLeft == 2){
			FILLCOLOR = Color.LIGHT_GRAY;
		}else if (hitsLeft == 1){
			FILLCOLOR = Color.WHITE;
		}else if (hitsLeft <= 0){
			ALIVE = false;
			deleteNet();
		}
	}
	
	
}

