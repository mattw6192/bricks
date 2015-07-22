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
	JButton btnPause;
	JLabel lblCurrentPowerups;
	JLabel lblactivePowerups;

	/**
	 * Create the panel.
	 */
	public SidebarMenu() {
		setLayout(null);
		
		txtMenu = new JTextField();
		txtMenu.setEditable(false);
		txtMenu.setBounds(6, 6, 303, 28);
		txtMenu.setHorizontalAlignment(SwingConstants.CENTER);
		txtMenu.setText("Menu");
		add(txtMenu);
		txtMenu.setColumns(10);
		
		btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Game.isPaused==false){
					Game.isPaused=true;
					btnPause.setText("Resume Game");
				}
				else{
					Game.isPaused=false;
					btnPause.setText("Pause Game");
				}
				btnPause.transferFocus();
				btnExit.transferFocus();
			}
		});
		
		final JButton btnTest = new JButton("Mute Sound");
		btnTest.setBounds(6, 46, 303, 29);
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Sound.isMuted==false){
					Sound.isMuted=true;
					btnTest.setText("Unmute Sound");
				}
				else{
					Sound.isMuted=false;
					btnTest.setText("Mute Sound");
				}
				btnTest.transferFocus();
				btnPause.transferFocus();
				btnExit.transferFocus();
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
		
		
		btnPause.setBounds(29, 520, 117, 52);
		add(btnPause);
		
		lblCurrentPowerups = new JLabel("Current Powerups:");
		lblCurrentPowerups.setBounds(29, 237, 144, 16);
		add(lblCurrentPowerups);
		
		lblactivePowerups = new JLabel("None");
		lblactivePowerups.setBounds(29, 237, 293, 67);
		add(lblactivePowerups);

	}
}
