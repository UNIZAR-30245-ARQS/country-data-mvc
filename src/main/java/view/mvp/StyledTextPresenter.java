package view.mvp;

import java.util.*;

import model.CountryDAO;
import model.CountryDTO;

public class StyledTextPresenter implements Observer {
	private CountryDAO countryDAO;
	private StyledTextPassiveView view;
	
	public StyledTextPresenter(StyledTextPassiveView view) {
		this.view = view;
	}
	
	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
		this.countryDAO.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {		
		CountryDTO countryDTO = (CountryDTO) arg;	  
		view.setData(countryDTO.getName(), countryDTO.getPopulation());
	}

}
