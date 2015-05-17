import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Ball ball = new Ball(this);
	//Ball ball2 = new Ball(this);
	Brick brick = new Brick(this);
	
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
		
		frame.add(game);
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(game.brick.getBounds());
		while (true) {
			game.move();
			game.repaint();
			
			if (game.ball.getBounds().intersects(game.brick.getBounds())){
				System.out.println("Intersection");
				hideBrick(game.brick);
			}
			//System.out.println(game.ball.getBounds().;
			Thread.sleep(10);
		}
		
	
	}
	
	public static void hideBrick(Brick brick){
		brick.setColor(Color.green);
		//brick = null;
	}
}
