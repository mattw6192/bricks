import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Ball ball = new Ball(this, 20, 320);
	double tempBallSize = 0;
	Boolean started = false;
	int Lives = 3;
	static String lifeString = "***";

	static int Score = 000000;
	Boolean hold = false;
	static ArrayList<Powerup> placeHolder = new ArrayList<Powerup>();
	static int pointMultiplier = 1;
	static Boolean hasFireball = false;
	static Boolean hasMetalPower = false;
	static ArrayList<Ball> activeBalls = new ArrayList<Ball>();
	static Random randNum = new Random();
	
	//top row of bricks from left to right
	static Brick brick = new Brick(5, 10, 35, 15, 4);
	static Brick brick2 = new Brick(45, 10, 35, 15, 4);
	static Brick brick3 = new Brick(85, 10, 35, 15, 4);
	static Brick brick4 = new Brick(125, 10, 35, 15, 4);
	static Brick brick5 = new Brick(165, 10, 35, 15, 4);
	static Brick brick6 = new Brick(205, 10, 35, 15, 4);
	static Brick brick7 = new Brick(245, 10, 35, 15, 4);
	static Brick brick8 = new Brick(285, 10, 35, 15, 4);
	static Brick brick9 = new Brick(325, 10, 35, 15, 4);
	static Brick brick10 = new Brick(365, 10, 35, 15, 4);
	static Brick brick11 = new Brick(405, 10, 35, 15, 4);
	static Brick brick12 = new Brick(445, 10, 35, 15, 4);
	static Brick brick13 = new Brick(485, 10, 35, 15, 4);
	static Brick brick14 = new Brick(525, 10, 35, 15, 4);
	static Brick brick15 = new Brick(565, 10, 35, 15, 4);
	static Brick brick16 = new Brick(605, 10, 35, 15, 4);
	static Brick brick17 = new Brick(645, 10, 35, 15, 4);
	static Brick brick18 = new Brick(685, 10, 35, 15, 4);
	static Brick brick19 = new Brick(725, 10, 35, 15, 4);
	static Brick brick20 = new Brick(765, 10, 35, 15, 4);
	static Brick brick21 = new Brick(805, 10, 35, 15, 4);
	static Brick brick22 = new Brick(845, 10, 35, 15, 4);
	static Brick brick23 = new Brick(885, 10, 35, 15, 4);
	static Brick brick24 = new Brick(925, 10, 35, 15, 4);
	static Brick brick25 = new Brick(965, 10, 35, 15, 4);
	
	// second row of bricks from left to right
	static Brick brick2a = new Brick(25, 30, 35, 15, 4);
	static Brick brick2b = new Brick(65, 30, 35, 15, 4);
	static Brick brick2c = new Brick(105, 30, 35, 15, 4);
	static Brick brick2d = new Brick(145, 30, 35, 15, 4);
	static Brick brick2e = new Brick(185, 30, 35, 15, 4);
	static Brick brick2f = new Brick(225, 30, 35, 15, 4);
	static Brick brick2g = new Brick(265, 30, 35, 15, 4);
	static Brick brick2h = new Brick(305, 30, 35, 15, 4);
	static Brick brick2i = new Brick(345, 30, 35, 15, 4);
	static Brick brick2j = new Brick(385, 30, 35, 15, 4);
	static Brick brick2k = new Brick(425, 30, 35, 15, 4);
	static Brick brick2l = new Brick(465, 30, 35, 15, 4);
	static Brick brick2m = new Brick(505, 30, 35, 15, 4);
	static Brick brick2n = new Brick(545, 30, 35, 15, 4);
	static Brick brick2o = new Brick(585, 30, 35, 15, 4);
	static Brick brick2p = new Brick(625, 30, 35, 15, 4);
	static Brick brick2q = new Brick(665, 30, 35, 15, 4);
	static Brick brick2r = new Brick(705, 30, 35, 15, 4);
	static Brick brick2s = new Brick(745, 30, 35, 15, 4);
	static Brick brick2t = new Brick(785, 30, 35, 15, 4);
	static Brick brick2u = new Brick(825, 30, 35, 15, 4);
	static Brick brick2v = new Brick(865, 30, 35, 15, 4);
	static Brick brick2w = new Brick(905, 30, 35, 15, 4);
	static Brick brick2x = new Brick(945, 30, 35, 15, 4);
	
	// third row of bricks from left to right
	static Brick brick3a = new Brick(45, 50, 35, 15, 3);
	static Brick brick3b = new Brick(85, 50, 35, 15, 3);
	static Brick brick3c = new Brick(125, 50, 35, 15, 3);
	static Brick brick3d = new Brick(165, 50, 35, 15, 3);
	static Brick brick3e = new Brick(205, 50, 35, 15, 3);
	static Brick brick3f = new Brick(245, 50, 35, 15, 3);
	static Brick brick3g = new Brick(285, 50, 35, 15, 3);
	static Brick brick3h = new Brick(325, 50, 35, 15, 3);
	static Brick brick3i = new Brick(365, 50, 35, 15, 3);
	static Brick brick3j = new Brick(405, 50, 35, 15, 3);
	static Brick brick3k = new Brick(445, 50, 35, 15, 3);
	static Brick brick3l = new Brick(485, 50, 35, 15, 3);
	static Brick brick3m = new Brick(525, 50, 35, 15, 3);
	static Brick brick3n = new Brick(565, 50, 35, 15, 3);
	static Brick brick3o = new Brick(605, 50, 35, 15, 3);
	static Brick brick3p = new Brick(645, 50, 35, 15, 3);
	static Brick brick3q = new Brick(685, 50, 35, 15, 3);
	static Brick brick3r = new Brick(725, 50, 35, 15, 3);
	static Brick brick3s = new Brick(765, 50, 35, 15, 3);
	static Brick brick3t = new Brick(805, 50, 35, 15, 3);
	static Brick brick3u = new Brick(845, 50, 35, 15, 3);
	static Brick brick3v = new Brick(885, 50, 35, 15, 3);
	static Brick brick3w = new Brick(925, 50, 35, 15, 3);
	
	// fourth row of bricks from left to right
	static Brick brick4a = new Brick(65, 70, 35, 15, 2);
	static Brick brick4b = new Brick(105, 70, 35, 15, 2);
	static Brick brick4c = new Brick(145, 70, 35, 15, 2);
	static Brick brick4d = new Brick(185, 70, 35, 15, 2);
	static Brick brick4e = new Brick(225, 70, 35, 15, 2);
	static Brick brick4f = new Brick(265, 70, 35, 15, 2);
	static Brick brick4g = new Brick(305, 70, 35, 15, 2);
	static Brick brick4h = new Brick(345, 70, 35, 15, 2);
	static Brick brick4i = new Brick(385, 70, 35, 15, 2);
	static Brick brick4j = new Brick(425, 70, 35, 15, 2);
	static Brick brick4k = new Brick(465, 70, 35, 15, 2);
	static Brick brick4l = new Brick(505, 70, 35, 15, 2);
	static Brick brick4m = new Brick(545, 70, 35, 15, 2);
	static Brick brick4n = new Brick(585, 70, 35, 15, 2);
	static Brick brick4o = new Brick(625, 70, 35, 15, 2);
	static Brick brick4p = new Brick(665, 70, 35, 15, 2);
	static Brick brick4q = new Brick(705, 70, 35, 15, 2);
	static Brick brick4r = new Brick(745, 70, 35, 15, 2);
	static Brick brick4s = new Brick(785, 70, 35, 15, 2);
	static Brick brick4t = new Brick(825, 70, 35, 15, 2);
	static Brick brick4u = new Brick(865, 70, 35, 15, 2);
	static Brick brick4v = new Brick(905, 70, 35, 15, 2);
	
	// fifth row of bricks from left to right
	static Brick brick5a = new Brick(85, 90, 35, 15, 2);
	static Brick brick5b = new Brick(125, 90, 35, 15, 2);
	static Brick brick5c = new Brick(165, 90, 35, 15, 2);
	static Brick brick5d = new Brick(205, 90, 35, 15, 2);
	static Brick brick5e = new Brick(245, 90, 35, 15, 2);
	static Brick brick5f = new Brick(285, 90, 35, 15, 2);
	static Brick brick5g = new Brick(325, 90, 35, 15, 2);
	static Brick brick5h = new Brick(365, 90, 35, 15, 2);
	static Brick brick5i = new Brick(405, 90, 35, 15, 2);
	static Brick brick5j = new Brick(445, 90, 35, 15, 2);
	static Brick brick5k = new Brick(485, 90, 35, 15, 2);
	static Brick brick5l = new Brick(525, 90, 35, 15, 2);
	static Brick brick5m = new Brick(565, 90, 35, 15, 2);
	static Brick brick5n = new Brick(605, 90, 35, 15, 2);
	static Brick brick5o = new Brick(645, 90, 35, 15, 2);
	static Brick brick5p = new Brick(685, 90, 35, 15, 2);
	static Brick brick5q = new Brick(725, 90, 35, 15, 2);
	static Brick brick5r = new Brick(765, 90, 35, 15, 2);
	static Brick brick5s = new Brick(805, 90, 35, 15, 2);
	static Brick brick5t = new Brick(845, 90, 35, 15, 2);
	static Brick brick5u = new Brick(885, 90, 35, 15, 2);
	
	// sixth row of bricks from left to right
	static Brick brick6a = new Brick(105, 110, 35, 15, 1);
	static Brick brick6b = new Brick(145, 110, 35, 15, 1);
	static Brick brick6c = new Brick(185, 110, 35, 15, 1);
	static Brick brick6d = new Brick(225, 110, 35, 15, 1);
	static Brick brick6e = new Brick(265, 110, 35, 15, 1);
	static Brick brick6f = new Brick(305, 110, 35, 15, 1);
	static Brick brick6g = new Brick(345, 110, 35, 15, 1);
	static Brick brick6h = new Brick(385, 110, 35, 15, 1);
	static Brick brick6i = new Brick(425, 110, 35, 15, 1);
	static Brick brick6j = new Brick(465, 110, 35, 15, 1);
	static Brick brick6k = new Brick(505, 110, 35, 15, 1);
	static Brick brick6l = new Brick(545, 110, 35, 15, 1);
	static Brick brick6m = new Brick(585, 110, 35, 15, 1);
	static Brick brick6n = new Brick(625, 110, 35, 15, 1);
	static Brick brick6o = new Brick(665, 110, 35, 15, 1);
	static Brick brick6p = new Brick(705, 110, 35, 15, 1);
	static Brick brick6q = new Brick(745, 110, 35, 15, 1);
	static Brick brick6r = new Brick(785, 110, 35, 15, 1);
	static Brick brick6s = new Brick(825, 110, 35, 15, 1);
	static Brick brick6t = new Brick(865, 110, 35, 15, 1);
	
	// final row of bricks 
	static Brick brick7a = new Brick(125, 130, 35, 15, 4);
	static Brick brick7b = new Brick(165, 130, 35, 15, 4);
	static Brick brick7c = new Brick(205, 130, 35, 15, 4);
	static Brick brick7d = new Brick(245, 130, 35, 15, 4);
	static Brick brick7e = new Brick(285, 130, 35, 15, 4);
	static Brick brick7f = new Brick(325, 130, 35, 15, 4);
	static Brick brick7g = new Brick(365, 130, 35, 15, 4);
	static Brick brick7h = new Brick(405, 130, 35, 15, 4);
	static Brick brick7i = new Brick(445, 130, 35, 15, 4);
	static Brick brick7j = new Brick(485, 130, 35, 15, 4);
	static Brick brick7k = new Brick(525, 130, 35, 15, 4);
	static Brick brick7l = new Brick(565, 130, 35, 15, 4);
	static Brick brick7m = new Brick(605, 130, 35, 15, 4);
	static Brick brick7n = new Brick(645, 130, 35, 15, 4);
	static Brick brick7o = new Brick(685, 130, 35, 15, 4);
	static Brick brick7p = new Brick(725, 130, 35, 15, 4);
	static Brick brick7q = new Brick(765, 130, 35, 15, 4);
	static Brick brick7r = new Brick(805, 130, 35, 15, 4);
	static Brick brick7s = new Brick(845, 130, 35, 15, 4);
	

	private static ArrayList<Brick> allBricks = new ArrayList<Brick>();
	Racquet racquet = new Racquet(this);

	public Game() { //comment
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
				 if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {started = true;}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}

			
		});
		setFocusable(true);
	}
	
	private void move() {
		if (started == true){
			for (int i =0; i<activeBalls.size(); i++){
				activeBalls.get(i).move();
			}
			//hold = false;
		}else{
			for (int i =0; i<activeBalls.size(); i++){
				activeBalls.get(i).setX((int) racquet.getBounds().getX() + 20);
				activeBalls.get(i).setY((int) racquet.getBounds().getY() - 10);
			}
		}
		racquet.move();
	}

	public boolean isGameOver(){
		if (Lives <= 0){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getLifeString() {
		return lifeString;
	}

	public static void setLifeString(String lifeString) {
		Game.lifeString = lifeString;
	}
	
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
		if (placeHolder.isEmpty() == false){
			for (int i=0; i<placeHolder.size();i++){
				placeHolder.get(i).paint(g2d);
			}
		}	
	}
	
	public void gameOver() {
		JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.ERROR_MESSAGE);
		System.exit(ABORT);
	}
	
	public void gameWon() {
		JOptionPane.showMessageDialog(this, "You have completed this round.", "Winner!", JOptionPane.INFORMATION_MESSAGE);
		System.exit(ABORT);
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Brick Breaker");
		Game game = new Game();
		StartMenu menu = new StartMenu(frame, true);
		menu.setLocationRelativeTo(game);
		menu.setVisible(true);
                Instructions instructions = new Instructions(frame,true);
                instructions.setLocationRelativeTo(game);
                if (menu.getInstructions()==true){
                    instructions.setVisible(true);
                }
		allBricks.add(brick);
		allBricks.add(brick2);
		allBricks.add(brick3);
		allBricks.add(brick4);
		allBricks.add(brick5);
		allBricks.add(brick6);
		allBricks.add(brick7);
		allBricks.add(brick8);
		allBricks.add(brick9);
		allBricks.add(brick10);
		allBricks.add(brick11);
		allBricks.add(brick12);
		allBricks.add(brick13);
		allBricks.add(brick14);
		allBricks.add(brick15);
		allBricks.add(brick16);
		allBricks.add(brick17);
		allBricks.add(brick18);
		allBricks.add(brick19);
		allBricks.add(brick20);
		allBricks.add(brick21);
		allBricks.add(brick22);
		allBricks.add(brick23);
		allBricks.add(brick24);
		allBricks.add(brick25);
		allBricks.add(brick2a);
		allBricks.add(brick2b);
		allBricks.add(brick2c);
		allBricks.add(brick2d);
		allBricks.add(brick2e);
		allBricks.add(brick2f);
		allBricks.add(brick2g);
		allBricks.add(brick2h);
		allBricks.add(brick2i);
		allBricks.add(brick2j);
		allBricks.add(brick2k);
		allBricks.add(brick2l);
		allBricks.add(brick2m);
		allBricks.add(brick2n);
		allBricks.add(brick2o);
		allBricks.add(brick2p);
		allBricks.add(brick2q);
		allBricks.add(brick2r);
		allBricks.add(brick2s);
		allBricks.add(brick2t);
		allBricks.add(brick2u);
		allBricks.add(brick2v);
		allBricks.add(brick2w);
		allBricks.add(brick2x);
		allBricks.add(brick3a);
		allBricks.add(brick3b);
		allBricks.add(brick3c);
		allBricks.add(brick3d);
		allBricks.add(brick3e);
		allBricks.add(brick3f);
		allBricks.add(brick3g);
		allBricks.add(brick3h);
		allBricks.add(brick3i);
		allBricks.add(brick3j);
		allBricks.add(brick3k);
		allBricks.add(brick3l);
		allBricks.add(brick3m);
		allBricks.add(brick3n);
		allBricks.add(brick3o);
		allBricks.add(brick3p);
		allBricks.add(brick3q);
		allBricks.add(brick3r);
		allBricks.add(brick3s);
		allBricks.add(brick3t);
		allBricks.add(brick3u);
		allBricks.add(brick3v);
		allBricks.add(brick3w);
		allBricks.add(brick4a);
		allBricks.add(brick4b);
		allBricks.add(brick4c);
		allBricks.add(brick4d);
		allBricks.add(brick4e);
		allBricks.add(brick4f);
		allBricks.add(brick4d);
		allBricks.add(brick4g);
		allBricks.add(brick4h);
		allBricks.add(brick4i);
		allBricks.add(brick4j);
		allBricks.add(brick4k);
		allBricks.add(brick4l);
		allBricks.add(brick4m);
		allBricks.add(brick4n);
		allBricks.add(brick4o);
		allBricks.add(brick4p);
		allBricks.add(brick4q);
		allBricks.add(brick4r);
		allBricks.add(brick4s);
		allBricks.add(brick4t);
		allBricks.add(brick4u);
		allBricks.add(brick4v);
		allBricks.add(brick5a);
		allBricks.add(brick5b);
		allBricks.add(brick5c);
		allBricks.add(brick5d);
		allBricks.add(brick5e);
		allBricks.add(brick5f);
		allBricks.add(brick5g);
		allBricks.add(brick5h);
		allBricks.add(brick5i);
		allBricks.add(brick5j);
		allBricks.add(brick5k);
		allBricks.add(brick5l);
		allBricks.add(brick5m);
		allBricks.add(brick5n);
		allBricks.add(brick5o);
		allBricks.add(brick5p);
		allBricks.add(brick5q);
		allBricks.add(brick5r);
		allBricks.add(brick5s);
		allBricks.add(brick5t);
		allBricks.add(brick5u);
		allBricks.add(brick6a);
		allBricks.add(brick6b);
		allBricks.add(brick6c);
		allBricks.add(brick6d);
		allBricks.add(brick6e);
		allBricks.add(brick6f);
		allBricks.add(brick6g);
		allBricks.add(brick6h);
		allBricks.add(brick6i);
		allBricks.add(brick6j);
		allBricks.add(brick6k);
		allBricks.add(brick6l);
		allBricks.add(brick6m);
		allBricks.add(brick6n);
		allBricks.add(brick6o);
		allBricks.add(brick6p);
		allBricks.add(brick6q);
		allBricks.add(brick6r);
		allBricks.add(brick6s);
		allBricks.add(brick6t);
		allBricks.add(brick7a);
		allBricks.add(brick7b);
		allBricks.add(brick7c);
		allBricks.add(brick7d);
		allBricks.add(brick7e);
		allBricks.add(brick7f);
		allBricks.add(brick7g);
		allBricks.add(brick7h);
		allBricks.add(brick7i);
		allBricks.add(brick7j);
		allBricks.add(brick7k);
		allBricks.add(brick7l);
		allBricks.add(brick7m);
		allBricks.add(brick7n);
		allBricks.add(brick7o);
		allBricks.add(brick7p);
		allBricks.add(brick7q);
		allBricks.add(brick7r);
		allBricks.add(brick7s);
		for(int i = 0; i<allBricks.size(); i++){ //update the color for certain hit count
			if (allBricks.get(i).getHits() == 4){allBricks.get(i).setColor(Color.BLACK);}
			if (allBricks.get(i).getHits() == 3){allBricks.get(i).setColor(Color.BLUE);}
			if (allBricks.get(i).getHits() == 2){allBricks.get(i).setColor(Color.GREEN);}
			if (allBricks.get(i).getHits() == 1){allBricks.get(i).setColor(Color.YELLOW);} 
		}

		frame.add(game);
		frame.setSize(1005, 700);
		frame.setResizable(false);
		frame.setLocationRelativeTo(game);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) { //game loop
			frame.setTitle("Lives: " + Game.getLifeString() + "   Score: " + Score );
			game.move();
			game.repaint();
			if (placeHolder.isEmpty() == false){ // placeholder is powerups on screen
				for (int j=0; j<placeHolder.size();j++){
					placeHolder.get(j).move();
				}
			}
			for (int j = 0; j<activeBalls.size(); j++){
				for(int i = 0; i<allBricks.size(); i++){ 
				
					if (activeBalls.get(j).getBounds().intersects(allBricks.get(i).getBounds())){
						final Brick saveBrickForAction = allBricks.get(i);
						Score += (100 * pointMultiplier);
						
						if (checkSideHits(allBricks.get(i), activeBalls.get(j)) == true){
							//activeBalls.get(j).ya = activeBalls.get(j).ya * (-1); //update coordinates of ball to avoid multiple hits at the same time
							activeBalls.get(j).xa = activeBalls.get(j).xa * (-1); 
						}else{
							activeBalls.get(j).ya = activeBalls.get(j).ya * (-1); 
							//activeBalls.get(j).xa = activeBalls.get(j).xa * (-1);
							
						}
						
						
						if (hasFireball == true && allBricks.get(i).canBeHit == true){
							allBricks.get(i).subtractAllHits();
						}else if(hasMetalPower == true && allBricks.get(i).canBeHit == true){
							allBricks.get(i).subtractTwoHits(); // metal ball subtracts two hits
						}else{
							if (allBricks.get(i).canBeHit == true){
								allBricks.get(i).subtractHit(); // this is where im subtracting a hit for every hit with the ball
							}
						}
					    
					    boolean havePowerup = game.getPowerup();
					    if (havePowerup == true){
					    	Powerup savePower = game.generatePowerup(allBricks.get(i));
					    	placeHolder.add(savePower);
					    }
					    //activeBalls.get(j).ya = activeBalls.get(j).ya * (-1); //update coordinates of ball to avoid multiple hits at the same time
						//game.ball.xa = game.ball.xa * (-1);
					    
					    allBricks.get(i).setCanBeHit(false);
						int delay = 500; //milliseconds
						ActionListener taskPerformer = new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								saveBrickForAction.setCanBeHit(true);
						        //System.out.println("Brick can now be hit again");
						    }
						};
						Timer timer = new Timer(delay, taskPerformer);
						timer.setRepeats(false);
						timer.start();
						
						if (allBricks.get(i).getHits() == 4){allBricks.get(i).setColor(Color.BLACK);}
						if (allBricks.get(i).getHits() == 3){allBricks.get(i).setColor(Color.BLUE);}
						if (allBricks.get(i).getHits() == 2){allBricks.get(i).setColor(Color.GREEN);} // update the color
						if (allBricks.get(i).getHits() == 1){allBricks.get(i).setColor(Color.YELLOW);} // for certain hit count
						
						if (allBricks.get(i).getHits() <= 0){ // remove a brick if its hit counter is 0
							hideBrick(allBricks.get(i), activeBalls.get(j));
							allBricks.remove(i);
						}
						//System.out.println("Score is: " + Score);
					}
					if (allBricks.isEmpty()){
						game.gameWon();
					}
				}
				
		}
			Thread.sleep(10);
		}
	}
	
	// This method checks for collisions with the sides of bricks and changes the course of the ball accordingly 
	public static boolean checkSideHits(Brick tempBrick, Ball tempBall){
		if (((tempBall.getBounds().getX()) >= (tempBrick.getBounds().getX() + tempBrick.getBounds().getWidth() - 1)) ){
			//tempBall.setXa((tempBall.getXa() * (-1)));
			//tempBall.setYa((tempBall.getYa() * (-1)));
			return true;
		}else if (((tempBall.getBounds().getX() + Ball.DIAMETER) <= (tempBrick.getBounds().getX() + 1))){
			//tempBall.setXa((tempBall.getXa() * (-1)));
			//tempBall.setYa((tempBall.getYa() * (-1)));
			return true;
		}
		return false;
	}
	
	public static int randInt(int min, int max) {
	    int randomNum = randNum.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public boolean getPowerup(){
		int tempRandNum = randInt(1,10); // random number has to be 2 or 7 to get a powerup
		if (tempRandNum == 7 || tempRandNum == 2){
			return true;
		}
		return false;	
	}
	
	public Powerup generatePowerup(Brick currentBrick){
		//int tempRandNum2 = randInt(1,12); 
		int tempRandNum2 = 12; // Set this to a specific number to test one powerup
		switch(tempRandNum2){
			case 12:
				System.out.println("Powerup Gained: " + "Extra Life");
				Powerup powerup12 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Extra Life");
				return powerup12;
			case 11:
				System.out.println("Powerup Gained: " + "Metal Ball");
				Powerup powerup11 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Metal Ball");
				return powerup11; 
			case 10:
				System.out.println("Powerup Gained: " + "Fireball");
				Powerup powerup10 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Fireball");
				return powerup10; 
			case 9:
				System.out.println("Powerup Gained: " + "Double Points");
				Powerup powerup9 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Double Points");
				return powerup9; 
			case 8:
				System.out.println("Powerup Gained: " + "Ball Decrease");
				Powerup powerup8 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Ball Decrease");
				return powerup8; 
			case 7:
				System.out.println("Powerup Gained: " + "Multiple Balls");
				Powerup powerup7 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Multiple Balls");
				return powerup7; 
			case 6:
				System.out.println("Powerup Gained: " + "Paddle Decrease");
				Powerup powerup6 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Paddle Decrease");
				return powerup6; 
			case 5:
				System.out.println("Powerup Gained: " + "Slow Down");
				Powerup powerup5 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Slow Down");
				return powerup5; 
			case 4:
				System.out.println("Powerup Gained: " + "Speed Up");
				Powerup powerup4 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Speed Up");
				return powerup4; 
			case 3:
				System.out.println("Powerup Gained: " + "Paddle Increase");
				Powerup powerup3 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Paddle Increase");
				return powerup3; 
			case 2:
				System.out.println("Powerup Gained: " + "Ball Increase");
				Powerup powerup2 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Ball Increase");
				return powerup2; 
			case 1:
				System.out.println("Powerup Gained: " + "Freeze");
				Powerup powerup1 = new Powerup(this, currentBrick.getBounds().x, currentBrick.getBounds().y, 0, "Freeze");
				return powerup1; 
			default:
				break;
			
	}
		return null;
	}
	
	public static void hideBrick(Brick newbrick, Ball saveBall){
		newbrick.getBounds().setBounds(-10, -10, 0, 0);
		newbrick.setAlive(false);
		//double saveXa = saveBall.getXa();
		//saveBall.setXa(saveXa * (-1));
		//double saveYa = saveBall.getYa();
		//saveBall.setYa(saveYa * (-1));
		newbrick = null;
	}
	

}
