package org.herokuapp.test;
import org.herokuapp.pages.ContactListPage;
import org.herokuapp.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

public class LoginPageTestCases extends BrowserTest {
	protected ContactListPage contactListPage;
	protected LoginPage loginPage;
	private RemoteWebDriver driver;


	public void setUp(ExtentTest test) {
		contactListPage = new ContactListPage(driver, test);
		loginPage = new LoginPage(driver, test);
	}
	

	// TC_LOGIN_00
	public void loginPageElementsIsLoaded() {
		driver = (RemoteWebDriver) setUpBrowser();
		ExtentTest test =createTest("verifyLoginPageElementsIsLoaded","LoginTestCases");
		setUp(test);
		try {
			if (loginPage.isEmailTextBoxDisplayed() && 
					loginPage.isPasswordTextBoxDisplayed() && 
					loginPage.isSubmitButtonDisplayed() && 
					loginPage.isSignUpButtonDisplayed()) {

				logAndUpdateStatusWithScreenShot("The elements in the login page are loaded successfully.", "TC_LOGIN_00", TestStatus.PASS, test);
			} else {
				logAndUpdateStatusWithScreenShot("The elements in the login page are not loaded successfully.", "TC_LOGIN_00", TestStatus.FAIL, test);
			}
		} catch (Exception e) {
			logAndUpdateStatusWithScreenShot("An error occurred while verifying elements in the login page: " + e.getMessage(), "TC_LOGIN_00", TestStatus.FAIL, test);
		}
	}

