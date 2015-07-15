import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class HighScores extends javax.swing.JDialog{

	boolean hasStarted;
	Frame parentFrame;
	startMenu4 menu;
    /**
     * Creates new form HighScores
     * @throws IOException 
     */
    public HighScores(java.awt.Frame parent, boolean modal, startMenu4 tempMenu, boolean ifStarted, Frame tempFrame) throws IOException {
        super(parent, modal);
        initComponents();
        hasStarted = ifStarted;
        menu = tempMenu;
        parentFrame = tempFrame;
    }
    
    public void setLocalScores(Game thisGame){
    	LocalScoreLabel.setText("Your Score is: " + thisGame.Score);
    	
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws IOException {

        scoreLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        closeButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        LocalScoreLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        scoreLabel.setText("High Scores");
        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }

			private void exitButtonActionPerformed(ActionEvent evt) {
				System.exit(ABORT);
				
			}
        });
        
        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (hasStarted == true){
                	closeButtonActionPerformed(evt);
                }else if (hasStarted == false){
                	closeButtonReturnAction(evt);
                }
            }

			private void closeButtonActionPerformed(ActionEvent evt) {
				setVisible(false);	
				parentFrame.setVisible(true);
			}
			
			private void closeButtonReturnAction(ActionEvent evt) {
				setVisible(false);
				parentFrame.setVisible(false);
				menu.setVisible(true);
			}
        });
        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
 
        BufferedReader in = new BufferedReader(new FileReader("scores.dat"));
        String line = in.readLine();
        while(line != null){
          jTextArea1.append(line + "\n");
          line = in.readLine();
        }
        in.close();
        
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(132)
        					.addComponent(scoreLabel))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(45)
        					.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(100)
        					.addComponent(LocalScoreLabel)))
        			.addContainerGap(45, Short.MAX_VALUE))
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addContainerGap(59, Short.MAX_VALUE)
        			.addComponent(exitButton)
        			.addGap(51)
        			.addComponent(closeButton)
        			.addGap(70))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(15)
        			.addComponent(scoreLabel)
        			.addGap(18)
        			.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(LocalScoreLabel)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(30)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(closeButton)
        						.addComponent(exitButton))))
        			.addContainerGap(104, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel LocalScoreLabel;
    // End of variables declaration//GEN-END:variables
}
