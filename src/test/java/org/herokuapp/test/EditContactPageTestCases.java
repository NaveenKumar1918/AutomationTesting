package org.herokuapp.test;

import java.util.List;
import org.herokuapp.pages.AddContactPage;
import org.herokuapp.pages.ContactDetailsPage;
import org.herokuapp.pages.ContactListPage;
import org.herokuapp.pages.EditContactPage;
import org.herokuapp.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

public class EditContactPageTestCases extends BrowserTest{
	protected ContactListPage contactListPage;
	protected LoginPage loginPage;
	protected AddContactPage addContactPage;
	protected ContactDetailsPage contactDetailsPage;
	protected EditContactPage editContactDetailsPage;
	private WebDriver driver ;

	public void setUp(ExtentTest test) {
		contactListPage = new ContactListPage(driver, test);
		loginPage = new LoginPage(driver, test);
		addContactPage = new AddContactPage(driver,test);
		contactDetailsPage = new ContactDetailsPage(driver,test);
		editContactDetailsPage = new EditContactPage(driver,test);
	}
	
	public void validateLogoutFunctionalityRedirectsToLoginPage(String validEmail, String validPassword, int index, String loginPageUrl) {
		driver = setUpBrowser();
		ExtentTest test =createTest("validateAllContactDetailsElementLoaded","EditContactTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickEditRow(index);
			contactDetailsPage.clickEditContactButton();
			editContactDetailsPage.clickLogoutButton();
			if(getCurrentUrl(driver).equals(loginPageUrl)) {
				logAndUpdateStatusWithScreenShot("Successfully redirected to the login page after clicking the Logout button.", "TC_EDIT_CONTACT_05", TestStatus.PASS, test);
			}else {
				logAndUpdateStatusWithScreenShot("Failed to redirect to the login page after clicking the Logout button.", "TC_EDIT_CONTACT_05", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}
			
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("Error encountered while verifying logout functionality; unable to validate redirection to the login page.", "TC_EDIT_CONTACT_05", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}		
	}
	public void validateFieldValidationsInEditContactPage(String validEmail, String validPassword, int index, String invalidFirstNameField, String invalidSecondNameFiled, String invalidDob, String invalidEmail, String invalidPhone, String invalidAddressOne, String invalidAddressTwo, String invalidCity, String invalidState, String invalidPostalCode, String invalidContry, String loginPageUrl) {
		ExtentTest test =createTest("verifyAllFieldValidationsInEditContactPage","EditContactTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickEditRow(index);
			contactDetailsPage.clickEditContactButton();
			editContactDetailsPage.enterFirstName(invalidFirstNameField)
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
			if(editContactDetailsPage.isDisplayedErrorMessage()) {
				logAndUpdateStatusWithScreenShot("All field validations on the 'edit Contact' page are functioning correctly, with appropriate error messages displaying as expected for invalid or missing inputs.", "TC_EDIT_CONTACT_04", TestStatus.PASS, test);
				editContactDetailsPage.clickLogoutButton();
			}else {
				logAndUpdateStatusWithScreenShot("Some field validations on the 'edit Contact' page are not functioning correctly. Errors did not display as expected for certain invalid or missing inputs.", "TC_EDIT_CONTACT_04", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An unexpected error occurred while attempting to validate fields on the 'Edit Contact' page: " + e.getMessage(), "TC_ADD_CON_04", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}	
	}
	public void validateSubmitAfterClearingAllDetails(String validEmail, String validPassword, int index, String emptyFirstNameField, String emptyLastNameField, String loginPageUrl) {
		ExtentTest test =createTest("verifySubmitWithClearedDetailsInEditContactPage","EditContactTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickEditRow(index);
			contactDetailsPage.clickEditContactButton();
			editContactDetailsPage.enterFirstName(emptyFirstNameField)
			.enterLastName(emptyLastNameField)
			.clickSubmitButton();
			if(editContactDetailsPage.isDisplayedErrorMessage()) {
				logAndUpdateStatusWithScreenShot("Required field validations on the 'edit Contact' page are functioning correctly, with appropriate error messages displaying as expected for invalid or missing inputs.", "TC_EDIT_CONTACT_03", TestStatus.PASS, test);
				editContactDetailsPage.clickLogoutButton();
			}else {
				logAndUpdateStatusWithScreenShot("Required field  validations on the 'edit Contact' page are not functioning correctly. Errors did not display as expected for certain invalid or missing inputs.", "TC_EDIT_CONTACT_03", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}
			
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An unexpected error occurred while attempting to required validate fields on the 'Edit Contact' page: ", "TC_EDIT_CONTACT_03", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}
	}
	
	public void checkEditContactPageCancelButtonRedirect(String validEmail, String validPassword, int index, String loginPageUrl) {
		ExtentTest test =createTest("verifyAllFieldValidationsInEditContactPage","EditContactTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			List<WebElement> datas = contactListPage.getRowDetails(index);
			String beforeSubmitingContactName = datas.get(1).getText().toLowerCase();
			contactListPage.clickEditRow(index);
			contactDetailsPage.clickEditContactButton();
			editContactDetailsPage.clickCancelButton();
			String contactNameInContactDetailsPage= contactDetailsPage.getTextFirstName().toLowerCase()+" "+contactDetailsPage.getTextLastName().toLowerCase();
			if(beforeSubmitingContactName.equals(contactNameInContactDetailsPage)) {
				contactDetailsPage.clickReturnToContactListButton();
				List<WebElement> data = contactListPage.getRowDetails(index);
				String afterSubmitingContactName = data.get(1).getText().toLowerCase();
				if(beforeSubmitingContactName.equals(afterSubmitingContactName)) {
					logAndUpdateStatusWithScreenShot("Clicking the Cancel button redirects to the contact list page, with no changes saved.", "TC_EDIT_CONTACT_02", TestStatus.PASS, test);
					contactListPage.clickLogoutButton();
				}else {
					logAndUpdateStatusWithScreenShot("Clicking the Cancel button redirects to the contact list page, with changes of data is saved.", "TC_EDIT_CONTACT_02", TestStatus.FAIL, test);
					contactListPage.clickLogoutButton();
				}
			}
			else {
				logAndUpdateStatusWithScreenShot("Clicking the Cancel button in editContact details page is  not redirects to the contact list page, with changes of data is saved and reflecting in contactDetailsPage.", "TC_EDIT_CONTACT_02", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("Error occurred while testing the Cancel button functionality on the Edit Contact  page.The error is: "+e.getMessage(), "TC_EDIT_CONTACT_02", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}
		
	}
	public void checkEditContactAllowsSavingWithoutChanges(String validEmail, String validPassword, int index, String loginPageUrl, String contactDetailPageUrl) {
		ExtentTest test =createTest("verifyEditContactAllowsSavingWithoutChanges","EditContactTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickEditRow(index);
			contactDetailsPage.clickEditContactButton();
			Thread.sleep(500);
			editContactDetailsPage.clickSubmitButton();
			if(getCurrentUrl(driver).equals(contactDetailPageUrl)) {
				logAndUpdateStatusWithScreenShot("The Edit Contact page allowed saving the contact without any changes, and it is redirect to contactdetails page successfully", "TC_EDIT_CONTACT_01", TestStatus.PASS, test);
				contactDetailsPage.clickLogoutButton();
			}		
			else {
				logAndUpdateStatusWithScreenShot("The Edit Contact page did not allow saving the contact without any changes, or it is not redirect to contactdetails page.If the error message is throwing: "+editContactDetailsPage.isDisplayedErrorMessage(), "TC_EDIT_CONTACT_01", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("Error occurred while testing the submit button functionality on the Edit Contact  page.The error is: "+e.getMessage(), "TC_EDIT_CONTACT_01", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}
	}
	public void checkEditContactUpdatesAndRedirectsToContactDetails(String validEmail, String validPassword, int index, String contactDetailPageUrl, String loginPageUrl, String updateFirstName, String updateSecondName, String updatePhoneNumber ) {
		ExtentTest test =createTest("verifyEditContactAllowsSavingWithoutChanges","EditContactTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickEditRow(index);
			contactDetailsPage.clickEditContactButton();
			editContactDetailsPage.enterFirstName(updateFirstName)
			.enterLastName(updateSecondName)
			.enterPhoneNumber(updatePhoneNumber)
			.clickSubmitButton();
			if(getCurrentUrl(driver).equals(contactDetailPageUrl) 
					&& contactDetailsPage.getTextFirstName().equals(updateFirstName)
					&& contactDetailsPage.getTextLastName().equals(updateSecondName)
					&& contactDetailsPage.getTextPhoneNumber().equals(updatePhoneNumber)){
				logAndUpdateStatusWithScreenShot("Successfully saved the edited contact details, and the user was redirected to the Contact Details page.", "TC_EDIT_CONTACT_00", TestStatus.PASS, test);
				contactDetailsPage.clickLogoutButton();
			}else {
				logAndUpdateStatusWithScreenShot("Failed to save the edited contact details or did not redirect to the Contact Details page.", "TC_EDIT_CONTACT_00", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}
			closeBrowser(driver); 
			
		}
		catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An error occurred while attempting to save edited contact details, or redirection to the Contact Details page did not happen.The error is: "+e.getMessage(), "TC_EDIT_CONTACT_00", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}
		
	}
}
