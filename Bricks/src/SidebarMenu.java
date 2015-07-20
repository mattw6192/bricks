import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SidebarMenu extends JPanel {
	private JTextField txtMenu;
	JLabel lblLevel;
	JLabel lblScore;
	JLabel lblMissiles;
	JLabel lblLives;
	JLabel lblActivePowerups;
	JLabel lblPowerups;
	private JButton btnExit;

	/**
	 * Create the panel.
	 */
	public SidebarMenu() {
		setLayout(null);
		
		txtMenu = new JTextField();
		txtMenu.setEditable(false);
		txtMenu.setBounds(6, 6, 303, 28);
		txtMenu.setHorizontalAlignment(SwingConstants.CENTER);
		txtMenu.setText("Menu (UNDER CONSTRUCTION)");
		add(txtMenu);
		txtMenu.setColumns(10);
		
		final JButton btnTest = new JButton("Mute/Unmute Sound");
		btnTest.setBounds(6, 46, 303, 29);
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Sound.isMuted==false){
					Sound.isMuted=true;
				}
				else{Sound.isMuted=false;}
				
				
			}
		});
		add(btnTest);
		
		lblLevel = new JLabel("Level: ");
		lblLevel.setBounds(29, 87, 160, 16);
		add(lblLevel);
		
		lblScore = new JLabel("Score: ");
		lblScore.setBounds(29, 115, 160, 16);
		add(lblScore);
		
		lblLives = new JLabel("Lives: 3");
		lblLives.setBounds(29, 143, 160, 16);
		add(lblLives);
		
		lblMissiles = new JLabel("Missiles: ");
		lblMissiles.setBounds(149, 143, 160, 16);
		add(lblMissiles);
		
		lblActivePowerups = new JLabel("Availiable Powerups:");
		lblActivePowerups.setBounds(29, 171, 144, 16);
		add(lblActivePowerups);
		
		lblPowerups = new JLabel("None");
		lblPowerups.setBounds(29, 171, 293, 67);
		add(lblPowerups);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(191, 520, 117, 52);
		add(btnExit);

	}
}
