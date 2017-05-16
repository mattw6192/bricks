import java.awt.Color; 
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import javax.sound.sampled.Clip;


public class Ball { 
	static int DIAMETER = 12;
	double x = 0;
	double y = 0;
	double xa;
	double ya;
	private Game game;
	double speed;
	// ballMods represents the modifications made to the ball size
	// -1 means the size was decreased once, 0 means no changes, 1 means one increase
	int ballMods = 0;
	private Color c;
	Clip audioClip;

	/**
	 * initializes the ball information
	 * @param game
	 * @param X
	 * @param Y
	 */
	public Ball(Game game, int X, int Y) {
		this.game = game;
		x = X;
		y = Y;
		speed = 2;
		xa = speed;
		ya = speed;
		this.c = Color.BLACK;

	}
	
	/**
	 * sets the number for the ball modifications/ ball size
	 * @param num
	 */
	public void setBallMods(int num){
		ballMods = num;
	}
	
	/**
	 * sets the color of the ball
	 * @param c
	 */
	public void setColor(Color c){
		this.c = c; 
	}
	
	/**
	 * returns the color of the ball
	 * @return
	 */
	public Color getColor(){
		return this.c;
	}

	/**
	 * adds an additional ball modification to the ball
	 */
	public void addBallMod(){
		ballMods = ballMods + 1;
	}
	
	/**
	 * removes a ball modification from the ball
	 */
	public void SubtractBallMod(){
		ballMods = ballMods - 1;
	}
	
	/**
	 * returns the number of ball modifications the ball has
	 * @return
	 */
	public int getBallMods(){
		return ballMods;
	}
	
	/**
	 * workhorse for ball movement
	 * @throws IOException
	 */
	void move() throws IOException {
		if (x + xa < 0)
			xa =  speed;
		if (x + xa > game.getWidth() - DIAMETER)
			xa =  -speed;
		if (y + ya < 0)
			ya =  speed;
		if (y + ya > game.getHeight() - DIAMETER){
			
			if (game.isGameOver() == true && Game.activeBalls.isEmpty() == true){
				game.gameOver();
			}else{
				Game.pointMultiplier = 1;
				Game.missiles.clear();
				Game.hasShot = false;
				
				
				if (Game.activeBalls.size() == 1){
					if (game.Lives <= 1){
						Game.activeBalls.remove(this);
						game.gameOver();
					}
					game.started = false;
					game.Lives = game.Lives - 1;
					
					/*this part makes the frames title the number of lives the 
					 * user has.
					 */
					String life = "";
					for (int i = 0; i < game.Lives;i++){ 
						life += "*";
					}
					Game.hasSafetyNet = false;
					Game.setLifeString(life);
					System.out.println("Lives left: " + game.Lives);
					speed = 2;
					game.overwritePowerupLimits = false;
					game.probs.needsCollisions = false;
					Game.hold = false;
					Game.hasFireball = false;
					Game.hasMetalPower = false;
					Game.hasInsanityMode = false;
					Game.hasBoreyMode = false;
					Game.hasMagnet = false;
					Game.droppedPowerups.clear();
					Game.activeBalls.clear();
					Game.activeBalls.add(this);
					ballMods = 0;
					DIAMETER = 12;
					game.paddle.setWIDTH(60);
					game.paddle.racquetMods = 0;
					game.paddle.setX(100);
					setX(20);
					setY(320);
					//this.setColor(Color.BLACK);
					game.hasShot = false;
					game.bullets.clear();
					game.hasGun = false;
					game.missileCount = 0;
					game.missiles.clear();
					game.repaint();
				}
				if (Game.activeBalls.size() > 1){
					Game.activeBalls.remove(this);
				}
			}}
		if (collision()){
			if (game.hold == true){
				game.started = false;
			}
			Sound.Play(Sound.BALL);
			y = game.paddle.getTopY() - DIAMETER;
			// These conditionals check for collisions with the side of the paddle -- If such a collision occurs, the ball completely reverses
			// This conditional check for collisions with the right side of the paddle
			if (((getBounds().getX()) >= (game.paddle.getBounds().getX() + game.paddle.getBounds().getWidth() - 2)) ){
				ya = ya * (-1);
				xa = xa * (-1);
			// This conditional checks for collisions with the left side of the paddle
			}else if (((getBounds().getX() + DIAMETER) <= (game.paddle.getBounds().getX() + 3))){
				ya = ya * (-1);
				xa = xa * (-1);
			}
			else{
				ya =  -speed;
			}
		}
		x = x + xa;
		y = y + ya;
	}
	
	
	/**
	 * returns the X coordinate of the ball
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * sets the X coordinate of the ball
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * returns the y coordinate for the ball
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * sets the Y coordinate for the ball
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * returns the delta (change) value of the X coordinate
	 * @return
	 */
	public double getXa() {
		return xa;
	}

	/**
	 * sets the delta (change) value of the X coordinate
	 * @param d
	 */
	public void setXa(double d) {
		this.xa = d;
	}

	/**
	 * gets the delta (change) value of the Y coordinate
	 * @return
	 */
	public double getYa() {
		return ya;
	}

	/**
	 * sets the delta (change) value of the Y coordinate
	 * @param ya
	 */
	public void setYa(double ya) {
		this.ya = ya;
	}

	/**
	 * checks collisions of the paddle and the ball
	 * @return
	 */
	private boolean collision() {
		return game.paddle.getBounds().intersects(getBounds());
	}
	
	/**
	 * paints the ball onto the screen
	 * @param g
	 */
	public void paint(Graphics2D g) {
		g.setColor(c);
		g.fillOval((int) x, (int) y, DIAMETER, DIAMETER);
	}
	
	/**
	 * returns the calculated bounds of the ball
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, DIAMETER, DIAMETER);
	}
}
