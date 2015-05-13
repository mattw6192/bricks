import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Brick {
	private static final int Y = 330;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	int x = 0;
	int xa = 0;
	private Game game;
	
	public Brick(Game game){
		System.out.println("Made a brick");
		this.game = game;
	}
	
	public void paint(Graphics2D g) {
		g.fillRect(100, 10, 35, 15);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}
	
}
