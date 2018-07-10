package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Utils;


public class ExtractingNamesAPI {
	
	

	Properties prop = new Properties();

	@BeforeTest
	public void setUp() throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Home\\eclipse-workspace\\API_TESTING\\src\\test\\java\\files\\env.properties");
		prop.load(fis);

	}

	
	@Test
	public void extractName() {
	RestAssured.baseURI=prop.getProperty("HOST");
		
	Response response=given()
						.param("location" ,"-33.8670522,151.1957362")
						.param("radius", "500")
						.param("key", "AIzaSyBlN9gIFQnRigmW2zQjhFkyDm8oero5Gtw")
						.log().all()
						.when()
						.get("/maps/api/place/nearbysearch/json")
						.then().assertThat().statusCode(200)
						.and().contentType(ContentType.JSON)
						.and().body("results[0].name", equalTo("Sydney"))
						.and().body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"))
						.and().header("Server", "scaffolding on HTTPServer2")
						.and().extract().response();
		
		JsonPath jsonData = Utils.convertFromRawDataToJsonData(response);
		int count = jsonData.get("results.size()");
		
		for(int i=0; i<count; i++) {
			System.out.println(jsonData.get("results["+i+"].name"));
		}
		System.out.println(count);
		
		
		

	}

}
