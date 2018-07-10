package files;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Utils {
	
	
	static Properties prop = new Properties();

	@BeforeTest
	public void setUp() throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Home\\eclipse-workspace\\API_TESTING\\src\\test\\java\\files\\env.properties");
		prop.load(fis);

	}

	@Test
	public static XmlPath convertFromRawDataToXmlData(Response r) {

		String resp = r.asString();
		XmlPath x = new XmlPath(resp);
		return x;

	}

	@Test
	public static JsonPath convertFromRawDataToJsonData(Response r) {

		String resp = r.asString();
		JsonPath x = new JsonPath(resp);
		return x;

	}

	@Test
	public static String getSessionId() {
		
		// Creating session
		RestAssured.baseURI=prop.getProperty("JIRAHOST");
				
		Response response = given()
							.header("Content-Type", "application/json")
							.body("{ \"username\": \"parahat1380\", \"password\": \"Bayramaly80\" }")
							.when()
							.post("/rest/auth/1/session").then().statusCode(200)
							.extract().response();
		
		JsonPath jsonData = Utils.convertFromRawDataToJsonData(response);
		String sessionID = jsonData.get("session.value");
	   
		return sessionID;
	}
	
	@Test
	public static String test() {
		return null;
		
	} 
}
