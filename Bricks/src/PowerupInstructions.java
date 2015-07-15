import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;


public class PowerupInstructions extends JPanel {
	private final JLabel lblGreen = new JLabel("green ");

	/**
	 * Create the panel.
	 */
	public PowerupInstructions() {
		setBackground(UIManager.getColor("Button.background"));
		setLayout(null);
		lblGreen.setBounds(99, 45, 61, 16);
		add(lblGreen);
		
		
		
		JLabel lblPowerupInformation = new JLabel("Powerup Information");
		lblPowerupInformation.setBounds(164, 6, 131, 16);
		add(lblPowerupInformation);
		
		JTextArea txtrGreenColoredPowerups = new JTextArea();
		txtrGreenColoredPowerups.setBackground(UIManager.getColor("Button.background"));
		txtrGreenColoredPowerups.setText("Green colored powerups\nare beneficial.");
		txtrGreenColoredPowerups.setBounds(240, 45, 164, 68);
		add(txtrGreenColoredPowerups);
		
		JLabel lblNewLabel = new JLabel("White");
		lblNewLabel.setBounds(99, 157, 61, 16);
		add(lblNewLabel);
		
		JTextArea txtrWhitePowerupsAre = new JTextArea();
		txtrWhitePowerupsAre.setBackground(UIManager.getColor("Button.background"));
		txtrWhitePowerupsAre.setText("White colored powerups \nare neutral.");
		txtrWhitePowerupsAre.setBounds(240, 157, 164, 50);
		add(txtrWhitePowerupsAre);

	}
}
