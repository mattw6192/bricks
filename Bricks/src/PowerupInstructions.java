import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;


public class PowerupInstructions extends JPanel {
	private final JLabel lblGreen = new JLabel();
	

	/**
	 * Create the panel.
	 */
	public PowerupInstructions() {
		setBackground(UIManager.getColor("Button.background"));
		setLayout(null);
		lblGreen.setBounds(99, 45, 61, 16);
		lblGreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green powerup.png")));
		add(lblGreen);
		
		
		
		JLabel lblPowerupInformation = new JLabel("Powerup Information");
		lblPowerupInformation.setBounds(164, 6, 131, 16);
		add(lblPowerupInformation);
		
		JTextArea txtrGreenColoredPowerups = new JTextArea();
		txtrGreenColoredPowerups.setEditable(false);
		txtrGreenColoredPowerups.setBackground(UIManager.getColor("Button.background"));
		txtrGreenColoredPowerups.setText("Green colored powerups\nare beneficial.");
		txtrGreenColoredPowerups.setBounds(240, 45, 164, 68);
		add(txtrGreenColoredPowerups);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/white powerup.png")));
		lblNewLabel.setBounds(99, 157, 61, 16);
		add(lblNewLabel);
		
		JTextArea txtrWhitePowerupsAre = new JTextArea();
		txtrWhitePowerupsAre.setEditable(false);
		txtrWhitePowerupsAre.setBackground(UIManager.getColor("Button.background"));
		txtrWhitePowerupsAre.setText("White colored powerups \nare neutral.");
		txtrWhitePowerupsAre.setBounds(240, 157, 164, 50);
		add(txtrWhitePowerupsAre);
		
		JLabel lblI = new JLabel();
		lblI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/skull_and_bones2.png")));
		lblI.setBounds(99, 264, 41, 25);
		add(lblI);
		
		JTextArea txtrYouBetterLeave = new JTextArea();
		txtrYouBetterLeave.setEditable(false);
		txtrYouBetterLeave.setBackground(UIManager.getColor("Button.background"));
		txtrYouBetterLeave.setText("YOU BETTER LEAVE\nTHIS ONE ALONE BOY.\nIF YA KNOW WHATS \nGOOD FOR YA.");
		txtrYouBetterLeave.setBounds(240, 264, 164, 75);
		add(txtrYouBetterLeave);
		
		


	}
}
