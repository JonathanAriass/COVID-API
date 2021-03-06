package maven.test.mavenproject;

import maven.test.mavenproject.logic.Country;
import maven.test.mavenproject.logic.DataRequester;


/**
 * Main class where all code will be executed
 * 
 * @author Arias
 * @version 0.1
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        DataRequester d = new DataRequester("spain");
        d.requestDataCountries();
        d.requestCasesForCountry(new Country("spain"));
    }
}
