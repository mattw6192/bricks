public class Round2 {

	 public Round2(Game thisGame) {
	      
		// Top Layer of  horizontal Bricks
			for (int i=0; i<21; i++){
	        	Brick brick = new Brick((40 * i) + 10, 5, 35, 15, thisGame.randInt(3,4));
	        	thisGame.allBricks.add(brick);
	        }
			
			// far left vertical layer of Bricks
			for (int i=0; i<7; i++){
				Brick brick = new Brick(10, (40 * i) + 25, 15, 35, thisGame.randInt(3,4));
			    thisGame.allBricks.add(brick);
			}
			
			// second layer of horizontal bricks
			for (int i=0; i<8; i++){
	        	Brick brick = new Brick((40 * i) + 70, 65, 35, 15, thisGame.randInt(3,4));
	        	thisGame.allBricks.add(brick);
	        }
			
			for (int i=0; i<8; i++){
	        	Brick brick = new Brick((40 * i) + 470, 65, 35, 15, thisGame.randInt(3,4));
	        	thisGame.allBricks.add(brick);
	        }
			
			// far right layer of vertical bricks
			for (int i=0; i<7; i++){
				Brick brick = new Brick(830, (40 * i) + 25, 15, 35, thisGame.randInt(3,4));
			    thisGame.allBricks.add(brick);
			}
			
			// bottom layer of horizontal bricks
			for (int i=0; i<18; i++){
				Brick brick = new Brick((40 * i) + 70, 285, 35, 15, thisGame.randInt(3,4));
				thisGame.allBricks.add(brick);
			}
			
			// 2nd vertical wall of bricks on left
			for (int i=0; i<5; i++){
				Brick brick = new Brick(70, (40 * i) + 85, 15, 35, thisGame.randInt(3,4));
			    thisGame.allBricks.add(brick);
			}
			
			// vertical
			for (int i=0; i<5; i++){
				Brick brick = new Brick(770, (40 * i) + 85, 15, 35, thisGame.randInt(3,4));
			    thisGame.allBricks.add(brick);
			}
			
			//vertical
			for (int i=0; i<4; i++){
				Brick brick = new Brick(370, (40 * i) + 85, 15, 35, thisGame.randInt(3,4));
			    thisGame.allBricks.add(brick);
			}
			
			// vertical
			for (int i=0; i<4; i++){
				Brick brick = new Brick(470, (40 * i) + 85, 15, 35, thisGame.randInt(3,4));
			    thisGame.allBricks.add(brick);
			}
			
			//horizontal
			for (int i=0; i<6; i++){
	        	Brick brick = new Brick((40 * i) + 130, 225, 35, 15, thisGame.randInt(3,4));
	        	thisGame.allBricks.add(brick);
	        }
			
			//horizontal
			for (int i=0; i<6; i++){
				Brick brick = new Brick((40 * i) + 490, 225, 35, 15, thisGame.randInt(3,4));
			    thisGame.allBricks.add(brick);
			}
			
			for (int j=0; j < 7; j++){	
				//horizontal
				for (int i=0; i<6; i++){
				    Brick brick = new Brick((40 * i) + 130, 205 - (20 * j), 35, 15, thisGame.randInt(1,2));
				    thisGame.allBricks.add(brick);
				}
						
				//horizontal
				for (int i=0; i<6; i++){
					Brick brick = new Brick((40 * i) + 490, 205 - (20 * j), 35, 15, thisGame.randInt(1,2));
					thisGame.allBricks.add(brick);
				}
			} 
		 
		
	        
	 }
	
}