import java.awt.Color; 
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

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
	static String lifeString = "***"; // displays the user's lives in the JFrame title
	static ArrayList<String> users = new ArrayList<String>();
	static int Score = 000000;
	static Boolean hold = false;
	static ArrayList<Powerup> placeHolder = new ArrayList<Powerup>();
	static int pointMultiplier = 1;
	static Boolean hasFireball = false;
	static Boolean hasMetalPower = false;
	static ArrayList<Ball> activeBalls = new ArrayList<Ball>();
	static Random randNum = new Random();
	Boolean powerupsEnabled = true;
	static int Round = 1;
	
	static boolean isPaused = false; // true if user has paused the game
	private static boolean hasQuit = false;  // true if user has quit the game
	
	static boolean hasShot = false; // missile stuff
	static int missileCount = 0;   // missile stuff
	
	static ArrayList<MachineGun> bullets = new ArrayList<MachineGun>(); // machine gun stuff
	static boolean hasGun = false;
	
	//static ArrayList<Integer> scores = new ArrayList<Integer>();
	Map<Integer, String> scores = new TreeMap<Integer, String>(Collections.reverseOrder());
	

    static ArrayList<Brick> allBricks = new ArrayList<Brick>();
	static int maxRound = 4;
	Racquet racquet = new Racquet(this);
	private static JFrame frame;
	static HighScores TempscoreWindow;
	static Game TempGame;
	int saveTempScore = Score;

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
		addKeyListener(new KeyListener(){
		

			//public void mouseClicked(MouseEvent arg0) { //old code for mouseListener, if we want to go back
				//started = true;
				//hold = false;
			//}

			@Override
			public void keyPressed(KeyEvent arg0) {
				 if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
					 started = true;
					 if (missiles.size()>0 && hasShot == false){
						 //Game.fireMissile();
						 Game.missiles.get(0).setX(racquet.getX() + 25);
						 Game.missileCount = Game.missileCount - 1;
						 hasShot = true;
						 //Game.missiles.remove(0);
						 // change hasShot to false in the collision part of the missile
					 }
				 }
				 if (arg0.getKeyCode() == KeyEvent.VK_P){ isPaused = true;}
				 if (arg0.getKeyCode() == KeyEvent.VK_SPACE && isPaused == true) {isPaused = false;}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		setFocusable(true);
	}
	
	@SuppressWarnings("static-access")
	private void move() throws IOException {
		if (started == true){
			
			for (int i =0; i<activeBalls.size(); i++){
				activeBalls.get(i).move();
			}
			if (hasShot == true && Game.missiles.size()>0){
				//for (Missile m : Game.missiles){
				//for (int i = 0; i<missiles.size();i++){
					missiles.get(0).move();
				//}
			}
			if (bullets.size()>0){
				for (int i = 0; i < bullets.size(); i++){
					bullets.get(i).move();
				}
			}
			//hold = false;
		}
		else{
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
		if (missiles.isEmpty()==false && hasShot == true){
			//for (Missile m : missiles){
				missiles.get(0).paint(g2d);
			//}
		}
		if (bullets.isEmpty()==false){
			for (int i = 0; i < bullets.size(); i++){
				bullets.get(i).paint(g2d);
			}
		}
	}
	
	public void processHighScores()  throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("scores.dat"));
	     String line = in.readLine();
	     int numTracker = 0;
	     String subLine = line.substring(2).trim();
	     while(line != null){
	       String[] ar=line.split(":");
	       if (numTracker < 9){
   		   ar[0] = ar[0].substring(2);
   	   }else if (numTracker >= 9){
   		   ar[0] = ar[0].substring(3);
   	   }
	       numTracker += 1;
	       scores.put(Integer.parseInt(ar[1].trim()), ar[0]);
	       line = in.readLine();
	     }
	     in.close();
	     
	     Integer min = Collections.min(scores.keySet());
	     
	     
		//this part adds the high score to the list
		if (Score > min){
			EnterName nameBox = new EnterName(frame, true);
			nameBox.setLocationRelativeTo(frame);
			nameBox.setVisible(true);
			String user = nameBox.getUserName();
			String saveOldUser = null;
			if (scores.get(Score) != null){
				saveOldUser = scores.get(Score);
			}
			scores.put(Score, user);
			String userPlace = scores.get(Score); // location (rank) of the score
			ArrayList keys = new ArrayList(scores.values());
			for (int i = 0; i < keys.size();i++){
				if (keys.get(i).equals(userPlace)){
					String finalUser = userPlace;
					scores.remove(Score);
					saveTempScore = Score;
					checkForCopies(saveOldUser, finalUser, Score); // checks to see if there is a high score with the same value
					scores.put(saveTempScore, finalUser);
					Score = saveTempScore;
					//scores.put(Score, userPlace);
					break;
				}
			};
		}else{
			JOptionPane.showMessageDialog(this, "What have I done wrong?", "Game Over!!!", JOptionPane.ERROR_MESSAGE,new javax.swing.ImageIcon(getClass().getResource("/images/bill gates.jpg")));	
		}
		if (scores.size()>10){ //trims the scores list to be the top 10
			scores.remove(min);
		}
		//Collections.sort(scores.keySet());
		//Collections.reverseOrder(scores.keySet());
		writeHighScores();
		HighScores scoreWindow = new HighScores(frame, true);
		TempscoreWindow = scoreWindow;
		scoreWindow.setLocationRelativeTo(TempGame);
		scoreWindow.setVisible(false);
		Round = 1;
		TempscoreWindow.setLocalScores(TempGame);
		frame.setVisible(false);
		TempscoreWindow.setVisible(true);
	}
	
	public void checkForCopies(String saveOldUser, String finalUser, int tempScore){
		if (saveOldUser != null){
			scores.put(tempScore, saveOldUser);
			if (scores.get(tempScore-1) != null){
				String newOldUser = scores.get(tempScore - 1);
				String newFinUser = finalUser;
				checkForCopies(newOldUser, newFinUser, tempScore-1);
				scores.put(tempScore-1, newOldUser);
			}
			saveTempScore  = saveTempScore -1;
		}else{
			scores.put(tempScore, finalUser);
		}
	}
	
	public void gameOver()  throws IOException { 		//read in the list of scores to the scores arraylist
		processHighScores();
		System.exit(ABORT);
	}
	
	public void writeHighScores(){
		//finally, write all the scores to the scores file
				try {
					FileWriter fileToSave = new FileWriter("scores.dat");
					int scorePlace = 1;
					for (Map.Entry<Integer,String> entry : scores.entrySet()) {
					    fileToSave.append(scorePlace + "."+ entry.getValue() + ": " + entry.getKey().toString());
					    fileToSave.write("\n");
					    scorePlace += 1;
					}
					fileToSave.close();
					}
					catch (IOException e1) {}
	}
	
	public void nextRoundMessage(){
		JOptionPane.showMessageDialog(this, "Now get ready for the next round!", "Great Job!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void gameWon() throws IOException {
		//read in the list of scores to the scores arraylist
		processHighScores();
		JOptionPane.showMessageDialog(this, "Congratulations! You have completed all of the levels.", "Winner!", JOptionPane.INFORMATION_MESSAGE);
		System.exit(ABORT);
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		frame = new JFrame("Brick Breaker");
		Game game = new Game();
		TempGame = game;
		StartMenu menu = new StartMenu(frame, true);
		
		//highscore stuff
		HighScores scoreWindow = new HighScores(frame, true);
		TempscoreWindow = scoreWindow;
		scoreWindow.setLocationRelativeTo(game);
		scoreWindow.setVisible(false);
		//end highscore stuff
		
		menu.setLocationRelativeTo(game);
		menu.setVisible(true);
		
        Instructions instructions = new Instructions(frame,true);
        instructions.setLocationRelativeTo(game);
        if (menu.getInstructions()==true){
        	instructions.setVisible(true);
        }
        Round1 round = new Round1(TempGame);
        colorBricks();
		
		frame.add(game);
		frame.setSize(855, 600);
		frame.setLocation(300, 50);
		//frame.setLocationRelativeTo(game);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		while (true) { //game loop
			if (!isPaused){
				if (Game.missileCount == 0){
					if (Game.placeHolder.size() > 0){ 
					frame.setTitle("Lives: " + Game.getLifeString() + "   Score: " + Score  + "           Available Powerups:  "+Game.placeHolder.toString());
					}
					else{
						frame.setTitle("Lives: " + Game.getLifeString() + "   Score: " + Score  + "           Available Powerups:  "+"None");
					}
				}else{
					if (Game.placeHolder.size() > 0){ 
						frame.setTitle("Lives: " + Game.getLifeString() + "   Score: " + Score  + "           Available Powerups:  "+Game.placeHolder.toString() + "    Missiles: " + missileCount);
						}
						else{
							frame.setTitle("Lives: " + Game.getLifeString() + "   Score: " + Score  + "           Available Powerups:  "+"None" + "    Missiles: " + missileCount);
						}
				}
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
								allBricks.get(i).subtractHit(); // this is where im subtracting a hit from brick
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
						    }
						};
						Timer timer = new Timer(delay, taskPerformer);
						timer.setRepeats(false); 
						timer.start();
						
						colorBricks();
						
						if (allBricks.get(i).getHits() <= 0){ // remove a brick if its hit counter is 0
							hideBrick(allBricks.get(i), activeBalls.get(j));
							allBricks.remove(i);
						}
					}
					if (allBricks.isEmpty()){
						if (Round < maxRound){
							Ball saveBall = activeBalls.get(0);
							saveBall.setX(game.racquet.getBounds().x);
							saveBall.setY(game.racquet.getBounds().y - 10);
							game.nextRoundMessage();
							Round += 1;
							bullets.clear();
							started = false;
							hold = false;
							placeHolder.clear();
							nextRound();
							hasFireball = false;
							hasMetalPower = false;
							hasShot = false;
							missileCount = 0;
							missiles.clear();
							hasGun = false;
							bullets.clear();
							activeBalls.clear();
							activeBalls.add(saveBall);
							saveBall.speed = 2;
							saveBall.ballMods = 0;
							saveBall.DIAMETER = 12;
							game.racquet.setWIDTH(60);
							game.racquet.racquetMods = 0;
						}
						if (Round == maxRound ){
							game.gameWon();
						}
					}
				}	
		}
			Thread.sleep(10);
		}   // else statement contains pause feature
			else{
				Thread.sleep(100);
				frame.setTitle("Game Paused: Press space to continue");
			}
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
	
	//public static void quitGame(){ 
	//	if (hasQuit == true){
	//		
	//	}
	//}
	
	public static void colorBricks(){
		for(int i = 0; i<allBricks.size(); i++){ //update the color for certain hit count
			if (allBricks.get(i).getHits() == 4){allBricks.get(i).setColor(Color.BLACK);}
			if (allBricks.get(i).getHits() == 3){allBricks.get(i).setColor(Color.BLUE);}
			if (allBricks.get(i).getHits() == 2){allBricks.get(i).setColor(Color.GREEN);}
			if (allBricks.get(i).getHits() == 1){allBricks.get(i).setColor(Color.YELLOW);} 
		}
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
		int tempRandNum = randInt(1,10); // random number has to be 2 or 7 to get a powerup  (20% chance).
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
	
	public Powerup generatePowerup(Brick currentBrick){
		//int tempRandNum2 = randInt(1,15); 
		//int tempRandNum2 = randInt(1,2);
		int tempRandNum2 = 13; // Set this to a specific number to test one powerup
		switch(tempRandNum2){
			case 15:
				Powerup powerup15 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Insanity Mode", Color.RED);
				return powerup15;
			case 14:
				Powerup powerup14 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Machine Gun", Color.GREEN);
				return powerup14;
			case 13:
				Powerup powerup13 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Missile", Color.GREEN);
				return powerup13;
			case 12:
				System.out.println("Powerup Gained: " + "Extra Life");
				Powerup powerup12 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Extra Life", Color.GREEN);
				return powerup12;
			case 11:
				System.out.println("Powerup Gained: " + "Metal Ball");
				Powerup powerup11 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Metal Ball", Color.GREEN);
				return powerup11; 
			case 10:
				System.out.println("Powerup Gained: " + "Fireball");
				Powerup powerup10 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Fireball", Color.GREEN);
				return powerup10; 
			case 9:
				System.out.println("Powerup Gained: " + "Double Points");
				Powerup powerup9 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Double Points", Color.GREEN);
				return powerup9; 
			case 8:
				System.out.println("Powerup Gained: " + "Ball Decrease");
				Powerup powerup8 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Ball Decrease", Color.WHITE);
				return powerup8; 
			case 7:
				System.out.println("Powerup Gained: " + "Multiple Balls");
				Powerup powerup7 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Multiple Balls", Color.GREEN);
				return powerup7; 
			case 6:
				System.out.println("Powerup Gained: " + "Paddle Decrease");
				Powerup powerup6 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Paddle Decrease", Color.WHITE);
				return powerup6; 
			case 5:
				System.out.println("Powerup Gained: " + "Slow Down");
				Powerup powerup5 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Slow Down", Color.WHITE);
				return powerup5; 
			case 4:
				System.out.println("Powerup Gained: " + "Speed Up");
				Powerup powerup4 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Speed Up", Color.WHITE);
				return powerup4; 
			case 3:
				System.out.println("Powerup Gained: " + "Paddle Increase");
				Powerup powerup3 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Paddle Increase", Color.GREEN);
				return powerup3; 
			case 2:
				System.out.println("Powerup Gained: " + "Ball Increase");
				Powerup powerup2 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Ball Increase", Color.GREEN);
				return powerup2; 
			case 1:
				System.out.println("Powerup Gained: " + "Magnet");
				Powerup powerup1 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Magnet", Color.GREEN);
				return powerup1; 
			default:
				break;
	}
		return null;
	}
	
	public static void hideBrick(Brick newbrick, Ball saveBall){
		newbrick.getBounds().setBounds(-10, -10, 0, 0);
		newbrick.setAlive(false);
		newbrick = null;
	}

	public int getMissileCount() {
		return missileCount;
	}

	public void setMissileCount(int missileCount) {
		this.missileCount = missileCount;
	}
	
	public static void nextRound(){
		allBricks.clear();
		hasShot = false;
		bullets.clear();
		hasGun = false;
		missileCount = 0;
		missiles.clear();
		if (Round == 2){
			Round2 round = new Round2(TempGame);
		}else if (Round == 3){
			Round3 round = new Round3(TempGame);	
		}
		colorBricks(); 
	}
}
