import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Paddle{
	private static final int Y = 530;
	private int WIDTH = 60;
	private int HEIGHT = 10;
	private int x = 0;
	private int xa = 0;
	private Game game;
	

	int racquetMods = 0;
	// paddleMods represents the modifications made to the paddle length
	// -1 means the size has been decreased once, 0 means no changes, 1 represents one size increase, etc

	/**
	 * initializes the paddle
	 * @param game
	 */
	public Paddle(Game game) {
		this.game = game;
		setX(100);
	}

	public void increaseSize(){
		int increaseAmount = (int) (getWIDTH() * 0.25);
		checkEdges(increaseAmount);
		setWIDTH((int) (getWIDTH() * 1.25));
		addRacquetMod();
		System.out.println("Paddle Increase - New level "  + racquetMods);
	}

	public void checkEdges(int num){
		if (getX() > 400){
			setX((int) getX() - num);
		}
	}
	
	/**
	 * sets the number for the modifications to the paddle
	 * @param num
	 */
	public void setRacquetMods(int num){
		racquetMods = num;
	}
	
	/**
	 * returns the X coordinate
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * adds an additional modification to the paddle
	 */
	public void addRacquetMod(){
		racquetMods = racquetMods + 1;
	}
	
	public void SubtractRacquetMod(){
		racquetMods = racquetMods - 1;
	}
	
	/**
	 * returns the number representation for the paddle modifications
	 * @return
	 */
	public int getRacquetMods(){
		return racquetMods;
	}
	
	/**
	 * controls movement of the paddle
	 */
	public void move() {
		if (getX() + xa > 0 && getX() + xa < game.getWidth() - getWIDTH())
			setX(getX() + xa);
	}

	/**
	 * paints the paddle to the screen
	 * @param g
	 */
	public void paint(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.fillRect(getX(), Y, getWIDTH(), HEIGHT);
	}

	/**
	 * updates the delta (change) in paddle to nothing when the key is released
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void MousePressed(MouseEvent e){
	}
	
	public void MouseClicked(MouseEvent e){
	}
	
	/**
	 * moves the paddle when arrow keys are pressed
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -5;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = 5;
	}

	/**
	 * returns the bounds of the paddle
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
	 * returns the Width of the paddle
	 * @return
	 */
	public int getWIDTH() {
		return WIDTH;
	}

	
	/**
	 * sets the width of the paddle
	 * @param num
	 */
	public void setWIDTH(int num) {
		checkEdges(num);
		WIDTH = num;
	}

	/**
	 * sets the X coordinate
	 * @param x
	 */
	void setX(int x) {
		this.x = x;
	}
}

