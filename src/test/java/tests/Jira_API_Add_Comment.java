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


public class Jira_API_Add_Comment {
	

	Properties prop = new Properties();

	@BeforeTest
	public void setUp() throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Home\\eclipse-workspace\\API_TESTING\\src\\test\\java\\files\\env.properties");
		//prop.load(fis);

	}

	@Test
	public void addComment() {
		
		// Creating issue/Defect
		RestAssured.baseURI="http://localhost:8090";
		
		Response res = given()
							.header("Content-Type", "application/json")
							.header("Cookie", "JSESSIONID"+Utils.getSessionId())
							.pathParams("commentid", "10102")
							.body("{\r\n" + 
									"	\"body\": \" hey I am updating  commment\",\r\n" + 
									"	\"visibility\": {\r\n" + 
									"		\"type\": \"role\",\r\n" + 
									"		\"value\": \"Administrators\"\r\n" + 
									"	}\r\n" + 
									"}").when()
							.post("/rest/api/2/issue/10102/comment/{commentid}").then().statusCode(201)
							.extract().response();
		
		JsonPath js= Utils.convertFromRawDataToJsonData(res);
		String id=js.getString("id");
		System.out.println(id);
		
	
	}
}
