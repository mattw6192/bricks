import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;


public class Probability {

	public Game game;
	static Random randNum = new Random();
	Boolean needsCollisions = false;
	
		public Probability(Game thisGame){
			game = thisGame;
		}
		public static int randInt(int min, int max) {
		    int randomNum = randNum.nextInt((max - min) + 1) + min;
		    return randomNum;
		}
		
		public boolean getPowerup(){
			int tempRandNum = randInt(1,10); // random number has to be 2 or 7 to get a powerup  (20% chance).
			//int tempRandNum = 7; // use this to automatically receive a powerup everytime a brick is hit by a ball
			if (tempRandNum == 7 || tempRandNum == 2 || game.overwritePowerupLimits == true){ 
				int delay = 1000; //milliseconds
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						game.powerupsEnabled = true;
				    }
				};
				
				if (game.powerupsEnabled == true){
					game.powerupsEnabled = false;
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
		
		
		public boolean checkPowerup(int powerupNum){
			if (powerupNum == 1){
				if (game.hasMagnet == false){
					return true;
				}
				return false;
			}else if (powerupNum == 2){
				if (game.activeBalls.get(0).getBallMods() <= 5){
					return true;
				}
				return false;
			}else if (powerupNum == 3){
				if (game.racquet.getRacquetMods() < 7){
					return true;
				}
				return false;
			}else if (powerupNum == 4){
				if (game.activeBalls.get(0).speed < 3.5){
					return true;
				}
				return false;
			}else if (powerupNum == 5){
				if (game.activeBalls.get(0).speed >= 1){
					return true;
				}
				return false;
			}else if (powerupNum == 6){
				if (game.racquet.racquetMods >= -3){
					return true;
				}
				return false;
			}else if (powerupNum == 7){
				if (game.activeBalls.size() <= 5){
					return true;
				}
				return false;
			}else if (powerupNum == 8){
				if (game.activeBalls.get(0).getBallMods() >= -1){
					return true;
				}
				return false;
			}else if (powerupNum == 9){
				if (game.pointMultiplier <= 4){
					return true;
				}
				return false;
			}else if (powerupNum == 10){
				if (game.hasFireball == false){
					return true;
				}
				return false;
			}else if (powerupNum == 11){
				if (game.hasMetalPower == false && game.hasFireball){
					return true;
				}
				return false;
			}else if (powerupNum == 12){
				if (game.getLives() <= 5){
					return true;
				}
				return false;
			}else if (powerupNum == 13){
				if (game.missileCount <= 4){
					return true;
				}
				return false;
			}else if (powerupNum == 14){
				if (Game.hasGun == false){
					return true;
				}
				return false;
			}else if (powerupNum == 15){
				if (game.hasInsanityMode == false){
					return true;
				}
				return false;
			}else if (powerupNum == 16){
				if (game.Lives > 1){
					return true;
				}
				return false;
			}else if (powerupNum == 17){
				return true;
			}else if (powerupNum == 18){
				if (game.Score > 1){
					return true;
				}
				return false;
			}else if (powerupNum == 19){
				if (game.hasBoreyMode == false){
					return true;
				}
				return false;
			}else if (powerupNum == 20){
				if (game.hasSafetyNet == false){
					return true;
				}
				return false;
			}
			return false;
		}
		
		
		public int checkConditions(int randInt){
			if (needsCollisions == true){
				System.out.println("No Collision Conditions"); // Not enough Collisions - probability adjusted to help hit bricks easier
				return lowCollisionConditions(randInt);  
			}else if (game.activeBalls.get(0).speed == 2 && game.activeBalls.get(0).ballMods == 0 && game.racquet.racquetMods == 0){
				System.out.println("Normal Conditions");
				return normalConditions(randInt); // no modifications require probability adjustments
			}else if (game.activeBalls.get(0).speed < 2){
				System.out.println("Slow Conditions");
				return slowConditions(randInt); // ball too slow - probablility adjusted for speed ups
			}else if (game.activeBalls.get(0).ballMods < 0){
				System.out.println("Small Ball Conditions");
				return smallBallConditions(randInt); // ball too small - probability adjusted for larger ball
			}else if (game.racquet.racquetMods < -1){
				System.out.println("Small Paddle Conditions");
				return smallPaddleConditions(randInt); // racquet too small - probability adjusted for larger paddle
			}else if (game.activeBalls.get(0).speed > 3 && game.hasInsanityMode == false){
				System.out.println("Fast Conditions");
				return fastConditions(randInt); // ball too fast - probability adjusted for slowing down -- only activates if insanity mode not activated
			}
			System.out.println("No Conditions Selected - Assuming Normal Condtions");
			return normalConditions(randInt);
		}
		
		public int normalConditions(int randInt){
			if (randInt <= 5){
				return 1;
			}else if (randInt <= 11){
				return 2;
			}else if (randInt <= 17){
				return 3;
			}else if (randInt <= 23){
				return 4;
			}else if (randInt <= 28){
				return 5;
			}else if (randInt <= 33){
				return 6;
			}else if (randInt <= 39){
				return 7;
			}else if (randInt <= 44){
				return 8;
			}else if (randInt <= 49){
				return 9;
			}else if (randInt <= 54){
				return 10;
			}else if (randInt <= 60){
				return 11;
			}else if (randInt <= 65){
				return 12;
			}else if (randInt <= 70){
				return 13;
			}else if (randInt <= 76){
				return 14;
			}else if (randInt <= 79){
				return 15;
			}else if (randInt <= 84){
				return 16;
			}else if (randInt <= 90){
				return 17;
			}else if (randInt <= 97){
				return 18;
			}else if (randInt <= 99){
				return 19;
			}else if (randInt <= 103){
				return 20;
			}
			return 1;
		}
		
		public int slowConditions(int randInt){
			if (randInt <= 4){
				return 1;
			}else if (randInt <= 9){
				return 2;
			}else if (randInt <= 14){
				return 3;
			}else if (randInt <= 39){
				return 4;
			}else if (randInt <= 41){
				return 5;
			}else if (randInt <= 45){
				return 6;
			}else if (randInt <= 49){
				return 7;
			}else if (randInt <= 53){
				return 8;
			}else if (randInt <= 58){
				return 9;
			}else if (randInt <= 63){
				return 10;
			}else if (randInt <= 68){
				return 11;
			}else if (randInt <= 72){
				return 12;
			}else if (randInt <= 76){
				return 13;
			}else if (randInt <= 80){
				return 14;
			}else if (randInt <= 83){
				return 15;
			}else if (randInt <= 87){
				return 16;
			}else if (randInt <= 92){
				return 17;
			}else if (randInt <= 96){
				return 18;
			}else if (randInt <= 99){
				return 19;
			}else if (randInt <= 103){
				return 20;
			}
			return 1;
		}
		
		public int smallBallConditions(int randInt){
			if (randInt <= 4){
				return 1;
			}else if (randInt <= 24){
				return 2;
			}else if (randInt <= 29){
				return 3;
			}else if (randInt <= 34){
				return 4;
			}else if (randInt <= 39){
				return 5;
			}else if (randInt <= 44){
				return 6;
			}else if (randInt <= 49){
				return 7;
			}else if (randInt <= 54){
				return 8;
			}else if (randInt <= 56){
				return 9;
			}else if (randInt <= 61){
				return 10;
			}else if (randInt <= 66){
				return 11;
			}else if (randInt <= 70){
				return 12;
			}else if (randInt <= 75){
				return 13;
			}else if (randInt <= 80){
				return 14;
			}else if (randInt <= 83){
				return 15;
			}else if (randInt <= 87){
				return 16;
			}else if (randInt <= 92){
				return 17;
			}else if (randInt <= 97){
				return 18;
			}else if (randInt <= 99){
				return 19;
			}else if (randInt <= 103){
				return 20;
			}
			return 1;
		}
		
		public int smallPaddleConditions(int randInt){
			if (randInt <= 4){
				return 1;
			}else if (randInt <= 9){
				return 2;
			}else if (randInt <= 24){
				return 3;
			}else if (randInt <= 29){
				return 4;
			}else if (randInt <= 34){
				return 5;
			}else if (randInt <= 37){
				return 6;
			}else if (randInt <= 42){
				return 7;
			}else if (randInt <= 47){
				return 8;
			}else if (randInt <= 52){
				return 9;
			}else if (randInt <= 57){
				return 10;
			}else if (randInt <= 62){
				return 11;
			}else if (randInt <= 67){
				return 12;
			}else if (randInt <= 72){
				return 13;
			}else if (randInt <= 77){
				return 14;
			}else if (randInt <= 80){
				return 15;
			}else if (randInt <= 85){
				return 16;
			}else if (randInt <= 91){
				return 17;
			}else if (randInt <= 96){
				return 18;
			}else if (randInt <= 99){
				return 19;
			}else if (randInt <= 103){
				return 20;
			}
			return 1;
		}
		
		public int fastConditions(int randInt){
			if (randInt <= 4){
				return 1;
			}else if (randInt <= 9){
				return 2;
			}else if (randInt <= 14){
				return 3;
			}else if (randInt <= 18){
				return 4;
			}else if (randInt <= 33){
				return 5;
			}else if (randInt <= 37){
				return 6;
			}else if (randInt <= 42){
				return 7;
			}else if (randInt <= 46){
				return 8;
			}else if (randInt <= 51){
				return 9;
			}else if (randInt <= 56){
				return 10;
			}else if (randInt <= 61){
				return 11;
			}else if (randInt <= 66){
				return 12;
			}else if (randInt <= 71){
				return 13;
			}else if (randInt <= 76){
				return 14;
			}else if (randInt <= 79){
				return 15;
			}else if (randInt <= 84){
				return 16;
			}else if (randInt <= 90){
				return 17;
			}else if (randInt <= 96){
				return 18;
			}else if (randInt <= 99){
				return 19;
			}else if (randInt <= 103){
				return 20;
			}
			return 1;
		}
		
		public int lowCollisionConditions(int randInt){
			if (randInt <= 4){
				return 1;
			}else if (randInt <= 9){
				return 2;
			}else if (randInt <= 12){
				return 3;
			}else if (randInt <= 15){
				return 4;
			}else if (randInt <= 18){
				return 5;
			}else if (randInt <= 21){
				return 6;
			}else if (randInt <= 31){
				return 7;
			}else if (randInt <= 34){
				return 8;
			}else if (randInt <= 37){
				return 9;
			}else if (randInt <= 41){
				return 10;
			}else if (randInt <= 51){
				return 11;
			}else if (randInt <= 54){
				return 12;
			}else if (randInt <= 64){
				return 13;
			}else if (randInt <= 79){
				return 14;
			}else if (randInt <= 80){
				return 15;
			}else if (randInt <= 84){
				return 16;
			}else if (randInt <= 88){
				return 17;
			}else if (randInt <= 98){
				return 18;
			}else if (randInt <= 99){
				return 19;
			}else if (randInt <= 103){
				return 20;
			}
			return 1;
		}
}