	// TC_LOGIN_01
	public void successfulLoginWithValidCredentials(String validEmail, String validPassword, String contactListPageUrl) {
		ExtentTest test =createTest("verifySuccessfulLoginWithValidCredentials","LoginTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			if(getCurrentUrl(driver).equals(contactListPageUrl)) {
				logAndUpdateStatusWithScreenShot("Login successfully verified with valid credentials. User is able to access the application as expected.", "TC_LOGIN_01", TestStatus.PASS, test);
				contactListPage.clickLogoutButton();
			}else {
				logAndUpdateStatusWithScreenShot("Login was not successfully verified with valid credentials. User is able to access the application as expected.", "TC_LOGIN_01", TestStatus.FAIL, test);
			}
		} catch (Exception e) {
			logAndUpdateStatusWithScreenShot("An error occurred while verifying login with valid credentials: " + e.getMessage(), "TC_LOGIN_01", TestStatus.FAIL, test);
		}
	}

	// TC_LOGIN_02
	public void loginWithValidEmailAndShortPassword(String validEmail,String inValidPassword) {
		ExtentTest test =createTest("verifyLoginWithValidEmailAndShortPassword","LoginTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, inValidPassword);
			
			if (loginPage.isErrorMessageDisplayed()) {
				logAndUpdateStatusWithScreenShot("Login attempt blocked as expected with a password of fewer than 7 characters.", "TC_LOGIN_02", TestStatus.PASS, test);
			} else {
				logAndUpdateStatusWithScreenShot("Login was not blocked with a short password, which is incorrect behavior.", "TC_LOGIN_02", TestStatus.FAIL, test);
			}
		} catch (Exception e) {
			logAndUpdateStatusWithScreenShot("An exception occurred while verifying login with a short password: " + e.getMessage(), "TC_LOGIN_02", TestStatus.FAIL, test);
		}
	}

	// TC_LOGIN_03
	public void loginWithInValidEmailAndValidPassword(String inValidEmail, String validPassword) {
		ExtentTest test =createTest("verifyLoginWithInValidEmailAndValidPassword","LoginTestCases");
		setUp(test);
		try {
			loginPage.login(inValidEmail, validPassword);
			if (loginPage.isErrorMessageDisplayed()) {
				logAndUpdateStatusWithScreenShot("Login attempt blocked as expected with an invalid email.", "TC_LOGIN_03", TestStatus.PASS, test);
			} else {
				logAndUpdateStatusWithScreenShot("Login was not blocked with an invalid email, which is incorrect behavior.", "TC_LOGIN_03", TestStatus.FAIL, test);
			}
		} catch (Exception e) {
			logAndUpdateStatusWithScreenShot("An exception occurred while verifying login with an invalid email: " + e.getMessage(), "TC_LOGIN_03", TestStatus.FAIL, test);
		}
	}

	// TC_LOGIN_04
	public void loginWithEmptyEmailAndValidPassword(String emptyField,String validPassword) {
		ExtentTest test =createTest("verifyLoginWithEmptyEmailAndValidPassword","LoginTestCases");
		setUp(test);
		try {
			
			loginPage.login(emptyField, validPassword);

			if (loginPage.isErrorMessageDisplayed()) {
				logAndUpdateStatusWithScreenShot("Login attempt blocked as expected with an empty email field.", "TC_LOGIN_04", TestStatus.PASS, test);
			} else {
				logAndUpdateStatusWithScreenShot("Login was not blocked with an empty email field, which is incorrect behavior.", "TC_LOGIN_04", TestStatus.FAIL, test);
			}
		} catch (Exception e) {
			logAndUpdateStatusWithScreenShot("An exception occurred while verifying login with an empty email field: " + e.getMessage(), "TC_LOGIN_04", TestStatus.FAIL, test);
		}
	}

	// TC_LOGIN_05
	public void loginWithValidEmailAndEmptyPassword(String validEmail,String emptyField) {
		ExtentTest test =createTest("verifyLoginWithValidEmailAndEmptyPassword","LoginTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, emptyField);

			if (loginPage.isErrorMessageDisplayed()) {
				logAndUpdateStatusWithScreenShot("Login attempt blocked as expected with an empty password field.", "TC_LOGIN_05", TestStatus.PASS, test);
			} else {
				logAndUpdateStatusWithScreenShot("Login was not blocked with an empty password field, which is incorrect behavior.", "TC_LOGIN_05", TestStatus.FAIL, test);
			}
		} catch (Exception e) {
			logAndUpdateStatusWithScreenShot("An exception occurred while verifying login with an empty password field: " + e.getMessage(), "TC_LOGIN_05", TestStatus.FAIL, test);
		}
	}

	// TC_LOGIN_06
	public void loginWithBothEmptyFields(String emptyFieldOne,String emptyFieldTwo) {
		ExtentTest test =createTest("verifyLoginWithBothEmptyFields","LoginTestCases");
		setUp(test);
		try {
			loginPage.login(emptyFieldOne, emptyFieldTwo);
			if (loginPage.isErrorMessageDisplayed()) {
				logAndUpdateStatusWithScreenShot("Login attempt blocked as expected with both email and password as empty.", "TC_LOGIN_06", TestStatus.PASS, test);
			} else {
				logAndUpdateStatusWithScreenShot("Login was not blocked with both email and password fields empty, which is incorrect behavior.", "TC_LOGIN_06", TestStatus.FAIL, test);
			}
		} catch (Exception e) {
			logAndUpdateStatusWithScreenShot("An exception occurred while verifying login with both email and password fields empty: " + e.getMessage(), "TC_LOGIN_06", TestStatus.FAIL, test);
		}
	}

	// TC_LOGIN_07
	public void successfulLoginNavigatesToContactPage(String validEmail,String validPassword) {
		ExtentTest test =createTest("confirmSuccessfulLoginNavigatesToContactPage","LoginTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			
			String currentUrl = getCurrentUrl(driver);
			System.out.println(currentUrl);
			
			
			if (currentUrl.equals("https://thinking-tester-contact-list.herokuapp.com/contactList")) {
				logAndUpdateStatusWithScreenShot("Login successfully navigated to the contact page.", "TC_LOGIN_07", TestStatus.PASS, test);
			} else {
				logAndUpdateStatusWithScreenShot("Login did not navigate to the contact page, which is incorrect behavior.", "TC_LOGIN_07", TestStatus.FAIL, test);
			}
		} catch (Exception e) {
			logAndUpdateStatusWithScreenShot("An exception occurred while verifying navigation to the contact page after login: " + e.getMessage(), "TC_LOGIN_07", TestStatus.FAIL, test);
		}
		contactListPage.clickLogoutButton();
	}
	//TC_LOGIN_08
	public void navigationToSignupPageAfterClickingSignupButton() {
		ExtentTest test =createTest("verifyNavigationToSignupPageAfterClickingSignupButton","LoginTestCases");
		setUp(test);
		try {
			loginPage.clickSignUpButton();
			String currentUrl = getCurrentUrl(driver);
			System.out.println(currentUrl);
			if (currentUrl.equals("https://thinking-tester-contact-list.herokuapp.com/addUser")) {
				logAndUpdateStatusWithScreenShot("After clicking signup button in loging page successfully navigated to the SignUp page.", "TC_LOGIN_08", TestStatus.PASS, test);
			} else {
				logAndUpdateStatusWithScreenShot("After clicking signup button in loging page is not successfully navigated to the SignUp page.", "TC_LOGIN_08", TestStatus.FAIL, test);
			}
		}catch(Exception e){
			logAndUpdateStatusWithScreenShot("An exception occurred while verifying navigation to the sign page after clicking singnup button in login page: " + e.getMessage(), "TC_LOGIN_08", TestStatus.FAIL, test);
		}
		closeBrowser(driver);
	}
	
}

