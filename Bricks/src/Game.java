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
//import java.util.Random;
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
	//double tempBallSize = 0;
	static Boolean started = false;
	int Lives = 3;
	static ArrayList<Missile> missiles = new ArrayList<>();
	static private String lifeString = "***"; // displays the user's lives in the JFrame title
	//static ArrayList<String> users = new ArrayList<String>();
	static int Score = 0;
	static Boolean hold = false;
	static ArrayList<Powerup> droppedPowerups = new ArrayList<>();
	static ArrayList<Powerup> activePowerups = new ArrayList<>();
	static ArrayList<SafetyNet> safetyList = new ArrayList<>();
	static Boolean hasSafetyNet = false;
	static int pointMultiplier = 1;
	static Boolean hasFireball = false;
	static Boolean hasMetalPower = false;
	static Boolean hasBoreyMode = false;
	static Boolean hasMagnet = false;
	static Boolean hasInsanityMode = false;
	static ArrayList<Ball> activeBalls = new ArrayList<>();
	//static Random randNum = new Random();
	Boolean powerupsEnabled = true;
	static int Round = 1;
	static private int quickHits = 0;
	
	
	static boolean isPaused = false; // true if user has paused the game
	//private static boolean hasQuit = false;  // true if user has quit the game
	
	static boolean hasShot = false; // missile stuff
	static int missileCount = 0;   // missile stuff
	
	static ArrayList<MachineGun> bullets = new ArrayList<>(); // machine gun stuff
	static boolean hasGun = false;
	
	//static ArrayList<Integer> scores = new ArrayList<Integer>();
	private Map<Integer, String> scores = new TreeMap<>(Collections.reverseOrder());
	

    static ArrayList<Brick> allBricks = new ArrayList<>();
	static int maxRound = 7;
	Paddle paddle = new Paddle(this);
	private static JFrame frame;
	static private HighScores TempscoreWindow;
	static private Game TempGame;
	private int saveTempScore = Score;
	static private StartMenu menu;
	static Probability probs;

	static private Timer quickHitsTimer;
	static boolean overwritePowerupLimits = false;

	public Game() { 
		this.requestFocus();
		activeBalls.add(ball);
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				paddle.keyReleased(e);
			}

			public void keyPressed(KeyEvent e) {
				paddle.keyPressed(e);
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
					 
					 if (missiles.size()>0 && !hasShot){
						 //Game.fireMissile();
						 Sound.Play(Sound.missile);
						 Game.missiles.get(0).setX(paddle.getX() + (paddle.getWIDTH()/2));
						 Game.missileCount = Game.missileCount - 1;
						 hasShot = true;
						 //Game.missiles.remove(0);
						 // change hasShot to false in the collision part of the missile
					 }
				 }
				 if (arg0.getKeyCode() == KeyEvent.VK_P){
				 	isPaused = !isPaused;
				 }
				 if (arg0.getKeyCode() == KeyEvent.VK_SPACE && isPaused) {isPaused = false;}
				 if (arg0.getKeyCode() == KeyEvent.VK_SPACE && !started ) {started = true;}
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
	 * @return Lives
	 */
	int getLives(){
		return Lives;
	}
	
	/**
	 * Controls the ball movement if the game is in an active state
	 * @throws IOException IOException when errors with ball movement
	 */
	@SuppressWarnings("static-access")
	private void move() throws IOException {
		if (started){

			for (int i = 0; i < activeBalls.size(); i++) {
				Ball activeBall = activeBalls.get(i);
				activeBall.move();
			}
			
			//hold = false;
		}
		else{
			for (Ball activeBall : activeBalls) {
				activeBall.setX((int) paddle.getBounds().getX() + (int) (paddle.getBounds().getWidth() / 2));
				activeBall.setY((int) paddle.getBounds().getY() -  activeBall.DIAMETER);
			}
		}
		if (hasShot && Game.missiles.size()>0){
			//for (Missile m : Game.missiles){
			//for (int i = 0; i<missiles.size();i++){
				missiles.get(0).move();
			//}
		}
		if (bullets.size()>0){
			for (int i = 0; i < bullets.size(); i++) {
				MachineGun bullet = bullets.get(i);
				bullet.move();
			}
		}
		paddle.move();
	}

	/**
	 * Checks to see if any lives remain. If no lives remain it returns True, returns False otherwise.
	 * @return true if no more lives, false if lives remain
	 */
	boolean isGameOver(){
		return Lives <= 0;
	}
	
	/**
	 * returns the string value for the life string that is displayed on the status bar
	 * @return lifestring
	 */
	static String getLifeString() {
		return lifeString;
	}

	/**
	 * allows for setting the value of the life string that is displayed in the status bar
	 * @param lifeString the value of lives remaining, via asterisks
	 */
	static void setLifeString(String lifeString) {
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
		if (!activeBalls.isEmpty()){
			for (Ball activeBall : activeBalls) {
				activeBall.paint(g2d);
			}
		}
		paddle.paint(g2d);
		if (!allBricks.isEmpty()){
			for (Brick allBrick : allBricks) {
				allBrick.paint(g2d);
			}
		}
		if (!safetyList.isEmpty()){
			for (SafetyNet aSafetyList : safetyList) {
				aSafetyList.paint(g2d);
			}
		}
		if (!droppedPowerups.isEmpty()){
			for (Powerup droppedPowerup : droppedPowerups) {
				droppedPowerup.paint(g2d);
			}
		}
		if (!missiles.isEmpty() && hasShot){
			//TODO : this code looks wrong
			//for (Missile m : missiles){
				missiles.get(0).paint(g2d);
			//}
		}
		if (!bullets.isEmpty()){
			for (MachineGun bullet : bullets) {
				bullet.paint(g2d);
			}
		}
	}
	
	/**
	 * Takes a high score and processes the information, and updates the high score page as necessary
	 * @throws IOException IOException when the input file is not found
	 */
	private void processHighScores()  throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("/Users/Matt/IdeaProjects/bricks/Bricks/scores.dat"));
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
			for (Object key : keys) {
				if (key.equals(userPlace)) {
					String finalUser = userPlace;
					scores.remove(Score);
					saveTempScore = Score;
					checkForCopies(saveOldUser, finalUser, Score); // checks to see if there is a high score with the same value
					scores.put(saveTempScore, finalUser);
					Score = saveTempScore;
					//scores.put(Score, userPlace);
					break;
				}
			}

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
	 * @param saveOldUser The first user to be compared
	 * @param finalUser The second  user to be compared
	 * @param tempScore The score to be compared
	 */
	private void checkForCopies(String saveOldUser, String finalUser, int tempScore){
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
	 * @throws IOException IOException when issues processing High Scores
	 */
	void gameOver()  throws IOException { 		//read in the list of scores to the scores arraylist
		processHighScores();
		System.exit(ABORT);
	}
	
	/**
	 * writes the high scores to the high scores page
	 */
	private void writeHighScores(HighScores window){
		//finally, write all the scores to the scores file
		window.jTextArea1.removeAll();	
		int scorePlace;
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
					catch (IOException ignored) {} /// this is a problem :: (edit: May 2017 <-- that was a hell of a comment lol)
			
	}
	
	/**
	 * A visual pop up box that tells the user to prepare for the next round
	 */
	void nextRoundMessage(){
		JOptionPane.showMessageDialog(this, "Now get ready for the next round!", "Great Job!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * All conditions for winning the game have been met, so the high score is processed and a popup message with congrats is displayed
	 * @throws IOException IOException if high scores cannot be processed
	 */
	void gameWon() throws IOException {
		//read in the list of scores to the scores arraylist
		int temppoints = 1000 * getLives();
		Score += temppoints;
		processHighScores();
		JOptionPane.showMessageDialog(this, "Congratulations! You have completed all of the levels.", "Winner!", JOptionPane.INFORMATION_MESSAGE);
		System.exit(ABORT);
	}

	/**
	 * This is the main game execution method, it includes the main game loop as well
	 * @param args The arguments passed in to the game
	 * @throws InterruptedException Interrupted Exception
	 * @throws IOException IO Exception
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		frame = new JFrame("Brick Breaker");
		Game game = new Game();
		SidebarMenu sideMenu;
		sideMenu = new SidebarMenu();
		TempGame = game;
		menu = new StartMenu(frame, true);
		
		////////////// Testing Area //////////
		//PowerupInstructions kks = new PowerupInstructions(frame, true, menu, false, frame);
		//kks.setSize(500, 400);
		//kks.setTitle("Powerup Information");
		//kks.setVisible(true);
		
		//powerupsList ssk = new powerupsList(frame, true, menu, false, frame);
		//ssk.setSize(700, 350);
		//ssk.setTitle("Powerups List");
		//ssk.setVisible(true);
		////////////// END TESTING //////////


		 // Initialize High Scores so we can updated them after a game ends

		HighScores scoreWindow = new HighScores(frame, true, menu, false, frame);
		TempscoreWindow = scoreWindow;
		scoreWindow.setLocationRelativeTo(game);
		scoreWindow.setVisible(false);
		//end highscore stuff
		
		menu.setLocationRelativeTo(game);
		menu.setVisible(true);
		
        while (!menu.getStart()){
	        if (menu.getInstructions()){
	        	Instructions instructions = new Instructions(frame,true, menu);
	            instructions.setLocationRelativeTo(game);
	        	instructions.setVisible(true);
	        	menu.setInstructions(false);
	        }
	        if (menu.getScores()){
	        	scoreWindow.setVisible(true);
	        	menu.setScores(false);
	        }
        }
        if (menu.getStart()){
        	new Round1(TempGame);
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
		ActionListener quickHitsDetection = evt -> {
            if (quickHits >= 4){
                probs.needsCollisions = false;
                overwritePowerupLimits = false;
            }
            quickHits = 0;
        };
		int quickHitsDuration = 15000; //milliseconds
		quickHitsTimer = new Timer(quickHitsDuration, quickHitsDetection);
		quickHitsTimer.setRepeats(true);
		
		// Action Listener
		ActionListener collisionDetection = evt -> {
            probs.needsCollisions = true;
            quickHitsTimer.start();
            quickHits = 0;
            overwritePowerupLimits = true;
        };
		int collisionDuration = 30000; //milliseconds
		Timer collisionTimer = new Timer(collisionDuration, collisionDetection);
		collisionTimer.setRepeats(false);
		collisionTimer.start();


		// This is the main game loop
		while (true) {
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

			// Check if there are any power-up items deployed from bricks, if so, move each item downwards
			if (!droppedPowerups.isEmpty()){
				for (int i = 0; i < droppedPowerups.size(); i++) {
					Powerup droppedPowerup = droppedPowerups.get(i);
					droppedPowerup.move();
				}
			}

				for (int i1 = 0; i1 < activeBalls.size(); i1++) {
					Ball activeBall = activeBalls.get(i1);
					for (int i = 0; i < allBricks.size(); i++) {
						if (activeBall.getBounds().intersects(allBricks.get(i).getBounds())) {
							quickHits += 1;
							collisionTimer.stop();
							collisionTimer.restart();
							final Brick saveBrickForAction = allBricks.get(i);
							Score += (10 * pointMultiplier);
							//sactiveBalls.get(j).xa = activeBalls.get(j).xa * (-1);

							if (!hasFireball) {
								if (!checkSideHits(allBricks.get(i), activeBall)) {
									activeBall.ya = activeBall.ya * (-1); //update coordinates of ball to avoid multiple hits at the same time
									checkNormalHits(allBricks.get(i), activeBall);
									//activeBalls.get(j).xa = activeBalls.get(j).xa * (-1);
									//}else{
									//activeBalls.get(j).ya = activeBalls.get(j).ya * (-1);
									//activeBalls.get(j).xa = activeBalls.get(j).xa * (-1);
								}
							}

							if (hasFireball && allBricks.get(i).canBeHit) {
								Sound.Play(Sound.BrickHit);
								allBricks.get(i).subtractHit(-1);
							} else if (hasMetalPower && allBricks.get(i).canBeHit) {
								allBricks.get(i).subtractHit(2); // metal ball subtracts two hits
								Sound.Play(Sound.BrickHit);
							} else {
								if (allBricks.get(i).canBeHit) {
									allBricks.get(i).subtractHit(1); // this is where im subtracting a hit from brick
									Sound.Play(Sound.BrickHit);
								}
							}

							boolean havePowerup = probs.getPowerup();
							if (havePowerup) {
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

							if (allBricks.get(i).getHits() <= 0) { // remove a brick if its hit counter is 0
								hideBrick(allBricks.get(i), activeBall);
								allBricks.remove(i);
							}
						}
						if (allBricks.isEmpty()) {
							if (Round < maxRound) {
								nextRound(game);
							}
							if (Round == maxRound) {
								game.gameWon();
							}
							if (Round < maxRound) {
								game.nextRoundMessage();
							}
						}
					}
					for (int i = 0; i < safetyList.size(); i++) {
						SafetyNet aSafetyList = safetyList.get(i); // checks if the ball hits the safety net
						if (activeBall.getBounds().intersects(aSafetyList.getBounds())) {
							activeBall.setYa(activeBall.getYa() * -1);
							activeBall.setY(aSafetyList.getTopY() - Ball.DIAMETER - 1);
							aSafetyList.checkHits();


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
				frame.setTitle("Game Paused: Press space or P to continue");
				sideMenu.btnPause.setText("Resume Game");
			}
		}
	}
	
 
	/**
	 * This method checks for collisions with the sides of bricks and changes the course of the ball accordingly
	 * @param tempBrick The brick being compared for collision
	 * @param tempBall The ball being compared for collision
	 * @return boolean, true if it hits either the left or the right side
	 */
	private static boolean checkSideHits(Brick tempBrick, Ball tempBall){
		if (((tempBall.getBounds().getX()) >= (tempBrick.getBounds().getX() + tempBrick.getBounds().getWidth() - 2)) ){ // right side
			tempBall.setXa((tempBall.getXa() * (-1)));
			//tempBall.setYa((tempBall.getYa() * (-1)));
			tempBall.setX((int) (tempBrick.x + tempBrick.getBounds().getWidth()));
			return true;
		}else if (((tempBall.getBounds().getX() + Ball.DIAMETER) <= (tempBrick.getBounds().getX() + 2))){ // left side
			tempBall.setXa((tempBall.getXa() * (-1)));
			//tempBall.setYa((tempBall.getYa() * (-1)));
			tempBall.setX(tempBrick.x - Ball.DIAMETER);
			return true;
		}
		return false;
	}
	
	/**
	 * This method sets the colors of each brick according to its hit count
	 */
	static void colorBricks(){
		for (int i = 0; i < allBricks.size(); i++) {
			Brick allBrick = allBricks.get(i); //update the color for certain hit count
			if (allBrick.getHits() == 4) {
				allBrick.setColor(Color.BLACK);
			}
			if (allBrick.getHits() == 3) {
				allBrick.setColor(Color.BLUE);
			}
			if (allBrick.getHits() == 2) {
				allBrick.setColor(Color.GREEN);
			}
			if (allBrick.getHits() == 1) {
				allBrick.setColor(Color.YELLOW);
			}
		}
	}
	
	/**
	 * This checks for normal hits between a brick and the ball 
	 * and offsets the ball when such a hit occurs so the ball doesnt warp inside of a brick and cause issues
	 * @param tempBrick The Brick being compared for collision
	 * @param tempBall The Ball being compared for collision
	 */
	private static void checkNormalHits(Brick tempBrick, Ball tempBall){
		if(tempBall.getBounds().getY() <= (tempBrick.getBounds().getY() + 2) ){ // top
			tempBall.setY((int) (tempBrick.getBounds().getY() - Ball.DIAMETER));
		}else if(tempBall.getBounds().getY() >= (tempBrick.getBounds().getY() + tempBrick.getBounds().getHeight() - 2)){ //bottom
			tempBall.setY((int) (tempBrick.getBounds().getY() + tempBrick.getBounds().getHeight()));
		}
	}
	
	/**
	 * This is the powerhorse for generating powerups. Randomized powerup selection and creation
	 * @param currentBrick The brick that was hit, causing a powerup to be generated
	 * @return A randomly generated powerup, based on certain probabilities
	 */
	private Powerup generatePowerup(Brick currentBrick){
		int tempRandNum2 = probs.checkConditions(Probability.randInt(0,103)); // choose a power up based on conditions
		//int tempRandNum2 = 19; // choose a certain powerup
		boolean probsAns;
		switch(tempRandNum2){
			case 20:
				probsAns = probs.checkPowerup(20);
				//probsAns = true;
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Safety Net");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Safety Net", Color.GREEN);
				}
				return generatePowerup(currentBrick);
			case 19:
				probsAns = probs.checkPowerup(19);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Golden Borey");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Golden Borey", Color.GREEN);
				}
				return generatePowerup(currentBrick);
			case 18:
				probsAns = probs.checkPowerup(18);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Lose Points");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Lose Points", Color.RED);
				}
				return generatePowerup(currentBrick);

			case 17:
				probsAns = probs.checkPowerup(17);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Gain Extra Points");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Gain Extra Points", Color.GREEN);
				}
				return generatePowerup(currentBrick);

			case 16:
				probsAns = probs.checkPowerup(16);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Lose a Life");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Lose a Life", Color.RED);
				}
				return generatePowerup(currentBrick);

			case 15:
				probsAns = probs.checkPowerup(15);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Insanity Mode");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Insanity Mode", Color.RED);
				}
				return generatePowerup(currentBrick);

			case 14:
				probsAns = probs.checkPowerup(14);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Machine Gun");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Machine Gun", Color.GREEN);
				}
				return generatePowerup(currentBrick);

			case 13:
				probsAns = probs.checkPowerup(13);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Missile");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Missile", Color.GREEN);
				}
				return generatePowerup(currentBrick);

			case 12:
				probsAns = probs.checkPowerup(12);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Extra Life");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Extra Life", Color.GREEN);
				}
				return generatePowerup(currentBrick);

			case 11:
				probsAns = probs.checkPowerup(11);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Metal Ball");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Metal Ball", Color.GREEN);
				}
				return generatePowerup(currentBrick);

			case 10:
				probsAns = probs.checkPowerup(10);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Fireball");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Fireball", Color.GREEN);
				}
				return generatePowerup(currentBrick);

			case 9:
				probsAns = probs.checkPowerup(9);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Double Points");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Double Points", Color.GREEN);
				}
				return generatePowerup(currentBrick);

			case 8:
				probsAns = probs.checkPowerup(8);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Ball Decrease");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Ball Decrease", Color.WHITE);
				}
				return generatePowerup(currentBrick);

			case 7:
				probsAns = probs.checkPowerup(7);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Multiple Balls");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Multiple Balls", Color.GREEN);
				}
				return generatePowerup(currentBrick);

			case 6:
				probsAns = probs.checkPowerup(6);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Paddle Decrease");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Paddle Decrease", Color.WHITE);
				}
				return generatePowerup(currentBrick);

			case 5:
				probsAns = probs.checkPowerup(5);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Slow Down");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Slow Down", Color.WHITE);
				}
				return generatePowerup(currentBrick);

			case 4:
				probsAns = probs.checkPowerup(4);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Speed Up");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Speed Up", Color.WHITE);
				}
				return generatePowerup(currentBrick);

			case 3:
				probsAns = probs.checkPowerup(3);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Paddle Increase");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Paddle Increase", Color.GREEN);
					//Powerup powerup3 = new Powerup(this, 800, 5, 0, "Paddle Increase", Color.GREEN);
				}
				return generatePowerup(currentBrick);

			case 2:
				probsAns = probs.checkPowerup(2);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Ball Increase");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Ball Increase", Color.GREEN);
				}
				return generatePowerup(currentBrick);

			case 1:
				probsAns = probs.checkPowerup(1);
				if (probsAns){
					//System.out.println("Powerup Gained: " + "Magnet");
					return new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Magnet", Color.GREEN);
				}
				return generatePowerup(currentBrick);

			default:
				break;
	}
		return null;
	}
	
	/**
	 * hides a brick once it has been destroyed
	 * @param newbrick The brick to be destroyed
	 * @param saveBall The ball that dealt the final blow
	 */
	static void hideBrick(Brick newbrick, Ball saveBall){
		newbrick.getBounds().setBounds(-10, -10, 0, 0);
		newbrick.setAlive(false);
		newbrick = null;
	}

	/**
	 * returns the number of missiles the user has
	 * @return Missile Count
	 */
	int getMissileCount() {
		return missileCount;
	}

	/**
	 * sets the number of missiles the user has
	 * @param missileCount The number of missiles the user has
	 */
	void setMissileCount(int missileCount) {
		Game.missileCount = missileCount;
	}
	
	/**
	 * resets the game for the next round and loads the proper game board
	 * @param thisGame The current game
	 */
	static void nextRound(Game thisGame){
		hasSafetyNet = false;
		overwritePowerupLimits = false;
		probs.needsCollisions = false;
		Ball saveBall = activeBalls.get(0);
		saveBall.setX(thisGame.paddle.getBounds().x);
		saveBall.setY(thisGame.paddle.getBounds().y - 10);
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
		thisGame.paddle.setX(100);
		//activeBalls.clear();
		if (activeBalls.size()>1){ 
			activeBalls.clear();
			Ball newBall = new Ball(thisGame, 20, 320);
			//Ball newBall = new Ball(thisGame, thisGame.paddle.getBounds().x, thisGame.paddle.getBounds().y - 10);
			activeBalls.add(newBall);
		}
		activeBalls.add(saveBall);
		safetyList.clear();
		saveBall.speed = 2;
		saveBall.ballMods = 0;
		Ball.DIAMETER = 12;
		thisGame.paddle.setWIDTH(60);
		thisGame.paddle.racquetMods = 0;
		
		allBricks.clear();
		
		if (Round == 2){
			new Round2(TempGame);
			activeBalls.clear();
			activeBalls.add(saveBall);
		}else if (Round == 3){
			new Round3(TempGame);
			activeBalls.clear();
			activeBalls.add(saveBall);
		}else if (Round == 4){
			new Round4(TempGame);
			activeBalls.clear();
			activeBalls.add(saveBall);
		}else if (Round == 5){
			new Round5(TempGame);
			activeBalls.clear();
			activeBalls.add(saveBall);
		}else if (Round == 6){
			new Round6(TempGame);
			activeBalls.clear();
			activeBalls.add(saveBall);
		}
		colorBricks(); 
	}
}
