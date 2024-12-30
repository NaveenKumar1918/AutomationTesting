package org.herokuapp.test;
import org.herokuapp.pages.ContactListPage;
import org.herokuapp.pages.LoginPage;
import org.herokuapp.pages.SignupPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
public class SignupPageTestCases extends BrowserTest{
	protected SignupPage signup;
	protected ContactListPage contactListPage;
	protected LoginPage loginPage;
	private WebDriver driver;
	
	
	public void setUp(ExtentTest test) {
		signup = new SignupPage(driver, test);
		contactListPage = new ContactListPage(driver, test);
		loginPage = new LoginPage(driver, test);
	}
	
	// TC_ADD_USER_00 & 09 & 01
	public void verifySignupAndRedirectToContactList(String fname, String lname, String validEmail, String password) {
		driver = setUpBrowser();
		ExtentTest test =createTest("verifySignupAndRedirectToContactList","SingUpTestCases");
	    setUp(test);
	    try {
	        signup.addUser(fname, lname, validEmail, password);
	        logAndUpdateStatusWithScreenShot(
	            "The SignUp page is loaded successfully",
	            "TC_ADD_USER_00", 
	            TestStatus.PASS, test);
	        if (contactListPage.logoutButtonIsDisplayed()) {
	            logAndUpdateStatusWithScreenShot("New user is created successfully", "TC_ADD_USER_01", TestStatus.PASS, test);
	        } else {
	            logAndUpdateStatusWithScreenShot("The expected behavior is changed.", "TC_ADD_USER_01", TestStatus.FAIL, test);
	        }

	        if (getCurrentUrlSegment(driver).equals("contactList")) {
	            logAndUpdateStatusWithScreenShot("If I clicked the submit button, the page is redirected to contactList page successfully.", "TC_ADD_USER_09", TestStatus.PASS, test);
	        } else {
	            logAndUpdateStatusWithScreenShot("If I clicked the submit button, the page is not redirected to contactList page.", "TC_ADD_USER_09", TestStatus.FAIL, test);
	        }	              		        
	        contactListPage.clickLogoutButton();
	    } catch (Exception e) {
	        logAndUpdateStatusWithScreenShot("Exception caught in this method verifySignupAndRedirectToContactList: " + e.getMessage(), "TC_ADD_USER_00", TestStatus.FAIL, test);
	        logAndUpdateStatusWithScreenShot("The expected behavior is changed.", "TC_ADD_USER_01", TestStatus.FAIL, test);
	        logAndUpdateStatusWithScreenShot("If I clicked the submit button, the page is not redirected to contactList page.", "TC_ADD_USER_09", TestStatus.FAIL, test);
	        signup.clickCancelButton();
	    }
	}

	// TC_ADD_USER_02
	public void signupWithoutFirstName(String emptyField, String lname, String validEmail, String password,String errorMessageForEmptyFirstNameField) {
	    ExtentTest test = createTest("signupWithoutFirstName","SingUpTestCases");
	    setUp(test);
	    try {
	        signup.addUser(emptyField, lname, validEmail, password);
	        if (signup.getErrorMessage().equals(errorMessageForEmptyFirstNameField)) {
	            logAndUpdateStatusWithScreenShot("It does not allow creating a user without a first name; field validation working perfectly.", "TC_ADD_USER_02", TestStatus.PASS, test);
	        } else {
	            logAndUpdateStatusWithScreenShot("The expected behavior has changed.", "TC_ADD_USER_02", TestStatus.FAIL, test);
	        }
	        signup.clickCancelButton();
	    } catch (Exception e) {
	        logAndUpdateStatusWithScreenShot("Exception caught in this method signupWithoutFirstName: " + e.getMessage(), "TC_ADD_USER_02", TestStatus.FAIL, test);
	        signup.clickCancelButton();
	    }
	}

	// TC_ADD_USER_03	
	public void signupWithoutLastName(String fname, String emptyField, String validEmail, String password,String errorMessageForEmptyLastNameField) {
	    ExtentTest test = createTest("signupWithoutLastName","SingUpTestCases");
	    setUp(test);
	    try {
	        signup.addUser(fname, emptyField, validEmail, password);
	        if (signup.getErrorMessage().equals(errorMessageForEmptyLastNameField)) {
	            logAndUpdateStatusWithScreenShot("It does not allow creating a user without a last name; field validation working perfectly.", "TC_ADD_USER_03", TestStatus.PASS, test);
	        } else {
	            logAndUpdateStatusWithScreenShot("The expected behavior has changed.", "TC_ADD_USER_03", TestStatus.FAIL, test);
	        }
	        signup.clickCancelButton();
	    } catch (Exception e) {
	        logAndUpdateStatusWithScreenShot("Exception caught in this method signupWithoutLastName: " + e.getMessage(), "TC_ADD_USER_03", TestStatus.FAIL, test);
	        signup.clickCancelButton();
	    }
	}

	// TC_ADD_USER_04	
	public void signupWithoutEmail(String fname, String lname, String emptyField, String password,String errorMessageForEmptyEmailField) {
	    ExtentTest test = createTest("signupWithoutEmail","SingUpTestCases");
	    setUp(test);
	    try {
	        signup.addUser(fname, lname, emptyField, password);
	        if (signup.getErrorMessage().equals(errorMessageForEmptyEmailField)) {
	            logAndUpdateStatusWithScreenShot("It does not allow creating a user without an email; field validation working perfectly.", "TC_ADD_USER_04", TestStatus.PASS, test);
	        } else {
	            logAndUpdateStatusWithScreenShot("The expected behavior has changed.", "TC_ADD_USER_04", TestStatus.FAIL, test);
	        }
	        signup.clickCancelButton();
	    } catch (Exception e) {
	        logAndUpdateStatusWithScreenShot("Exception caught in this method signupWithoutEmail: " + e.getMessage(), "TC_ADD_USER_04", TestStatus.FAIL, test);
	        signup.clickCancelButton();
	    }
	}

