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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

	public void performAction(int caseNumber){
		if (active == true){
			switch(caseNumber){

			case 15: // Insanity Mode
				game.racquet.setRacquetMods(-4);
				game.racquet.setWIDTH(24);
				game.hasFireball = false;
				game.hasMetalPower = false;
				game.hasGun = false;
				System.out.println("Insanity Mode - Good Luck!! ");
				game.placeHolder.remove(this);
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
						game.racquet.setRacquetMods(0);
						game.racquet.setWIDTH(60);
						for (int i=0; i< Game.activeBalls.size(); i++){	
							Game.activeBalls.get(i).DIAMETER = 10;
							Game.activeBalls.get(i).setBallMods(0);
							Game.activeBalls.get(i).speed = 2;
							Game.activeBalls.get(i).setX((int) game.racquet.getBounds().getX() + (int) (game.racquet.getBounds().getWidth() / 2));
							Game.activeBalls.get(i).setY((int) game.racquet.getBounds().getY() - (int) (game.activeBalls.get(i).DIAMETER));
					}
						powerupEnd(); // remove the powerup from available powerups display
					}
				};
				int DelayDuration = 60000; //milliseconds
				Timer insanityTimer = new Timer(DelayDuration, insanityMode);
				insanityTimer.setRepeats(false);
				insanityTimer.start();
				break; 
			case 14: // Machine Gun
				game.placeHolder.remove(this);
				if (Game.hasGun == false){
					Game.hasGun = true;
					int delayShot = 250;
					int timeForGun = 10000;
					Game.bullets.clear();
					
					ActionListener shootGun = new ActionListener(){
						public void actionPerformed(ActionEvent evt){
							if (Game.hasGun == true){
								MachineGun mgLeft = new MachineGun(game,game.racquet.getX(),game.racquet.getTopY());
								MachineGun mgRight = new MachineGun(game,game.racquet.getX() + game.racquet.getWIDTH() - 10,game.racquet.getTopY());
								Game.bullets.add(mgLeft);
								Game.bullets.add(mgRight);
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
						}
					};
					
					Timer gunTime = new Timer(timeForGun, endGun);
					gunTime.setRepeats(false);
					gunTime.start();
				}
				break;
				
				
			case 13: // Missiles
				if (Game.missileCount <= 4){
					Missile m = new Missile(game,game.racquet.getX(),game.racquet.getTopY());
					Game.missiles.add(m);
					game.setMissileCount(game.getMissileCount() + 1);
					game.placeHolder.remove(m);
					powerupEnd();
				}else{
					System.out.println("You have reached the maximum number of missiles.");
				}
				break;
				
			case 12: // Extra Life
				if (game.Lives <= 5){
					System.out.println("Lives  "  + (game.Lives + 1));
					game.Lives += 1;
					Game.setLifeString(Game.getLifeString()+"*"); //adds a life to the lifestring
					
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
					System.out.println("The Maximum Limit of lives has been reached");
				}
				break;
				
			case 11: // Metal Ball - deals two hits
				System.out.println("Metalball Activated");
				
				if (Game.hasFireball == false){
					Game.hasMetalPower = true;
					powerupEnd(); // remove the powerup from available powerups display
					for (int i=0; i<Game.activeBalls.size(); i++){
						Game.activeBalls.get(i).setColor(Color.lightGray);
						final Ball saveBall = Game.activeBalls.get(i);
						int delay4 = 30000; //milliseconds
						ActionListener taskPerformer4 = new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								Game.hasMetalPower = false;
								saveBall.setColor(Color.BLACK);
						        System.out.println("Powerup Ended");
						    }
						};
						Timer timer4 = new Timer(delay4, taskPerformer4);
						timer4.setRepeats(false);
						timer4.start();
					}	
				}
				break;
				
			case 10: // Fireball - destroys any block one hit
				System.out.println("Fireball Activated");
				Game.hasFireball = true;
				powerupEnd(); // remove the powerup from available powerups display
				
				for (int i=0; i<Game.activeBalls.size();i++){
					final Ball saveBall = Game.activeBalls.get(i);
					
					ActionListener flashTask = new ActionListener() {
						public void actionPerformed(ActionEvent evt){ //controls the flashing of the fireball powerup
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
					};
					
					Timer flashTimer = new Timer(200,flashTask);
					flashTimer.setRepeats(true);
					flashTimer.start();
					//if (!flashTimer.isRunning()){flashTimer.restart();}
					if (Game.hasFireball.equals(false)){flashTimer.stop();
					saveBall.setColor(Color.BLACK);}
					
					
					int delay3 = 20000; //milliseconds
					ActionListener taskPerformer3 = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							Game.hasFireball = false;
					        System.out.println("Powerup Ended");
					        saveBall.setColor(Color.BLACK);
					    }
					};
					Timer timer3 = new Timer(delay3, taskPerformer3);
					timer3.setRepeats(false);
					timer3.start();
					saveBall.setColor(Color.BLACK);
				}
				break; 
				
			case 9: // Double Points
				System.out.println("Current Multiplier "  + Game.pointMultiplier);
				if (Game.pointMultiplier <= 4){
					Game.pointMultiplier = Game.pointMultiplier * 2;
					System.out.println("Double Points - New Multiplier "  + Game.pointMultiplier);
					int delay2 = 30000; //milliseconds
					powerupEnd(); // remove the powerup from available powerups display

					
					// End the powerup after the timer finishes
					ActionListener taskPerformer2 = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (Game.pointMultiplier > 1){
								Game.pointMultiplier = Game.pointMultiplier / 2;
							}
							System.out.println("Powerup Ended");
						}
					};

					Timer timer2 = new Timer(delay2, taskPerformer2);
					timer2.setRepeats(false);
					timer2.start();
					
				}else{
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
							System.out.println("Powerup not available - the ball size is too low.");
						}
				}
				break; 
				
			case 7: // Multiple Balls
				if (Game.activeBalls.size() <= 5){
					System.out.println("Multiple Balls Enabled");
					Ball extraBall1 = new Ball(game, game.activeBalls.get(0).getBounds().x + 5, game.activeBalls.get(0).getBounds().y - 10);
					extraBall1.setXa(1);
					extraBall1.setYa(-1);
					extraBall1.speed = game.ball.speed;
					extraBall1.DIAMETER = game.ball.DIAMETER;
					extraBall1.ballMods = game.ball.ballMods;
					Ball extraBall2 = new Ball(game, game.activeBalls.get(0).getBounds().x + 5, game.activeBalls.get(0).getBounds().y - 10);
					extraBall2.setXa(-1);
					extraBall2.setYa(-1);
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
					int multBallDisplayDuration = 3000; // how long the powerup will be displayed on the title
					Timer timerMultBalls = new Timer(multBallDisplayDuration, multipleBallsDisplay);
					timerMultBalls.setRepeats(false);
					timerMultBalls.start();
					
				}
				
				else{
					System.out.println("Powerup Not Enabled!: Too many balls currently in play.");
				}
				
				break; 
				
			case 6: // Smaller Paddle
				if (game.racquet.racquetMods >= -3){
					game.racquet.setWIDTH((int) (game.racquet.getWIDTH() * .8));
					game.racquet.SubtractRacquetMod();
					System.out.println("Racquet Decrease - New level "  + game.racquet.racquetMods + " WIDTH " + game.racquet.getWIDTH());
					powerupEnd(); // remove the powerup from available powerups display
					
					
					ActionListener smallPaddleDisplay = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("Powerup Ended");
							
						}
					};
					int smallPaddleDisplayDuration = 3000; // milliseconds
					Timer smallRacquetTimer = new Timer(smallPaddleDisplayDuration, smallPaddleDisplay);
					smallRacquetTimer.setRepeats(false);
					smallRacquetTimer.start();
					
				}
				else{
					System.out.println("Powerup not available - the racquet size is too low.");
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
				
				if (game.racquet.racquetMods < 7){
					game.racquet.setWIDTH((int) (game.racquet.getWIDTH() * 1.25));
					game.racquet.addRacquetMod();
					powerupEnd(); // remove the powerup from available powerups display
					System.out.println("Racquet Increase - New level "  + game.racquet.racquetMods);
				}else{
					System.out.println("Powerup not available - the racquet size is too high.");
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
				int delay = 30000; // milliseconds
				powerupEnd(); // remove the powerup from available powerups display
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						game.hold = false;
				        System.out.println("Powerup Ended");
				        
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
	
	public void setActive(Boolean status){
		active = status;
		setXa(0);
		setYa(0);
		//System.out.println("Powerup Activated");
	}
	
	@Override
	public String toString(){
		return this.ability;
	}
	
	public void powerupEnd(){
		active = false;
		Game.placeHolder.remove(this);
	}
	
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
		return 0;
	}
	
	void move() {
		
		
		if (y + ya > game.getHeight() - DIAMETER){
			//System.out.println("Powerup Lost!");
			powerupEnd();
		}
		if (collision()){
			// These conditionals check for collisions with the side of the racquet -- If such a collision occurs, the ball completely reverses
			// This conditional check for collisions with the right side of the racquet
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
		return game.racquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		if (ability == "Insanity Mode"){
			g.drawImage(image, x, y, null);
		}else{
			g.setColor(FillColor);
			g.fillRect(x, y, DIAMETER, DIAMETER);
		
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
