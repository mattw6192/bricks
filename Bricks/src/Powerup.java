import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;


public class Powerup {
	static final int DIAMETER = 10; //test
	int x = 0; // reverse test
	int y = 0;
	int xa = 0;
	int ya = 1;
	private Game game;
	int duration; // duration of the powerup
	String ability; // the name of the ability
	Boolean active; // is this powerup active
	Color FillColor; // Color of the powerup
	public static BufferedImage image;

	/**
	 * initializes the Powerup stats
	 * @param game
	 * @param X
	 * @param Y
	 * @param time
	 * @param powerup
	 * @param color
	 */
	public Powerup(Game game, int X, int Y, int time, String powerup, Color color) {
		this.game = game;
		x = X;
		y = Y;
		active = false;
		duration = time;
		ability = powerup;
		setXa(0);
		FillColor = color;
		try {
			image = ImageIO.read((getClass().getResource("/images/skull_and_bones2.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}

	
	/**
	 * extra controls for the actions of the Fireball powerup
	 * @param timeDelay
	 */
	public void Fireball(int timeDelay){
		Game.hasFireball = true;
		//powerupEnd(); // remove the powerup from available powerups display
		game.droppedPowerups.remove(this);
		for (int i=0; i<Game.activeBalls.size();i++){
			final Ball saveBall = Game.activeBalls.get(i);
			
			ActionListener flashTask = new ActionListener() {
				public void actionPerformed(ActionEvent evt){ //controls the flashing of the fireball powerup
					for (int i=0; i<Game.activeBalls.size();i++){
						final Ball saveBall = Game.activeBalls.get(i);
					boolean hasExecuted = false; 
					if (Game.hasFireball.equals(false)){
					saveBall.setColor(Color.BLACK); hasExecuted = true;}
					if (saveBall.getColor().equals(Color.BLACK) && hasExecuted == false){
						saveBall.setColor(Color.RED);}
					else if (saveBall.getColor().equals(Color.RED)){
						saveBall.setColor(Color.YELLOW);}
					else if (saveBall.getColor().equals(Color.YELLOW)){
						saveBall.setColor(Color.RED);
					}
					}
				}
			};
			
			Timer flashTimer = new Timer(200,flashTask);
			flashTimer.setRepeats(true);
			flashTimer.start();
			//if (!flashTimer.isRunning()){flashTimer.restart();}
			if (Game.hasFireball.equals(false)){flashTimer.stop();
			saveBall.setColor(Color.BLACK);}
			
			
			//int delay3 = 20000; //milliseconds
			ActionListener taskPerformer3 = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					Game.hasFireball = false;
			        System.out.println("Powerup Ended");
			        saveBall.setColor(Color.BLACK);
			        powerupEnd();
			    }
			};
			Timer timer3 = new Timer(timeDelay, taskPerformer3);
			timer3.setRepeats(false);
			timer3.start();
			saveBall.setColor(Color.BLACK);
		}
	}
	
	/**
	 * extra controls for the actions of the multiball powerup
	 * @param timeDelay
	 * @param directionMultiplier
	 */
	public void multiBall(int timeDelay, double directionMultiplier){
		if (Game.activeBalls.size() <= 5){
			System.out.println("Multiple Balls Enabled");
			Ball extraBall1 = new Ball(game, game.activeBalls.get(0).getBounds().x + 5, game.activeBalls.get(0).getBounds().y - 10);
			extraBall1.setXa(extraBall1.getXa() * directionMultiplier);
			extraBall1.setYa(-(extraBall1.getYa() * directionMultiplier));
			extraBall1.speed = game.ball.speed;
			extraBall1.DIAMETER = game.ball.DIAMETER;
			extraBall1.ballMods = game.ball.ballMods;
			Ball extraBall2 = new Ball(game, game.activeBalls.get(0).getBounds().x + 5, game.activeBalls.get(0).getBounds().y - 10);
			extraBall2.setXa((extraBall1.getXa() * directionMultiplier));
			extraBall2.setYa(-(extraBall1.getYa() * directionMultiplier));
			extraBall2.speed = game.ball.speed;
			extraBall2.DIAMETER = game.ball.DIAMETER;
			extraBall2.ballMods = game.ball.ballMods;
			Game.activeBalls.add(extraBall1);
			Game.activeBalls.add(extraBall2);
			powerupEnd(); // remove the powerup from available powerups display
			
			
			ActionListener multipleBallsDisplay = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					System.out.println("Powerup Ended");
					
				}
			};
			//int multBallDisplayDuration = 3000; // how long the powerup will be displayed on the title
			Timer timerMultBalls = new Timer(timeDelay, multipleBallsDisplay);
			timerMultBalls.setRepeats(false);
			timerMultBalls.start();
			
		}
		
		else{
			game.droppedPowerups.remove(this);
			game.activePowerups.remove(this);
			System.out.println("Powerup Not Enabled!: Too many balls currently in play.");
		}
	}
	
	/**
	 * interprets the powerup number (RND generated) and performs that action
	 * @param caseNumber
	 */
	public void performAction(int caseNumber){
		if (active == true){
			switch(caseNumber){
			case 20: // Safety Net
				if (game.hasSafetyNet == false){
					game.hasSafetyNet = true;
					SafetyNet safeNet = new SafetyNet(game, this);
					game.safetyList.add(safeNet);
					
				}else{
					game.activePowerups.remove(this);
				}
				game.droppedPowerups.remove(this);
				//powerupEnd();
				break;
			case 19: // Golden Borey
				if (game.hasBoreyMode == false){	
					game.hasBoreyMode = true;
					game.paddle.setRacquetMods(4);
					game.paddle.setWIDTH(150);
					game.hasGun = true;
					multiBall(3000,1);
					multiBall(3000,0.5);
					Fireball(45000);
					game.droppedPowerups.remove(this);
					for (int i=0; i< Game.activeBalls.size(); i++){	
							Game.activeBalls.get(i).DIAMETER = 11;
							Game.activeBalls.get(i).setBallMods(1);
							Game.activeBalls.get(i).speed = 2;
					}	
						
					// Action Listener
					ActionListener boreyMode = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("Powerup Over");
							game.paddle.setRacquetMods(0);
							game.paddle.setWIDTH(60);
							game.hasBoreyMode = false;
							for (int i=0; i< Game.activeBalls.size(); i++){	
								Game.activeBalls.get(i).DIAMETER = 10;
								Game.activeBalls.get(i).setBallMods(0);
								Game.activeBalls.get(i).speed = 2;
								Game.pointMultiplier = 1;
								game.activePowerups.remove(this);
						}
							powerupEnd(); // remove the powerup from available powerups display
							game.activePowerups.remove(this);
						}
					};
					int boreyDuration = 20000; //milliseconds
					Timer boreyTimer = new Timer(boreyDuration, boreyMode);
					boreyTimer.setRepeats(false);
					boreyTimer.start();
				}else{
					game.activePowerups.remove(this);
				}
				game.droppedPowerups.remove(this);
				break; 
			case 18: // Lose Points
				int rand = Game.probs.randInt(0,10);
				if (rand <= 3){ // Removes 1% of points
					game.Score -= game.Score * 0.1;
				}else if (rand <= 6){ // removes 5% of points
					game.Score -= game.Score * 0.5;
				}else if (rand <= 9){ // removes 10% of points
					game.Score -= game.Score * 0.10;
				}else if (rand == 10){ // removes 25% of points
					game.Score -= game.Score * 0.25;
				}
				powerupEnd();
				break;
			case 17: // Gain Extra Points
				int randomNum = Game.probs.randInt(0,10);
				if (randomNum <= 5){ // add 1,000 points
					game.Score += 1000;
				}else if (randomNum <= 8){ // add 2,500 points
					game.Score += 2500;
				}else if (randomNum <= 9){ // add 5,000 points
					game.Score += 5000;
				}else if (randomNum == 10){ // add 7,500 points
					game.Score += 7500;
				}
				powerupEnd();
				break;
			case 16: // Lose a life
				if (game.Lives > 1){
					System.out.println("Lives  "  + (game.Lives - 1));
					game.Lives -= 1;
					//String tempLifeString = Game.getLifeString();
					int lifeStringLength = Game.getLifeString().length();
					Game.setLifeString(Game.getLifeString().substring(0, lifeStringLength -1)); //Removes a life to the lifestring
					game.droppedPowerups.remove(this);
					int delayLifeDisplay = 10; //milliseconds
					ActionListener lifeDisplayOff = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
					        System.out.println("Powerup Ended");
					        powerupEnd(); // remove the powerup from available powerups display
					    }
					};
					Timer timeLifeDisplay = new Timer(delayLifeDisplay, lifeDisplayOff);
					timeLifeDisplay.setRepeats(false);
					timeLifeDisplay.start();
					
				}else{
					game.droppedPowerups.remove(this);
					game.activePowerups.remove(this);
					System.out.println("The Minimum Limit of lives has been reached");
				}
				powerupEnd();
				break;
			case 15: // Insanity Mode
				game.hasInsanityMode = true;
				game.paddle.setRacquetMods(-4);
				game.paddle.setWIDTH(24);
				game.hasFireball = false;
				game.hasMetalPower = false;
				game.hasGun = false;
				if (game.pointMultiplier <= 4){
					game.pointMultiplier = game.pointMultiplier  * 2;
				}
				System.out.println("Insanity Mode - Good Luck!! ");
				game.droppedPowerups.remove(this);
				for (int i=0; i< Game.activeBalls.size(); i++){	
						Game.activeBalls.get(i).DIAMETER = 9;
						Game.activeBalls.get(i).setBallMods(-1);
						Game.activeBalls.get(i).speed = 4.0;
						game.started = false;
						
				}	
						//hasBallMods = true;
					
