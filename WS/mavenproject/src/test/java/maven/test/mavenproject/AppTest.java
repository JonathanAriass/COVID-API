package maven.test.mavenproject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws UnirestException 
     */
    public void testApp() throws UnirestException
    {
        //assertTrue( true );
        
        
        HttpResponse<JsonNode> jsonResponse 
        = Unirest.get("http://www.mocky.io/v2/5a9ce37b3100004f00ab5154")
        .header("accept", "application/json").queryString("apiKey", "123")
        .asJson();

      assertNotNull(jsonResponse.getBody());
      assertEquals(200, jsonResponse.getStatus());
    }
}
