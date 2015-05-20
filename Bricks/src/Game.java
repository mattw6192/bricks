import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel implements MouseListener {

	Ball ball = new Ball(this, 20, 320);
	//Ball ball2 = new Ball(this);
	double tempBallSize = 0;
	Boolean started = false;
	int Lives = 3;
	static int Score = 000000;
	Boolean hold = false;
	
	static Random randNum = new Random();
	
	//top row of bricks from left to right
	static Brick brick = new Brick(10, 10, 35, 15, 4);
	static Brick brick2 = new Brick(50, 10, 35, 15, 4);
	static Brick brick3 = new Brick(90, 10, 35, 15, 4);
	static Brick brick4 = new Brick(130, 10, 35, 15, 4);
	static Brick brick5 = new Brick(170, 10, 35, 15, 4);
	static Brick brick6 = new Brick(210, 10, 35, 15, 4);
	static Brick brick7 = new Brick(250, 10, 35, 15, 4);
	
	// second row of bricks from left to right
	static Brick brick2a = new Brick(28, 30, 35, 15, 4);
	static Brick brick2b = new Brick(68, 30, 35, 15, 4);
	static Brick brick2c = new Brick(108, 30, 35, 15, 4);
	static Brick brick2d = new Brick(148, 30, 35, 15, 4);
	static Brick brick2e = new Brick(188, 30, 35, 15, 4);
	static Brick brick2f = new Brick(228, 30, 35, 15, 4);
	
	// third row of bricks from left to right
	static Brick brick3a = new Brick(50, 50, 35, 15, 3);
	static Brick brick3b = new Brick(90, 50, 35, 15, 3);
	static Brick brick3c = new Brick(130, 50, 35, 15, 3);
	static Brick brick3d = new Brick(170, 50, 35, 15, 3);
	static Brick brick3e = new Brick(210, 50, 35, 15, 3);
	
	// fourth row of bricks from left to right
	static Brick brick4a = new Brick(70, 70, 35, 15, 2);
	static Brick brick4b = new Brick(110, 70, 35, 15, 2);
	static Brick brick4c = new Brick(150, 70, 35, 15, 2);
	static Brick brick4d = new Brick(190, 70, 35, 15, 2);
	
	// fifth row of bricks from left to right
	static Brick brick5a = new Brick(90, 90, 35, 15, 2);
	static Brick brick5b = new Brick(130, 90, 35, 15, 2);
	static Brick brick5c = new Brick(170, 90, 35, 15, 2);
	
	// sixth row of bricks from left to right
	static Brick brick6a = new Brick(108, 110, 35, 15, 1);
	static Brick brick6b = new Brick(148, 110, 35, 15, 1);
	
	// final row of bricks 
	static Brick brick7a = new Brick(130, 130, 35, 15, 4);
	
	
	private static ArrayList<Brick> allBricks = new ArrayList<Brick>();
	
	Racquet racquet = new Racquet(this);

	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		addMouseListener(new MouseListener(){
			public void MousePressed(MouseEvent e){
				if(e.getButton() == MouseEvent.BUTTON1)
			    {
			      System.out.println("left button click");
			    }	    
			    else if(e.getButton() == MouseEvent.BUTTON3)
			    {
			      System.out.println("right button click");;
			    }
			}

			public void mouseClicked(MouseEvent arg0) {
				started = true;
				hold = false;
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		setFocusable(true);
	}
	
	private void move() {
		//ball.move();
		//ball2.move();
		if (started == true){
			ball.move();
			//hold = false;
		}else{
			ball.setX((int) racquet.getBounds().getX() + 20);
			ball.setY((int) racquet.getBounds().getY() - 10);
		}
		racquet.move();
		//ball2.move();
		
	}

	public boolean isGameOver(){
		if (Lives <= 0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		//ball2.paint(g2d);
		racquet.paint(g2d);
		brick.paint(g2d);
		brick2.paint(g2d);
		brick3.paint(g2d);
		brick4.paint(g2d);
		brick5.paint(g2d);
		brick6.paint(g2d);
		brick7.paint(g2d);
		
		brick2a.paint(g2d);
		brick2b.paint(g2d);
		brick2c.paint(g2d);
		brick2d.paint(g2d);
		brick2e.paint(g2d);
		brick2f.paint(g2d);
		
		brick3a.paint(g2d);
		brick3b.paint(g2d);
		brick3c.paint(g2d);
		brick3d.paint(g2d);
		brick3e.paint(g2d);
		
		brick4a.paint(g2d);
		brick4b.paint(g2d);
		brick4c.paint(g2d);
		brick4d.paint(g2d);
		
		brick5a.paint(g2d);
		brick5b.paint(g2d);
		brick5c.paint(g2d);
		
		brick6a.paint(g2d);
		brick6b.paint(g2d);
		
		brick7a.paint(g2d);
		
	}
	
	public void gameOver() {
		JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.ERROR_MESSAGE);
		System.exit(ABORT);
	}
	
	public void gameWon() {
		
		JOptionPane.showMessageDialog(this, "You have completed this round.", "Winner!", JOptionPane.INFORMATION_MESSAGE);
		System.exit(ABORT);
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Brick Breaker");
		Game game = new Game();
		//game.ball.setXa(-3);
		//game.ball2.setXa(2);
		//game.ball2.setX(3);
		//game.ball2.setY(3);
		//game.ball2.setYa(2);
		
		
		allBricks.add(brick);
		allBricks.add(brick2);
		allBricks.add(brick3);
		allBricks.add(brick4);
		allBricks.add(brick5);
		allBricks.add(brick6);
		allBricks.add(brick7);
		allBricks.add(brick2a);
		allBricks.add(brick2b);
		allBricks.add(brick2c);
		allBricks.add(brick2d);
		allBricks.add(brick2e);
		allBricks.add(brick2f);
		allBricks.add(brick3a);
		allBricks.add(brick3b);
		allBricks.add(brick3c);
		allBricks.add(brick3d);
		allBricks.add(brick3e);
		allBricks.add(brick4a);
		allBricks.add(brick4b);
		allBricks.add(brick4c);
		allBricks.add(brick4d);
		allBricks.add(brick5a);
		allBricks.add(brick5b);
		allBricks.add(brick5c);
		allBricks.add(brick6a);
		allBricks.add(brick6b);
		allBricks.add(brick7a);
		for(int i = 0; i<allBricks.size(); i++){ 
			if (allBricks.get(i).getHits() == 4){allBricks.get(i).setColor(Color.BLACK);}
			if (allBricks.get(i).getHits() == 3){allBricks.get(i).setColor(Color.BLUE);}
			if (allBricks.get(i).getHits() == 2){allBricks.get(i).setColor(Color.GREEN);} // update the color
			if (allBricks.get(i).getHits() == 1){allBricks.get(i).setColor(Color.YELLOW);} // for certain hit count
		}
		
		//for (int i = 0; i < allBricks.size(); i+= 2){ //set the colors of the bricks.
		//	allBricks.get(i).setColor(Color.BLUE);
		//}
		
		//or (Brick b : allBricks){
		//	if (b.getColor().equals(Color.BLUE)){
			//	b.setHits(3);
				//System.out.println("hits for blue: "+b.getHits());
		//	}
			//else{
				//b.setHits(4);
			//}
		//}
		frame.add(game);
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(game);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.move();
			game.repaint();
			for(int i = 0; i<allBricks.size(); i++){ 
				
				if (game.ball.getBounds().intersects(allBricks.get(i).getBounds())){
					Score += 100;
					checkSideHits(allBricks.get(i), game.ball);
				    allBricks.get(i).subtractHit(); // this is where im subtracting a hit for every hit with the ball
				    game.getPowerup(allBricks.get(i));
				    
				    game.ball.ya = game.ball.ya * (-1); //update coordinates of ball to avoid multiple hits at the same time
					//game.ball.xa = game.ball.xa * (-1);
					
					if (allBricks.get(i).getHits() == 4){allBricks.get(i).setColor(Color.BLACK);}
					if (allBricks.get(i).getHits() == 3){allBricks.get(i).setColor(Color.BLUE);}
					if (allBricks.get(i).getHits() == 2){allBricks.get(i).setColor(Color.GREEN);} // update the color
					if (allBricks.get(i).getHits() == 1){allBricks.get(i).setColor(Color.YELLOW);} // for certain hit count
					
					if (allBricks.get(i).getHits() <= 0){ // remove a brick if its hit counter is 0
						hideBrick(allBricks.get(i), game.ball);
						allBricks.remove(i);
					}
					//System.out.println("Score is: " + Score);
					
					
					
					
				}
			if (allBricks.isEmpty()){
				game.gameWon();
			}
			}
			
			//System.out.println(game.ball.getBounds().;
			Thread.sleep(10);
		}
		
	
	}
	
	// This method checks for collisions with the sides of bricks and changes the course of the ball accordingly 
	public static void checkSideHits(Brick tempBrick, Ball tempBall){
		if (((tempBall.getBounds().getX()) >= (tempBrick.getBounds().getX() + tempBrick.getBounds().getWidth() - 1)) ){
			tempBall.setXa(tempBall.getXa() * (-1));
			tempBall.setYa(tempBall.getYa() * (-1));
		}else if (((tempBall.getBounds().getX() + tempBall.DIAMETER) <= (tempBrick.getBounds().getX() + 1))){
			tempBall.setXa(tempBall.getXa() * (-1));
			tempBall.setYa(tempBall.getYa() * (-1));
		}
	}
	
	public static int randInt(int min, int max) {
	    int randomNum = randNum.nextInt((10 - 1) + 1) + 1;
	    return randomNum;
	}
	
	public Powerup getPowerup(Brick currentBrick){
		int tempRandNum = randInt(1,10); // random number has to be 2 or 7 to get a powerup
		
		//System.out.println("Random num " + tempRandNum + " Ball Size Before: " + tempBallSize);
		if (tempRandNum == 7 || tempRandNum == 2){
			tempBallSize = tempBallSize + 1;
			Powerup powerup = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Extra Life");
			
			//System.out.println("Ball Increase - New Level " + tempBallSize);
			return powerup;
		}
		tempBallSize = tempBallSize + 1;
		Powerup powerup = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Extra Life");
		
		//System.out.println("Ball Decrease - New Level " + tempBallSize);
		return powerup;
		
	}
	
	public static void hideBrick(Brick newbrick, Ball saveBall){
		//newbrick.setColor(Color.BLUE);
		//brick = null;
		
		newbrick.getBounds().setBounds(-10, -10, 0, 0);
		newbrick.setAlive(false);
		newbrick = null;
		int saveXa = saveBall.getXa();
		//saveBall.setXa(saveXa * (-1));
		int saveYa = saveBall.getYa();
		saveBall.setYa(saveYa * (-1));
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
