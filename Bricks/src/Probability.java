
public class Probability {

	public Game game;
	
		public Probability(Game thisGame){
			game = thisGame;
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
				if (game.hasMetalPower == false){
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
				System.out.println("Made it");
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
			}
			return false;
		}
}
