package files;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Resources {
	

	
	
	public static String placePostData() {
		String res="/maps/api/place/add/json";
		return res;
	}

	
	public static String deleteData() {
		String res="/maps/api/place/delete/json";
		return res;
	}

	
	
}
