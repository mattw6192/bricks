import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public Powerup(Game game, int X, int Y, int time, String powerup) {
		this.game = game;
		x = X;
		y = Y;
		active = false;
		duration = time;
		ability = powerup;
		setXa(0);
		 
	}

	public void performAction(int caseNumber){
		if (active == true){
			switch(caseNumber){
			case 13: // Missiles
				Missile m = new Missile(game,game.racquet.getBounds().x + 5, game.racquet.getBounds().y - 10);
				Game.missiles.add(m);
				Game.setHasPowerup(true);
				break;
			case 12: // Extra Life
				if (game.Lives <= 5){
					System.out.println("Lives  "  + (game.Lives + 1));
					game.Lives += 1;
					Game.setLifeString(Game.getLifeString()+"*"); //adds a life to the lifestring
					Game.setHasPowerup(true);
					
					int delayLifeDisplay = 3000; //milliseconds
					ActionListener lifeDisplayOff = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							Game.setHasPowerup(false); // set hasPowerup to false to display current powerup as false
					        System.out.println("Powerup Ended");
					    }
					};
					Timer timeLifeDisplay = new Timer(delayLifeDisplay, lifeDisplayOff);
					timeLifeDisplay.setRepeats(false);
					timeLifeDisplay.start();
					
				}else{
					System.out.println("The Maximum Limit of lives has been reached");
				}
				
				break;
				//powerup display finished
			case 11: // Metal Ball - deals two hits
				System.out.println("Metalball Activated");
				
				if (Game.hasFireball == false){
					Game.hasMetalPower = true;
					Game.setHasPowerup(true); // set hasPowerup to true to display current powerup on jframe title
					for (int i=0; i<Game.activeBalls.size(); i++){
						Game.activeBalls.get(i).setColor(Color.lightGray);
						final Ball saveBall = Game.activeBalls.get(i);
						int delay4 = 30000; //milliseconds
						ActionListener taskPerformer4 = new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								Game.hasMetalPower = false;
								Game.setHasPowerup(false); // set hasPowerup to false to display current powerup as false
								saveBall.setColor(Color.BLACK);
						        System.out.println("Powerup Ended");
						    }
						};
						Timer timer4 = new Timer(delay4, taskPerformer4);
						timer4.setRepeats(false);
						timer4.start();
						
					}
				}
				//powerup display finished
				break; 
			case 10: // Fireball - destroys any block one hit
				System.out.println("Fireball Activated");
				Game.hasFireball = true;
				Game.setHasPowerup(true); // set hasPowerup to true to display current powerup on jframe title
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
							Game.setHasPowerup(false); // set hasPowerup to false to display current powerup as none
					        saveBall.setColor(Color.BLACK);
					        Game.setHasPowerup(false); // set powerup display back to None.
					       
					    }
					};
					Timer timer3 = new Timer(delay3, taskPerformer3);
					timer3.setRepeats(false);
					timer3.start();
					
					
					saveBall.setColor(Color.BLACK);
				}
				//powerup display finished
				break; 
			case 9: // Double Points
				System.out.println("Current Multiplier "  + Game.pointMultiplier);
				if (Game.pointMultiplier <= 4){
					Game.pointMultiplier = Game.pointMultiplier * 2;
					System.out.println("Double Points - New Multiplier "  + Game.pointMultiplier);
					int delay2 = 30000; //milliseconds
					Game.setHasPowerup(true); // set hasPowerup to true to display current powerup on jframe title
					ActionListener taskPerformer2 = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (Game.pointMultiplier > 1){
								Game.pointMultiplier = Game.pointMultiplier / 2;
								Game.setHasPowerup(false);
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
				//powerup display finished
				break; 
			case 8: // Smaller Ball
				for (int i=0; i< Game.activeBalls.size(); i++){	
					if (Game.activeBalls.get(i).ballMods >= -1){
							Game.activeBalls.get(i).DIAMETER = (int) (Game.activeBalls.get(i).DIAMETER * .8);
							Game.activeBalls.get(i).SubtractBallMod();
							System.out.println("Ball Decrease - New level "  + Game.activeBalls.get(i).ballMods);
							Game.setHasPowerup(true);
							
							ActionListener smallBallStop = new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									Game.setHasPowerup(false);
									System.out.println("Powerup Ended");
								}
							};
							int delaySmallBall = 30000; //milliseconds
							Timer timerSmallBall = new Timer(delaySmallBall, smallBallStop);
							timerSmallBall.setRepeats(false);
							timerSmallBall.start();

						}else{
							System.out.println("Powerup not available - the ball size is too low.");
						}}
				break; 
				//powerup display finished
			case 7: // Multiple Balls
				if (Game.activeBalls.size() <= 5){
					System.out.println("Multiple Balls Enabled");
					Ball extraBall1 = new Ball(game, game.activeBalls.get(0).getBounds().x + 5, game.activeBalls.get(0).getBounds().y - 10);
					extraBall1.setXa(1);
					extraBall1.setYa(-1);
					Ball extraBall2 = new Ball(game, game.activeBalls.get(0).getBounds().x + 5, game.activeBalls.get(0).getBounds().y - 10);
					extraBall2.setXa(-1);
					extraBall2.setYa(-1);
					Game.activeBalls.add(extraBall1);
					Game.activeBalls.add(extraBall2);
				}else{
					System.out.println("Powerup Not Enabled!: Too many balls currently in play.");
				}
				break; 
			case 6: // Smaller Paddle
				if (game.racquet.racquetMods >= -5){
					game.racquet.WIDTH = (int) (game.racquet.WIDTH * .8);
					game.racquet.SubtractRacquetMod();
					System.out.println("Racquet Decrease - New level "  + game.racquet.racquetMods);
				}else{
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
					}}
				break; 
			case 4: // Speed Up
				for (int i=0; i< Game.activeBalls.size();i++){
					if (Game.activeBalls.get(i).speed < 3.5){
						Game.activeBalls.get(i).speed += 0.5;
						System.out.println("Speed Up - game speed " + Game.activeBalls.get(i).speed);
					}else{
						System.out.println("Powerup not available - the ball speed is too high.");
					}}
				break; 
			case 3: // Larger Paddle
				
				if (game.racquet.racquetMods < 7){
					game.racquet.WIDTH = (int) (game.racquet.WIDTH * 1.25);
					game.racquet.addRacquetMod();
					System.out.println("Racquet Increase - New level "  + game.racquet.racquetMods);
				}else{
					System.out.println("Powerup not available - the racquet size is too high.");
				}
				break; 
			case 2: // Larger Ball
				for (int i=0; i<Game.activeBalls.size(); i++){	
					if (Game.activeBalls.get(i).ballMods < 5){
						Game.activeBalls.get(i).DIAMETER = (int) (Game.activeBalls.get(i).DIAMETER * 1.25) ;
						Game.activeBalls.get(i).addBallMod();
						System.out.println("Ball Increase - New level "  + Game.activeBalls.get(i).ballMods);
					}else{
						System.out.println("Powerup not available - the ball size is too high.");
					}}
				
				break; 
			case 1: // Magnet
				int delay = 30000; //milliseconds
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
		if (ability.equals("Missile")){return 13;}
		if (ability.equals("Extra Life")){return 12;}
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
		g.setColor(Color.WHITE);
		g.fillRect(x, y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
