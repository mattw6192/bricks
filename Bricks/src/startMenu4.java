import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;


public class startMenu4 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private boolean wantInstructions = false;
	private boolean wantScores = false;
	private boolean wantStart = false;
	java.awt.event.ActionEvent TempEventSave;

	/**
	 * Launch the application.
	 */
	public static void main(Frame args) {
		
		
		try {
			startMenu4 dialog = new startMenu4(args, true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public startMenu4(java.awt.Frame parent, boolean modal) {
	    super(parent, modal);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			
			final JButton btnStartGame = new JButton("Play Game");
			btnStartGame.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER){
						dispose();
					}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
						btnStartGame.transferFocusBackward();
					}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
						btnStartGame.transferFocus();
					}
				}
			});
			btnStartGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GameStarted();
				}

				private void GameStarted() {
					wantStart = true;
					dispose();
					
				}
			});
			
			
			
			btnStartGame.setToolTipText("Start the Game");
			btnStartGame.setBounds(90, 129, 117, 29);
			contentPanel.add(btnStartGame);
		}
		
		
		
		{
			final JButton btnInstructions = new JButton("Instructions");
			btnInstructions.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER){
						openInstructions();
					}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
						btnInstructions.transferFocusBackward();
					}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
						btnInstructions.transferFocus();
					}
				}
			});
			btnInstructions.addActionListener(new ActionListener() {
				

				public void actionPerformed(ActionEvent e) {
					openInstructions();
				}

				
			});
			

			btnInstructions.setToolTipText("View the Instructions");
			btnInstructions.setBounds(239, 129, 117, 29);
			contentPanel.add(btnInstructions);
			btnInstructions.setActionCommand("Cancel");
		}
		
		JLabel lblBrickBreaker = new JLabel("Brick Breaker");
		lblBrickBreaker.setFont(new Font("Lucida Console", Font.PLAIN, 24));
		lblBrickBreaker.setBounds(123, 46, 193, 42);
		contentPanel.add(lblBrickBreaker);
		
		final JButton btnHighScores = new JButton("High Scores");
		btnHighScores.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					openHighScores();
				}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
					btnHighScores.transferFocusBackward();
				}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					btnHighScores.transferFocus();
				}
			}
		});
		btnHighScores.setToolTipText("View High Scores");
		btnHighScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openHighScores();
			}

			
		});
		btnHighScores.setBounds(239, 182, 117, 29);
		contentPanel.add(btnHighScores);
		
		final JButton btnQuit = new JButton("Quit");
		btnQuit.setToolTipText("Exit the game");
		
		
		btnQuit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					System.exit(0);
				}else if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
					btnQuit.transferFocusBackward();
				}else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
					btnQuit.transferFocus();
				
				}
			}
		});
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(90, 182, 117, 29);
		contentPanel.add(btnQuit);
		
		
	}

	private void openInstructions() {
		wantInstructions = true;
		dispose();
		
	}
	
	private void openHighScores() {
		wantScores = true;
		dispose();
		
	}
	
	public boolean getInstructions(){
		return wantInstructions;
	}
	
	public boolean getScores(){
		return wantScores;
	}
	
	public boolean getStart(){
		return wantStart;
	}
	
	public void setStart(boolean value){
		wantStart = value;
	}
	
	public void setScores(boolean value){
		wantScores = value;
	}
	
	public void setInstructions(boolean value){
		wantInstructions = value;
	}
}
