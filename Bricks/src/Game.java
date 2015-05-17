import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Ball ball = new Ball(this);
	//Ball ball2 = new Ball(this);
	static Brick brick = new Brick(100, 10, 35, 15);
	static Brick brick2 = new Brick(200, 100, 35, 15);
	static Brick brick3 = new Brick(200, 10, 35, 15);
	static Brick brick4 = new Brick(100, 100, 35, 15);
	static Brick brick5 = new Brick(50, 200, 35, 15);
	private static ArrayList<Brick> allBricks = new ArrayList<Brick>();
	
	Racquet racquet = new Racquet(this);

	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	private void move() {
		ball.move();
		//ball2.move();
		racquet.move();
		//ball2.move();
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		//ball2.paint(g2d);
		racquet.paint(g2d);
		brick.paint(g2d);
		brick2.paint(g2d);
		brick3.paint(g2d);
		brick4.paint(g2d);
		brick5.paint(g2d);
	}
	
	public void gameOver() {
		JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Brick Breaker");
		Game game = new Game();
		//game.ball.setXa(-3);
		
		//game.ball2.setXa(2);
		//game.ball2.setX(3);
		//game.ball2.setY(3);
		//game.ball2.setYa(2);
		
		
		
		
		allBricks.add(brick);
		allBricks.add(brick2);
		allBricks.add(brick3);
		allBricks.add(brick4);
		allBricks.add(brick5);
		frame.add(game);
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(game.brick.getBounds());
		System.out.println(game.brick2.getBounds());
		System.out.println(game.brick3.getBounds());
		System.out.println(game.brick4.getBounds());
		System.out.println(game.brick5.getBounds());
		while (true) {
			Rectangle temp = game.brick.getBounds();
			game.move();
			game.repaint();
			for(int i = 0; i<allBricks.size(); i++){
				if (game.ball.getBounds().intersects(allBricks.get(i).getBounds())){
					System.out.println("Intersection with Brick " + i);
					hideBrick(allBricks.get(i));
					allBricks.remove(i);
				}
				
			}
			
			//System.out.println(game.ball.getBounds().;
			Thread.sleep(10);
		}
		
	
	}
	
	public static void hideBrick(Brick newbrick){
		newbrick.setColor(Color.BLUE);
		//brick = null;
		
		newbrick.getBounds().setBounds(-10, -10, 0, 0);
		newbrick.setAlive(false);
		newbrick = null;
		
		
	}
}