				// Action Listener
				ActionListener insanityMode = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("Amazing! You Survived!!! - 10,000 Points!!!");
						game.Score += 10000;
						game.hasInsanityMode = false;
						game.paddle.setRacquetMods(0);
						game.paddle.setWIDTH(60);
						for (int i=0; i< Game.activeBalls.size(); i++){	
							Game.activeBalls.get(i).DIAMETER = 10;
							Game.activeBalls.get(i).setBallMods(0);
							Game.activeBalls.get(i).speed = 2;
							Game.activeBalls.get(i).setX((int) game.paddle.getBounds().getX() + (int) (game.paddle.getBounds().getWidth() / 2));
							Game.activeBalls.get(i).setY((int) game.paddle.getBounds().getY() - (int) (game.activeBalls.get(i).DIAMETER));
							Game.pointMultiplier = 1;
					}
						powerupEnd(); // remove the powerup from available powerups display
						game.activePowerups.remove(this);
					}
				};
				int DelayDuration = 60000; //milliseconds
				Timer insanityTimer = new Timer(DelayDuration, insanityMode);
				insanityTimer.setRepeats(false);
				insanityTimer.start();
				break; 
			case 14: // Machine Gun
				game.droppedPowerups.remove(this);
				if (Game.hasGun == false){
					Game.hasGun = true;
					int delayShot = 250;
					int timeForGun = 10000;
					Game.bullets.clear();
					
					ActionListener shootGun = new ActionListener(){
						public void actionPerformed(ActionEvent evt){
							if (Game.hasGun == true){
								MachineGun mgLeft = new MachineGun(game,game.paddle.getX(),game.paddle.getTopY());
								MachineGun mgRight = new MachineGun(game,game.paddle.getX() + game.paddle.getWIDTH() - 10,game.paddle.getTopY());
								Game.bullets.add(mgLeft);
								Game.bullets.add(mgRight);
								Sound.Play(Sound.machineGun);
							}
						}
					};
					final Timer timeShot = new Timer(delayShot, shootGun);
					timeShot.setRepeats(true);
					timeShot.start();
					
					ActionListener endGun = new ActionListener(){
						public void actionPerformed(ActionEvent evt){
							Game.hasGun = false;
							timeShot.stop();
							game.activePowerups.remove(this);
							powerupEnd();
						}
					};
					
					Timer gunTime = new Timer(timeForGun, endGun);
					gunTime.setRepeats(false);
					gunTime.start();
				}else{
					game.activePowerups.remove(this);
				}
				break;
				
				
			case 13: // Missiles
				if (Game.missileCount <= 4){
					Missile m = new Missile(game,game.paddle.getX(),game.paddle.getTopY());
					Game.missiles.add(m);
					game.setMissileCount(game.getMissileCount() + 1);
					game.droppedPowerups.remove(this);
					powerupEnd();
				}else{
					game.droppedPowerups.remove(this);
					game.activePowerups.remove(this);
					powerupEnd();
					System.out.println("You have reached the maximum number of missiles.");
				}
				break;
				
			case 12: // Extra Life
				if (game.Lives <= 5){
					System.out.println("Lives  "  + (game.Lives + 1));
					game.Lives += 1;
					Game.setLifeString(Game.getLifeString()+"*"); //adds a life to the lifestring
					game.droppedPowerups.remove(this);
					int delayLifeDisplay = 10; //milliseconds
					ActionListener lifeDisplayOff = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
					        System.out.println("Powerup Ended");
					        powerupEnd(); // remove the powerup from available powerups display
					    }
					};
					Timer timeLifeDisplay = new Timer(delayLifeDisplay, lifeDisplayOff);
					timeLifeDisplay.setRepeats(false);
					timeLifeDisplay.start();
					
				}else{
					game.droppedPowerups.remove(this);
					game.activePowerups.remove(this);
					System.out.println("The Maximum Limit of lives has been reached");
				}
				powerupEnd();
				break;
				
			case 11: // Metal Ball - deals two hits
				System.out.println("Metalball Activated");
				Game.droppedPowerups.remove(this);
				if (Game.hasFireball == false){
					Game.hasMetalPower = true;
					powerupEnd(); // remove the powerup from available powerups display
					for (int i=0; i<Game.activeBalls.size(); i++){
						Game.activeBalls.get(i).setColor(Color.lightGray);
						final Ball saveBall = Game.activeBalls.get(i);
						saveBall.setColor(Color.LIGHT_GRAY);
						int delay4 = 30000; //milliseconds
						ActionListener taskPerformer4 = new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								Game.hasMetalPower = false;
								saveBall.setColor(Color.BLACK);
						        System.out.println("Powerup Ended");
						        game.activePowerups.remove(this);
						        powerupEnd();
						    }
						};
						Timer timer4 = new Timer(delay4, taskPerformer4);
						timer4.setRepeats(false);
						timer4.start();
					}	
				}else{
					game.activePowerups.remove(this);
				}
				break;
				
			case 10: // Fireball - destroys any block one hit
				System.out.println("Fireball Activated");
				Fireball(20000);
				break; 
				
			case 9: // Double Points
				System.out.println("Current Multiplier "  + Game.pointMultiplier);
				game.droppedPowerups.remove(this);
				if (Game.pointMultiplier <= 4){
					Game.pointMultiplier = Game.pointMultiplier * 2;
					System.out.println("Double Points - New Multiplier "  + Game.pointMultiplier);
					int delay2 = 30000; //milliseconds
					//powerupEnd(); // remove the powerup from available powerups display

					
					// End the powerup after the timer finishes
					ActionListener taskPerformer2 = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (Game.pointMultiplier > 1){
								Game.pointMultiplier = Game.pointMultiplier / 2;
							}
							System.out.println("Powerup Ended");
							game.activePowerups.remove(this);
							powerupEnd();
						}
					};

					Timer timer2 = new Timer(delay2, taskPerformer2);
					timer2.setRepeats(false);
					timer2.start();
					
				}else{
					powerupEnd();
					game.activePowerups.remove(this);
					System.out.println("Powerup Not Activated: Current Multiplier is Maxed Out");
				}
				
				break; 
				
			case 8: // Smaller Ball
				//boolean hasBallMods = false;
				for (int i=0; i< Game.activeBalls.size(); i++){	
					if (Game.activeBalls.get(i).ballMods >= -1){
							Game.activeBalls.get(i).DIAMETER = (int) (Game.activeBalls.get(i).DIAMETER * .8);
							Game.activeBalls.get(i).SubtractBallMod();
							System.out.println("Ball Decrease - New level "  + Game.activeBalls.get(i).ballMods + " DIAMETER " + Game.activeBalls.get(i).DIAMETER);

							//hasBallMods = true;
							
							// Action Listener
							ActionListener smallBallStop = new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out.println("Powerup Ended");
									powerupEnd(); // remove the powerup from available powerups display
								}
							};

							int delaySmallBall = 10; //milliseconds
							Timer timerSmallBall = new Timer(delaySmallBall, smallBallStop);
							timerSmallBall.setRepeats(false);
							timerSmallBall.start();

					}
					else{
						game.droppedPowerups.remove(this);
						powerupEnd();
						System.out.println("Powerup not available - the ball size is too low.");
					}
				}
				powerupEnd();
				break; 
				
			case 7: // Multiple Balls
				multiBall(3000,1);
				break; 
				
			case 6: // Smaller Paddle
				if (game.paddle.racquetMods >= -3){
					game.paddle.setWIDTH((int) (game.paddle.getWIDTH() * .8));
					game.paddle.SubtractRacquetMod();
					System.out.println("Paddle Decrease - New level "  + game.paddle.racquetMods + " WIDTH " + game.paddle.getWIDTH());
					powerupEnd(); // remove the powerup from available powerups display
					
					
					ActionListener smallPaddleDisplay = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("Powerup Ended");
							powerupEnd();
						}
					};
					int smallPaddleDisplayDuration = 3000; // milliseconds
					Timer smallRacquetTimer = new Timer(smallPaddleDisplayDuration, smallPaddleDisplay);
					smallRacquetTimer.setRepeats(false);
					smallRacquetTimer.start();
					
				}
				else{
					powerupEnd();
					game.droppedPowerups.remove(this);
					System.out.println("Powerup not available - the paddle size is too low.");
				}
		
				break; 
				
			case 5: // Slow Down
				
				for (int i=0; i < Game.activeBalls.size(); i++){
					if (Game.activeBalls.get(i).speed >= 1){
						Game.activeBalls.get(i).speed -= 0.5;
						System.out.println("Speed Down - game speed " + Game.activeBalls.get(i).speed);
						
						
					}else{
						
						System.out.println("Powerup not available - the ball speed is too slow.");
					}
				}
				powerupEnd(); // remove the powerup from available powerups display
				
				ActionListener slowDownDisplayEnd = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("Powerup Ended");	
					}
				};
				int slowDownDisplayDuration = 3000; // milliseconds
				Timer slowDownDisplay = new Timer(slowDownDisplayDuration, slowDownDisplayEnd);
				slowDownDisplay.setRepeats(false);
				slowDownDisplay.start();
				
				break; 
				
			case 4: // Speed Up
				for (int i=0; i< Game.activeBalls.size();i++){
					if (Game.activeBalls.get(i).speed < 3.5){
						Game.activeBalls.get(i).speed += 0.5;
						System.out.println("Speed Up - game speed " + Game.activeBalls.get(i).speed);
					}else{
						System.out.println("Powerup not available - the ball speed is too high.");
					}
				}
				powerupEnd(); // remove the powerup from available powerups display
				
				ActionListener speedUpDisplayEnd = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("Powerup Ended");	
					}
				};
				int speedUpDisplayDuration = 3000; // milliseconds
				Timer speedUpDisplay = new Timer(speedUpDisplayDuration, speedUpDisplayEnd);
				speedUpDisplay.setRepeats(false);
				speedUpDisplay.start();
				
				break; 
				
			case 3: // Larger Paddle
				
				if (game.paddle.racquetMods < 7){
					game.paddle.increaseSize();
					powerupEnd(); // remove the powerup from available powerups display
				}else{
					game.droppedPowerups.remove(this);
					System.out.println("Powerup not available - the paddle size is too high.");
				}
				
				ActionListener largerPaddleDisplayEnd = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("Powerup Ended");	
					}
				};
				int largerPaddleDisplayDuration = 3000; // milliseconds
				Timer largerPaddleDisplay = new Timer(largerPaddleDisplayDuration, largerPaddleDisplayEnd);
				largerPaddleDisplay.setRepeats(false);
				largerPaddleDisplay.start();
				powerupEnd();
				break; 
			case 2: // Larger Ball
				
				for (int i=0; i<Game.activeBalls.size(); i++){	
					if (Game.activeBalls.get(i).ballMods < 5){
						Game.activeBalls.get(i).DIAMETER = (int) (Game.activeBalls.get(i).DIAMETER * 1.25) ;
						Game.activeBalls.get(i).addBallMod();
						System.out.println("Ball Increase - New level "  + Game.activeBalls.get(i).ballMods);
					}else{
						System.out.println("Powerup not available - the ball size is too high.");
					}
				}
				powerupEnd(); // remove the powerup from available powerups display

				ActionListener largerBallDisplayEnd = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("Powerup Ended");
						
					}
				};
				int largerBallDisplayDuration = 3000; // milliseconds
				Timer largerBallDisplay = new Timer(largerBallDisplayDuration, largerBallDisplayEnd);
				largerBallDisplay.setRepeats(false);
				largerBallDisplay.start();
				

				break; 
			case 1: // Magnet
				game.hasMagnet = true;
				int delay = 30000; // milliseconds
				game.droppedPowerups.remove(this);
				//powerupEnd(); // remove the powerup from available powerups display
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						game.hold = false;
						game.hasMagnet = true;
				        System.out.println("Powerup Ended");
				        game.activePowerups.remove(this);
				        powerupEnd();
				    }
				};
				Timer timer = new Timer(delay, taskPerformer);
				timer.setRepeats(false);
				timer.start();
				game.hold = true;
				

				break; 
		}
	}
	}
	
	/**
	 * sets the powerup status as true/false to state if it is active or not
	 * @param status
	 */
	public void setActive(Boolean status){
		active = status;
		setXa(0);
		setYa(0);
		//System.out.println("Powerup Activated");
	}
	
	/**
	 * returns the powerup name
	 */
	@Override
	public String toString(){
		return this.ability;
	}
	
	/**
	 * ends the powerup
	 */
	public void powerupEnd(){
		active = false;
		Game.droppedPowerups.remove(this);
		Game.activePowerups.remove(this);
	}
	
	/**
	 * returns a number based on the name of the powerup
	 * @return
	 */
	public int getPowerNum(){
		if (ability.equals("Magnet")){return 1;};
		if (ability.equals("Ball Increase")){return 2;};
		if (ability.equals("Paddle Increase")){return 3;};
		if (ability.equals("Speed Up")){return 4;};
		if (ability.equals("Slow Down")){return 5;};
		if (ability.equals("Paddle Decrease")){return 6;};
		if (ability.equals("Multiple Balls")){return 7;};
		if (ability.equals("Ball Decrease")){return 8;};
		if (ability.equals("Double Points")){return 9;};
		if (ability.equals("Fireball")){return 10;};
		if (ability.equals("Metal Ball")){return 11;};
		if (ability.equals("Extra Life")){return 12;}
		if (ability.equals("Missile")){return 13;}
		if (ability.equals("Machine Gun")){return 14;}
		if (ability.equals("Insanity Mode")){return 15;}
		if (ability.equals("Lose a Life")){return 16;}
		if (ability.equals("Gain Extra Points")){return 17;}
		if (ability.equals("Lose Points")){return 18;}
		if (ability.equals("Golden Borey")){return 19;}
		if (ability.equals("Safety Net")){return 20;}
		return 0;
	}
	
	/**
	 * controls the movement of the powerup
	 */
	void move() {
		
		
		if (y + ya > game.getHeight() - DIAMETER){
			//System.out.println("Powerup Lost!");
			powerupEnd();
		}
		if (collision()){
			// These conditionals check for collisions with the side of the paddle -- If such a collision occurs, the ball completely reverses
			// This conditional check for collisions with the right side of the paddle
			game.activePowerups.add(this);
			Sound.Play(Sound.Powerup);
			setActive(true);
			setXa(0);
			setYa(0);
			setX(-100); // removes the powerup from the playing field
			setY(-100); // removes the powerup from the playing field
			int caseNumber = getPowerNum();
			performAction(caseNumber);
		}
		x = x + xa;
		y = y + ya;
	}

	/**
	 * returns the X coordinate
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * sets the X coordinate
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * returns the Y coordinate
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * sets the Y coordinate
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * returns the delta (change) of the X coordinate
	 * @return
	 */
	public int getXa() {
		return xa;
	}

	/**
	 * sets the delta (change) of the X coordinate
	 * @param xa
	 */
	public void setXa(int xa) {
		this.xa = xa;
	}

	/**
	 * returns the delta (change) of the Y coordinate
	 * @return
	 */
	public int getYa() {
		return ya;
	}

	/**
	 * sets the delta (change) of the Y coordinate
	 * @param ya
	 */
	public void setYa(int ya) {
		this.ya = ya;
	}

	/**
	 * checks for collisions with the paddle
	 * @return
	 */
	private boolean collision() {
		return game.paddle.getBounds().intersects(getBounds());
	}

	/**
	 * paints the powerup on the screen
	 * @param g
	 */
	public void paint(Graphics2D g) {
		if (ability == "Insanity Mode"){
			g.drawImage(image, x, y, null);
		}else{
			g.setColor(FillColor);
			g.fillRect(x, y, DIAMETER, DIAMETER);
		
		}
	}
	
	/**
	 * returns the bounds of the powerup
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
