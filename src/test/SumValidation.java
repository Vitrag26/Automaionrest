package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import file.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumofCourses()
	{
		int sum=0;
        JsonPath bd = new JsonPath(payload.CoursePrice());
        int count = bd.getInt("courses.size()");
        for(int i=0;i<count;i++)
        {
        	int proce=bd.getInt("courses["+i+"].price");
        	int copies=bd.getInt("courses["+i+"].copies");
        	int amount=proce * copies;
        	System.out.println(amount);
        	sum=sum+amount;
        	
        }
        System.out.println(sum);
        int purchaseamount=bd.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, purchaseamount);
        

	}

}
