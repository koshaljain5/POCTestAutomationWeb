package Testpoc;

import org.testng.Assert;
import org.testng.annotations.Test;

import groovy.util.logging.Log;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;

public class videogameAPI {

	static String gameID = "100";
	
	//TC-1: PUT:- Place a new record in DB, Verify Status code and Message
	@Test(priority = 1, groups = "UpdateData")
	public void test_post()
	{
		HashMap data = new HashMap();
		data.put("id", gameID);
		data.put("name", "TestPocGame");
		data.put("releaseDate", "2020-05-10T00:00:00.000Z");
		data.put("reviewScore", "5");
		data.put("category", "Adventure");
		data.put("rating", "Universal");
		
		Response resp = 
		given().contentType("application/json").body(data)
			.when().post("http://localhost:8080/app/videogames").then().statusCode(200)
				.log().body().extract().response();
		
		String respjson = resp.asString();
		Assert.assertEquals(respjson.contains("Record Added Successfully"), true);
		
	}
	
	//TC-2: GET:- Get the newly placed record, Verify Status code and details of new record
	@Test(priority = 2, groups = "GetData")
	public void test_get()
	{
		given().when().get("http://localhost:8080/app/videogames/"+gameID)
			.then().statusCode(200)
				.body("videoGame.id", equalTo(gameID))
				.body("videoGame.name", equalTo("TestPocGame"));
	}
	
	//TC-3: PUT:- Update the newly placed record, Verify Status code and details of updated record
	@Test(priority = 3, groups = "UpdateData")
	public void test_put()
	{
		HashMap data = new HashMap();
		data.put("id", gameID);
		data.put("name", "TestPocGameUpdated");
		data.put("releaseDate", "2020-05-10T00:00:00.000Z");
		data.put("reviewScore", "5");
		data.put("category", "Adventure");
		data.put("rating", "Universal");

		given().contentType("application/json").body(data)
			.when().put("http://localhost:8080/app/videogames/"+gameID).then().statusCode(200)
			.log().body()
				.body("videoGame.id", equalTo(gameID))
				.body("videoGame.name", equalTo("TestPocGameUpdated"));	
	}
	
	//TC-4: DELETE:- Delete the record, Verify Status code and Message
	@Test(priority = 4, groups = "UpdateData")
	public void test_delete() throws InterruptedException
	{
		Response resp = 
		given().when().delete("http://localhost:8080/app/videogames/"+gameID)
			.then().statusCode(200)
				.log().body().extract().response();
		Thread.sleep(3000);
		String respjson = resp.asString();
		Assert.assertEquals(respjson.contains("Record Deleted Successfully"), true);		
	}
	
	//TC-5: GET:- Get the non-existing record, Verify Status code and Message (Negative Scenario)
	@Test(priority = 5, groups = "GetData")
	public void test_getAgain()
	{
		String temp = "";
		given().when().get("http://localhost:8080/app/videogames/"+gameID)
			.then().statusCode(500)
				.log().body()
					.body("Map.error", equalTo("Internal Server Error"));
				
	}	
}
