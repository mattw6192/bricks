import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Missile {

	private Game game;
	private int x;
	private int y;
	int xa = 0;
	int ya = 1;
	static final int DIAMETER = 10;

	public Missile(Game game, int X, int Y){
		this.game = game;
		x = X;
		y = Y;
		
	}
	
	void move() {
		
		
		//if (y + ya > game.getHeight() - DIAMETER){
			//System.out.println("Powerup Lost!");
			
		//}
		if (collision()){
			// These conditionals check for collisions with the side of the racquet -- If such a collision occurs, the ball completely reverses
			// This conditional check for collisions with the right side of the racquet
			
			setXa(0);
			setYa(0);
			setX(-100); // removes the powerup from the playing field
			setY(-100); // removes the powerup from the playing field
		}
		x = x + xa;
		y = y + ya;
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
		for (Brick b : Game.allBricks){
			if (b.getBounds().intersects(getBounds())){ return true; }
		}
		return false;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, DIAMETER, DIAMETER);
	}
	 
}
