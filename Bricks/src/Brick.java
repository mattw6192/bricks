import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Brick {
	private int Y = 10;
	private int WIDTH;
	private int HEIGHT;
	int x;
	int xa = 0;
	//private Game game;
	private Color color;
	private Boolean Alive;
	private int hits;
	Boolean canBeHit = true;
	
	/**
	 * returns the number of hits the brick has remaining
	 * @return
	 */
	public int getHits() {
		return hits;
	}
	
	/**
	 * sets the value of if the brick can be hit or not to true or false
	 * @param value
	 */
	public void setCanBeHit(Boolean value){
		canBeHit = value;
	}

	/**
	 * sets the number of hits the brick can take
	 * @param hits
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}
	
	/**
	 * removes one from the number of hits the brick can take
	 */
	public void subtractHit(){
		this.hits -= 1;
	}
	
	/** 
	 * removes two from the number of hits the brick can take
	 */
	public void subtractTwoHits(){
		this.hits -= 2;
	}
	
	/**
	 * subtracts all of the possible hits the brick can take
	 */
	public void subtractAllHits(){
		this.hits = 0;
	}

	/**
	 * initializes the brick information
	 * @param startX
	 * @param startY
	 * @param startWidth
	 * @param startHeight
	 * @param starthits
	 */
	public Brick(int startX,int startY,int startWidth,int startHeight, int starthits){
		//System.out.println("Made a brick");
		//this.game = game;
		x = startX;
		Y = startY;
		WIDTH = startWidth;
		HEIGHT = startHeight;
		color = Color.BLACK;
		Alive = true;
		hits = starthits;
	}
	
	/**
	 * returns the color of the brick
	 * @return
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * sets the color of the brick
	 * @param newColor
	 */
	public void setColor(Color newColor){
		color = newColor;
	}
	
	/**
	 * returns the X coordinates of the brick
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * sets the X coordinates of the brick
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * These methods are currently disabled as they were not needed. The Y value, width, and height are all cast upon creation of the object
	 * 
	 */
	
	//public static int getY() {
	//	return Y;
	//}

	//public static int getWidth() {
	//	return WIDTH;
	//}

	//public static int getHeight() {
		//return HEIGHT;
	//}
	
	/**
	 * paints the brick onto the screen
	 * @param g
	 */
	public void paint(Graphics2D g) {
		if (Alive){
			g.setColor(color);
			g.fillRect(x, Y, WIDTH, HEIGHT);
		}
	}
	
	/**
	 * returns the status of the brick (Alive or dead) as a boolean
	 * @return
	 */
	public Boolean getAlive() {
		return Alive;
	}

	/**
	 * sets the status of the brick (alive or dead) as a boolean
	 * @param alive
	 */
	public void setAlive(Boolean alive) {
		Alive = false;
	}

	/**
	 * returns the bounds of the brick object
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}
	
}
