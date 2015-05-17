import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Brick {
	private static final int Y = 10;
	private static final int WIDTH = 35;
	private static final int HEIGHT = 15;
	int x = 100;
	int xa = 0;
	private Game game;
	private Color color;
	private Boolean Alive;
	
	public Brick(Game game){
		System.out.println("Made a brick");
		this.game = game;
		color = Color.BLACK;
		Alive = true;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color newColor){
		color = newColor;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public static int getY() {
		return Y;
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

	public void paint(Graphics2D g) {
		if (Alive){
			g.setColor(color);
			g.fillRect(100, 10, 35, 15);
		}
	}
	
	
	
	public Boolean getAlive() {
		return Alive;
	}

	public void setAlive(Boolean alive) {
		Alive = alive;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}
	
}
