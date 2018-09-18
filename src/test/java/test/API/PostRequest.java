package test.API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostRequest {
	
	

	@Test
	public void searchPlace() {
		
		RestAssured.baseURI="https://maps.googleapis.com";
		
		given()
				.queryParam("key", "AIzaSyC8prKi35zdU0oeArC-c9SmW4nR3U1E_Qg")
				 .body("{\r\n" + 
					 		"  \"location\": {\r\n" + 
					 		"    \"lat\": -33.8669710,\r\n" + 
					 		"    \"lng\": 151.1958750\r\n" + 
					 		"  },\r\n" + 
					 		"  \"accuracy\": 50,\r\n" + 
					 		"  \"name\": \"Google Shoes!\",\r\n" + 
					 		"  \"phone_number\": \"(02) 9374 4000\",\r\n" + 
					 		"  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\r\n" + 
					 		"  \"types\": [\"shoe_store\"],\r\n" + 
					 		"  \"website\": \"http://www.google.com.au/\",\r\n" + 
					 		"  \"language\": \"en-AU\"\r\n" + 
					 		"}")
				 .when().post("/maps/api/place/add/json")
				 .then().assertThat().statusCode(202)
				 .and().body("status", equalTo("OK"));
		
		
						
				System.out.println("Request is executed succesfully");
		
	}


}
