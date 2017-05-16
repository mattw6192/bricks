import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JButton;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PowerupInstructions extends javax.swing.JDialog {
	private final JLabel lblGreen = new JLabel();
	/**
	 * @wbp.nonvisual location=256,451
	 */
	
	

	/**
	 * Create the panel.
	 */
	public PowerupInstructions(java.awt.Frame parent, boolean modal, StartMenu tempMenu, boolean ifStarted, Frame tempFrame) {
		super(parent, modal);
		setBackground(UIManager.getColor("Button.background"));
		getContentPane().setLayout(null);
		lblGreen.setBounds(45, 45, 61, 16);
		lblGreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green powerup.png")));
		getContentPane().add(lblGreen);
		
		
		
		//JLabel lblPowerupInformation = new JLabel("Powerup Information");
		//lblPowerupInformation.setBounds(164, 6, 131, 16);
		//getContentPane().add(lblPowerupInformation);
		
		JTextArea txtrGreenColoredPowerups = new JTextArea();
		txtrGreenColoredPowerups.setEditable(false);
		txtrGreenColoredPowerups.setBackground(UIManager.getColor("Button.background"));
		txtrGreenColoredPowerups.setText("Green colored powerups provide benefits");
		txtrGreenColoredPowerups.setBounds(150, 45, 260, 50);
		getContentPane().add(txtrGreenColoredPowerups);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/white powerup.png")));
		lblNewLabel.setBounds(45, 100, 61, 16);
		getContentPane().add(lblNewLabel);
		
		JTextArea txtrWhitePowerupsAre = new JTextArea();
		txtrWhitePowerupsAre.setEditable(false);
		txtrWhitePowerupsAre.setBackground(UIManager.getColor("Button.background"));
		txtrWhitePowerupsAre.setText("White colored powerups are neutral.");
		txtrWhitePowerupsAre.setBounds(150, 97, 250, 50);
		getContentPane().add(txtrWhitePowerupsAre);
		
		JLabel lblNewLabel2 = new JLabel();
		lblNewLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red powerup.png")));
		lblNewLabel2.setBounds(45, 150, 61, 16);
		getContentPane().add(lblNewLabel2);
		
		JTextArea txtrRedPowerupsAre = new JTextArea();
		txtrRedPowerupsAre.setEditable(false);
		txtrRedPowerupsAre.setBackground(UIManager.getColor("Button.background"));
		txtrRedPowerupsAre.setText("Red colored powerups are provide disadvantages.");
		txtrRedPowerupsAre.setBounds(150, 147, 325, 50);
		getContentPane().add(txtrRedPowerupsAre);
		
		JLabel lblI = new JLabel();
		lblI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/skull_and_bones2.png")));
		lblI.setBounds(45, 190, 41, 25);
		getContentPane().add(lblI);
		
		JTextArea txtrYouBetterLeave = new JTextArea();
		txtrYouBetterLeave.setEditable(false);
		txtrYouBetterLeave.setBackground(UIManager.getColor("Button.background"));
		txtrYouBetterLeave.setText("TOP SECRET");
		txtrYouBetterLeave.setBounds(150, 197, 164, 50);
		getContentPane().add(txtrYouBetterLeave);
		
		JButton btnPowerupsList = new JButton("Powerups List");
		btnPowerupsList.setBounds(125, 250, 200, 29);
		getContentPane().add(btnPowerupsList);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				System.exit(0);
			}
		});
		btnQuit.setBounds(32, 300, 164, 29);
		getContentPane().add(btnQuit);
		
		JButton btnPlayGame = new JButton("Play Game");
		btnPlayGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPlayGame.setBounds(240, 300, 164, 29);
		getContentPane().add(btnPlayGame);
		
		


	}
}
