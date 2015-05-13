import java.awt.Graphics2D;


public class Brick {
	
	
	public Brick(){
		System.out.println("Made a brick");
	}
	
	public void paint(Graphics2D g) {
		g.fillRect(10, 10, 35, 15);
	}
	
}
