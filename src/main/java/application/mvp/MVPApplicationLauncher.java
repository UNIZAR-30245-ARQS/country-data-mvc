package application.mvp;

import javax.swing.*;

import view.mvp.*;
import application.*;

public class MVPApplicationLauncher extends ApplicationLauncher {
	public MVPApplicationLauncher() {
		super();	
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MVPApplicationLauncher launcher = new MVPApplicationLauncher();
				
				OrderedByPopulationPassiveView orderedView = 
						new OrderedByPopulationPassiveView();		
				OrderedByPopulationPresenter orderedPresenter = 
						orderedView.getPresenter();		
				orderedPresenter.setCountryDAO(launcher.countryDAO);
				
				StyledTextPassiveView styledView =
						new StyledTextPassiveView();
				StyledTextPresenter styledPresenter =
						styledView.getPresenter();
				styledPresenter.setCountryDAO(launcher.countryDAO);
				
				launcher.createAndShowMainWindow("Countries: Model-View-Presenter (Passive View)", 
						new MVPApplicationMainPanel(launcher.countryDAO, orderedView, styledView));
			}
		});
	}
}
