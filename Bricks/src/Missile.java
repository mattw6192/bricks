import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;



public class Missile {

	private Game game;
	private int x;
	private int y;
	int xa = 0;
	int ya = 3;
	static final int DIAMETER = 10; //was 10 when missile was red square (increasing diameter without overall size might enable destroying nearby bricks
	//public static BufferedImage image;

	/**
	 * initializes the missile object attributes
	 * @param game
	 * @param X
	 * @param Y
	 */
	public Missile(Game game, int X, int Y){
		this.game = game;
		this.x = X;
		this.y = Y;
		//try {
			//image = ImageIO.read((getClass().getResource("/images/lil stevie.jpeg")));
		///} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
	}
	
	
/**
 * controls movement of the missile object
 * @throws IOException
 */
void move() throws IOException {
		
		
		if (y - ya < - 150){
			Game.hasShot = false;
			Game.missiles.remove(this);
			//Game.missileCount = Game.missileCount - 1;
		}
		if (collision()){
			// These conditionals check for collisions with the side of the paddle -- If such a collision occurs, the ball completely reverses
			// This conditional check for collisions with the right side of the paddle
			//Game.missiles.remove(this);
			//game.hasShot = false;
			//Game.hasShot = false;
			//setXa(0);
			//setYa(0);
			//setX(-100); // removes the powerup from the playing field
			//setY(-100); // removes the powerup from the playing field

		}
		x = x - xa;
		y = y - ya;
	}

	/**
	 * returns the X coordinate of the missile
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * sets the X coordinate of the missile
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * returns the Y coordinate of the missile
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * sets the Y coordinate of the missile
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * returns the delta (change) of the X coordinate
	 * @return
	 */
	public int getXa() {
		return xa;
	}

	/**
	 * sets the delta (change) of the X coordinate
	 * @param xa
	 */
	public void setXa(int xa) {
		this.xa = xa;
	}

	/**
	 * returns the delta (change) of the Y coordinate
	 * @return
	 */
	public int getYa() {
		return ya;
	}

	/**
	 * sets the delta (change) of the Y coordinate
	 * @param ya
	 */
	public void setYa(int ya) {
		this.ya = ya;
	}
	
	/**
	 * checks for collisions with bricks and reacts accordingly
	 * @return
	 * @throws IOException
	 */
	private boolean collision() throws IOException {
		if (Game.missiles.size()>0){
		for(int i = 0; i<Game.allBricks.size(); i++){ 
			if (Game.missiles.get(0).getBounds().intersects(Game.allBricks.get(i).getBounds())){
				Game.allBricks.get(i).subtractHit(2);
				Game.Score += 1;
				
				game.colorBricks();
				
				if (Game.allBricks.get(i).getHits() <= 0){ // remove a brick if its hit counter is 0
					Game.hideBrick(Game.allBricks.get(i), Game.activeBalls.get(0));
					Game.allBricks.remove(i);
					if (Game.allBricks.isEmpty()){
						if (Game.Round < Game.maxRound){
							Game.nextRound(game);
						}
						if (Game.Round == Game.maxRound ){
							game.gameWon();
						}
						if (Game.Round < Game.maxRound){
							game.nextRoundMessage();
						}
					}
				}
				return true;
			}
		}
		}return false;
	}
	
	/**
	 * returns the bounds of the missile
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
	/**
	 * paints the missile on the screen
	 * @param g
	 */
	public void paint(Graphics2D g) {
		//g.drawImage(image, x, y, null);
		g.setColor(Color.RED);
		g.fillRect(x, y, DIAMETER, DIAMETER);
	}
	
	 
}
