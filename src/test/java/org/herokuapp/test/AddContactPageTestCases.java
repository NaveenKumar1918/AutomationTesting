package org.herokuapp.test;

import org.herokuapp.pages.AddContactPage;
import org.herokuapp.pages.ContactListPage;
import org.herokuapp.pages.LoginPage;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class AddContactPageTestCases extends BrowserTest{
	protected ContactListPage contactListPage;
	protected LoginPage loginPage;
	protected AddContactPage addContactPage;
	private WebDriver driver;

	public void setUp(ExtentTest test) {
		contactListPage = new ContactListPage(driver, test);
		loginPage = new LoginPage(driver, test);
		addContactPage = new AddContactPage(driver,test);
	}

	//TC_ADD_CON_04
	public void validateAllFieldsInAddContactPage(String validEmail, String validPassword, String invalidFirstNameField, String invalidSecondNameFiled, String invalidDob, String invalidEmail, String invalidPhone, String invalidAddressOne, String invalidAddressTwo, String invalidCity, String invalidState, String invalidPostalCode, String invalidContry) {
		driver = setUpBrowser();
		ExtentTest test =createTest("verifyFormFieldValidationsForAddContact","AddContactTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickAddNewContactButton();
			addContactPage.enterFirstName(invalidFirstNameField)
			.enterLastName(invalidSecondNameFiled)
			.enterDOB(invalidDob)
			.enterEmail(invalidEmail)
			.enterPhoneNumber(invalidPhone)
			.enterAddressOne(invalidAddressOne)
			.enterAddressTwo(invalidAddressTwo)
			.enterCityName(invalidCity)
			.enterStateName(invalidState)
			.enterPostalCodeNumber(invalidPostalCode)
			.enterCountryName(invalidContry)
			.clickSubmitButton();
			if(addContactPage.errorMessageIsDisplayed()) {
				logAndUpdateStatusWithScreenShot("All field validations on the 'Add Contact' page are functioning correctly, with appropriate error messages displaying as expected for invalid or missing inputs.", "TC_ADD_CON_04", TestStatus.PASS, test);
			}else {
				logAndUpdateStatusWithScreenShot("Some field validations on the 'Add Contact' page are not functioning correctly. Errors did not display as expected for certain invalid or missing inputs.", "TC_ADD_CON_04", TestStatus.FAIL, test);
			}
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An unexpected error occurred while attempting to validate fields on the 'Add Contact' page: " + e.getMessage(), "TC_ADD_CON_04", TestStatus.FAIL, test);
		}
		addContactPage.clickLogoutButton();
	}

	public void validateErrorOnBlankSubmissionInAddContactPage(String validEmail, String validPassword, String loginPageUrl) {
		ExtentTest test =createTest("verifyErrorOnEmptySubmitInAddContactPage","AddContactTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickAddNewContactButton();
			addContactPage.clickSubmitButton();
			if(addContactPage.errorMessageIsDisplayed()) {
				logAndUpdateStatusWithScreenShot("Error message displayed as expected on submitting empty form, and empty user was not created. The error message is: "+ addContactPage.getErrorMessage(), "TC_ADD_CON_05", TestStatus.PASS, test);	
				addContactPage.clickLogoutButton();	 
			}
			else {
				logAndUpdateStatusWithScreenShot("Error message was not displayed on submitting empty form, or an empty user was created, which is not expected behavior.", "TC_ADD_CON_05", TestStatus.FAIL, test);	
				driver.navigate().to(loginPageUrl);
			}

		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An unexpected error occurred while verifying error handling for empty field submission on the 'Add Contact' page; unable to confirm behavior: "+e.getMessage(), "TC_ADD_CON_05", TestStatus.FAIL, test);	
			driver.navigate().to(loginPageUrl);
		}
	}	
	public void validateErrorOnPartialFirstNameSubmission(String validEmail, String validPassword, String firstNameField, String loginPageUrl) {
		ExtentTest test =createTest("verifySubmissionWithOnlyFirstNameFilled","AddContactTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickAddNewContactButton();
			addContactPage.enterFirstName(firstNameField)
			.clickSubmitButton();
			if(addContactPage.errorMessageIsDisplayed()) {
				logAndUpdateStatusWithScreenShot("Validation errors displayed as expected when only the first name was filled required last name the error messase is: "+ addContactPage.getErrorMessage()+ ". No contact was created. ", "TC_ADD_CON_07", TestStatus.PASS, test);	
				addContactPage.clickLogoutButton();
			}else {
				logAndUpdateStatusWithScreenShot("Error message was not displayed on submitting the form after filling only first name, or an empty user was created, which is not expected behavior.", "TC_ADD_CON_07", TestStatus.FAIL, test);	
				driver.navigate().to(loginPageUrl);
			}
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An error occurred during form submission with only the first name filled; unable to verify if validation errors were displayed as expected.The error  message is : "+e.getMessage() ,"TC_ADD_CON_07", TestStatus.FAIL, test);	
			driver.navigate().to(loginPageUrl);
		}
	}
	public void validateErrorOnPartialLastNameSubmission(String validEmail, String validPassword, String secondNameFiled, String loginPageUrl) {
		ExtentTest test =createTest("verifySumbmissionWithOnlyLastNameFielled","AddContactTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickAddNewContactButton();
			addContactPage.enterLastName(secondNameFiled)
			.clickSubmitButton();
			if(addContactPage.errorMessageIsDisplayed()) {
				logAndUpdateStatusWithScreenShot("Validation errors displayed as expected when only the last name was filled. required first  name the error messase is: "+ addContactPage.getErrorMessage()+ ". No contact was created. ", "TC_ADD_CON_08", TestStatus.PASS, test);	
				addContactPage.clickLogoutButton();
			}else {
				logAndUpdateStatusWithScreenShot("Error message was not displayed on submitting the form after filling only last name, or an empty user was created, which is not expected behavior.", "TC_ADD_CON_08", TestStatus.FAIL, test);	
				driver.navigate().to(loginPageUrl);
			}
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An error occurred during form submission with only the last name filled; unable to verify if validation errors were displayed as expected.The error  message is : "+e.getMessage() ,"TC_ADD_CON_08", TestStatus.FAIL, test);	
			driver.navigate().to(loginPageUrl);
		}
	}	
	public void validateSuccessfulSubmissionWithAllFieldsFilled(String validEmail, String validPassword, String firstNameField, String secondNameFiled, String dob, String email, String phone, String addressOne, String addressTwo, String city, String state, String postalCode, String country, String loginPageUrl) {
		ExtentTest test =createTest("verifyContactCreationWithValidDetails","AddContactTestCases");
		setUp(test);
		int beforeCreatingContact; 
		try {
			loginPage.login(validEmail, validPassword);
			beforeCreatingContact = contactListPage.returnTable().size();
			contactListPage.clickAddNewContactButton();
			addContactPage.enterFirstName(firstNameField)
			.enterLastName(secondNameFiled)
			.enterDOB(dob)
			.enterEmail(email)
			.enterPhoneNumber(phone)
			.enterAddressOne(addressOne)
			.enterAddressTwo(addressTwo)
			.enterCityName(city)
			.enterStateName(state)
			.enterPostalCodeNumber(postalCode)
			.enterCountryName(country)
			.clickSubmitButton();
			System.out.println(beforeCreatingContact);
			if(contactListPage.returnTable().size()>beforeCreatingContact) {
				logAndUpdateStatusWithScreenShot("Contact was created successfully as expected when all required fields were filled and submitted.", "TC_ADD_CON_06", TestStatus.PASS, test);	
				contactListPage.clickLogoutButton();	 
			}else {
				logAndUpdateStatusWithScreenShot("Contact was not created even after filling all required fields and submitting, which is unexpected behavior", "TC_ADD_CON_06", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}			
		}
		catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An unexpected error occurred during contact creation test; unable to verify if contact was successfully created on valid form submission.The error is: "+e.getMessage(), "TC_ADD_CON_06", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}
	}
	public void validateCancelButtonNavigatesToContactList(String validEmail, String validPassword, String contactListUrl, String loginPageUrl)
	{
		ExtentTest test =createTest("verifyCancelButtonNavigateToContactList","AddContactTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickAddNewContactButton();
			addContactPage.clickCancelButton();
			if(getCurrentUrl(driver).equals(contactListUrl)) {
				logAndUpdateStatusWithScreenShot("Clicking the Cancel button in addsuccessfully navigates to the Contact List page.", "TC_ADD_CON_10", TestStatus.PASS, test);	
				contactListPage.clickLogoutButton();
			}else {
				logAndUpdateStatusWithScreenShot("Cancel button did not navigate to the Contact List page. Expected redirection to Contact List, but navigation failed.", "TC_ADD_CON_10", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}
		}
		catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An error occurred while verifying Cancel button navigation to the Contact List page. Possible issues with button availability or page loading.The error messgae isL "+e.getMessage(), "TC_ADD_CON_10", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}
	}
	public void validateRequiredFieldsContactCreationWithFirstAndLastName(String validEmail, String validPassword, String firstNameField, String secondNameFiled, String loginPageUrl) {
		ExtentTest test =createTest("verifyContactCreationWithFirstAndLastNameOnlyRequiredFieldsCheck","AddContactTestCases");
		setUp(test);
		int beforeCreatingContact; 
		try {
			loginPage.login(validEmail, validPassword);
			beforeCreatingContact = contactListPage.returnTable().size();
			contactListPage.clickAddNewContactButton();
			addContactPage.enterFirstName(firstNameField)
			.enterLastName(secondNameFiled)
			.clickSubmitButton();
			System.out.println(beforeCreatingContact);
			if(contactListPage.returnTable().size()>beforeCreatingContact) {
				logAndUpdateStatusWithScreenShot("Contact was created successfully as expected when  required fields were filled and submitted.", "TC_ADD_CON_09", TestStatus.PASS, test);	
				contactListPage.clickLogoutButton();	 
			}else {
				logAndUpdateStatusWithScreenShot("Contact was not created even after filling all required fields and submitting, which is unexpected behavior", "TC_ADD_CON_09", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}			
		}
		catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An unexpected error occurred during contact creation test; unable to verify if contact was successfully created on valid form submission.The error is: "+e.getMessage(), "TC_ADD_CON_09", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}
	}
	
	public void validateLogoutButtonAction(String validEmail, String validPassword, String addContactUrl2, String loginPageUrl) {
		ExtentTest test =createTest("verifyLogoutButtonRedirectsToLoginPage","AddContactTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickAddNewContactButton();
			String addContactUrl = getCurrentUrl(driver);
			if(addContactUrl.equals(addContactUrl)) {
				addContactPage.clickLogoutButton();
				if(getCurrentUrl(driver).equals(loginPageUrl)) {
					logAndUpdateStatusWithScreenShot("After clicking addcontact page logout button successfully redirected to the login page", "TC_ADD_CON_12", TestStatus.PASS, test);	

				}else {
					logAndUpdateStatusWithScreenShot("Logout button failed to redirect to the login page; logout functionality is not working as expected.", "TC_ADD_CON_12", TestStatus.FAIL, test);					 			 
				}

			}else {
				logAndUpdateStatusWithScreenShot("After Login clicking the add new button in contact list page it is not naviagate to addcontact page", "TC_ADD_CON_12", TestStatus.FAIL, test);	
				driver.navigate().to(loginPageUrl);
			}
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An unexpected error occurred while verifying the logout button functionality; unable to confirm logout behavior: "+e.getMessage(), "TC_ADD_CON_12", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);		
		}
		closeBrowser(driver); 
	}
	
}
