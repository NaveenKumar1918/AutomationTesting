package org.herokuapp.pages;

import org.herokuapp.seleniumbase.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;

public class AddContactPage extends Base {
    private ExtentTest test; // ExtentTest instance for logging

    // Constructor
    public AddContactPage(WebDriver driver, ExtentTest test) {
        super(driver);
        this.test = test; // Initialize the test instance
    }

    // Locators
    private By firstNameTextBox = By.id("firstName");
    private By lastNameTextBox = By.id("lastName");
    private By dobTextBox = By.id("birthdate");
    private By emailTextBox = By.id("email");
    private By phoneNumberTextBox = By.id("phone");
    private By address1TextBox = By.id("street1");
    private By address2TextBox = By.id("street2");
    private By cityTextBox = By.id("city");
    private By stateTextBox = By.id("stateProvince");
    private By postalCodeTextBox = By.id("postalCode");
    private By countryTextBox = By.id("country");
    private By submitButton = By.id("submit");
    private By logoutButton = By.id("logout");
    private By cancelButton = By.id("cancel");
    private By errorMessage=By.id("error");

    // Method to find elements with wait
    private WebElement findingElements(By locator) {
        return returnElementAfterWaits(locator, 30, WaitTypes.VISIBILITY_OF_ELEMENT);
    }

    // Actions
    public AddContactPage enterFirstName(String fname) {
        enterText(findingElements(firstNameTextBox), fname);
        test.info("Entered first name: " + fname);
        return this;
    }

    public AddContactPage enterLastName(String lname) {
        enterText(findingElements(lastNameTextBox), lname);
        test.info("Entered last name: " + lname);
        return this;
    }

    public AddContactPage enterDOB(String dob) {
        enterText(findingElements(dobTextBox), dob);
        test.info("Entered date of birth: " + dob);
        return this;
    }

    public AddContactPage enterEmail(String email) {
        enterText(findingElements(emailTextBox), email);
        test.info("Entered email: " + email);
        return this;
    }

    public AddContactPage enterPhoneNumber(String phnum) {
        enterText(findingElements(phoneNumberTextBox), phnum);
        test.info("Entered phone number: " + phnum);
        return this;
    }

    public AddContactPage enterAddressOne(String addone) {
        enterText(findingElements(address1TextBox), addone);
        test.info("Entered address line 1: " + addone);
        return this;
    }

    public AddContactPage enterAddressTwo(String addtwo) {
        enterText(findingElements(address2TextBox), addtwo);
        test.info("Entered address line 2: " + addtwo);
        return this;
    }

    public AddContactPage enterCityName(String cityname) {
        enterText(findingElements(cityTextBox), cityname);
        test.info("Entered city: " + cityname);
        return this;
    }

    public AddContactPage enterStateName(String state) {
        enterText(findingElements(stateTextBox), state);
        test.info("Entered state: " + state);
        return this;
    }

    public AddContactPage enterPostalCodeNumber(String pincode) {
        enterText(findingElements(postalCodeTextBox), pincode);
        test.info("Entered postal code: " + pincode);
        return this;
    }

    public AddContactPage enterCountryName(String country) {
        enterText(findingElements(countryTextBox), country);
        test.info("Entered country: " + country);
        return this;
    }

    public AddContactPage clickSubmitButton() {
        clickElement(findingElements(submitButton));
        test.info("Clicked on 'Submit' button");
        return this;
    }

    public AddContactPage clickCancelButton() {
        clickElement(findingElements(cancelButton));
        test.info("Clicked on 'Cancel' button");
        return this;
    }

    public AddContactPage clickLogoutButton() {
        clickElement(findingElements(logoutButton));
        test.info("Clicked on 'Logout' button");
        return this;
    }
    public String getErrorMessage() {
        String errorMsg = getElementText(findingElements(errorMessage));
        test.info("It will show this error message: "+ errorMsg );
        return errorMsg;
    }
    public boolean errorMessageIsDisplayed() {
    	String errorMsg = getElementText(findingElements(errorMessage));
        test.info("It will show this error message: "+ errorMsg );
    	return isElementDisplayed(findingElements(errorMessage));
    }
    
}

