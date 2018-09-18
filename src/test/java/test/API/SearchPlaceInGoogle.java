package test.API;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SearchPlaceInGoogle {
	
	
	@Test
	public void searchPlace() {
		
		String baseURI="https://maps.googleapis.com";
		RestAssured.baseURI=baseURI;
		
							given()
							.param("location", "-33.8670522,151.1957362")
							.param("radius", "1500")
							.param("type", "restaurant")
							.param("key", "AIzaSyACVo5_jNtxnUdP7kMJYqq0bJczmmjZalI")
							.when().get("/maps/api/place/nearbysearch/json")
							.then().assertThat().statusCode(200)
							.contentType(ContentType.JSON)
							.and().body("results[0].geometry.scope", equalTo("GOOGLE"));
							
		//response.prettyPrint();
				
				System.out.println("Request is executed succesfully");
		
	}

}
