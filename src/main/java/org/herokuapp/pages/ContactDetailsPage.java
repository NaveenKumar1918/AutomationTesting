package org.herokuapp.pages;

import org.herokuapp.seleniumbase.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;

public class ContactDetailsPage extends Base {
    private ExtentTest test; // ExtentTest instance for logging

    // Constructor
    public ContactDetailsPage(WebDriver driver, ExtentTest test) {
        super(driver);
        this.test = test; // Initialize the test instance
    }

    // Locators
    private By editContactButton = By.id("edit-contact");
    private By deleteContactButton = By.id("delete");
    private By returnToContactButton = By.id("return");
    private By logoutButton = By.id("logout");
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
    private By contactDetailsFields = By.id("contactDetails");

    // Method to find elements with wait
    private WebElement findingElements(By locator) {
        return returnElementAfterWaits(locator, 30, WaitTypes.VISIBILITY_OF_ELEMENT);
    }

    // Validations
    public boolean isDisplayedEditContactButton() {
    	return isElementDisplayed(findingElements(editContactButton));
    }
    public boolean isDisplayedReturnToContactListButton() {
    	return isElementDisplayed(findingElements(returnToContactButton));
    }
    public boolean isDisplayedDeleteButon() {
    	return isElementDisplayed(findingElements(deleteContactButton));
    }
    public boolean isDisplayedLogutButton() {
    	return isElementDisplayed(findingElements(logoutButton));
    }
    public boolean isDisplayedContactDetails() {
    	return isElementDisplayed(findingElements(contactDetailsFields));
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
    public ContactDetailsPage clickEditContactButton() {
        clickElement(findingElements(editContactButton));
        test.info("Clicked on 'Edit Contact' button"); // Log the action
        return this;
    }

    public ContactDetailsPage clickDeleteContactButton() {
        clickElement(findingElements(deleteContactButton));
        acceptAlert();
        test.info("Clicked on 'Delete' button and accepted alert"); // Log the action
        return this;
    }
    public ContactDetailsPage clickDeleteContactButtonAndCancel() {
        clickElement(findingElements(deleteContactButton));
        dismissAlert();
        test.info("Clicked on 'Delete' button and Dismiss alert"); // Log the action
        return this;
    }

    public ContactDetailsPage clickReturnToContactListButton() {
        clickElement(findingElements(returnToContactButton));
        test.info("Clicked on 'Return to Contact List' button"); // Log the action
        return this;
    }

    public ContactDetailsPage clickLogoutButton() {
        clickElement(findingElements(logoutButton));
        test.info("Clicked on 'Logout' button"); // Log the action
        return this;
    }
}
