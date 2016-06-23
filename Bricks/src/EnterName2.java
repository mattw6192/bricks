import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

  public class EnterName2 extends javax.swing.JDialog {
  
      private String userName;
      java.awt.event.ActionEvent TempEventSave;
      private JTextField textField;
    
	/**
     * Creates new form EnterName
     */
    public EnterName2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        getContentPane().setLayout(null);
        
        JLabel lblCongratulationsYouHave = new JLabel("Congratulations! You have made a high score!!");
        lblCongratulationsYouHave.setBounds(58, 34, 450, 16);
        getContentPane().add(lblCongratulationsYouHave);
        
        JLabel lblEnterYourName = new JLabel("Enter your Name:");
        lblEnterYourName.setBounds(58, 62, 166, 74);
        getContentPane().add(lblEnterYourName);
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TempEventSave = e;
        		btnSubmitActionPerformed(e);
        	}
        });
        
        btnSubmit.addKeyListener(new java.awt.event.KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == 10){
					btnSubmitActionPerformed(TempEventSave);
				}
				//System.out.println(arg0.getKeyCode()); // ENTER Key_Code is 10;
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        });
        
       // textField.addActionListener(new java.awt.event.ActionListener() {
         //   public void actionPerformed(java.awt.event.ActionEvent evt) {
           //     TempEventSave = evt;
            //	textFieldActionPerformed(evt);
            //}
        //});
        
        /*
        textField.addKeyListener(new java.awt.event.KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == 10){ // enter key
					btnSubmitActionPerformed(TempEventSave);
				}
				//System.out.println(arg0.getKeyCode()); // ENTER Key_Code is 10;
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        });
        */
        
        btnSubmit.setBounds(58, 182, 300, 29);
        getContentPane().add(btnSubmit);
        
        textField = new JTextField();
        textField.setToolTipText("Enter Your Name Here");
        textField.setBounds(58, 120, 300, 29);
        getContentPane().add(textField);
        textField.setColumns(10);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        //initComponents();
    }

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      	//System.out.println("made it here"); 
    	Boolean check = checkIfEmpty(); // checks if the name has at least two characters
      	 if (check == false){
      		userName = textField.getText(); 
      		setVisible(false);
      	 }else{
      		 System.out.println("You must enter a name with at least 2 characters.");
      	 }
      	//System.out.println(userName);
           
      }
    
    private void textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }
    
    public boolean checkIfEmpty(){
    	if (textField.getText().length() >= 2) {
    		return false; // true means it is not empty
    	}
    	this.userName = "Guest";
    	return true;
    }
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    
}
