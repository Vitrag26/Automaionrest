package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matcher.*;

import file.ReUsableMethods;
import file.payload;

public class AddJson {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//given --all details
		//when --submit the api resource .http method
		//then --validate the respone 
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = RestAssured.given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json").body(new String(Files.readAllBytes(Paths.get("C:\\Users\\VITRAG JAIN\\OneDrive\\Desktop\\add.json")))).when()
				.post("/maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeid = js.getString("place_id");
		System.out.println(placeid);
		
		//update place
		String add="kharadi";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+add+"\",\r\n"				
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").
		when().put("/maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Given Place
		
		
		String getresponse =given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeid).when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		System.out.println(getresponse);
		JsonPath js1=ReUsableMethods.rawtojson(getresponse);
		String actualAdress=js1.getString("address");
		System.out.println(actualAdress);
	Assert.assertEquals(actualAdress, "pacifi");
	
		
		
	}

}
