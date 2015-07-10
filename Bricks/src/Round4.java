public class Round4 {

	public Round4(Game thisGame) {
		for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 5, 35, 15, thisGame.randInt(3,4));
        	thisGame.allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 25, 35, 15, thisGame.randInt(3,4));
        	thisGame.allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 45, 35, 15, thisGame.randInt(1,3));
        	thisGame.allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 65, 35, 15, thisGame.randInt(1,3));
        	thisGame.allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 85, 35, 15, thisGame.randInt(1,4));
        	thisGame.allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 105, 35, 15, thisGame.randInt(2,4));
        	thisGame.allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 125, 35, 15, thisGame.randInt(2,4));
        	thisGame.allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 145, 35, 15, thisGame.randInt(2,4));
        	thisGame.allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 165, 35, 15, thisGame.randInt(1,4));
        	thisGame.allBricks.add(brick);
        }
        
        for (int i=0; i<21; i++){
        	Brick brick = new Brick((40 * i) + 10, 185, 35, 15, thisGame.randInt(1,4));
        	thisGame.allBricks.add(brick);
        }
	}
	
}
