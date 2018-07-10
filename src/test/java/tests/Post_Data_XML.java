package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Utils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Post_Data_XML {
	
	Properties prop = new Properties();

	@BeforeTest
	public void setUp() throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Home\\eclipse-workspace\\API_TESTING\\src\\test\\java\\files\\env.properties");
		prop.load(fis);

	}

	@Test
	public void postDataXML() throws IOException {
		
		String postData = GenerateStringFromResources("C:\\Users\\Home\\Desktop\\postdata.xml");
		RestAssured.baseURI=prop.getProperty("HOST");
		
		Response response = given()
						    .queryParam("key", prop.getProperty("KEY"))
						    .body(postData)
						    .log().all()
						    .when().post("/maps/api/place/add/xml")
						    .then().assertThat().statusCode(200).contentType(ContentType.XML)
						    .extract().response();
		
	//Convert raw data into String
	XmlPath x = Utils.convertFromRawDataToXmlData(response);
	System.out.println(x.get("PlaceAddResponse.place_id"));

	}

	@Test
	public static String GenerateStringFromResources(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}

}
