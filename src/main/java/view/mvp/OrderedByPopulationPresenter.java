package view.mvp;

import java.util.*;

import model.CountryDAO;
import model.CountryDTO;

public class OrderedByPopulationPresenter implements Observer {
	private CountryDAO countryDAO;
	private OrderedByPopulationPassiveView view;

	public OrderedByPopulationPresenter(	OrderedByPopulationPassiveView view) {
		this.view = view;
	}
	
	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
		this.countryDAO.addObserver(this);
		List<String> dataList = new ArrayList<String>();
		try {
			for (CountryDTO country: countryDAO.findAllOrderedByPopulation()) {
				dataList.add(country.getName()+", "+country.getPopulation());
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Exception reading data from the model. We can't go on
			System.exit(1);
		}        
		view.setData(dataList);
	}

	@Override
	public void update(Observable o, Object arg) {		
		List<String> dataList = new ArrayList<String>();
		try {
			for (CountryDTO country: countryDAO.findAllOrderedByPopulation()) {
				dataList.add(country.getName()+", "+country.getPopulation());
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Exception reading data from the model. We can't go on
			System.exit(1);
		}        
		view.setData(dataList);
	}
	
	public void deleteSelectedCountry(String selected) {		
		if (selected != null) {
			String countryName = selected.substring(0, selected.indexOf(','));
			try {
				countryDAO.delete(countryName);
			} catch (Exception ex) {
				ex.printStackTrace();
				// Deleting a country which does not exist should not happen. Exit
				System.exit(1);
			}
		}
	}
}
