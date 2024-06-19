package test;

import file.payload; // Ensure this import points to the correct package and class
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {
        JsonPath bd = new JsonPath(payload.CoursePrice());
        int count = bd.getInt("courses.size()");
        System.out.println("Number of courses: " + count);
        
        int totalAmount = bd.getInt("dashboard.purchaseAmount");
        System.out.println("Total purchase amount: " + totalAmount);
        
        String course = bd.getString("courses[2].title");
        System.out.println("Third course title: " + course);
        
        // Loop to print course titles and prices
        for (int i = 0; i < count; i++) {
            String courseTitle = bd.getString("courses[" + i + "].title");
            int coursePrice = bd.getInt("courses[" + i + "].price");
            
            System.out.println("Course title: " + courseTitle);
            System.out.println("Course price: " + coursePrice);
        }

        System.out.println("Loop to find RPA course copies:");
        
        // Loop to find the number of copies sold for the RPA course
        for (int i = 0; i < count; i++) {
            String courseTitle = bd.getString("courses[" + i + "].title");
            if (courseTitle.equalsIgnoreCase("RPA")) {
                int copyCount = bd.getInt("courses[" + i + "].copies");
                System.out.println("RPA course copies sold: " + copyCount);
                break;
            }
        }
    }
}
