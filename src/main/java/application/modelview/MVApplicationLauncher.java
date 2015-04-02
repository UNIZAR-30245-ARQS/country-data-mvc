package application.modelview;

import javax.swing.*;

import view.modelview.*;
import application.*;

public class MVApplicationLauncher extends ApplicationLauncher {	

	public MVApplicationLauncher() {
		super();	
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {            	
				MVApplicationLauncher launcher = new MVApplicationLauncher();   
				OrderedByPopulationView orderedView = new OrderedByPopulationView(launcher.countryDAO);
				StyledTextView styledView = new StyledTextView(launcher.countryDAO);
				launcher.createAndShowMainWindow("Countries: Model-View", 
						new MVApplicationMainPanel(launcher.countryDAO, orderedView, styledView));
			}
		});
	}
}