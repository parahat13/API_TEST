package twitter_API;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import  static io.restassured.RestAssured.*;

public class getLatestTweet {
	
	String consumerKey="pjW2M7NoLhO6pEUASPGjS4MTQ";
	String consumerSecret="mguFaqMLsahsHuQ8PeRY2DuoKZXqJSqNSeBNlN5bhbNPR5VJmY";
	String accessToken="1016362074983817218-A4IhnPXc3jmHsrJ2Pqrw2gv9zjGHQC";
	String secretToken="IcByxNty4CRz8AcKHaVmn9QUDezIXfroqljZW8ipoxNlO";
	
	String id;
	
	
	Properties prop = new Properties();

	@BeforeTest
	public void setUp() throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Home\\eclipse-workspace\\API_TESTING\\src\\test\\java\\files\\env.properties");
		//prop.load(fis);

	}

	@Test
	public void getLatestTwet() {
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
							.queryParam("count", "1")
							.when().get("/home_timeline.json")
							.then()
							.extract()
							.response();
		
		String res = response.asString();
		System.out.println(res);
		JsonPath js=new JsonPath(res);
		System.out.println(js.get("text"));
		System.out.println(js.get("id"));
	
	}
	
	@Test
	public void createTweet() {
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
							.queryParam("status", "I am creating  tweet")
							.when().post("/update.json")
							.then()
							.extract()
							.response();
		
		String res = response.asString();
		System.out.println(res);
		JsonPath js=new JsonPath(res);
		
		System.out.println("Tweet added Below");
		//System.out.println(js.get("text"));
		System.out.println(js.get("id"));
		id=js.get("id").toString();
	}
	
	@Test
	public void createAndDeleteTweet() {
		
		createTweet();
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		
		Response response = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken)
							.when().post("/destroy/"+id+".json")
							.then()
							.extract()
							.response();
		
		String res = response.asString();
		System.out.println(res);
		JsonPath js=new JsonPath(res);
		
		System.out.println("Tweet which got deleted with automation is below");
		System.out.println(js.get("text"));
		System.out.println(js.get("truncated"));
	
	}

}
