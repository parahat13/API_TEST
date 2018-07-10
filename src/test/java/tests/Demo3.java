package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.PayLoad;
import files.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.*;



public class Demo3 {
	
	private static Logger log=LogManager.getLogger(Demo3.class.getName());
	
	Properties prop=new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		
		
		FileInputStream fis= new FileInputStream("C:\\Users\\Home\\eclipse-workspace\\API_TESTING\\src\\test\\java\\files\\env.properties");
		prop.load(fis);
		
		
	}
	@Test
	public void andAndDelete() {
		
		//Task 1- Grab the response
		log.info("Host information "+prop.getProperty("HOST"));
		RestAssured.baseURI=prop.getProperty("HOST");
		Response res= given()
					 .queryParam("key",prop.getProperty("KEY"))
					 .body(PayLoad.getPostData())
					 .when()
					 .post(Resources.placePostData())
					 .then().assertThat().statusCode(200).contentType(ContentType.JSON)
					 .and().body("status", equalTo("OK"))
					 .extract().response();
		
		
		//Task 1-Grab the place ID from the response
		
		String response=res.asString();
		log.info(response);
		
		JsonPath js=new JsonPath(response);
		String place_id = js.get("place_id");
		log.info(place_id);
		
		
		
		//Task 2-Place this place_id in the DELETE request
		
		given()
		.queryParam("key",prop.getProperty("KEY"))
		.body("{" + 
				"\"place_id\": \""+place_id+"\"" + 
				"}")
		.when()
		.post(Resources.deleteData())
		.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		.body("status", equalTo("OK"));
		
		
		
		
	}

}
