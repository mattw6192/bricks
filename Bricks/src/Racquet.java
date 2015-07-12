import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Racquet {
	private static final int Y = 530;
	private int WIDTH = 60;
	private int HEIGHT = 10;
	private int x = 0;
	private int xa = 0;
	private Game game;
	

	int racquetMods = 0;
	// paddleMods represents the modifications made to the paddle length
	// -1 means the size has been decreased once, 0 means no changes, 1 represents one size increase, etc

	public Racquet(Game game) {
		this.game = game;
		setX(100);
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
		g.setColor(Color.GRAY);
		g.fillRect(getX(), Y, getWIDTH(), HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void MousePressed(MouseEvent e){
	}
	
	public void MouseClicked(MouseEvent e){
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -5;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = 5;
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
}

