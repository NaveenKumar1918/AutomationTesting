package org.herokuapp.tescases;
import org.herokuapp.test.BrowserTest;
import org.herokuapp.test.LoginPageTestCases;
import org.testng.annotations.Test;

public class MasterLoginTest extends BrowserTest{

	private LoginPageTestCases loginTests = new LoginPageTestCases();
	private final String validEmail = "uit20128@rmd.ac.in";
	private final String validPassword = "naveen@19";
	private final String inValidEmail = "uit@.com";
	private final String inValidPassword = "naveen";
	private final String emptyField = "";
	private final String emptyFieldOne = "";
	private final String emptyFieldTwo = "";
	private final String contactListPageUrl = "https://thinking-tester-contact-list.herokuapp.com/contactList";
	
	// TC_LOGIN_00
	@Test(priority = 1)
	public void VerifyLoginPageElementsIsLoaded() {
		loginTests.loginPageElementsIsLoaded();
	}
	
	// TC_LOGIN_01
	@Test(priority = 2)
	public void VerifySuccessfulLoginWithValidCredentials() {
		loginTests.successfulLoginWithValidCredentials(validEmail,validPassword,contactListPageUrl);
	}
	
	// TC_LOGIN_02
	@Test(priority = 3)
	public void VerifyLoginWithValidEmailAndShortPassword() {
		loginTests.loginWithValidEmailAndShortPassword(validEmail,inValidPassword);
	}
	
	// TC_LOGIN_03
	@Test(priority = 4)
	public void VerifyLoginWithInValidEmailAndValidPassword() {
		loginTests.loginWithInValidEmailAndValidPassword(inValidEmail,validPassword);
	}
	
	// TC_LOGIN_04
	@Test(priority = 5)
	public void runVerifyLoginWithEmptyEmailAndValidPassword() {
		loginTests.loginWithEmptyEmailAndValidPassword(emptyField, validPassword);
	}
	
	// TC_LOGIN_05
	@Test(priority = 6)
	public void VerifyLoginWithValidEmailAndEmptyPassword() {
		loginTests.loginWithValidEmailAndEmptyPassword(validEmail, emptyField);
	}
	
	// TC_LOGIN_06
	@Test(priority = 7)
	public void VerifyLoginWithBothEmptyFields() {
		loginTests.loginWithBothEmptyFields(emptyFieldOne, emptyFieldTwo);
	}
	
	// TC_LOGIN_07
	@Test(priority = 8)
	public void ConfirmSuccessfulLoginNavigatesToContactPage() {
		loginTests.successfulLoginNavigatesToContactPage(validEmail, validPassword);
	}
	
	//TC_LOGIN_08
	@Test(priority = 9)
	public void VerifyNavigationToSignupPageAfterClickingSignupButton() {
		loginTests.navigationToSignupPageAfterClickingSignupButton();
	}
}
