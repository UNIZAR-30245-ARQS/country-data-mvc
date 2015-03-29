package model;

public class CountryDTO implements Comparable<CountryDTO> {

	private String name;
	private int population;

	public CountryDTO(String name, int population) {
		this.name = name;
		this.population = population;
	}

	public String getName() {
		return name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int newPopulation) {
		if (newPopulation > 0) {
			population = newPopulation;
		}
	}

	/**
	 * I say that "natural ordering" for CountryDTOs is on their population 
	 * (largest comes first)
	 */
	@Override
	public int compareTo(CountryDTO o) {
		return o.population - population;		
	}

}
