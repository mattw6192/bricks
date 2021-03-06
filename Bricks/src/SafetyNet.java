import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SafetyNet {
	private static final int Y = 550;
	private int WIDTH = 850;
	private int HEIGHT = 10;
	private int x = 10;
	private Game game;
	private Color FILLCOLOR;
	private int hitsLeft;
	private Boolean ALIVE = true;
	private Powerup thisParent;

	/**
	 * initializes the safety net powerup
	 * @param game
	 * @param parent
	 */
	public SafetyNet(Game game, Powerup parent) {
		this.game = game;
		setX(2);
		FILLCOLOR = Color.LIGHT_GRAY;
		hitsLeft = 2;
		thisParent = parent;
	}
	
	/**
	 * returns the x coordinate of the safety net
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * paints the net on the screen
	 * @param g
	 */
	public void paint(Graphics2D g) {
		if (ALIVE == true){	
			g.setColor(FILLCOLOR);
			g.fillRect(getX(), Y, getWIDTH(), HEIGHT);
		}
	}


	/**
	 * returns the bounds of the net
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle(getX(), Y, getWIDTH(), HEIGHT);
	}

	/**
	 * returns the Y coordinate
	 * @return
	 */
	public int getTopY() {
		return Y;
	}


	/**
	 * returns the width
	 * @return
	 */
	public int getWIDTH() {
		return WIDTH;
	}


	/**
	 * sets the width
	 * @param num
	 */
	public void setWIDTH(int num) {
		WIDTH = num;
	}

	/**
	 * sets the x coordinate
	 * @param x
	 */
	void setX(int x) {
		this.x = x;
	}
	
	/**
	 * sets the color of the net
	 * @param fill
	 */
	public void setColor(Color fill){
		FILLCOLOR = fill;
	}
	
	/**
	 * removes a hit from the net
	 */
	public void subtractHit(){
		hitsLeft -= 1;
	}
	
	/**
	 * returns the number of hits the net can take
	 * @return
	 */
	public int getHitsLeft(){
		return hitsLeft;
	}
	
	/**
	 * removes the net from the board
	 */
	public void deleteNet(){
		game.safetyList.remove(this);
		game.activePowerups.remove(this);
		game.hasSafetyNet = false;
		game.activePowerups.remove(thisParent);
	}
	
	/**
	 * subtracts a hit, checks if the net is dead (removes if so) else it just recolors
	 */
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
	
	/**
	 * colors the net according to hits the net can take
	 */
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

