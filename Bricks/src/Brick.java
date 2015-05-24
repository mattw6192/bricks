import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Brick {
	private int Y = 10;
	private int WIDTH;
	private int HEIGHT;
	int x;
	int xa = 0;
	//private Game game;
	private Color color;
	private Boolean Alive;
	private int hits;
	Boolean canBeHit = true;
	
	public int getHits() {
		return hits;
	}
	
	public void setCanBeHit(Boolean value){
		canBeHit = value;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}
	
	public void subtractHit(){
		this.hits -= 1;
	}
	
	public void subtractTwoHits(){
		this.hits -= 2;
	}
	
	public void subtractAllHits(){
		this.hits = 0;
	}

	public Brick(int startX,int startY,int startWidth,int startHeight, int starthits){
		//System.out.println("Made a brick");
		//this.game = game;
		x = startX;
		Y = startY;
		WIDTH = startWidth;
		HEIGHT = startHeight;
		color = Color.BLACK;
		Alive = true;
		hits = starthits;
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

	//public static int getY() {
	//	return Y;
	//}

	//public static int getWidth() {
	//	return WIDTH;
	//}

	//public static int getHeight() {
		//return HEIGHT;
	//}

	public void paint(Graphics2D g) {
		if (Alive){
			g.setColor(color);
			g.fillRect(x, Y, WIDTH, HEIGHT);
		}
	}
	
	public Boolean getAlive() {
		return Alive;
	}

	public void setAlive(Boolean alive) {
		Alive = false;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}
	
}
