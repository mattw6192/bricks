import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Missile {

	private Game game;
	private int x;
	private int y;
	int xa = 0;
	int ya = 3;
	static final int DIAMETER = 10; //was 10 when missile was red square (increasing diameter without overall size might enable destroying nearby bricks
	//public static BufferedImage image;

	public Missile(Game game, int X, int Y){
		this.game = game;
		this.x = X;
		this.y = Y;
		//try {
			//image = ImageIO.read((getClass().getResource("/images/lil stevie.jpeg")));
		///} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
	}
	
void move() {
		
		
		if (y - ya < - 150){
			Game.hasShot = false;
			Game.missiles.remove(this);
		}
		if (collision()){
			// These conditionals check for collisions with the side of the racquet -- If such a collision occurs, the ball completely reverses
			// This conditional check for collisions with the right side of the racquet
			//Game.missiles.remove(this);
			//game.hasShot = false;
			//Game.hasShot = false;
			//setXa(0);
			//setYa(0);
			//setX(-100); // removes the powerup from the playing field
			//setY(-100); // removes the powerup from the playing field

		}
		x = x - xa;
		y = y - ya;
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
		if (Game.missiles.size()>0){
		for(int i = 0; i<Game.allBricks.size(); i++){ 
			if (Game.missiles.get(0).getBounds().intersects(Game.allBricks.get(i).getBounds())){
				Game.allBricks.get(i).subtractTwoHits();
				
				if (Game.allBricks.get(i).getHits() == 4){Game.allBricks.get(i).setColor(Color.BLACK);}
				if (Game.allBricks.get(i).getHits() == 3){Game.allBricks.get(i).setColor(Color.BLUE);}
				if (Game.allBricks.get(i).getHits() == 2){Game.allBricks.get(i).setColor(Color.GREEN);} // update the color
				if (Game.allBricks.get(i).getHits() == 1){Game.allBricks.get(i).setColor(Color.YELLOW);} // for certain hit count
				
				if (Game.allBricks.get(i).getHits() <= 0){ // remove a brick if its hit counter is 0
					Game.hideBrick(Game.allBricks.get(i), Game.activeBalls.get(0));
					Game.allBricks.remove(i);
					if (Game.allBricks.isEmpty()){
						if (Game.Round < Game.maxRound){
							
							Game.Round += 1;
							Game.started = false;
							Game.hold = false;
							Game.placeHolder.clear();
							Game.nextRound();
							Game.hasFireball = false;
							Game.hasMetalPower = false;
							Game.hasShot = false;
							Game.missileCount = 0;
							Game.missiles.clear();
							Ball saveBall = Game.activeBalls.get(0);
							Game.activeBalls.clear();
							Game.activeBalls.add(saveBall);
							saveBall.speed = 2;
							saveBall.ballMods = 0;
							saveBall.DIAMETER = 12;
							game.racquet.setWIDTH(60);
							game.racquet.racquetMods = 0;

						}
						if (Game.Round == Game.maxRound ){
							game.gameWon();
						}
						if (Game.Round < Game.maxRound) {
							game.nextRoundMessage();
						}
						
					}
				}
				
				
				return true;
			}
		}
		}return false;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
	public void paint(Graphics2D g) {
		//g.drawImage(image, x, y, null);
		g.setColor(Color.RED);
		g.fillRect(x, y, DIAMETER, DIAMETER);
	}
	
	 
}
