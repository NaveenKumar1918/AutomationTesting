package org.herokuapp.pages;

import org.herokuapp.seleniumbase.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;

public class EditContactPage extends Base {
    private ExtentTest test; // ExtentTest instance for logging

    // Constructor
    public EditContactPage(WebDriver driver, ExtentTest test) {
        super(driver);
        this.test = test; // Initialize the test instance
    }

    // Locators
    protected By firstNameTextBox = By.id("firstName");
    protected By lastNameTextBox = By.id("lastName");
    protected By dobTextBox = By.id("birthdate");
    protected By emailTextBox = By.id("email");
    protected By phoneNumberTextBox = By.id("phone");
    protected By address1TextBox = By.id("street1");
    protected By address2TextBox = By.id("street2");
    protected By cityTextBox = By.id("city");
    protected By stateTextBox = By.id("stateProvince");
    protected By postalCodeTextBox = By.id("postalCode");
    protected By countryTextBox = By.id("country");
    protected By submitButton = By.id("submit");
    protected By logoutButton = By.id("logout");
    protected By cancelButton = By.id("cancel");
    protected By errorMessages = By.id("error");

    // Method to find elements with wait
    private WebElement findingElements(By locator) {
        return returnElementAfterWaits(locator, 30, WaitTypes.VISIBILITY_OF_ELEMENT);
    }

    // Verifications
 // Method to check if first name field is displayed
    public boolean isDisplayedErrorMessage() {
        WebElement errorMessage = findingElements(errorMessages);
        test.info("The error message: " + errorMessage.getText());
        return isElementDisplayed(errorMessage);
    }
    
    public boolean isDisplayedFirstName() {
        boolean displayed = isElementDisplayed(findingElements(firstNameTextBox));
        test.info("First Name field displayed: " + displayed);
        return displayed;
    }

    // Method to check if last name field is displayed
    public boolean isDisplayedLastName() {
        boolean displayed = isElementDisplayed(findingElements(lastNameTextBox));
        test.info("Last Name field displayed: " + displayed);
        return displayed;
    }

    // Method to check if date of birth field is displayed
    public boolean isDisplayedDob() {
        boolean displayed = isElementDisplayed(findingElements(dobTextBox));
        test.info("Date of Birth field displayed: " + displayed);
        return displayed;
    }

    // Method to check if email field is displayed
    public boolean isDisplayedEmail() {
        boolean displayed = isElementDisplayed(findingElements(emailTextBox));
        test.info("Email field displayed: " + displayed);
        return displayed;
    }

    // Method to check if phone number field is displayed
    public boolean isDisplayedPhoneNumber() {
        boolean displayed = isElementDisplayed(findingElements(phoneNumberTextBox));
        test.info("Phone Number field displayed: " + displayed);
        return displayed;
    }

    // Method to check if address line 1 field is displayed
    public boolean isDisplayedAddress1() {
        boolean displayed = isElementDisplayed(findingElements(address1TextBox));
        test.info("Address Line 1 field displayed: " + displayed);
        return displayed;
    }

    // Method to check if address line 2 field is displayed
    public boolean isDisplayedAddress2() {
        boolean displayed = isElementDisplayed(findingElements(address2TextBox));
        test.info("Address Line 2 field displayed: " + displayed);
        return displayed;
    }

    // Method to check if city field is displayed
    public boolean isDisplayedCity() {
        boolean displayed = isElementDisplayed(findingElements(cityTextBox));
        test.info("City field displayed: " + displayed);
        return displayed;
    }

    // Method to check if state field is displayed
    public boolean isDisplayedState() {
        boolean displayed = isElementDisplayed(findingElements(stateTextBox));
        test.info("State/Province field displayed: " + displayed);
        return displayed;
    }

    // Method to check if postal code field is displayed
    public boolean isDisplayedPostalCode() {
        boolean displayed = isElementDisplayed(findingElements(postalCodeTextBox));
        test.info("Postal Code field displayed: " + displayed);
        return displayed;
    }

    // Method to check if country field is displayed
    public boolean isDisplayedCountry() {
        boolean displayed = isElementDisplayed(findingElements(countryTextBox));
        test.info("Country field displayed: " + displayed);
        return displayed;
    }

    public String getTextFirstName() {
        String firstName = findingElements(firstNameTextBox).getText();
        test.info("Fetched first name: " + firstName); // Log the action
        return firstName;
    }

