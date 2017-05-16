import java.awt.Frame;
import java.io.IOException;

import javax.swing.JTable;

import java.awt.BorderLayout;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class powerupsList extends javax.swing.JDialog{

	boolean hasStarted;
	Frame parentFrame;
	StartMenu menu;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
    /**
     * Creates new form HighScores
     * @throws IOException 
     */
    public powerupsList(java.awt.Frame parent, boolean modal, StartMenu tempMenu, boolean ifStarted, Frame tempFrame) throws IOException {

    	String[] columnNames = {"ID Number",
                "Powerup",
                "Color",
                "Decription"};
    	
    	Object[][] data = {
    		    {"1", "Magnet", "Green", "Temorarily makes the ball stick to the paddle until the user releases it."},
    		    {"2", "Ball Increase", "Green", "Increases the ball size."},
    		    {"3", "Paddle Increase", "Green", "Increases the paddle size."},
    		    {"4", "Speed Up", "White", "Speeds the ball up."},
    		    {"5", "Slow Down", "White", "Slows the ball down."},
     		    {"6", "Paddle Decrease", "White", "Decreases the size of the paddle."},
        		{"7", "Multiple Balls", "Green", "Adds two extra balls to the game."},
            	{"8", "Ball Decrease", "White", "Decreases the ball size."},
            	{"9", "Double Points", "Green", "Provides double points for a temporary time period."},
                {"10", "Fireball", "Green", "Allows the ball to destroy any brick it touches for a limited time."},
                {"11", "Metal Ball", "Green", "Allows the ball to do double damage to any brick it touches for a limited time."},
            	{"12", "Extra Life", "Green", "Gives the user an extra life."},
            	{"13", "Missile", "Green", "Gives the user a missile that can be shot and destroys any bricks it hits."},
            	{"14", "Machine Gun", "Green", "Shoots mulitple bullets from each end of the paddle at the bricks."},
            	{"15", "Insanity", "Red", "Top Secret!!"},
             	{"16", "Lose a Life", "Red", "Removes a life from the user."},
                {"17", "Extra Points", "Green", "Provides the user with a random amount of points."},
                {"18", "Lose Points", "Red", "Removes a certain amount of points from the user."},
                {"19", "Golden Ball", "Green", "Top Secret!!"},
                {"20", "Safety Net", "Green", "Allows for two balls to remain in play if they get past the paddle."},     
        		
    		};
    	
    	
    	
    	table = new JTable(data, columnNames);
    	
    	
    	table.getColumnModel().getColumn(0).setMaxWidth(25);
    	table.getColumnModel().getColumn(1).setMaxWidth(125);
    	table.getColumnModel().getColumn(2).setMaxWidth(50);
    	table.getColumnModel().getColumn(3).setMaxWidth(500);
    	table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    	
    	getContentPane().add(table, BorderLayout.CENTER);
    
    }
    

    // </editor-fold>//GEN-END:initComponents
}
