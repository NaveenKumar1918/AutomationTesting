//package org.herokuapp.pages;
//import org.herokuapp.seleniumbase.Base;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//
//public class LoginPage extends Base {
//		
//	public LoginPage(WebDriver driver) {
//		super(driver);
//	}
//	
//	//locators
//	protected By emailTextBox = By.id("email");
//    protected By passwordTextBox = By.id("password");
//    protected By submitButton = By.id("submit");
//    protected By signUp = By.id("signup");
//    
//    
//    WebElement findingElements(By locator) {
//		return returnElementAfterWaits(locator,30,WaitTypes.VISIBILITY_OF_ELEMENT);
//    }
//    
//    //actions
//    
//    public LoginPage enterEmail(String email) {
//    	enterText(findingElements(emailTextBox),email);
//    	return this;
//    }
//    public LoginPage enterPassword(String pass) {
//    	enterText(findingElements(passwordTextBox),pass);
//    	return this;
//    }
//    public LoginPage clickSubmitButton() {
//    	clickElement(submitButton);
//    	return this;
//    }
//    
//    public LoginPage clickSignButton(){
//    	clickElement(signUp);
//    	return this;
//    }



package org.herokuapp.pages;


import org.herokuapp.seleniumbase.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;

public class LoginPage extends Base {
	private ExtentTest test; // ExtentTest instance for logging

	// Constructor
	public LoginPage(WebDriver driver, ExtentTest test) {
		super(driver);
		this.test = test; // Initialize the test instance
	}


	// Locators
	private By emailTextBox = By.id("email");
	private By passwordTextBox = By.id("password");
	private By submitButton = By.id("submit");
	private By signUpButton = By.id("signup");
	private By errorMessage = By.id("error");

	// Method to find elements with wait
	private WebElement findElement(By locator) {
		return returnElementAfterWaits(locator, 30, WaitTypes.VISIBILITY_OF_ELEMENT);
	}
	
	public boolean isElementDisplayed (By locator) {
        return isElementDisplayed(findElement(locator));   
	}

	// Actions
	
	
	public boolean isEmailTextBoxDisplayed() {
        boolean displayed = isElementDisplayed(emailTextBox);
        test.info("Email text box displayed: " + displayed);
        return displayed;
    }

    public boolean isPasswordTextBoxDisplayed() {
        boolean displayed = isElementDisplayed(passwordTextBox);
        test.info("Password text box displayed: " + displayed);
        return displayed;
    }

    public boolean isSubmitButtonDisplayed() {
        boolean displayed = isElementDisplayed(submitButton);
        test.info("Submit button displayed: " + displayed);
        return displayed;
    }

    public boolean isSignUpButtonDisplayed() {
        boolean displayed = isElementDisplayed(signUpButton);
        test.info("Sign up button displayed: " + displayed);
        return displayed;
    }
    public LoginPage enterEmail(String email) {
		enterText(findElement(emailTextBox), email);
		test.info("Entered email: " + email);
		return this;
	}
	public LoginPage enterPassword(String password) {
		enterText(findElement(passwordTextBox), password);
		test.info("Entered password: "+password); // Log the action (consider logging password only as "Entered password" for security)
		return this;
	}

	public LoginPage clickSubmitButton() {
		clickElement(submitButton);
		test.info("Clicked on submit button"); // Log the action
		return this;
	}

	public LoginPage clickSignUpButton() {
		clickElement(signUpButton);
		test.info("Clicked on sign up button"); // Log the action
		return this;
	}
	
	public void login(String email,String password) {
			 new LoginPage(driver, test)
			.enterEmail(email)
			.enterPassword(password)
			.clickSubmitButton();
	}
	 public String getErrorMessage() {
	        String errorMsg = getElementText(findElement(errorMessage));
	        test.info("It will show this error message: "+ errorMsg );
	        return errorMsg;
	    }
	public boolean isErrorMessageDisplayed() {	
		return isElementDisplayed(errorMessage);
	}
	
}


