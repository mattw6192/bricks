import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
	static ArrayList<Powerup> droppedPowerups = new ArrayList<Powerup>();
	static ArrayList<Powerup> activePowerups = new ArrayList<Powerup>();
	static ArrayList<SafetyNet> safetyList = new ArrayList<SafetyNet>();
	static Boolean hasSafetyNet = false;
	static int pointMultiplier = 1;
	static Boolean hasFireball = false;
	static Boolean hasMetalPower = false;
	static Boolean hasBoreyMode = false;
	static Boolean hasMagnet = false;
	static Boolean hasInsanityMode = false;
	static ArrayList<Ball> activeBalls = new ArrayList<Ball>();
	static Random randNum = new Random();
	Boolean powerupsEnabled = true;
	static int Round = 1;
	static int quickHits = 0;
	
	
	static boolean isPaused = false; // true if user has paused the game
	//private static boolean hasQuit = false;  // true if user has quit the game
	
	static boolean hasShot = false; // missile stuff
	static int missileCount = 0;   // missile stuff
	
	static ArrayList<MachineGun> bullets = new ArrayList<MachineGun>(); // machine gun stuff
	static boolean hasGun = false;
	
	//static ArrayList<Integer> scores = new ArrayList<Integer>();
	Map<Integer, String> scores = new TreeMap<Integer, String>(Collections.reverseOrder());
	

    static ArrayList<Brick> allBricks = new ArrayList<Brick>();
	static int maxRound = 7;
	Racquet racquet = new Racquet(this);
	private static JFrame frame;
	static HighScores TempscoreWindow;
	static Game TempGame;
	int saveTempScore = Score;
	static startMenu4 menu;
	static Probability probs;
	static Timer collisionTimer;
	static Timer quickHitsTimer;
	private boolean probsAns;
	static boolean overwritePowerupLimits = false;
	static SidebarMenu sideMenu;

	public Game() { 
		this.requestFocus();
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
				 if (arg0.getKeyCode() == KeyEvent.VK_E) {
					 
					 if (missiles.size()>0 && hasShot == false){
						 //Game.fireMissile();
						 Sound.Play(Sound.missile);
						 Game.missiles.get(0).setX(racquet.getX() + (racquet.getWIDTH()/2));
						 Game.missileCount = Game.missileCount - 1;
						 hasShot = true;
						 //Game.missiles.remove(0);
						 // change hasShot to false in the collision part of the missile
					 }
				 }
				 if (arg0.getKeyCode() == KeyEvent.VK_P){ isPaused = true;}
				 if (arg0.getKeyCode() == KeyEvent.VK_SPACE && isPaused == true) {isPaused = false;}
				 if (arg0.getKeyCode() == KeyEvent.VK_SPACE && started == false) {started = true;}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		setFocusable(true);
	}
	
	/**
	 * Returns the number of lives the player has
	 * @return
	 */
	public int getLives(){
		return Lives;
	}
	
	/**
	 * Controls the ball movement if the game is in an active state
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	private void move() throws IOException {
		if (started == true){
			
			for (int i =0; i<activeBalls.size(); i++){
				activeBalls.get(i).move();
			}
			
			//hold = false;
		}
		else{
			for (int i =0; i<activeBalls.size(); i++){
				activeBalls.get(i).setX((int) racquet.getBounds().getX() + (int) (racquet.getBounds().getWidth() / 2));
				activeBalls.get(i).setY((int) racquet.getBounds().getY() - (int) (activeBalls.get(i).DIAMETER));
			}
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
		racquet.move();
	}

	/**
	 * Checks to see if any lives remain. If no lives remain it returns True, returns False otherwise.
	 * @return
	 */
	public boolean isGameOver(){
		if (Lives <= 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * returns the string value for the life string that is displayed on the status bar
	 * @return
	 */
	public static String getLifeString() {
		return lifeString;
	}

	/**
	 * allows for setting the value of the life string that is displayed in the status bar
	 * @param lifeString
	 */
	public static void setLifeString(String lifeString) {
		Game.lifeString = lifeString;
	}
	
	/**
	 * Graphics device that paints all the objects onto the screen
	 */
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
		if (safetyList.isEmpty() == false){
			for(int z = 0; z<safetyList.size(); z++){
				safetyList.get(z).paint(g2d);
			}
		}
		if (droppedPowerups.isEmpty() == false){
			for (int i=0; i<droppedPowerups.size();i++){
				droppedPowerups.get(i).paint(g2d);
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
	
	/**
	 * Takes a high score and processes the information, and updates the high score page as necessary
	 * @throws IOException
	 */
	public void processHighScores()  throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("scores.dat"));
	     String line = in.readLine();
	     int numTracker = 0;
	     //String subLine = line.substring(2).trim();
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
			EnterName2 nameBox = new EnterName2(frame, true);
			nameBox.setLocationRelativeTo(frame);
			nameBox.setSize(425, 300);
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
		//System.out.println(scores);
		//Collections.sort(scores.keySet());
		//Collections.reverseOrder(scores.keySet());
		writeHighScores(TempscoreWindow);
		HighScores scoreWindow = new HighScores(frame, true, menu, true,frame);
		TempscoreWindow = scoreWindow;
		scoreWindow.setLocationRelativeTo(TempGame);
		scoreWindow.setVisible(false);
		Round = 1;
		TempscoreWindow.setLocalScores(TempGame);
		frame.setVisible(false);
		TempscoreWindow.setVisible(true);
	}
	
	/**
	 * checks for duplicate scores in the high scores space and handles them accordingly
	 * @param saveOldUser
	 * @param finalUser
	 * @param tempScore
	 */
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
	
	/**
	 * ends the game and kills the program
	 * @throws IOException
	 */
	public void gameOver()  throws IOException { 		//read in the list of scores to the scores arraylist
		processHighScores();
		System.exit(ABORT);
	}
	
	/**
	 * writes the high scores to the high scores page
	 */
	public void writeHighScores(HighScores window){
		//finally, write all the scores to the scores file
		window.jTextArea1.removeAll();	
		int scorePlace = 1;
		//for (Map.Entry<Integer,String> entry : scores.entrySet()) {
	   //       window.jTextArea1.append(scorePlace + "." + entry.getValue() + ": " + entry.getKey().toString());
	    //      scorePlace += 1;
	          //line = in.readLine();
	      // }
				
				try {
					FileWriter fileToSave = new FileWriter("scores.dat");
					scorePlace = 1;
					for (Map.Entry<Integer,String> entry : scores.entrySet()) {
					    fileToSave.append(scorePlace + "."+ entry.getValue() + ": " + entry.getKey().toString());
					    fileToSave.write("\n");
					    scorePlace += 1;
					}
					fileToSave.close();
					}
					catch (IOException e1) {} /// this is a problem
			
	}
	
	/**
	 * A visual pop up box that tells the user to prepare for the next round
	 */
	public void nextRoundMessage(){
		JOptionPane.showMessageDialog(this, "Now get ready for the next round!", "Great Job!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * All conditions for winning the game have been met, so the high score is processed and a popup message with congrats is displayed
	 * @throws IOException
	 */
	public void gameWon() throws IOException {
		//read in the list of scores to the scores arraylist
		processHighScores();
		JOptionPane.showMessageDialog(this, "Congratulations! You have completed all of the levels.", "Winner!", JOptionPane.INFORMATION_MESSAGE);
		System.exit(ABORT);
	}

	/**
	 * This is the main game execution method, it includes the main game loop as well
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		frame = new JFrame("Brick Breaker");
		Game game = new Game();
		sideMenu = new SidebarMenu();
		TempGame = game;
		menu = new startMenu4(frame, true);
		
		////////////// Testing Area //////////
		//PowerupInstructions kks = new PowerupInstructions(frame, true, menu, false, frame);
		//kks.setSize(500, 400);
		//kks.setTitle("Powerup Information");
		//kks.setVisible(true);
		
		powerupsList ssk = new powerupsList(frame, true, menu, false, frame);
		ssk.setSize(700, 350);
		ssk.setTitle("Powerups List");
		ssk.setVisible(true);
		////////////// END TESTING //////////
		
		//highscore stuff
		HighScores scoreWindow = new HighScores(frame, true, menu, false, frame);
		TempscoreWindow = scoreWindow;
		scoreWindow.setLocationRelativeTo(game);
		scoreWindow.setVisible(false);
		//end highscore stuff
		
		menu.setLocationRelativeTo(game);
		menu.setVisible(true);
		
        while (menu.getStart() == false){
	        if (menu.getInstructions()==true){
	        	Instructions instructions = new Instructions(frame,true, menu);
	            instructions.setLocationRelativeTo(game);
	        	instructions.setVisible(true);
	        	menu.setInstructions(false);
	        }
	        if (menu.getScores()==true){
	        	scoreWindow.setVisible(true);
	        	menu.setScores(false);
	        }
        }
        if (menu.getStart() == true){
        	Round1 round = new Round1(TempGame);
        	frame.setVisible(true);
        	menu.setStart(false);
        }
        
        colorBricks();
		
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        game.setSize(515, 600);
        game.setPreferredSize(game.getSize());
        game.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sideMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sideMenu.setSize(100,600);

        container.add(game);
        container.add(sideMenu);
        frame.getContentPane().add(container);
		frame.setSize(1200, 600);
		frame.setLocation(100, 50);
		//frame.setLocationRelativeTo(game);
		probs = new Probability(TempGame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.grabFocus();
		ActionListener quickHitsDetection = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (quickHits >= 4){
					probs.needsCollisions = false;
					overwritePowerupLimits = false;
				}
				quickHits = 0;
			}
		};
		int quickHitsDuration = 15000; //milliseconds
		quickHitsTimer = new Timer(quickHitsDuration, quickHitsDetection);
		quickHitsTimer.setRepeats(true);
		
		// Action Listener
		ActionListener collisionDetection = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				probs.needsCollisions = true;
				quickHitsTimer.start();
				quickHits = 0; 
				overwritePowerupLimits = true;
			}
		};
		int collisionDuration = 30000; //milliseconds
		collisionTimer = new Timer(collisionDuration, collisionDetection);
		collisionTimer.setRepeats(false);
		collisionTimer.start();
		
		
		
		while (true) { //game loop
			if (!isPaused){
				if (Game.missileCount == 0){
					  sideMenu.lblMissiles.setVisible(false);
					}else{
					  sideMenu.lblMissiles.setVisible(true);
					  sideMenu.lblMissiles.setText("Missiles: " + missileCount);
					}

					if (Game.droppedPowerups.size() > 0){
					  sideMenu.lblPowerups.setText(Game.droppedPowerups.toString());
					}else{
					  sideMenu.lblPowerups.setText("None");
					}

					if (Game.activePowerups.size() > 0){
					  sideMenu.lblactivePowerups.setVisible(true);
					  sideMenu.lblCurrentPowerups.setVisible(true); 
					  sideMenu.lblactivePowerups.setText(Game.activePowerups.toString());
					}else{
					  sideMenu.lblactivePowerups.setVisible(false);
					  sideMenu.lblCurrentPowerups.setVisible(false);  
					}

					sideMenu.lblLevel.setText("Level " + Game.Round);
					sideMenu.lblScore.setText("Score: " + Score);
					sideMenu.lblLives.setText("Lives: " + Game.getLifeString());
			game.move();
			game.repaint();
			
			if (droppedPowerups.isEmpty() == false){ // droppedPowerups is powerups on screen
				for (int j=0; j<droppedPowerups.size();j++){
					droppedPowerups.get(j).move();
				}
			}
			
			for (int j = 0; j<activeBalls.size(); j++){
				for(int i = 0; i<allBricks.size(); i++){ 
					if (activeBalls.get(j).getBounds().intersects(allBricks.get(i).getBounds())){
						quickHits += 1;
						collisionTimer.stop();
						collisionTimer.restart();
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
							Sound.Play(Sound.BrickHit);
							allBricks.get(i).subtractAllHits();
						}else if(hasMetalPower == true && allBricks.get(i).canBeHit == true){
							allBricks.get(i).subtractTwoHits(); // metal ball subtracts two hits
							Sound.Play(Sound.BrickHit);
						}else{
							if (allBricks.get(i).canBeHit == true){
								allBricks.get(i).subtractHit(); // this is where im subtracting a hit from brick
								Sound.Play(Sound.BrickHit);
							}
						}
					    
					    boolean havePowerup = game.probs.getPowerup();
					    if (havePowerup == true){
					    	Powerup savePower = game.generatePowerup(allBricks.get(i));
					    	droppedPowerups.add(savePower);
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
							nextRound(game);
						}
						if (Round == maxRound ){
							game.gameWon();
						}
						if (Round < maxRound){
							game.nextRoundMessage();
						}
					}
				}
				for (int z = 0; z < safetyList.size();z++){ // checks if the ball hits the safety net
					if (activeBalls.get(j).getBounds().intersects(safetyList.get(z).getBounds())){
						activeBalls.get(j).setYa(activeBalls.get(j).getYa() * -1);
						activeBalls.get(j).setY(safetyList.get(z).getTopY() - activeBalls.get(j).DIAMETER -1);
						safetyList.get(z).checkHits();
						
						
					}
				}
		}
			Thread.sleep(10);
			frame.setTitle("Brick Breaker");
			sideMenu.btnPause.setText("Pause Game");
		}   // else statement contains pause feature
			else{
				collisionTimer.stop();
				Thread.sleep(100);
				frame.setTitle("Game Paused: Press space to continue");
				sideMenu.btnPause.setText("Resume Game");
			}
		}
	}
	
 
	/**
	 * This method checks for collisions with the sides of bricks and changes the course of the ball accordingly
	 * @param tempBrick
	 * @param tempBall
	 * @return
	 */
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
	
	/**
	 * This method sets the colors of each brick according to its hit count
	 */
	public static void colorBricks(){
		for(int i = 0; i<allBricks.size(); i++){ //update the color for certain hit count
			if (allBricks.get(i).getHits() == 4){allBricks.get(i).setColor(Color.BLACK);}
			if (allBricks.get(i).getHits() == 3){allBricks.get(i).setColor(Color.BLUE);}
			if (allBricks.get(i).getHits() == 2){allBricks.get(i).setColor(Color.GREEN);}
			if (allBricks.get(i).getHits() == 1){allBricks.get(i).setColor(Color.YELLOW);} 
		}
	}
	
	/**
	 * This checks for normal hits between a brick and the ball 
	 * and offsets the ball when such a hit occurs so the ball doesnt warp inside of a brick and cause issues
	 * @param tempBrick
	 * @param tempBall
	 */
	public static void checkNormalHits(Brick tempBrick, Ball tempBall){
		if(tempBall.getBounds().getY() <= (tempBrick.getBounds().getY() + 2) ){ // top
			tempBall.setY((int) (tempBrick.getBounds().getY() - tempBall.DIAMETER));
		}else if(tempBall.getBounds().getY() >= (tempBrick.getBounds().getY() + tempBrick.getBounds().getHeight() - 2)){ //bottom
			tempBall.setY((int) (tempBrick.getBounds().getY() + tempBrick.getBounds().getHeight()));
		}
	}
	
	/**
	 * This is the powerhorse for generating powerups. Randomized powerup selection and creation
	 * @param currentBrick
	 * @return
	 */
	public Powerup generatePowerup(Brick currentBrick){
		int tempRandNum2 = probs.checkConditions(probs.randInt(0,103)); 
		//int tempRandNum2 = randInt(7,10);
		//int tempRandNum2 = 14;
		switch(tempRandNum2){
			case 20:
				probsAns = probs.checkPowerup(20);
				probsAns = true;
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Safety Net");
					Powerup powerup20 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Safety Net", Color.GREEN);
					return powerup20;
				}
				return generatePowerup(currentBrick);
			case 19:
				probsAns = probs.checkPowerup(19);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Golden Borey");
					Powerup powerup19 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Golden Borey", Color.GREEN);
					return powerup19;
				}
				return generatePowerup(currentBrick);
			case 18:
				probsAns = probs.checkPowerup(18);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Lose Extra Points");
					Powerup powerup18 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Lose Extra Points", Color.RED);
					return powerup18;
				}
				return generatePowerup(currentBrick);
				
			case 17:
				probsAns = probs.checkPowerup(17);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Gain Extra Points");
					Powerup powerup17 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Gain Extra Points", Color.GREEN);
					return powerup17;
				}
				return generatePowerup(currentBrick);
				
			case 16:
				probsAns = probs.checkPowerup(16);
				if (probsAns == true){	
					//System.out.println("Powerup Gained: " + "Lose a Life");
					Powerup powerup16 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Lose a Life", Color.RED);
					return powerup16;
				}
				return generatePowerup(currentBrick);
				
			case 15:
				probsAns = probs.checkPowerup(15);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Insanity Mode");
					Powerup powerup15 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Insanity Mode", Color.RED);
					return powerup15;
				}
				return generatePowerup(currentBrick);
				
			case 14:
				probsAns = probs.checkPowerup(14);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Machine Gun");
					Powerup powerup14 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Machine Gun", Color.GREEN);
					return powerup14;
				}
				return generatePowerup(currentBrick);
				
			case 13:
				probsAns = probs.checkPowerup(13);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Missile");
					Powerup powerup13 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Missile", Color.GREEN);
					return powerup13;
				}
				return generatePowerup(currentBrick);
				
			case 12:
				probsAns = probs.checkPowerup(12);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Extra Life");
					Powerup powerup12 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Extra Life", Color.GREEN);
					return powerup12;
				}
				System.out.println("Not Allowed - Selecting New Powerup");
				return generatePowerup(currentBrick);
				
			case 11:
				probsAns = probs.checkPowerup(11);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Metal Ball");
					Powerup powerup11 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Metal Ball", Color.GREEN);
					return powerup11; 
				}
				return generatePowerup(currentBrick);
				
			case 10:
				probsAns = probs.checkPowerup(10);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Fireball");
					Powerup powerup10 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Fireball", Color.GREEN);
					return powerup10;
				}
				return generatePowerup(currentBrick);
				
			case 9:
				probsAns = probs.checkPowerup(9);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Double Points");
					Powerup powerup9 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Double Points", Color.GREEN);
					return powerup9; 
				}
				return generatePowerup(currentBrick);
				
			case 8:
				probsAns = probs.checkPowerup(8);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Ball Decrease");
					Powerup powerup8 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Ball Decrease", Color.WHITE);
					return powerup8;
				}
				return generatePowerup(currentBrick);
				
			case 7:
				probsAns = probs.checkPowerup(7);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Multiple Balls");
					Powerup powerup7 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Multiple Balls", Color.GREEN);
					return powerup7;
				}
				return generatePowerup(currentBrick);
				
			case 6:
				probsAns = probs.checkPowerup(6);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Paddle Decrease");
					Powerup powerup6 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Paddle Decrease", Color.WHITE);
					return powerup6;
				}
				return generatePowerup(currentBrick);
				
			case 5:
				probsAns = probs.checkPowerup(5);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Slow Down");
					Powerup powerup5 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Slow Down", Color.WHITE);
					return powerup5;
				}
				return generatePowerup(currentBrick);
				
			case 4:
				probsAns = probs.checkPowerup(4);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Speed Up");
					Powerup powerup4 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Speed Up", Color.WHITE);
					return powerup4;
				}
				return generatePowerup(currentBrick);
				
			case 3:
				probsAns = probs.checkPowerup(3);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Paddle Increase");
					Powerup powerup3 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Paddle Increase", Color.GREEN);
					//Powerup powerup3 = new Powerup(this, 800, 5, 0, "Paddle Increase", Color.GREEN);
					return powerup3;
				}
				return generatePowerup(currentBrick);
				
			case 2:
				probsAns = probs.checkPowerup(2);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Ball Increase");
					Powerup powerup2 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Ball Increase", Color.GREEN);
					return powerup2;
				}
				return generatePowerup(currentBrick);
				
			case 1:
				probsAns = probs.checkPowerup(1);
				if (probsAns == true){
					//System.out.println("Powerup Gained: " + "Magnet");
					Powerup powerup1 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Magnet", Color.GREEN);
					return powerup1;
				}
				return generatePowerup(currentBrick);
				
			default:
				break;
	}
		return null;
	}
	
	/**
	 * hides a brick once it has been destroyed
	 * @param newbrick
	 * @param saveBall
	 */
	public static void hideBrick(Brick newbrick, Ball saveBall){
		newbrick.getBounds().setBounds(-10, -10, 0, 0);
		newbrick.setAlive(false);
		newbrick = null;
	}

	/**
	 * returns the number of missiles the user has
	 * @return
	 */
	public int getMissileCount() {
		return missileCount;
	}

	/**
	 * sets the number of missiles the user has
	 * @param missileCount
	 */
	public void setMissileCount(int missileCount) {
		this.missileCount = missileCount;
	}
	
	/**
	 * resets the game for the next round and loads the proper game board
	 * @param thisGame
	 */
	public static void nextRound(Game thisGame){
		hasSafetyNet = false;
		overwritePowerupLimits = false;
		probs.needsCollisions = false;
		Ball saveBall = activeBalls.get(0);
		saveBall.setX(thisGame.racquet.getBounds().x);
		saveBall.setY(thisGame.racquet.getBounds().y - 10);
		Round += 1;
		bullets.clear();
		started = false;
		hold = false;
		droppedPowerups.clear();
		hasFireball = false;
		hasMetalPower = false;
		hasInsanityMode = false;
		hasBoreyMode = false;
		hasMagnet = false;
		hasShot = false;
		missileCount = 0;
		missiles.clear();
		hasGun = false;
		bullets.clear();
		thisGame.racquet.setX(100);
		//activeBalls.clear();
		if (activeBalls.size()>1){ 
			activeBalls.clear();
			Ball newBall = new Ball(thisGame, 20, 320);
			//Ball newBall = new Ball(thisGame, thisGame.racquet.getBounds().x, thisGame.racquet.getBounds().y - 10);
			activeBalls.add(newBall);
		}
		activeBalls.add(saveBall);
		saveBall.speed = 2;
		saveBall.ballMods = 0;
		saveBall.DIAMETER = 12;
		thisGame.racquet.setWIDTH(60);
		thisGame.racquet.racquetMods = 0;
		
		allBricks.clear();
		
		if (Round == 2){
			Round2 round = new Round2(TempGame);
			activeBalls.clear();
			activeBalls.add(saveBall);
		}else if (Round == 3){
			Round3 round = new Round3(TempGame);	
			activeBalls.clear();
			activeBalls.add(saveBall);
		}else if (Round == 4){
			Round4 round = new Round4(TempGame);	
			activeBalls.clear();
			activeBalls.add(saveBall);
		}else if (Round == 5){
			Round5 round = new Round5(TempGame);	
			activeBalls.clear();
			activeBalls.add(saveBall);
		}else if (Round == 6){
			Round6 round = new Round6(TempGame);	
			activeBalls.clear();
			activeBalls.add(saveBall);
		}
		colorBricks(); 
	}
}
