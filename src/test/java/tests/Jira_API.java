package tests;

import org.testng.annotations.Test;

import files.Utils;
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


public class Jira_API {
	

	Properties prop = new Properties();

	@BeforeTest
	public void setUp() throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Home\\eclipse-workspace\\API_TESTING\\src\\test\\java\\files\\env.properties");
		//prop.load(fis);

	}

	@Test
	public void test1() {
		
		// Creating issue/Defect
		RestAssured.baseURI="http://localhost:8090";
		
		Response res = given()
							.header("Content-Type", "application/json")
							.header("Cookie", "JSESSIONID"+Utils.getSessionId())
							.body("{" + 
									"\"fields\":{" + 
									"\"project\":{" + 
									"\"key\": \"RES\"" + 
									"}," + 
									"\"summary\": \"Adding new Issue\"," + 
									"\"description\": \"Creating second first bug\"," + 
									"\"issuetype\": {" + 
									"\"name\": \"Bug\"" + 
									"}"+
									"}}").when()
							.post("/rest/api/2/issue")
							.then().statusCode(201)
							.extract().response();
		JsonPath js =Utils.convertFromRawDataToJsonData(res);
		String id = js.get("id");
		System.out.println(id);
								
							
		

	}
}
