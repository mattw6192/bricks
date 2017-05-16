import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MachineGun {
	private Game game;
	private int x;
	private int y;
	int xa = 0;
	int ya = 3;
	static final int DIAMETER = 10; //was 10 when missile was red square (increasing diameter without overall size might enable destroying nearby bricks
	public static BufferedImage image;

	/**
	 * initializes the information for the machine gun
	 * @param game
	 * @param X
	 * @param Y
	 */
	public MachineGun(Game game, int X, int Y){
		this.game = game;
		this.x = X;
		this.y = Y;
		try {
			image = ImageIO.read((getClass().getResource("/images/coolbrule.gif")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * moves the machine gun bullets
	 * @throws IOException
	 */
	void move() throws IOException {
		
		
		if (y - ya < - 150){
			
		}
		if (collision()){
			// These conditionals check for collisions with the side of the paddle -- If such a collision occurs, the ball completely reverses
			// This conditional check for collisions with the right side of the paddle
			//Game.bullets.remove(this);
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
	 * returns the X coordinates of the machine gun
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * sets the X coordinates of the machine gun
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * returns the Y coordinates of the machine gun
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * sets the Y coordinates of the machine gun
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * returns the delta (change) value of the X coordinates
	 * @return
	 */
	public int getXa() {
		return xa;
	}

	/**
	 * sets the delta (change) value of the X coordinates
	 * @param xa
	 */
	public void setXa(int xa) {
		this.xa = xa;
	}

	/**
	 * returns the delta (change) value of the Y coordinates
	 * @return
	 */
	public int getYa() {
		return ya;
	}

	/**
	 * sets the delta (change) value of the Y coordinates
	 * @param ya
	 */
	public void setYa(int ya) {
		this.ya = ya;
	}
	
	/**
	 * checks for collisions with bricks
	 * @return
	 * @throws IOException
	 */
	private boolean collision() throws IOException {
		if (Game.bullets.size()>0){
			
		for (int j = 0; j < Game.bullets.size(); j++){
			for(int i = 0; i<Game.allBricks.size(); i++){ 
			
				
			if (Game.bullets.get(j).getBounds().intersects(Game.allBricks.get(i).getBounds())){
				Game.allBricks.get(i).subtractHit(1);
				Game.Score += 1;
				Game.bullets.remove(j);
				
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
		}
		}return false;
	}
	
	/**
	 * returns the bounds of the object
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
	/**
	 * paints the object onto the screen
	 * @param g
	 */
	public void paint(Graphics2D g) {
		g.drawImage(image, x, y, null);
		//g.setColor(Color.RED);
		//g.fillRect(x, y, DIAMETER, DIAMETER);
	}
}
