package test.API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class EndToEndTesting {
	
	
	@Test
	public void searchPlace() {
		
		String body="{\r\n" + 
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
				 		"}";
	
		
		RestAssured.baseURI="https://maps.googleapis.com";
		
		Response response=given()
						 .queryParam("key", "AIzaSyC8prKi35zdU0oeArC-c9SmW4nR3U1E_Qg")
						 .body(body)
						 .when().post("/maps/api/place/add/json")
						 .then().assertThat().statusCode(202)
						 .and().body("status", equalTo("OK"))
						 .extract().response();
		
		//To convert response to string
		String res=response.asString();		
		
		//convert string to Json
		JsonPath jsonResponse= new JsonPath(res);
		String place_Id = jsonResponse.get("place_Id");
		System.out.println(place_Id);
		
		
		//To Delete 
		given().queryParam("key", "AIzaSyC8prKi35zdU0oeArC-c9SmW4nR3U1E_Qg")
				.body("{" + "\"place_id\": \"place ID\"" + "}")
				.when().post("/maps/api/place/delete/json")
				.then().assertThat().statusCode(200)
				.and().body("status", equalTo("OK"));
		
		

	}
}
