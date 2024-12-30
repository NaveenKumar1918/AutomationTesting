package com.herokuapp.apiautomation;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BookingApiAutomation {

    public int createBooking(RequestSpecification reqSpec) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;  
        try (FileReader reader = new FileReader("postApiAutomationTask1.json")) {
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            throw new RuntimeException("Error reading the JSON file.", e);
        }

        // Extract values from the JSON file
        String firstName = (String) jsonObject.get("firstname");
        String lastName = (String) jsonObject.get("lastname");
        long totalPrice = (long) jsonObject.get("totalprice");
        boolean depositPaid = (boolean) jsonObject.get("depositpaid");
        JSONObject bookingDates = (JSONObject) jsonObject.get("bookingdates");
        String checkinDate = (String) bookingDates.get("checkin");
        String checkoutDate = (String) bookingDates.get("checkout");
        String additionalNeeds = (String) jsonObject.get("additionalneeds");

        int bookingId = 
            given()
                .log().all()
                .spec(reqSpec)
                .body(jsonObject.toJSONString())
            .when()
                .post()
            .then()
                .log().all()
                .statusCode(200)
                // Validate response body fields
                .body("booking.firstname", equalTo(firstName))
                .body("booking.lastname", equalTo(lastName))
                .body("booking.totalprice", equalTo((int) totalPrice))
                .body("booking.depositpaid", equalTo(depositPaid))
                .body("booking.bookingdates.checkin", equalTo(checkinDate))
                .body("booking.bookingdates.checkout", equalTo(checkoutDate))
                .body("booking.additionalneeds", equalTo(additionalNeeds))
                .extract().path("bookingid");

        System.out.println("This is the ID for created Booking: " + bookingId);
        return bookingId;
    }

    public void checkCreatedBooking(RequestSpecification reqSpec, int bookingId) {
        System.out.println("Now checking created booking, ID: " + bookingId);
        Response getAllBookingsResponse = 
            given()
                .log().all()
                .spec(reqSpec)
            .when()
                .get()
            .then()
                .statusCode(200)
                .extract()
                .response();

        List<Integer> allBookingIds = getAllBookingsResponse.jsonPath().getList("bookingid");

        if (allBookingIds.contains(bookingId)) {
            System.out.println("The created booking ID " + bookingId + " exists in the list of booking IDs.");
        } else {
            System.out.println("The created booking ID " + bookingId + " does NOT exist.");
        }
    }

    public void getCreatedBookingById(RequestSpecification reqSpec, int bookingId) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;

        try (FileReader reader = new FileReader("postApiAutomationTask1.json")) {
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            throw new RuntimeException("Error reading the JSON file.", e);
        }

        // Extract values from the JSON file
        String firstName = (String) jsonObject.get("firstname");
        String lastName = (String) jsonObject.get("lastname");
        long totalPrice = (long) jsonObject.get("totalprice");
        boolean depositPaid = (boolean) jsonObject.get("depositpaid");
        JSONObject bookingDates = (JSONObject) jsonObject.get("bookingdates");
        String checkinDate = (String) bookingDates.get("checkin");
        String checkoutDate = (String) bookingDates.get("checkout");
        String additionalNeeds = (String) jsonObject.get("additionalneeds");
        
        given()
            .log().all()
            .spec(reqSpec)
            .pathParam("id", bookingId)
        .when()
            .get("/{id}")
        .then()
            .statusCode(200)
            .log().all()
            // Body validation (adjust if "booking" is not a wrapper)
            .body("firstname", equalTo(firstName))
            .body("lastname", equalTo(lastName))
            .body("totalprice", equalTo((int) totalPrice))
            .body("depositpaid", equalTo(depositPaid))
            .body("bookingdates.checkin", equalTo(checkinDate))
            .body("bookingdates.checkout", equalTo(checkoutDate))
            .body("additionalneeds", equalTo(additionalNeeds));
    }

    public void partialUpdate(RequestSpecification reqSpec, int bookingId, File partialFile) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;

        try (FileReader reader = new FileReader(partialFile)) {
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            throw new RuntimeException("Error reading the JSON file.", e);
        }

        String firstName = (String) jsonObject.get("firstname");
        String lastName = (String) jsonObject.get("lastname");

        given()
            .log().all()
            .spec(reqSpec)
            .pathParam("id", bookingId)
            .auth().preemptive().basic("admin", "password123")
            .body(partialFile)
        .when()
            .patch("/{id}")
        .then()
            .log().all()
            .statusCode(200)
            // Body validation for updated fields
            .body("firstname", equalTo(firstName))
            .body("lastname", equalTo(lastName));

        System.out.println("It is Partially Updated Successfully");
    }

    public void fullUpdate(RequestSpecification reqSpec, int bookingId, File jsonFile) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;

        try (FileReader reader = new FileReader(jsonFile)) {
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            throw new RuntimeException("Error reading the JSON file.", e);
        }

        // Extract values from the JSON file
        String firstName = (String) jsonObject.get("firstname");
        String lastName = (String) jsonObject.get("lastname");
        long totalPrice = (long) jsonObject.get("totalprice");
        boolean depositPaid = (boolean) jsonObject.get("depositpaid");
        JSONObject bookingDates = (JSONObject) jsonObject.get("bookingdates");
        String checkinDate = (String) bookingDates.get("checkin");
        String checkoutDate = (String) bookingDates.get("checkout");
        String additionalNeeds = (String) jsonObject.get("additionalneeds");

        given()
            .log().all()
            .spec(reqSpec)
            .pathParam("id", bookingId)
            .auth().preemptive().basic("admin", "password123")
            .body(jsonFile)
        .when()
            .put("/{id}")
        .then()
            .log().all()
            .statusCode(200)
            .body("firstname", equalTo(firstName))
            .body("lastname", equalTo(lastName))
            .body("totalprice", equalTo((int) totalPrice))
            .body("depositpaid", equalTo(depositPaid))
            .body("bookingdates.checkin", equalTo(checkinDate))
            .body("bookingdates.checkout", equalTo(checkoutDate))
            .body("additionalneeds", equalTo(additionalNeeds));

        System.out.println("The booking details updated successfully!!!");
    }

    public void deleteBooking(RequestSpecification reqSpec, int bookingId) {
        given()
            .log().all()
            .spec(reqSpec)
            .pathParam("id", bookingId)
            .auth().preemptive().basic("admin", "password123")
        .when()
            .delete("/{id}")
        .then()
            .log().all()
            .statusCode(201);

        System.out.println("Successfully Deleted the Booking");
    }
}



