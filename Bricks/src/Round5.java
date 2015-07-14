
public class Round5 {
	public Round5(Game thisGame){
		
		for (int i=0; i < 18; i++){ // largest v shape of blocks
			for (int j=0; j<2; j++){ // left side
	        	Brick brick = new Brick(((40 * j) + 25) + (20 *i), (20*i) + 5, 35, 15, 3);
	        	thisGame.allBricks.add(brick);
	        }
			
			for (int k=0; k<2; k++){ // right side
	        	Brick brick = new Brick(((40 * k) + 765) - (20 *i), (20*i) + 5, 35, 15, 3);
	        	thisGame.allBricks.add(brick);
	        }
		}
		
		for (int i=0; i < 11; i++){ // smaller v shape of blocks (inside of larger one)
			for (int j=0; j<2; j++){ // left side
	        	Brick brick = new Brick(((40 * j) + 190) + (20 *i), (20*i) + 5, 35, 15, 4);
	        	thisGame.allBricks.add(brick);
	        }
			
			for (int k=0; k<2; k++){ // right side
	        	Brick brick = new Brick(((40 * k) + 600) - (20 *i), (20*i) + 5, 35, 15, 4);
	        	thisGame.allBricks.add(brick);
	        }
		}
		
		for (int i=0; i < 11; i++){ // sideburns of the v shape of blocks (outside the v shape)
			for (int j=0; j<2; j++){ // left side
	        	Brick brick = new Brick(((40 * j)) + (20 *i), (20*i) + 160, 35, 15, 2);
	        	thisGame.allBricks.add(brick);
	        }
			
			for (int k=0; k<2; k++){ // right side
	        	Brick brick = new Brick(((40 * k)+ 780) - (20 *i), (20*i) + 160, 35, 15, 2);
	        	thisGame.allBricks.add(brick);
	        }
		}
		
		Brick brick1 = new Brick(20, 140, 35, 15, 2);
    	thisGame.allBricks.add(brick1);
    	
    	Brick brick2 = new Brick(0, 120, 35, 15, 2);
    	thisGame.allBricks.add(brick2);
    	
    	Brick brick3 = new Brick(800, 140, 35, 15, 2);
    	thisGame.allBricks.add(brick3);
    	
    	Brick brick4 = new Brick(820, 120, 35, 15, 2);
    	thisGame.allBricks.add(brick4);
		
		for (int k=0; k<5; k++){ // left side bottom of sideburns
        	Brick brick = new Brick(((40 * k)),  360, 35, 15, 2);
        	thisGame.allBricks.add(brick);
        }
		
		for (int k=0; k<5; k++){ // left side bottom of sideburns
        	Brick brick = new Brick(((40 * k) + 660),  360, 35, 15, 2);
        	thisGame.allBricks.add(brick);
        }
		
		
		for (int i=0; i < 5; i++){ // golden triangle
			for (int j=0; j<(5-i); j++){ // left side
	        	Brick brick = new Brick(((40 * j) + 335) + (20 *i), (20*i) + 5, 35, 15, 1);
	        	thisGame.allBricks.add(brick);
	        }
		}
	}
}
