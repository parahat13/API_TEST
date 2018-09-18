package test.API;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ValidateResponse {
	
	
	@Test
	public void verifyResponse() {
		
		String baseURI="https://maps.googleapis.com";
		RestAssured.baseURI=baseURI;
		
							given()
							.param("location", "-33.8670522,151.1957362")
							.param("radius", "1500")
							.param("type", "restaurant")
							.param("key", "AIzaSyAWf7gKMXOMWQM7QfAUzXpApjdPS1KR5aI")
							.when().get("/maps/api/place/nearbysearch/json")
							.then().assertThat().statusCode(200)
							.contentType(ContentType.JSON)
							.and().body("results[0].geometry.scope", equalTo("GOOGLE"))
							.and().body("results[13].name", equalTo("Prime Steak Restaurant"))
							.and().header("Server", "scaffolding on HTTPServer2");
							
							
		//response.prettyPrint();
				
				System.out.println("Request is executed succesfully");
		
	}

}
