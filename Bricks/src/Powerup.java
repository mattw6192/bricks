import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Powerup {
	static final int DIAMETER = 10;
	int x = 0;
	int y = 0;
	int xa = 0;
	int ya = 1;
	private Game game;
	int duration; // duration of the powerup
	String ability; // the name of the ability
	Boolean active; // is this powerup active
	// paddleMods represents the modifications made to the paddle length
	// -1 means the size has been decreased once, 0 means no changes, 1 represents one size increase, etc
	int paddleMods = 0;
	// ballMods represents the modifications made to the ball size
	// -1 means the size was decreased once, 0 means no changes, 1 means one increase
	int ballMods = 0;

	public Powerup(Game game, int X, int Y, int time, String powerup) {
		this.game = game;
		x = X;
		y = Y;
		active = false;
		duration = time;
		ability = powerup;
		int caseNumber = getPowerNum();
		performAction(caseNumber);
		ballMods = 0;
		paddleMods = 0;
	}

	public void performAction(int caseNumber){
		switch(caseNumber){
		case 12:
			game.Lives += 1;
			break;
		case 11:
			break; // not ready
		case 10:
			break; // not ready
		case 9:
			break; // not ready
		case 8:
			
			if (game.ball.ballMods >= -3){
				game.ball.DIAMETER = (int) (game.ball.DIAMETER * .9);
				game.ball.SubtractBallMod();
				System.out.println("Ball Decrease - New level "  + game.ball.ballMods);
			}else{
				System.out.println("Powerup not availiable - the ball size is too low.");
			}
			break; // not ready
		case 7:
			break; // not ready
		case 6:
			if (game.racquet.racquetMods >= -5){
				game.racquet.WIDTH = (int) (game.racquet.WIDTH * .9);
				game.racquet.SubtractRacquetMod();
				System.out.println("Racquet Decrease - New level "  + game.racquet.racquetMods);
			}else{
				System.out.println("Powerup not availiable - the racquet size is too low.");
			}
			break; // not ready
		case 5:
			if (game.ball.speed > 1){
				game.ball.speed -= 1;
			}else{
				System.out.println("Powerup not availiable - the ball speed is too slow.");
			}
			break; // not ready
		case 4:
			if (game.ball.speed < 3){
				game.ball.speed += 1;
			}else{
				System.out.println("Powerup not availiable - the ball speed is too high.");
			}
			break; // not ready
		case 3:
			
			if (game.racquet.racquetMods < 7){
				game.racquet.WIDTH = (int) (game.racquet.WIDTH * 1.15);
				game.racquet.addRacquetMod();
				System.out.println("Racquet Increase - New level "  + game.racquet.racquetMods);
			}else{
				System.out.println("Powerup not availiable - the racquet size is too high.");
			}
			break; // not ready
		case 2:
			if (game.ball.ballMods < 5){
				game.ball.DIAMETER = (int) (game.ball.DIAMETER * 1.1);
				game.ball.addBallMod();
				System.out.println("Ball Increase - New level "  + game.ball.ballMods);
			}else{
				System.out.println("Powerup not availiable - the ball size is too high.");
			}
			
			break; // not ready
		case 1:
			game.hold = true;
			break; // not ready
	}
	}
	
	public void powerupEnd(){
		game.hold = false;
		active = false;
	}
	
	public int getPowerNum(){
		if (ability.equals("Freeze")){return 1;};
		if (ability.equals("Ball Increase")){return 2;};
		if (ability.equals("Paddle Increase")){return 3;};
		if (ability.equals("Speed Up")){return 4;};
		if (ability.equals("Slow Down")){return 5;};
		if (ability.equals("Paddle Decrease")){return 6;};
		if (ability.equals("Multiple Balls")){return 7;};
		if (ability.equals("Ball Decrease")){return 8;};
		if (ability.equals("Double Points")){return 9;};
		if (ability.equals("Fireball")){return 10;};
		if (ability.equals("Metal Ball")){return 11;};
		return 12;
		
	}
	
	void move() {
		if (x + xa < 0)
			xa = 1;
		if (x + xa > game.getWidth() - DIAMETER)
			xa = -1;
		if (y + ya < 0)
			ya = 1;
		if (y + ya > game.getHeight() - DIAMETER){
			System.out.println("Lives left: " + (game.Lives - 1));
			game.Lives = game.Lives - 1;
		
			if (game.isGameOver() == true){
				game.gameOver();
			}else{
				game.started = false;
			}}
		if (collision()){
			// These conditionals check for collisions with the side of the racquet -- If such a collision occurs, the ball completely reverses
			// This conditional check for collisions with the right side of the racquet
			active = true;
			setX(-100); // removes the powerup from the playing field
			setY(-100); // removes the powerup from the playing field
		}
		
	}
	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getXa() {
		return xa;
	}

	public void setXa(int xa) {
		this.xa = xa;
	}

	public int getYa() {
		return ya;
	}

	public void setYa(int ya) {
		this.ya = ya;
	}

	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