	// TC_ADD_USER_05	
	public void signupWithoutPassword(String fname, String lname, String validEmail, String emptyField, String errorMessageForEmptyPasswordField) {
	    ExtentTest test = createTest("signupWithoutPassword","SingUpTestCases");
	    setUp(test);
	    try {
	        signup.addUser(fname, lname, validEmail, emptyField);
	        if (signup.getErrorMessage().equals(errorMessageForEmptyPasswordField)) {
	            logAndUpdateStatusWithScreenShot("It does not allow creating a user without a password; field validation working perfectly.", "TC_ADD_USER_05", TestStatus.PASS, test);
	        } else {
	            logAndUpdateStatusWithScreenShot("The expected behavior has changed.", "TC_ADD_USER_05", TestStatus.FAIL, test);
	        }
	    } catch (Exception e) {
	        logAndUpdateStatusWithScreenShot("Exception caught in this method signupWithoutPassword: " + e.getMessage(), "TC_ADD_USER_05", TestStatus.FAIL, test);
	    } finally {
	        signup.clickCancelButton();
	    }
	}

	// TC_ADD_USER_06	
	public void checkPasswordLengthValidation(String fname, String lname, String validEmail, String invalidPasswordLength, String errorMessageForPasswordLengthLessThanSeven) {
	    ExtentTest test = createTest("checkPasswordLengthValidation","SingUpTestCases");
	    setUp(test);
	    try {
	        signup.addUser(fname, lname, validEmail, invalidPasswordLength);
	        if (signup.getErrorMessage().equals(errorMessageForPasswordLengthLessThanSeven)) {
	            logAndUpdateStatusWithScreenShot("It does not allow creating a user with a password length less than 7; field validation working perfectly.", "TC_ADD_USER_06", TestStatus.PASS, test);
	        } else {
	            logAndUpdateStatusWithScreenShot("The expected behavior has changed.", "TC_ADD_USER_06", TestStatus.FAIL, test);
	        }
	    } catch (Exception e) {
	        logAndUpdateStatusWithScreenShot("Exception caught in this method checkPasswordLengthValidation: " + e.getMessage(), "TC_ADD_USER_06", TestStatus.FAIL, test);
	    } finally {
	        signup.clickCancelButton();
	    }
	}
	// TC_ADD_USER_07	
	public void submitWithoutAllFields(String errorMessageForAllEmptyFields) {
	    ExtentTest test = createTest("submitWithoutAllFields","SingUpTestCases");
	    setUp(test);
	    try {
	        loginPage.clickSignUpButton();
	        signup.clickSubmitButton();
	        if (signup.getErrorMessage().equals(errorMessageForAllEmptyFields)) {
	            logAndUpdateStatusWithScreenShot("It does not allow creating a user with all fields empty and each field validation working perfectly.", "TC_ADD_USER_07", TestStatus.PASS, test);
	        } else {
	            logAndUpdateStatusWithScreenShot("The expected behavior has changed.", "TC_ADD_USER_07", TestStatus.FAIL, test);
	        }
	    } catch (Exception e) {
	        logAndUpdateStatusWithScreenShot("Exception caught in this method submitWithoutAllFields: " + e.getMessage(), "TC_ADD_USER_07", TestStatus.FAIL, test);
	    } finally {
	        signup.clickCancelButton();
	    }
	}

	// TC_ADD_USER_08
	public void invalidEmailFormat(String fname, String lname, String invalidEmailFormate, String password, String errorMessageForIvalidEmailFormate) {
	    ExtentTest test = createTest("invalidEmailFormat","SingUpTestCases");
	    setUp(test);
	    try {
	        signup.addUser(fname, lname, invalidEmailFormate, password);
	        if (signup.getErrorMessage().equals(errorMessageForIvalidEmailFormate)) {
	            logAndUpdateStatusWithScreenShot("It does not allow creating a user with an invalid email format; field validation working perfectly.", "TC_ADD_USER_08", TestStatus.PASS, test);
	        } else {
	            logAndUpdateStatusWithScreenShot("The expected behavior has changed.", "TC_ADD_USER_08", TestStatus.FAIL, test);
	        }
	        signup.clickCancelButton();
	      
	        String currentUrl = getCurrentUrlSegment(driver);
	        if (currentUrl.equals("login")) {
	            logAndUpdateStatusWithScreenShot("If the cancel button is clicked on the signup page, it redirects successfully to the login page.", "TC_ADD_USER_09", TestStatus.PASS, test);
	        } else {
	            logAndUpdateStatusWithScreenShot("If the cancel button is clicked on the signup page, it does not redirect to the login page.", "TC_ADD_USER_09", TestStatus.FAIL, test);
	        }
	    } catch (Exception e) {
	        logAndUpdateStatusWithScreenShot("Exception caught in this method invalidEmailFormat: " + e.getMessage(), "TC_ADD_USER_08", TestStatus.FAIL, test);
	    }
	    closeBrowser(driver); 
	}
	
	
}
