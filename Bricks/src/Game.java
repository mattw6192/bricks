import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Ball ball = new Ball(this, 20, 320);
	double tempBallSize = 0;
	static Boolean started = false;
	int Lives = 3;
	static ArrayList<Missile> missiles = new ArrayList<Missile>();
	static String lifeString = "***";

	static int Score = 000000;
	Boolean hold = false;
	static ArrayList<Powerup> placeHolder = new ArrayList<Powerup>();
	static int pointMultiplier = 1;
	static Boolean hasFireball = false;
	static Boolean hasMetalPower = false;
	static ArrayList<Ball> activeBalls = new ArrayList<Ball>();
	static Random randNum = new Random();
	Boolean powerupsEnabled = true;
	static int Round = 1;
	
	
	

    static ArrayList<Brick> allBricks = new ArrayList<Brick>();
	private static int maxRound = 4;
	Racquet racquet = new Racquet(this);

	public Game() { //comment
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
		addKeyListener(new KeyListener(){
			//public void mouseClicked(MouseEvent arg0) { //old code for mouseListener, if we want to go back
				//started = true;
				//hold = false;
			//}

			@Override
			public void keyPressed(KeyEvent arg0) {
				 if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
					 started = true;
					 if (Game.missiles.size()>0){
						 Game.fireMissile();
						 Game.missiles.remove(0);
					 }
				 }
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}

			
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
				activeBalls.get(i).setX((int) racquet.getBounds().getX() + (int) (racquet.getBounds().getWidth() / 2));
				activeBalls.get(i).setY((int) racquet.getBounds().getY() - (int) (activeBalls.get(i).DIAMETER));
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
	
	public static String getLifeString() {
		return lifeString;
	}

	public static void setLifeString(String lifeString) {
		Game.lifeString = lifeString;
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
		if (missiles.isEmpty()==false){
			for (Missile m : missiles){
				m.paint(g2d);
			}
		}
		
	}
	
	public void gameOver() {
		JOptionPane.showMessageDialog(this, "What have I done wrong?", "Oh no...", JOptionPane.ERROR_MESSAGE,new javax.swing.ImageIcon(getClass().getResource("/images/bill gates.jpg")));
		Round = 1;
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
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 5, 35, 15, randInt(3,4));
        	allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 25, 35, 15, randInt(3,4));
        	allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 45, 35, 15, randInt(1,3));
        	allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 65, 35, 15, randInt(1,3));
        	allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 85, 35, 15, randInt(1,4));
        	allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 105, 35, 15, randInt(2,4));
        	allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 125, 35, 15, randInt(2,4));
        	allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 145, 35, 15, randInt(2,4));
        	allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 5, 35, 15, randInt(1,4));
        	allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 5, 35, 15, randInt(1,4));
        	allBricks.add(brick);
        }
        
		
		
		//
		for(int i = 0; i<allBricks.size(); i++){ //update the color for certain hit count
			if (allBricks.get(i).getHits() == 4){allBricks.get(i).setColor(Color.BLACK);}
			if (allBricks.get(i).getHits() == 3){allBricks.get(i).setColor(Color.BLUE);}
			if (allBricks.get(i).getHits() == 2){allBricks.get(i).setColor(Color.GREEN);}
			if (allBricks.get(i).getHits() == 1){allBricks.get(i).setColor(Color.YELLOW);} 
		}
		frame.add(game);
		frame.setSize(855, 600);
		frame.setLocation(300, 50);
		//frame.setLocationRelativeTo(game);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) { //game loop
			frame.setTitle("Lives: " + Game.getLifeString() + "   Score: " + Score );
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
						final Brick saveBrickForAction = allBricks.get(i);
						Score += (10 * pointMultiplier);
						//sactiveBalls.get(j).xa = activeBalls.get(j).xa * (-1);
						
						if (hasFireball == false){
							if (checkSideHits(allBricks.get(i), activeBalls.get(j)) == false){
								activeBalls.get(j).ya = activeBalls.get(j).ya * (-1); //update coordinates of ball to avoid multiple hits at the same time
								checkNormalHits(allBricks.get(i), activeBalls.get(j));
								//activeBalls.get(j).xa = activeBalls.get(j).xa * (-1); 
							//}else{
								//activeBalls.get(j).ya = activeBalls.get(j).ya * (-1); 
								//activeBalls.get(j).xa = activeBalls.get(j).xa * (-1);
							}}
							
						if (hasFireball == true && allBricks.get(i).canBeHit == true){
							allBricks.get(i).subtractAllHits();
						}else if(hasMetalPower == true && allBricks.get(i).canBeHit == true){
							allBricks.get(i).subtractTwoHits(); // metal ball subtracts two hits
						}else{
							if (allBricks.get(i).canBeHit == true){
								allBricks.get(i).subtractHit(); // this is where im subtracting a hit for every hit with the ball
							}
						}
					    
					    boolean havePowerup = game.getPowerup();
					    if (havePowerup == true){
					    	Powerup savePower = game.generatePowerup(allBricks.get(i));
					    	placeHolder.add(savePower);
					    }
					    //activeBalls.get(j).ya = activeBalls.get(j).ya * (-1); //update coordinates of ball to avoid multiple hits at the same time
						//game.ball.xa = game.ball.xa * (-1);
					    
					    allBricks.get(i).setCanBeHit(false);
						int delay = 500; //milliseconds
						ActionListener taskPerformer = new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								saveBrickForAction.setCanBeHit(true);
						        //System.out.println("Brick can now be hit again");
						    }
						};
						Timer timer = new Timer(delay, taskPerformer);
						timer.setRepeats(false);
						timer.start();
						
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
						
						if (Round < maxRound){
							Round += 1;
							started = false;
							placeHolder.clear();
							nextRound();
							hasFireball = false;
							hasMetalPower = false;
							Ball saveBall = activeBalls.get(0);
							activeBalls.clear();
							activeBalls.add(saveBall);
							saveBall.speed = 2;
							saveBall.ballMods = 0;
							saveBall.DIAMETER = 10;
							game.racquet.WIDTH = 60;
						}
						if (Round == maxRound ){
							game.gameWon();
						}
						
					}
				}
				
		}
			Thread.sleep(10);
		}
	}
	
	// This method checks for collisions with the sides of bricks and changes the course of the ball accordingly 
	public static boolean checkSideHits(Brick tempBrick, Ball tempBall){
		if (((tempBall.getBounds().getX()) >= (tempBrick.getBounds().getX() + tempBrick.getBounds().getWidth() - 2)) ){ // right side
			tempBall.setXa((tempBall.getXa() * (-1)));
			//tempBall.setYa((tempBall.getYa() * (-1)));
			tempBall.setX((int) (tempBrick.x + tempBrick.getBounds().getWidth()));
			return true;
		}else if (((tempBall.getBounds().getX() + Ball.DIAMETER) <= (tempBrick.getBounds().getX() + 2))){ // left side
			tempBall.setXa((tempBall.getXa() * (-1)));
			//tempBall.setYa((tempBall.getYa() * (-1)));
			tempBall.setX((int) (tempBrick.x - tempBall.DIAMETER));
			return true;
		}
		return false;
	}
	
	public static void checkNormalHits(Brick tempBrick, Ball tempBall){
		if(tempBall.getBounds().getY() <= (tempBrick.getBounds().getY() + 2) ){ // top
			tempBall.setY((int) (tempBrick.getBounds().getY() - tempBall.DIAMETER));
		}else if(tempBall.getBounds().getY() >= (tempBrick.getBounds().getY() + tempBrick.getBounds().getHeight() - 2)){ //bottom
			tempBall.setY((int) (tempBrick.getBounds().getY() + tempBrick.getBounds().getHeight()));
		}
	}
	
	public static int randInt(int min, int max) {
	    int randomNum = randNum.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public boolean getPowerup(){
		int tempRandNum = randInt(1,10); // random number has to be 2 or 7 to get a powerup
		if (tempRandNum == 7 || tempRandNum == 2){
			int delay = 1000; //milliseconds
			
			ActionListener taskPerformer = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					powerupsEnabled = true;
			    }
			};
			
			if (powerupsEnabled == true){
				powerupsEnabled = false;
				Timer timer = new Timer(delay, taskPerformer);
				timer.setRepeats(false);
				timer.start();
				return true;
			}else{
				return false;
			}
			
		}
		return false;	
	}
	
	public static void nextRound(){
		allBricks.clear();
		if (Round == 2){
		
			//fifteenth row of bricks from left to right
			 Brick round2_brick_15a = new Brick(130, 285, 35, 15, 4);
			 Brick round2_brick_15b = new Brick(170, 285, 35, 15, 4);
			 Brick round2_brick_15c = new Brick(210, 285, 35, 15, 4);
			 Brick round2_brick_15d = new Brick(250, 285, 35, 15, 4);
			 Brick round2_brick_15e = new Brick(290, 285, 35, 15, 4);
			 Brick round2_brick_15f = new Brick(330, 285, 35, 15, 4);
			 Brick round2_brick_15g = new Brick(370, 285, 35, 15, 4);
			 Brick round2_brick_15h = new Brick(410, 285, 35, 15, 4);
			 Brick round2_brick_15i = new Brick(450, 285, 35, 15, 4);
			 Brick round2_brick_15j = new Brick(490, 285, 35, 15, 4);
			 Brick round2_brick_15k = new Brick(530, 285, 35, 15, 4);
			 Brick round2_brick_15l = new Brick(570, 285, 35, 15, 4);
			 Brick round2_brick_15m = new Brick(610, 285, 35, 15, 4);
			 Brick round2_brick_15n = new Brick(650, 285, 35, 15, 4);
			 Brick round2_brick_15o = new Brick(690, 285, 35, 15, 4);
			
			//fourteenth row of bricks from left to right
			 Brick round2_brick_14a = new Brick(150, 265, 35, 15, 4);
			 Brick round2_brick_14b = new Brick(190, 265, 35, 15, 4);
			 Brick round2_brick_14c = new Brick(230, 265, 35, 15, 3);
			 Brick round2_brick_14d = new Brick(270, 265, 35, 15, 3);
			 Brick round2_brick_14e = new Brick(310, 265, 35, 15, 3);
			 Brick round2_brick_14f = new Brick(350, 265, 35, 15, 3);
			 Brick round2_brick_14g = new Brick(390, 265, 35, 15, 3);
			 Brick round2_brick_14h = new Brick(430, 265, 35, 15, 3);
			 Brick round2_brick_14i = new Brick(470, 265, 35, 15, 3);
			 Brick round2_brick_14j = new Brick(510, 265, 35, 15, 3);
			 Brick round2_brick_14k = new Brick(550, 265, 35, 15, 3);
			 Brick round2_brick_14l = new Brick(590, 265, 35, 15, 3);
			 Brick round2_brick_14m = new Brick(630, 265, 35, 15, 4);
			 Brick round2_brick_14n = new Brick(670, 265, 35, 15, 4);
			
			//thirteenth row of bricks from left to right
			 Brick round2_brick_13a = new Brick(170, 245, 35, 15, 4);
			 Brick round2_brick_13b = new Brick(210, 245, 35, 15, 4);
			 Brick round2_brick_13c = new Brick(250, 245, 35, 15, 3);
			 Brick round2_brick_13d = new Brick(290, 245, 35, 15, 3);
			 Brick round2_brick_13e = new Brick(330, 245, 35, 15, 2);
			 Brick round2_brick_13f = new Brick(370, 245, 35, 15, 2);
			 Brick round2_brick_13g = new Brick(410, 245, 35, 15, 1);
			 Brick round2_brick_13h = new Brick(450, 245, 35, 15, 2);
			 Brick round2_brick_13i = new Brick(490, 245, 35, 15, 2);
			 Brick round2_brick_13j = new Brick(530, 245, 35, 15, 3);
			 Brick round2_brick_13k = new Brick(570, 245, 35, 15, 3);
			 Brick round2_brick_13l = new Brick(610, 245, 35, 15, 4);
			 Brick round2_brick_13m = new Brick(650, 245, 35, 15, 4);
			
			//Twelfth row of bricks from left to right
			 Brick round2_brick_12a = new Brick(190, 225, 35, 15, 4);
			 Brick round2_brick_12b = new Brick(230, 225, 35, 15, 4);
			 Brick round2_brick_12c = new Brick(270, 225, 35, 15, 3);
			 Brick round2_brick_12d = new Brick(310, 225, 35, 15, 3);
			 Brick round2_brick_12e = new Brick(350, 225, 35, 15, 2);
			 Brick round2_brick_12f = new Brick(390, 225, 35, 15, 2);
			 Brick round2_brick_12g = new Brick(430, 225, 35, 15, 2);
			 Brick round2_brick_12h = new Brick(470, 225, 35, 15, 2);
			 Brick round2_brick_12i = new Brick(510, 225, 35, 15, 3);
			 Brick round2_brick_12j = new Brick(550, 225, 35, 15, 3);
			 Brick round2_brick_12k = new Brick(590, 225, 35, 15, 4);
			 Brick round2_brick_12l = new Brick(630, 225, 35, 15, 4);
			
			//eleventh row of bricks from left to right
			 Brick round2_brick_11a = new Brick(210, 205, 35, 15, 4);
			 Brick round2_brick_11b = new Brick(250, 205, 35, 15, 4);
			 Brick round2_brick_11c = new Brick(290, 205, 35, 15, 3);
			 Brick round2_brick_11d = new Brick(330, 205, 35, 15, 3);
			 Brick round2_brick_11e = new Brick(370, 205, 35, 15, 2);
			 Brick round2_brick_11f = new Brick(410, 205, 35, 15, 1);
			 Brick round2_brick_11g = new Brick(450, 205, 35, 15, 2);
			 Brick round2_brick_11h = new Brick(490, 205, 35, 15, 3);
			 Brick round2_brick_11i = new Brick(530, 205, 35, 15, 3);
			 Brick round2_brick_11j = new Brick(570, 205, 35, 15, 4);
			 Brick round2_brick_11k = new Brick(610, 205, 35, 15, 4);
			
			//tenth row of bricks from left to right
			 Brick round2_brick_10a = new Brick(230, 185, 35, 15, 4);
			 Brick round2_brick_10b = new Brick(270, 185, 35, 15, 4);
			 Brick round2_brick_10c = new Brick(310, 185, 35, 15, 3);
			 Brick round2_brick_10d = new Brick(350, 185, 35, 15, 3);
			 Brick round2_brick_10e = new Brick(390, 185, 35, 15, 2);
			 Brick round2_brick_10f = new Brick(430, 185, 35, 15, 2);
			 Brick round2_brick_10g = new Brick(470, 185, 35, 15, 3);
			 Brick round2_brick_10h = new Brick(510, 185, 35, 15, 3);
			 Brick round2_brick_10i = new Brick(550, 185, 35, 15, 4);
			 Brick round2_brick_10j = new Brick(590, 185, 35, 15, 4);
			 
			//ninth row of bricks from left to right
			 Brick round2_brick_9a = new Brick(250, 165, 35, 15, 4);
			 Brick round2_brick_9b = new Brick(290, 165, 35, 15, 3);
			 Brick round2_brick_9c = new Brick(330, 165, 35, 15, 3);
			 Brick round2_brick_9d = new Brick(370, 165, 35, 15, 2);
			 Brick round2_brick_9e = new Brick(410, 165, 35, 15, 1);
			 Brick round2_brick_9f = new Brick(450, 165, 35, 15, 2);
			 Brick round2_brick_9g = new Brick(490, 165, 35, 15, 3);
			 Brick round2_brick_9h = new Brick(530, 165, 35, 15, 3);
			 Brick round2_brick_9i = new Brick(570, 165, 35, 15, 4);
			
			//eighth row of bricks from left to right
			 Brick round2_brick_8a = new Brick(270, 145, 35, 15, 4);
			 Brick round2_brick_8b = new Brick(310, 145, 35, 15, 3);
			 Brick round2_brick_8c = new Brick(350, 145, 35, 15, 3);
			 Brick round2_brick_8d = new Brick(390, 145, 35, 15, 2);
			 Brick round2_brick_8e = new Brick(430, 145, 35, 15, 2);
			 Brick round2_brick_8f = new Brick(470, 145, 35, 15, 3);
			 Brick round2_brick_8g = new Brick(510, 145, 35, 15, 3);
			 Brick round2_brick_8h = new Brick(550, 145, 35, 15, 4);
			
			//seventh row of bricks from left to right
			 Brick round2_brick_7a = new Brick(290, 125, 35, 15, 4);
			 Brick round2_brick_7b = new Brick(330, 125, 35, 15, 3);
			 Brick round2_brick_7c = new Brick(370, 125, 35, 15, 2);
			 Brick round2_brick_7d = new Brick(410, 125, 35, 15, 1);
			 Brick round2_brick_7e = new Brick(450, 125, 35, 15, 2);
			 Brick round2_brick_7f = new Brick(490, 125, 35, 15, 3);
			 Brick round2_brick_7g = new Brick(530, 125, 35, 15, 4);
			
			// sixth row of bricks from left to right
			 Brick round2_brick_6a = new Brick(308, 105, 35, 15, 4);
			 Brick round2_brick_6b = new Brick(348, 105, 35, 15, 3);
			 Brick round2_brick_6c = new Brick(388, 105, 35, 15, 2);
			 Brick round2_brick_6d = new Brick(428, 105, 35, 15, 2);
			 Brick round2_brick_6e = new Brick(468, 105, 35, 15, 3);
			 Brick round2_brick_6f = new Brick(508, 105, 35, 15, 4);
			
			// fifth row of bricks from left to right
			 Brick round2_brick_5a = new Brick(330, 85, 35, 15, 4);
			 Brick round2_brick_5b = new Brick(370, 85, 35, 15, 3);
			 Brick round2_brick_5c = new Brick(410, 85, 35, 15, 1);
			 Brick round2_brick_5d = new Brick(450, 85, 35, 15, 3);
			 Brick round2_brick_5e = new Brick(490, 85, 35, 15, 4);
			
			// fourth row of bricks from left to right
			 Brick round2_brick_4a = new Brick(350, 65, 35, 15, 4);
			 Brick round2_brick_4b = new Brick(390, 65, 35, 15, 3);
			 Brick round2_brick_4c = new Brick(430, 65, 35, 15, 3);
			 Brick round2_brick_4d = new Brick(470, 65, 35, 15, 4);
			
			// third row of bricks from left to right
			 Brick round2_brick_3a = new Brick(370, 45, 35, 15, 4);
			 Brick round2_brick_3b = new Brick(410, 45, 35, 15, 3);
			 Brick round2_brick_3c = new Brick(450, 45, 35, 15, 4);
			
			// second row of bricks from left to right
			 Brick round2_brick_2a = new Brick(388, 25, 35, 15, 4);
			 Brick round2_brick_2b = new Brick(428, 25, 35, 15, 4);
			
			// top row of bricks 
			 Brick round2_brick_1a = new Brick(410, 5, 35, 15, 4);
			 
			 
			 allBricks.add(round2_brick_1a);
			 allBricks.add(round2_brick_2a);
			 allBricks.add(round2_brick_2b);
			 allBricks.add(round2_brick_3a);
			 allBricks.add(round2_brick_3b);
			 allBricks.add(round2_brick_3c);
			 allBricks.add(round2_brick_4a);
			 allBricks.add(round2_brick_4b);
			 allBricks.add(round2_brick_4c);
			 allBricks.add(round2_brick_4d);
			 allBricks.add(round2_brick_5a);
			 allBricks.add(round2_brick_5b);
			 allBricks.add(round2_brick_5c);
			 allBricks.add(round2_brick_5d);
			 allBricks.add(round2_brick_5e);
			 allBricks.add(round2_brick_6a);
			 allBricks.add(round2_brick_6b);
			 allBricks.add(round2_brick_6c);
			 allBricks.add(round2_brick_6d);
			 allBricks.add(round2_brick_6e);
			 allBricks.add(round2_brick_6f);
			 allBricks.add(round2_brick_7a);
			 allBricks.add(round2_brick_7b);
			 allBricks.add(round2_brick_7c);
			 allBricks.add(round2_brick_7d);
			 allBricks.add(round2_brick_7e);
			 allBricks.add(round2_brick_7f);
			 allBricks.add(round2_brick_7g);
			 allBricks.add(round2_brick_8a);
			 allBricks.add(round2_brick_8b);
			 allBricks.add(round2_brick_8c);
			 allBricks.add(round2_brick_8d);
			 allBricks.add(round2_brick_8e);
			 allBricks.add(round2_brick_8f);
			 allBricks.add(round2_brick_8g);
			 allBricks.add(round2_brick_8h);
			 allBricks.add(round2_brick_9a);
			 allBricks.add(round2_brick_9b);
			 allBricks.add(round2_brick_9c);
			 allBricks.add(round2_brick_9d);
			 allBricks.add(round2_brick_9e);
			 allBricks.add(round2_brick_9f);
			 allBricks.add(round2_brick_9g);
			 allBricks.add(round2_brick_9h);
			 allBricks.add(round2_brick_9i);
			 allBricks.add(round2_brick_10a);
			 allBricks.add(round2_brick_10b);
			 allBricks.add(round2_brick_10c);
			 allBricks.add(round2_brick_10d);
			 allBricks.add(round2_brick_10e);
			 allBricks.add(round2_brick_10f);
			 allBricks.add(round2_brick_10g);
			 allBricks.add(round2_brick_10h);
			 allBricks.add(round2_brick_10i);
			 allBricks.add(round2_brick_10j);
			 allBricks.add(round2_brick_11a);
			 allBricks.add(round2_brick_11b);
			 allBricks.add(round2_brick_11c);
			 allBricks.add(round2_brick_11d);
			 allBricks.add(round2_brick_11e);
			 allBricks.add(round2_brick_11f);
			 allBricks.add(round2_brick_11g);
			 allBricks.add(round2_brick_11h);
			 allBricks.add(round2_brick_11i);
			 allBricks.add(round2_brick_11j);
			 allBricks.add(round2_brick_11k);
			 allBricks.add(round2_brick_12a);
			 allBricks.add(round2_brick_12b);
			 allBricks.add(round2_brick_12c);
			 allBricks.add(round2_brick_12d);
			 allBricks.add(round2_brick_12e);
			 allBricks.add(round2_brick_12f);
			 allBricks.add(round2_brick_12g);
			 allBricks.add(round2_brick_12h);
			 allBricks.add(round2_brick_12i);
			 allBricks.add(round2_brick_12j);
			 allBricks.add(round2_brick_12k);
			 allBricks.add(round2_brick_12l);
			 allBricks.add(round2_brick_13a);
			 allBricks.add(round2_brick_13b);
			 allBricks.add(round2_brick_13c);
			 allBricks.add(round2_brick_13d);
			 allBricks.add(round2_brick_13e);
			 allBricks.add(round2_brick_13f);
			 allBricks.add(round2_brick_13g);
			 allBricks.add(round2_brick_13h);
			 allBricks.add(round2_brick_13i);
			 allBricks.add(round2_brick_13j);
			 allBricks.add(round2_brick_13k);
			 allBricks.add(round2_brick_13l);
			 allBricks.add(round2_brick_13m);
			 allBricks.add(round2_brick_14a);
			 allBricks.add(round2_brick_14b);
			 allBricks.add(round2_brick_14c);
			 allBricks.add(round2_brick_14d);
			 allBricks.add(round2_brick_14e);
			 allBricks.add(round2_brick_14f);
			 allBricks.add(round2_brick_14g);
			 allBricks.add(round2_brick_14h);
			 allBricks.add(round2_brick_14i);
			 allBricks.add(round2_brick_14j);
			 allBricks.add(round2_brick_14k);
			 allBricks.add(round2_brick_14l);
			 allBricks.add(round2_brick_14m);
			 allBricks.add(round2_brick_14n);
			 allBricks.add(round2_brick_15a);
			 allBricks.add(round2_brick_15b);
			 allBricks.add(round2_brick_15c);
			 allBricks.add(round2_brick_15d);
			 allBricks.add(round2_brick_15e);
			 allBricks.add(round2_brick_15f);
			 allBricks.add(round2_brick_15g);
			 allBricks.add(round2_brick_15h);
			 allBricks.add(round2_brick_15i);
			 allBricks.add(round2_brick_15j);
			 allBricks.add(round2_brick_15k);
			 allBricks.add(round2_brick_15l);
			 allBricks.add(round2_brick_15m);
			 allBricks.add(round2_brick_15n);
			 allBricks.add(round2_brick_15o);
		}else if (Round == 3){
			// final row of bricks 
			 Brick brick1a = new Brick(130, 5, 35, 15, 4);
			
			// sixth row of bricks from left to right
			 Brick brick2a = new Brick(108, 25, 35, 15, 1);
			 Brick brick2b = new Brick(148, 25, 35, 15, 1);
			
			// fifth row of bricks from left to right
			 Brick brick3a = new Brick(90, 45, 35, 15, 2);
			 Brick brick3b = new Brick(130, 45, 35, 15, 2);
			 Brick brick3c = new Brick(170, 45, 35, 15, 2);
			
			// fourth row of bricks from left to right
			 Brick brick4a = new Brick(70, 65, 35, 15, 2);
			 Brick brick4b = new Brick(110, 65, 35, 15, 2);
			 Brick brick4c = new Brick(150, 65, 35, 15, 2);
			 Brick brick4d = new Brick(190, 65, 35, 15, 2);
			
			// row of bricks from left to right
			 Brick brick5a = new Brick(50, 85, 35, 15, 3);
			 Brick brick5b = new Brick(90, 85, 35, 15, 3);
			 Brick brick5c = new Brick(130, 85, 35, 15, 3);
			 Brick brick5d = new Brick(170, 85, 35, 15, 3);
			 Brick brick5e = new Brick(210, 85, 35, 15, 3);
			
			//  row of bricks from left to right
			 Brick brick6a = new Brick(28, 105, 35, 15, 4);
			 Brick brick6b = new Brick(68, 105, 35, 15, 4);
			 Brick brick6c = new Brick(108, 105, 35, 15, 4);
			 Brick brick6d = new Brick(148, 105, 35, 15, 4);
			 Brick brick6e = new Brick(188, 105, 35, 15, 4);
			 Brick brick6f = new Brick(228, 105, 35, 15, 4);
			
			//center row of bricks from left to right
			 Brick brick7a = new Brick(10, 125, 35, 15, 4);
			 Brick brick7b = new Brick(50, 125, 35, 15, 4);
			 Brick brick7c = new Brick(90, 125, 35, 15, 4);
			 Brick brick7d = new Brick(130, 125, 35, 15, 4);
			 Brick brick7e = new Brick(170, 125, 35, 15, 4);
			 Brick brick7f = new Brick(210, 125, 35, 15, 4);
			 Brick brick7g = new Brick(250, 125, 35, 15, 4);
			
			// second row of bricks from left to right
			 Brick brick8a = new Brick(28, 145, 35, 15, 4);
			 Brick brick8b = new Brick(68, 145, 35, 15, 4);
			 Brick brick8c = new Brick(108, 145, 35, 15, 4);
			 Brick brick8d = new Brick(148, 145, 35, 15, 4);
			 Brick brick8e = new Brick(188, 145, 35, 15, 4);
			 Brick brick8f = new Brick(228, 145, 35, 15, 4);
			
			// third row of bricks from left to right
			 Brick brick9a = new Brick(50, 165, 35, 15, 3);
			 Brick brick9b = new Brick(90, 165, 35, 15, 3);
			 Brick brick9c = new Brick(130, 165, 35, 15, 3);
			 Brick brick9d = new Brick(170, 165, 35, 15, 3);
			 Brick brick9e = new Brick(210, 165, 35, 15, 3);
			
			// fourth row of bricks from left to right
			 Brick brick10a = new Brick(70, 185, 35, 15, 2);
			 Brick brick10b = new Brick(110, 185, 35, 15, 2);
			 Brick brick10c = new Brick(150, 185, 35, 15, 2);
			 Brick brick10d = new Brick(190, 185, 35, 15, 2);
			
			// fifth row of bricks from left to right
			 Brick brick11a = new Brick(90, 205, 35, 15, 2);
			 Brick brick11b = new Brick(130, 205, 35, 15, 2);
			 Brick brick11c = new Brick(170, 205, 35, 15, 2);
			
			// sixth row of bricks from left to right
			 Brick brick12a = new Brick(108, 225, 35, 15, 1);
			 Brick brick12b = new Brick(148, 225, 35, 15, 1);
			
			// final row of bricks 
			 Brick brick13a = new Brick(130, 245, 35, 15, 4);
			
			//////////////// Second Pyramid //////////////////////
			
			// second row of bricks from left to right
			 Brick brick2_6a = new Brick(308, 105, 35, 15, 4);
			 Brick brick2_6b = new Brick(348, 105, 35, 15, 4);
			 Brick brick2_6c = new Brick(388, 105, 35, 15, 4);
			 Brick brick2_6d = new Brick(428, 105, 35, 15, 4);
			 Brick brick2_6e = new Brick(468, 105, 35, 15, 4);
			 Brick brick2_6f = new Brick(508, 105, 35, 15, 4);
			
			// third row of bricks from left to right
			 Brick brick2_5a = new Brick(330, 85, 35, 15, 3);
			 Brick brick2_5b = new Brick(370, 85, 35, 15, 3);
			 Brick brick2_5c = new Brick(410, 85, 35, 15, 3);
			 Brick brick2_5d = new Brick(450, 85, 35, 15, 3);
			 Brick brick2_5e = new Brick(490, 85, 35, 15, 3);
			
			// fourth row of bricks from left to right
			 Brick brick2_4a = new Brick(350, 65, 35, 15, 2);
			 Brick brick2_4b = new Brick(390, 65, 35, 15, 2);
			 Brick brick2_4c = new Brick(430, 65, 35, 15, 2);
			 Brick brick2_4d = new Brick(470, 65, 35, 15, 2);
			
			// fifth row of bricks from left to right
			 Brick brick2_3a = new Brick(370, 45, 35, 15, 2);
			 Brick brick2_3b = new Brick(410, 45, 35, 15, 2);
			 Brick brick2_3c = new Brick(450, 45, 35, 15, 2);
			
			// sixth row of bricks from left to right
			 Brick brick2_2a = new Brick(388, 25, 35, 15, 1);
			 Brick brick2_2b = new Brick(428, 25, 35, 15, 1);
			
			// final row of bricks 
			 Brick brick2_1a = new Brick(410, 5, 35, 15, 4);
			 
			//top row of bricks from left to right
				 Brick brick2_7a = new Brick(290, 125, 35, 15, 4);
				 Brick brick2_7b = new Brick(330, 125, 35, 15, 4);
				 Brick brick2_7c = new Brick(370, 125, 35, 15, 4);
				 Brick brick2_7d = new Brick(410, 125, 35, 15, 4);
				 Brick brick2_7e = new Brick(450, 125, 35, 15, 4);
				 Brick brick2_7f = new Brick(490, 125, 35, 15, 4);
				 Brick brick2_7g = new Brick(530, 125, 35, 15, 4);
				
				// second row of bricks from left to right
				 Brick brick2_8a = new Brick(308, 145, 35, 15, 4);
				 Brick brick2_8b = new Brick(348, 145, 35, 15, 4);
				 Brick brick2_8c = new Brick(388, 145, 35, 15, 4);
				 Brick brick2_8d = new Brick(428, 145, 35, 15, 4);
				 Brick brick2_8e = new Brick(468, 145, 35, 15, 4);
				 Brick brick2_8f = new Brick(508, 145, 35, 15, 4);
				
				// third row of bricks from left to right
				 Brick brick2_9a = new Brick(330, 165, 35, 15, 3);
				 Brick brick2_9b = new Brick(370, 165, 35, 15, 3);
				 Brick brick2_9c = new Brick(410, 165, 35, 15, 3);
				 Brick brick2_9d = new Brick(450, 165, 35, 15, 3);
				 Brick brick2_9e = new Brick(490, 165, 35, 15, 3);
				
				// fourth row of bricks from left to right
				 Brick brick2_10a = new Brick(350, 185, 35, 15, 2);
				 Brick brick2_10b = new Brick(390, 185, 35, 15, 2);
				 Brick brick2_10c = new Brick(430, 185, 35, 15, 2);
				 Brick brick2_10d = new Brick(470, 185, 35, 15, 2);
				
				// fifth row of bricks from left to right
				 Brick brick2_11a = new Brick(370, 205, 35, 15, 2);
				 Brick brick2_11b = new Brick(410, 205, 35, 15, 2);
				 Brick brick2_11c = new Brick(450, 205, 35, 15, 2);
				
				// sixth row of bricks from left to right
				 Brick brick2_12a = new Brick(388, 225, 35, 15, 1);
				 Brick brick2_12b = new Brick(428, 225, 35, 15, 1);
				
				// final row of bricks 
				 Brick brick2_13a = new Brick(410, 245, 35, 15, 4);
				
				///////////// Final / Third Pyramid ////////////////////

				// second row of bricks from left to right
				 Brick brick3_6a = new Brick(588, 105, 35, 15, 4);
				 Brick brick3_6b = new Brick(628, 105, 35, 15, 4);
				 Brick brick3_6c = new Brick(668, 105, 35, 15, 4);
				 Brick brick3_6d = new Brick(708, 105, 35, 15, 4);
				 Brick brick3_6e = new Brick(748, 105, 35, 15, 4);
				 Brick brick3_6f = new Brick(788, 105, 35, 15, 4);
				
				// third row of bricks from left to right
				 Brick brick3_5a = new Brick(610, 85, 35, 15, 3);
				 Brick brick3_5b = new Brick(650, 85, 35, 15, 3);
				 Brick brick3_5c = new Brick(690, 85, 35, 15, 3);
				 Brick brick3_5d = new Brick(730, 85, 35, 15, 3);
				 Brick brick3_5e = new Brick(770, 85, 35, 15, 3);
				
				// fourth row of bricks from left to right
				 Brick brick3_4a = new Brick(630, 65, 35, 15, 2);
				 Brick brick3_4b = new Brick(670, 65, 35, 15, 2);
				 Brick brick3_4c = new Brick(710, 65, 35, 15, 2);
				 Brick brick3_4d = new Brick(750, 65, 35, 15, 2);
				
				// fifth row of bricks from left to right
				 Brick brick3_3a = new Brick(650, 45, 35, 15, 2);
				 Brick brick3_3b = new Brick(690, 45, 35, 15, 2);
				 Brick brick3_3c = new Brick(730, 45, 35, 15, 2);
				
				// sixth row of bricks from left to right
				 Brick brick3_2a = new Brick(668, 25, 35, 15, 1);
				 Brick brick3_2b = new Brick(708, 25, 35, 15, 1);
				
				// final row of bricks 
				 Brick brick3_1a = new Brick(690, 5, 35, 15, 4);
				
				//top row of bricks from left to right
				Brick brick3_7a = new Brick(570, 125, 35, 15, 4);
				 Brick brick3_7b = new Brick(610, 125, 35, 15, 4);
				 Brick brick3_7c = new Brick(650, 125, 35, 15, 4);
				 Brick brick3_7d = new Brick(690, 125, 35, 15, 4);
				 Brick brick3_7e = new Brick(730, 125, 35, 15, 4);
				 Brick brick3_7f = new Brick(770, 125, 35, 15, 4);
				 Brick brick3_7g = new Brick(810, 125, 35, 15, 4);
				
				// second row of bricks from left to right
				 Brick brick3_8a = new Brick(588, 145, 35, 15, 4);
				 Brick brick3_8b = new Brick(628, 145, 35, 15, 4);
				 Brick brick3_8c = new Brick(668, 145, 35, 15, 4);
				 Brick brick3_8d = new Brick(708, 145, 35, 15, 4);
				 Brick brick3_8e = new Brick(748, 145, 35, 15, 4);
				 Brick brick3_8f = new Brick(788, 145, 35, 15, 4);
				
				// third row of bricks from left to right
				 Brick brick3_9a = new Brick(610, 165, 35, 15, 3);
				 Brick brick3_9b = new Brick(650, 165, 35, 15, 3);
				 Brick brick3_9c = new Brick(690, 165, 35, 15, 3);
				 Brick brick3_9d = new Brick(730, 165, 35, 15, 3);
				 Brick brick3_9e = new Brick(770, 165, 35, 15, 3);
				
				// fourth row of bricks from left to right
				 Brick brick3_10a = new Brick(630, 185, 35, 15, 2);
				 Brick brick3_10b = new Brick(670, 185, 35, 15, 2);
				 Brick brick3_10c = new Brick(710, 185, 35, 15, 2);
				 Brick brick3_10d = new Brick(750, 185, 35, 15, 2);
				
				// fifth row of bricks from left to right
				 Brick brick3_11a = new Brick(650, 205, 35, 15, 2);
				 Brick brick3_11b = new Brick(690, 205, 35, 15, 2);
				 Brick brick3_11c = new Brick(730, 205, 35, 15, 2);
				
				// sixth row of bricks from left to right
				 Brick brick3_12a = new Brick(668, 225, 35, 15, 1);
				 Brick brick3_12b = new Brick(708, 225, 35, 15, 1);
				
				// final row of bricks 
				 Brick brick3_13a = new Brick(690, 245, 35, 15, 4);
			 
				 allBricks.add(brick1a);
				 allBricks.add(brick2a);
				 allBricks.add(brick2b);
				 allBricks.add(brick3a);
				 allBricks.add(brick3b);
				 allBricks.add(brick3c);
				 allBricks.add(brick4a);
				 allBricks.add(brick4b);
				 allBricks.add(brick4c);
				 allBricks.add(brick4d);
				 allBricks.add(brick5a);
				 allBricks.add(brick5b);
				 allBricks.add(brick5c);
				 allBricks.add(brick5d);
				 allBricks.add(brick5e);
				 allBricks.add(brick6a);
				 allBricks.add(brick6b);
				 allBricks.add(brick6c);
				 allBricks.add(brick6d);
				 allBricks.add(brick6e);
				 allBricks.add(brick6f);
				 allBricks.add(brick7a);
				 allBricks.add(brick7b);
				 allBricks.add(brick7c);
				 allBricks.add(brick7d);
				 allBricks.add(brick7e);
				 allBricks.add(brick7f);
				 allBricks.add(brick7g);
				 allBricks.add(brick8a);
				 allBricks.add(brick8b);
				 allBricks.add(brick8c);
				 allBricks.add(brick8d);
				 allBricks.add(brick8e);
				 allBricks.add(brick8f);
				 allBricks.add(brick9a);
				 allBricks.add(brick9b);
				 allBricks.add(brick9c);
				 allBricks.add(brick9d);
				 allBricks.add(brick9e);
				 allBricks.add(brick10a);
				 allBricks.add(brick10b);
				 allBricks.add(brick10c);
				 allBricks.add(brick10d);
				 allBricks.add(brick11a);
				 allBricks.add(brick11b);
				 allBricks.add(brick11c);
				 allBricks.add(brick12a);
				 allBricks.add(brick12b);
				 allBricks.add(brick13a);
				 //
				 allBricks.add(brick2_1a);
				 allBricks.add(brick2_2a);
				 allBricks.add(brick2_2b);
				 allBricks.add(brick2_3a);
				 allBricks.add(brick2_3b);
				 allBricks.add(brick2_3c);
				 allBricks.add(brick2_4a);
				 allBricks.add(brick2_4b);
				 allBricks.add(brick2_4c);
				 allBricks.add(brick2_4d);
				 allBricks.add(brick2_5a);
				 allBricks.add(brick2_5b);
				 allBricks.add(brick2_5c);
				 allBricks.add(brick2_5d);
				 allBricks.add(brick2_5e);
				 allBricks.add(brick2_6a);
				 allBricks.add(brick2_6b);
				 allBricks.add(brick2_6c);
				 allBricks.add(brick2_6d);
				 allBricks.add(brick2_6e);
				 allBricks.add(brick2_6f);
				 allBricks.add(brick2_7a);
				 allBricks.add(brick2_7b);
				 allBricks.add(brick2_7c);
				 allBricks.add(brick2_7d);
				 allBricks.add(brick2_7e);
				 allBricks.add(brick2_7f);
				 allBricks.add(brick2_7g);
				 allBricks.add(brick2_8a);
				 allBricks.add(brick2_8b);
				 allBricks.add(brick2_8c);
				 allBricks.add(brick2_8d);
				 allBricks.add(brick2_8e);
				 allBricks.add(brick2_8f);
				 allBricks.add(brick2_9a);
				 allBricks.add(brick2_9b);
				 allBricks.add(brick2_9c);
				 allBricks.add(brick2_9d);
				 allBricks.add(brick2_9e);
				 allBricks.add(brick2_10a);
				 allBricks.add(brick2_10b);
				 allBricks.add(brick2_10c);
				 allBricks.add(brick2_10d);
				 allBricks.add(brick2_11a);
				 allBricks.add(brick2_11b);
				 allBricks.add(brick2_11c);
				 allBricks.add(brick2_12a);
				 allBricks.add(brick2_12b);
				 allBricks.add(brick2_13a);
				 //
				 allBricks.add(brick3_1a);
				 allBricks.add(brick3_2a);
				 allBricks.add(brick3_2b);
				 allBricks.add(brick3_3a);
				 allBricks.add(brick3_3b);
				 allBricks.add(brick3_3c);
				 allBricks.add(brick3_4a);
				 allBricks.add(brick3_4b);
				 allBricks.add(brick3_4c);
				 allBricks.add(brick3_4d);
				 allBricks.add(brick3_5a);
				 allBricks.add(brick3_5b);
				 allBricks.add(brick3_5c);
				 allBricks.add(brick3_5d);
				 allBricks.add(brick3_5e);
				 allBricks.add(brick3_6a);
				 allBricks.add(brick3_6b);
				 allBricks.add(brick3_6c);
				 allBricks.add(brick3_6d);
				 allBricks.add(brick3_6e);
				 allBricks.add(brick3_6f);
				 allBricks.add(brick3_7a);
				 allBricks.add(brick3_7b);
				 allBricks.add(brick3_7c);
				 allBricks.add(brick3_7d);
				 allBricks.add(brick3_7e);
				 allBricks.add(brick3_7f);
				 allBricks.add(brick3_7g);
				 allBricks.add(brick3_8a);
				 allBricks.add(brick3_8b);
				 allBricks.add(brick3_8c);
				 allBricks.add(brick3_8d);
				 allBricks.add(brick3_8e);
				 allBricks.add(brick3_8f);
				 allBricks.add(brick3_9a);
				 allBricks.add(brick3_9b);
				 allBricks.add(brick3_9c);
				 allBricks.add(brick3_9d);
				 allBricks.add(brick3_9e);
				 allBricks.add(brick3_10a);
				 allBricks.add(brick3_10b);
				 allBricks.add(brick3_10c);
				 allBricks.add(brick3_10d);
				 allBricks.add(brick3_11a);
				 allBricks.add(brick3_11b);
				 allBricks.add(brick3_11c);
				 allBricks.add(brick3_12a);
				 allBricks.add(brick3_12b);
				 allBricks.add(brick3_13a);
				
		}
		 
		 for(int i = 0; i<allBricks.size(); i++){ //update the color for certain hit count
				if (allBricks.get(i).getHits() == 4){allBricks.get(i).setColor(Color.BLACK);}
				if (allBricks.get(i).getHits() == 3){allBricks.get(i).setColor(Color.BLUE);}
				if (allBricks.get(i).getHits() == 2){allBricks.get(i).setColor(Color.GREEN);}
				if (allBricks.get(i).getHits() == 1){allBricks.get(i).setColor(Color.YELLOW);} 
			}
	}
	
	public Powerup generatePowerup(Brick currentBrick){
		int tempRandNum2 = randInt(1,12); 
		//int tempRandNum2 = randInt(1,2);
		//int tempRandNum2 = 7; // Set this to a specific number to test one powerup
		switch(tempRandNum2){
			case 13:
				Powerup powerup13 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Missile");
				return powerup13;
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
				System.out.println("Powerup Gained: " + "Magnet");
				Powerup powerup1 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Magnet");
				return powerup1; 
			default:
				break;
			
	}
		return null;
	}
	public static Missile fireMissile(){
		System.out.println("firing missile");
		return Game.missiles.get(0);
	}
	
	public static void hideBrick(Brick newbrick, Ball saveBall){
		newbrick.getBounds().setBounds(-10, -10, 0, 0);
		newbrick.setAlive(false);
		//double saveXa = saveBall.getXa();
		//saveBall.setXa(saveXa * (-1));
		//double saveYa = saveBall.getYa();
		//saveBall.setYa(saveYa * (-1));
		newbrick = null;
	}
	

}
