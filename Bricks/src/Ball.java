import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 10;
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
			
			if (((getBounds().getX()) >= (game.racquet.getBounds().getX() + game.racquet.getBounds().getWidth() - 3)) ){
				//&& (getBounds().getY() > game.racquet.getBounds().getY())
				ya = ya * (-1);
				xa = xa * (-1);
				System.out.println("Right Side Intersection");
				System.out.println("Ball " + getBounds());
				System.out.println("racquet " + game.racquet.getBounds());
			}else if (((getBounds().getX() + DIAMETER) <= (game.racquet.getBounds().getX() + 3))){
				//&& (getBounds().getY()) > game.racquet.getBounds().getY()
				ya = ya * (-1);
				xa = xa * (-1);
				System.out.println("Left Side Intersection");
				System.out.println("Ball " + getBounds());
				System.out.println("racquet " + game.racquet.getBounds());
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
