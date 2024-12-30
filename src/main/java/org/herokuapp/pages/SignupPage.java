package org.herokuapp.pages;

import org.herokuapp.seleniumbase.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;

public class SignupPage extends Base {
    private ExtentTest test;
    
    public SignupPage(WebDriver driver, ExtentTest test) {
        super(driver);
        this.test = test; // Initialize the test instance
    }
    
    // Locators
    protected By firstNameTextbox = By.id("firstName");
    protected By lastNameTextbox = By.id("lastName");
    protected By emailTextbox = By.id("email");
    protected By passwordTextbox = By.id("password");
    protected By submitButton = By.id("submit");
    protected By cancelButton = By.id("cancel");
    protected By errorMessage = By.id("error");

    WebElement findingElements(By locator) {
        return returnElementAfterWaits(locator, 30, WaitTypes.VISIBILITY_OF_ELEMENT);
    }    

    // Actions
    public SignupPage enterFirstName(String fname) {
        enterText(findingElements(firstNameTextbox), fname);
        test.info("Entered first name: " + fname); // Log the action
        return this;
    }

    public SignupPage enterSecondName(String lname) {
        enterText(findingElements(lastNameTextbox), lname);
        test.info("Entered last name: " + lname); // Log the action
        return this;
    }

    public SignupPage enterEmail(String email) {
        enterText(findingElements(emailTextbox), email);
        test.info("Entered email: " + email); // Log the action
        return this;
    }

    public SignupPage enterPassword(String pass) {
        enterText(findingElements(passwordTextbox), pass);
        test.info("Entered password: "+pass); // Log the action
        return this;
    }

    public SignupPage clickSubmitButton() {
        clickElement(submitButton);
        test.info("Clicked on submit button"); // Log the action
        return this;
    }

    public SignupPage clickCancelButton() {
        clickElement(cancelButton);
        test.info("Clicked on cancel button"); // Log the action
        return this;
    }

    public String getErrorMessage() {
        String errorMsg = getElementText(findingElements(errorMessage));
        test.info("It will show this error message: "+ errorMsg );
        return errorMsg;
    }

    // Creating a new user
    public void addUser(String firstname, String secondname, String email, String password) {
        new LoginPage(driver, test).clickSignUpButton();
        new SignupPage(driver, test) // Pass the ExtentTest instance to the SignupPage
            .enterFirstName(firstname)
            .enterSecondName(secondname)
            .enterEmail(email)
            .enterPassword(password)
            .clickSubmitButton();
    }
}

