import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	static final int DIAMETER = 10;
	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;
	private Game game;

	public Ball(Game game, int X, int Y) {
		this.game = game;
		x = X;
		y = Y;
	}

	void move() {
		if (x + xa < 0)
			xa = 1;
		if (x + xa > game.getWidth() - DIAMETER)
			xa = -1;
		if (y + ya < 0)
			ya = 1;
		if (y + ya > game.getHeight() - DIAMETER)
			game.gameOver();
		if (collision()){
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
				ya = -1;
				//y = game.racquet.getTopY() - DIAMETER;
			}
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
		return game.racquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
