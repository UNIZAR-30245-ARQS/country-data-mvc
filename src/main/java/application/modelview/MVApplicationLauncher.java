package application.modelview;

import javax.swing.*;

import model.*;
import application.*;

public class MVApplicationLauncher extends ApplicationLauncher {	

	public MVApplicationLauncher() {
		super();	
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {            	
				MVApplicationLauncher launcher = new MVApplicationLauncher();            			            	
				launcher.createAndShowMainWindow("Countries: Model-View", 
						new MVApplicationMainPanel(launcher.countryDAO));
			}
		});
	}

}
