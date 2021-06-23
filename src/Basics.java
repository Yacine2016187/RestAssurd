import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.PayLoad;
import Files.ReUsableMethodes;
public class Basics {

	public static void main(String[] args) {

// Validate if add place API is working as expected 
		
		//given --- all input details
		//when --- submit the API
		//there ---- Validate the response 
		
		
		
		//Add New Place
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().queryParam("key", "qaclick123").header("content-type","application/JSON").body(PayLoad.Addplace())
		.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);

		JsonPath Js=new JsonPath(response);
		String PlaceID=Js.getString("place_id");
		System.out.println("the place ID is: "+PlaceID);
		
		//Update Place 
		System.out.println("*************************************************");
		
		String newAddress="70 Summer walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("content-type","application/JSON")
		.body("{\r\n"
				+ "\"place_id\":\""+PlaceID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json").then().log().all().statusCode(200);
		
		System.out.println("*************************************************");
		//Get Place
		
		String GetPlace= given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",PlaceID)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath JsGet= ReUsableMethodes.rawToJson(GetPlace);
		
		String actualAdress=JsGet.getString("address");
		System.out.println(actualAdress);
		
		Assert.assertEquals(actualAdress, newAddress);
}
}