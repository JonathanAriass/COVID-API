package maven.test.mavenproject.logic;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


/**
 * Class where all data will be requested and stored
 * 
 * @author Arias
 * @version 0.1
 *
 */
public class DataRequester {

	private String country;
	private Country[] countries;
	private HashMap<Country, Integer> cases;
	
	/**
	 * Constructor of the DataRequester object
	 * 
	 * @param country Name of the country to search data about
	 */
	public DataRequester(String country) {
		this.country = country;
	}

	/**
	 * Method that get Country array
	 * 
	 * @return Countries array
	 */
	public Country[] getData() {
		return countries;
	}

	/**
	 * Method that get the value of contry we want to search data about
	 * 
	 * @return Name of the country to search data about
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Method that get the countries names from RapidAPI API Covid "https://rapidapi.com/api-sports/api/covid-193/"
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws UnirestException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public void requestDataCountries() throws UnsupportedEncodingException, UnirestException{
		// RapidAPI host URL
		String host_countries = "https://covid-193.p.rapidapi.com/countries";
    	// Charset for URLEncoder
		String charset = "UTF-8";
    	
    	// RapidAPI host
    	String x_rapidapi_host = "covid-193.p.rapidapi.com";
    	
    	// You should put your RapidAPI token in here
    	String x_rapidapi_key = ""; 
    	
    	
    	String query = String.format("s=%s", URLEncoder.encode("tt0110912", charset));
    	System.out.println(query);
    	HttpResponse<String> response = Unirest.get(host_countries)
    			.header("X-RapidAPI-Host", x_rapidapi_host)
        		.header("X-RapidAPI-Key", x_rapidapi_key)
    			.asString();
    	

    	// Using Gson for getting the rigth format on Json
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	// Creating the Json parser
    	JsonParser jp = new JsonParser();
    	// Parsing the response body into a JsonElement
    	JsonElement je = jp.parse(response.getBody().toString());
    	// Prettifying with Gson to get a Json format String object
      	String prettyJsonString = gson.toJson(je);
    	
    	// Parse json data into a json object
    	JsonObject jobj = (JsonObject) jp.parse(prettyJsonString);
    	
    	// Get the part of response where the interesting data really is
    	JsonArray jarr = (JsonArray) jobj.get("response");
    	
    	// Initialize the country array
    	countries = new Country[jarr.size()];
    	
    	// Adding the countries from de the Json Array into the Country array
    	for (int i = 0; i < jarr.size(); i++) {
    		countries[i] = new Country(jarr.get(i).toString());
    	}
    	
    	// Print countries from Country array
    	printCountries();
	}
	
	public void requestCases() throws UnsupportedEncodingException, UnirestException {
		// RapidAPI host URL
		String host_countries = "https://covid-193.p.rapidapi.com/countries";
		// Charset for URLEncoder
		String charset = "UTF-8";
		    	
		// RapidAPI host
		String x_rapidapi_host = "covid-193.p.rapidapi.com";
		    	
    	// You should put your RapidAPI token in here
		String x_rapidapi_key = ""; 
		    	    	
		String query = String.format("s=%s", URLEncoder.encode("tt0110912", charset));
		System.out.println(query);
		HttpResponse<String> response = Unirest.get(host_countries)
				.header("X-RapidAPI-Host", x_rapidapi_host)
				.header("X-RapidAPI-Key", x_rapidapi_key)
				.asString();
		
	}
	
	/**
	 * Method that prints countries array
	 */
	private void printCountries() {
		for (Country c : countries) {
			System.out.println(c.toString());
		}
 	}
	
	/**
	 * Class that represent a Country by the name
	 * 
	 * @author arias
	 * @version 0.1
	 */
	protected class Country {
	
		private String countryName; // Country name field
		
		/**
		 * Constructor for the Country object
		 * 
		 * @param countryName Name of the country
		 */
		protected Country(String countryName) {
			this.countryName = countryName;
		}

		@Override
		public String toString() {
			return "Country [countryName=" + countryName + "]";
		}
		
	}
	
	
}
