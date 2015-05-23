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
import java.util.TimerTask;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel implements MouseListener {

	Ball ball = new Ball(this, 20, 320);
	double tempBallSize = 0;
	Boolean started = false;
	int Lives = 3;
	static int Score = 000000;
	Boolean hold = false;
	static ArrayList<Powerup> placeHolder = new ArrayList<Powerup>();
	static int pointMultiplier = 1;
	static Boolean hasFireball = false;
	static Boolean hasMetalPower = false;
	static ArrayList<Ball> activeBalls = new ArrayList<Ball>();
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
		activeBalls.add(ball);
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				started = true;
				//hold = false;
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
		if (started == true){
			for (int i =0; i<activeBalls.size(); i++){
				activeBalls.get(i).move();
			}
			//hold = false;
		}else{
			for (int i =0; i<activeBalls.size(); i++){
				activeBalls.get(i).setX((int) racquet.getBounds().getX() + 20);
				activeBalls.get(i).setY((int) racquet.getBounds().getY() - 10);
			}
		}
		racquet.move();
	}

	public boolean isGameOver(){
		if (Lives <= 0){
			return true;
		}else{
			return false;
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (activeBalls.isEmpty() == false){
			for (int i=0; i<activeBalls.size();i++){
				activeBalls.get(i).paint(g2d);
			}
		}
		racquet.paint(g2d);
		if (allBricks.isEmpty() == false){
			for (int i=0; i<allBricks.size();i++){
				allBricks.get(i).paint(g2d);
			}
		}
		if (placeHolder.isEmpty() == false){
			for (int i=0; i<placeHolder.size();i++){
				placeHolder.get(i).paint(g2d);
			}
		}	
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
		StartMenu menu = new StartMenu(frame, true);
		menu.setLocationRelativeTo(game);
		menu.setVisible(true);
                Instructions instructions = new Instructions(frame,true);
                instructions.setLocationRelativeTo(game);
                if (menu.getInstructions()==true){
                    instructions.setVisible(true);
                }
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

		frame.add(game);
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(game);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.move();
			game.repaint();
			if (placeHolder.isEmpty() == false){ // placeholder is powerups on screen
				for (int j=0; j<placeHolder.size();j++){
					placeHolder.get(j).move();
				}
			}
			for (int j = 0; j<activeBalls.size(); j++){
				for(int i = 0; i<allBricks.size(); i++){ 
				
					if (activeBalls.get(j).getBounds().intersects(allBricks.get(i).getBounds())){
						Score += (100 * pointMultiplier);
						checkSideHits(allBricks.get(i), activeBalls.get(j));
						if (hasFireball == true){
							allBricks.get(i).subtractAllHits();
						}else if(hasMetalPower == true){
							allBricks.get(i).subtractTwoHits(); // metal ball subtracts two hits
						}else{
							allBricks.get(i).subtractHit(); // this is where im subtracting a hit for every hit with the ball
						}
					    
					    boolean havePowerup = game.getPowerup();
					    if (havePowerup == true){
					    	Powerup savePower = game.generatePowerup(allBricks.get(i));
					    	placeHolder.add(savePower);
					    }
					    activeBalls.get(j).ya = activeBalls.get(j).ya * (-1); //update coordinates of ball to avoid multiple hits at the same time
						//game.ball.xa = game.ball.xa * (-1);
					    
					    
						
						if (allBricks.get(i).getHits() == 4){allBricks.get(i).setColor(Color.BLACK);}
						if (allBricks.get(i).getHits() == 3){allBricks.get(i).setColor(Color.BLUE);}
						if (allBricks.get(i).getHits() == 2){allBricks.get(i).setColor(Color.GREEN);} // update the color
						if (allBricks.get(i).getHits() == 1){allBricks.get(i).setColor(Color.YELLOW);} // for certain hit count
						
						if (allBricks.get(i).getHits() <= 0){ // remove a brick if its hit counter is 0
							hideBrick(allBricks.get(i), activeBalls.get(j));
							allBricks.remove(i);
						}
						//System.out.println("Score is: " + Score);
					}
					if (allBricks.isEmpty()){
						game.gameWon();
					}
				}
				
		}
			Thread.sleep(10);
		}
	}
	
	// This method checks for collisions with the sides of bricks and changes the course of the ball accordingly 
	public static void checkSideHits(Brick tempBrick, Ball tempBall){
		if (((tempBall.getBounds().getX()) >= (tempBrick.getBounds().getX() + tempBrick.getBounds().getWidth() - 1)) ){
			tempBall.setXa((tempBall.getXa() * (-1)));
			tempBall.setYa((tempBall.getYa() * (-1)));
		}else if (((tempBall.getBounds().getX() + tempBall.DIAMETER) <= (tempBrick.getBounds().getX() + 1))){
			tempBall.setXa((tempBall.getXa() * (-1)));
			tempBall.setYa((tempBall.getYa() * (-1)));
		}
	}
	
	public static int randInt(int min, int max) {
	    int randomNum = randNum.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	//test
	public boolean getPowerup(){
		int tempRandNum = randInt(1,10); // random number has to be 2 or 7 to get a powerup
		if (tempRandNum == 7 || tempRandNum == 2){
			return true;
		}
		return false;	
	}
	
	public Powerup generatePowerup(Brick currentBrick){
		int tempRandNum2 = randInt(1,12); 
		//int tempRandNum2 = 11; // Set this to a specific number to test one powerup
		switch(tempRandNum2){
			case 12:
				System.out.println("Powerup Gained: " + "Extra Life");
				Powerup powerup12 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Extra Life");
				return powerup12;
			case 11:
				System.out.println("Powerup Gained: " + "Metal Ball");
				Powerup powerup11 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Metal Ball");
				return powerup11; 
			case 10:
				System.out.println("Powerup Gained: " + "Fireball");
				Powerup powerup10 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Fireball");
				return powerup10; 
			case 9:
				System.out.println("Powerup Gained: " + "Double Points");
				Powerup powerup9 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Double Points");
				return powerup9; 
			case 8:
				System.out.println("Powerup Gained: " + "Ball Decrease");
				Powerup powerup8 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Ball Decrease");
				return powerup8; 
			case 7:
				System.out.println("Powerup Gained: " + "Multiple Balls");
				Powerup powerup7 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Multiple Balls");
				return powerup7; 
			case 6:
				System.out.println("Powerup Gained: " + "Paddle Decrease");
				Powerup powerup6 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Paddle Decrease");
				return powerup6; 
			case 5:
				System.out.println("Powerup Gained: " + "Slow Down");
				Powerup powerup5 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Slow Down");
				return powerup5; 
			case 4:
				System.out.println("Powerup Gained: " + "Speed Up");
				Powerup powerup4 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Speed Up");
				return powerup4; 
			case 3:
				System.out.println("Powerup Gained: " + "Paddle Increase");
				Powerup powerup3 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Paddle Increase");
				return powerup3; 
			case 2:
				System.out.println("Powerup Gained: " + "Ball Increase");
				Powerup powerup2 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Ball Increase");
				return powerup2; 
			case 1:
				System.out.println("Powerup Gained: " + "Freeze");
				Powerup powerup1 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Freeze");
				return powerup1; 
			
	}
		return null;
	}
	
	public static void hideBrick(Brick newbrick, Ball saveBall){
		newbrick.getBounds().setBounds(-10, -10, 0, 0);
		newbrick.setAlive(false);
		double saveXa = saveBall.getXa();
		//saveBall.setXa(saveXa * (-1));
		double saveYa = saveBall.getYa();
		saveBall.setYa(saveYa * (-1));
		newbrick = null;
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub	
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

}