    public String getTextLastName() {
        String lastName = findingElements(lastNameTextBox).getText();
        test.info("Fetched last name: " + lastName); // Log the action
        return lastName;
    }

    public String getTextDOB() {
        String dob = findingElements(dobTextBox).getText();
        test.info("Fetched date of birth: " + dob); // Log the action
        return dob;
    }

    public String getTextEmail() {
        String email = findingElements(emailTextBox).getText();
        test.info("Fetched email: " + email); // Log the action
        return email;
    }

    public String getTextPhoneNumber() {
        String phoneNumber = findingElements(phoneNumberTextBox).getText();
        test.info("Fetched phone number: " + phoneNumber); // Log the action
        return phoneNumber;
    }

    public String getTextAddressOne() {
        String address1 = findingElements(address1TextBox).getText();
        test.info("Fetched address line 1: " + address1); // Log the action
        return address1;
    }

    public String getTextAddressTwo() {
        String address2 = findingElements(address2TextBox).getText();
        test.info("Fetched address line 2: " + address2); // Log the action
        return address2;
    }

    public String getTextCity() {
        String city = findingElements(cityTextBox).getText();
        test.info("Fetched city: " + city); // Log the action
        return city;
    }

    public String getTextState() {
        String state = findingElements(stateTextBox).getText();
        test.info("Fetched state: " + state); // Log the action
        return state;
    }

    public String getTextPostalCode() {
        String postalCode = findingElements(postalCodeTextBox).getText();
        test.info("Fetched postal code: " + postalCode); // Log the action
        return postalCode;
    }

    public String getTextCountry() {
        String country = findingElements(countryTextBox).getText();
        test.info("Fetched country: " + country); // Log the action
        return country;
    }

    // Actions
    public EditContactPage enterFirstName(String fname) {
        enterText(findingElements(firstNameTextBox), fname);
        test.info("Entered first name: " + fname); // Log the action
        return this;
    }

    public EditContactPage enterLastName(String lname) {
        enterText(findingElements(lastNameTextBox), lname);
        test.info("Entered last name: " + lname); // Log the action
        return this;
    }

    public EditContactPage enterDOB(String dob) {
        enterText(findingElements(dobTextBox), dob);
        test.info("Entered date of birth: " + dob); // Log the action
        return this;
    }

    public EditContactPage enterEmail(String email) {
        enterText(findingElements(emailTextBox), email);
        test.info("Entered email: " + email); // Log the action
        return this;
    }

    public EditContactPage enterPhoneNumber(String phnum) {
        enterText(findingElements(phoneNumberTextBox), phnum);
        test.info("Entered phone number: " + phnum); // Log the action
        return this;
    }

    public EditContactPage enterAddressOne(String addone) {
        enterText(findingElements(address1TextBox), addone);
        test.info("Entered address line 1: " + addone); // Log the action
        return this;
    }

    public EditContactPage enterAddressTwo(String addtwo) {
        enterText(findingElements(address2TextBox), addtwo);
        test.info("Entered address line 2: " + addtwo); // Log the action
        return this;
    }

    public EditContactPage enterCityName(String cityname) {
        enterText(findingElements(cityTextBox), cityname);
        test.info("Entered city: " + cityname); // Log the action
        return this;
    }

    public EditContactPage enterStateName(String state) {
        enterText(findingElements(stateTextBox), state);
        test.info("Entered state: " + state); // Log the action
        return this;
    }

    public EditContactPage enterPostalCodeNumber(String pincode) {
        enterText(findingElements(postalCodeTextBox), pincode);
        test.info("Entered postal code: " + pincode); // Log the action
        return this;
    }

    public EditContactPage enterCountryName(String country) {
        enterText(findingElements(countryTextBox), country);
        test.info("Entered country: " + country); // Log the action
        return this;
    }

    public EditContactPage clickSubmitButton() {
        clickElement(findingElements(submitButton));
        test.info("Clicked on 'Submit' button"); // Log the action
        return this;
    }

    public EditContactPage clickCancelButton() {
        clickElement(findingElements(cancelButton));
        test.info("Clicked on 'Cancel' button"); // Log the action
        return this;
    }

    public EditContactPage clickLogoutButton() {
        clickElement(findingElements(logoutButton));
        test.info("Clicked on 'Logout' button"); // Log the action
        return this;
    }
}
