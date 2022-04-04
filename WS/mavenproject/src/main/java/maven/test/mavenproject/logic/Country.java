package maven.test.mavenproject.logic;

/**
 * Class that represent a Country by the name
 * 
 * @author arias
 * @version 0.1
 */
public class Country {

	private String countryName; // Country name field
	
	/**
	 * Constructor for the Country object
	 * 
	 * @param countryName Name of the country
	 */
	public Country(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return countryName;
	}
	
}
