package com.herokuapp.apiautomation;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

public class BookingApiAutomationTest {

    BookingApiAutomation bookingHelper;
    RequestSpecification reqSpec;
    private int BookingId;

    @BeforeMethod
    public void setUp() {
        bookingHelper = new BookingApiAutomation();
        reqSpec = given()
            .baseUri("https://restful-booker.herokuapp.com")
            .basePath("/booking")
            .header("Content-Type", "application/json")
            .header("Accept", "application/json");
    }

    @Test(priority = 0)
    public void testCreateBooking() {
        BookingId = bookingHelper.createBooking(reqSpec);
    }
    @Test(priority=1)
    public void testCheckBooking() {
    	 bookingHelper.checkCreatedBooking(reqSpec, BookingId);
    }
    

    @Test(priority = 2)
    public void testGetCreatedBookingById() {
        bookingHelper.getCreatedBookingById(reqSpec, BookingId);
    }

    @Test(priority = 3)
    public void testPartialUpdate() {
        File partialFile = new File("partialUpdate.json");
        bookingHelper.partialUpdate(reqSpec, BookingId, partialFile);
    }
    @Test(priority = 4)
    public void testFullUpdate() {
        File jsonFile = new File("postApiAutomationTask1.json");
        bookingHelper.fullUpdate(reqSpec, BookingId, jsonFile);
    }
    @Test(priority = 5)
    public void testDeleteBooking() {
        bookingHelper.deleteBooking(reqSpec, BookingId);
    }
}
