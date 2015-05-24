import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	static int DIAMETER = 10;
	double x = 0;
	double y = 0;
	double xa = 2;
	double ya = 1;
	private Game game;
	double speed;
	// ballMods represents the modifications made to the ball size
	// -1 means the size was decreased once, 0 means no changes, 1 means one increase
	int ballMods = 0;
	private Color c;

	public Ball(Game game, int X, int Y) {
		this.game = game;
		x = X;
		y = Y;
		speed = 1;
		this.c = Color.BLACK;
		
	}
	
	public void setColor(Color c){
		this.c = c;
	}
	
	public Color getColor(){
		return this.c;
	}

	public void addBallMod(){
		ballMods = ballMods + 1;
	}
	
	public void SubtractBallMod(){
		ballMods = ballMods - 1;
	}
	
	public int getBallMods(){
		return ballMods;
	}
	
	void move() {
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
				//game.started = false;
				Game.pointMultiplier = 1;
				
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
						System.out.println(life);
					}
					game.setLifeString(life);
					
					
					this.setColor(Color.BLACK);
					System.out.println("Lives left: " + game.Lives);
					speed = 1;
					Game.hasFireball = false;
					Game.hasMetalPower = false;
					this.setColor(Color.BLACK);
					setX(game.racquet.getBounds().x);
					setY(game.racquet.getBounds().y - 10);
					this.setColor(Color.BLACK);
					
				}
				if (Game.activeBalls.size() > 1){
					Game.activeBalls.remove(this);
				}
			}}
		if (collision()){
			if (game.hold == true){
				game.started = false;
			}
			
			// These conditionals check for collisions with the side of the racquet -- If such a collision occurs, the ball completely reverses
			// This conditional check for collisions with the right side of the racquet
			if (((getBounds().getX()) >= (game.racquet.getBounds().getX() + game.racquet.getBounds().getWidth() - 2)) ){
				ya = ya * (-1);
				xa = xa * (-1);
			// This conditional checks for collisions with the left side of the racquet
			}else if (((getBounds().getX() + DIAMETER) <= (game.racquet.getBounds().getX() + 3))){
				ya = ya * (-1);
				xa = xa * (-1);
			}
			else{
				ya =  -speed;
				//y = game.racquet.getTopY() - DIAMETER;
			}
		}
		x = x + xa;
		y = y + ya;
		
	}
	
	

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getXa() {
		return xa;
	}

	public void setXa(double d) {
		this.xa = d;
	}

	public double getYa() {
		return ya;
	}

	public void setYa(double ya) {
		this.ya = ya;
	}

	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.setColor(c);
		g.fillOval((int) x, (int) y, DIAMETER, DIAMETER);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, DIAMETER, DIAMETER);
	}

	
}
