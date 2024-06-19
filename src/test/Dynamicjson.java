package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import file.ReUsableMethods;
import file.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Dynamicjson {
	
	@Test(dataProvider="BooksData")
	public void addbook(String newtest,String jet)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response=  given().log().all().header("Content-Type", "application/json").
		body(payload.addBook(newtest,jet)).
		when().
		post("Library/Addbook.php")
		.then().log() .all().statusCode(200)
		.extract().response().asString();
	JsonPath jsq=	ReUsableMethods.rawtojson(response);
		String str=jsq.get("ID");
		System.out.println(str);
		
	}
	
	
	@DataProvider(name="BooksData")
	public Object[][] getdata()
	{
		//collcection og elements
		//multidimensioal rray --collection of array 
		return new Object[][] { {"america" , "728"},{"london" , "234" },{ "mumbai" , "890" } };
	}
	
}
