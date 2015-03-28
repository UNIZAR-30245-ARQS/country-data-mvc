package application.modelview;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import model.*;
import application.*;

public class MVApplicationLauncher extends ApplicationLauncher {	
	
	public MVApplicationLauncher() {
		super();				
	}
	
	private void createAndShowMainWindow() {	 	
        JFrame frame = new JFrame("Countries: Model-View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new MVApplicationMainPanel(this.countryDAO));
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	MVApplicationLauncher launcher = new MVApplicationLauncher();
            	launcher.createAndShowMainWindow();
            }
        });
	}

}
