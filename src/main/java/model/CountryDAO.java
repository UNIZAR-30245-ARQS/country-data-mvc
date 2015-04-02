package model;

import java.util.*;

import infrastructure.*;


public class CountryDAO extends Observable {
	private DataRepository dataAccessor;

	public CountryDAO(DataRepository dataAccessor) {		
		this.dataAccessor = dataAccessor;
	}

	public CountryDTO find(String countryName) throws Exception {
		Integer population = dataAccessor.getValue(countryName);
		if (population != null) {
			return new CountryDTO(countryName, population);
		} 
		return null;
	}

	public List<CountryDTO> findAll() throws Exception {
		ArrayList<CountryDTO> list = new ArrayList<CountryDTO>();
		for (String name: dataAccessor.getAllKeys()) {
			list.add(find(name));
		}
		return list;
	}

	public List<CountryDTO> findAllOrderedByPopulation() throws Exception {
		ArrayList<CountryDTO> orderedList = new ArrayList<CountryDTO>();
		for (String name: dataAccessor.getAllKeys()) {
			orderedList.add(find(name));
		}		
		Collections.sort(orderedList);
		return orderedList;
	}

	public void update (CountryDTO country) throws Exception{
		dataAccessor.updateData(country.getName(), country.getPopulation());
		setChanged();
		notifyObservers(country);
	}

	public void insert (CountryDTO country) throws Exception {
		dataAccessor.insertData(country.getName(), country.getPopulation());
		setChanged();
		notifyObservers(country);
	}

	public void delete (String countryName) throws Exception {
		CountryDTO countryDeleted = find (countryName);
		dataAccessor.deleteData(countryName);
		setChanged();
		notifyObservers(countryDeleted);
	}

	/**
	 * This checks if name may be correct for a new
	 * country: name is not empty and population is > 0. 
	 * It is just a simple example to show how/when user input could be validated.
	 */
	public boolean isValidCountryName(String name) {
		return name != null && name.length() > 0;
	}

	/**
	 * This checks if population may be correct for a new
	 * country: population is > 0. 
	 * It is just a simple example to show how/when user input could be validated.
	 */
	public boolean isValidCountryPopulation(int population) {
		return population > 0;
	}
}
