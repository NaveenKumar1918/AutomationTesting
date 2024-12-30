package org.herokuapp.test;

import org.herokuapp.pages.AddContactPage;
import org.herokuapp.pages.ContactDetailsPage;
import org.herokuapp.pages.ContactListPage;
import org.herokuapp.pages.EditContactPage;
import org.herokuapp.pages.LoginPage;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class ContactDetailsPageTestCases extends BrowserTest {
	protected ContactListPage contactListPage;
	protected LoginPage loginPage;
	protected AddContactPage addContactPage;
	protected ContactDetailsPage contactDetailsPage;
	protected EditContactPage editContactDetailsPage;
	private WebDriver driver;

	public void setUp(ExtentTest test) {
		contactListPage = new ContactListPage(driver, test);
		loginPage = new LoginPage(driver, test);
		addContactPage = new AddContactPage(driver,test);
		contactDetailsPage = new ContactDetailsPage(driver,test);
		editContactDetailsPage = new EditContactPage(driver,test);
	}
	
	
	
	public void validateAllContactDetailsElementLoaded(String validEmail, String validPassword, int index, String contactDetailsPageUrl, String loginPageUrl) {
		driver = setUpBrowser();
		ExtentTest test =createTest("verifyContactDetailsElementLoadOnRowClick","ContactDetailsTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickEditRow(index);
			if(getCurrentUrl(driver).equals(contactDetailsPageUrl)
					&& contactDetailsPage.isDisplayedContactDetails() 
					&& contactDetailsPage.isDisplayedDeleteButon()
					&&contactDetailsPage.isDisplayedEditContactButton()
					&& contactDetailsPage.isDisplayedLogutButton()
					&& contactDetailsPage.isDisplayedReturnToContactListButton()) {
				logAndUpdateStatusWithScreenShot("Successfully navigated to the Contact Details page. All required fields and elements are present and correctly loaded.", "TC_CON_DETAILS_00", TestStatus.PASS, test);
				contactDetailsPage.clickLogoutButton();
			}else {
				logAndUpdateStatusWithScreenShot("One or more required elements did not load correctly on the Contact Details page after navigation.", "TC_CON_DETAILS_00", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An error occurred while navigating to the Contact Details page or checking for the presence of required elements.The error message is: "+e.getMessage(),"TC_CON_DETAILS_00", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}
	}
	
	public void checkEditContactButtonNavigatesToEditPage(String validEmail, String validPassword, int index, String contactDetailsPageUrl, String loginPageUrl, String editContactPageUrl) {
		ExtentTest test =createTest("verifyEditContactButtonRedirectsToEditContactPage","ContactDetailsTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickEditRow(index);
			contactDetailsPage.clickEditContactButton();
			if(getCurrentUrl(driver).equals(editContactPageUrl)
					&& editContactDetailsPage.isDisplayedFirstName()
					&&editContactDetailsPage.isDisplayedLastName()
					&&editContactDetailsPage.isDisplayedDob()
					&& editContactDetailsPage.isDisplayedEmail() 
					&& editContactDetailsPage.isDisplayedPhoneNumber()
					&& editContactDetailsPage.isDisplayedAddress1()
					&& editContactDetailsPage.isDisplayedCity()
					&& editContactDetailsPage.isDisplayedState()
					&& editContactDetailsPage.isDisplayedPostalCode()
					&& editContactDetailsPage.isDisplayedCountry()) {
				logAndUpdateStatusWithScreenShot("Successfully navigated to the Edit Contact page, and all fields are enabled for modification as expected.", "TC_CON_DETAILS_01", TestStatus.PASS, test);
				editContactDetailsPage.clickLogoutButton();
			}
			else {
				logAndUpdateStatusWithScreenShot("Failed to navigate to the Edit Contact page, or some fields are not editable as expected.", "TC_CON_DETAILS_01", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}
			
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An error occurred during verification, unable to complete the test of the Edit Contact button functionality due to unexpected behavior or element loading issues.The error message is: "+e.getMessage(), "TC_CON_DETAILS_01", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}
	}
	public void deleteContactButtonFunctionalityAcceptOk(String validEmail, String validPassword, int index, String contactListPageUrl, String loginPageUrl, String editContactPageUrl) {
		ExtentTest test =createTest("verifyDeleteContactButtonFunctionalityAcceptOk","ContactDetailsTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			int beforeDeletingContact = contactListPage.returnTable().size();
			contactListPage.clickEditRow(index);
			contactDetailsPage.clickDeleteContactButton();
			int afterDeletingContact = contactListPage.returnTable().size();
			if(afterDeletingContact<beforeDeletingContact && getCurrentUrl(driver).equals(contactListPageUrl)) {
				logAndUpdateStatusWithScreenShot("The contact was  deleted after accept delete confirmation.", "TC_CON_DETAILS_02", TestStatus.PASS, test);
				contactListPage.clickLogoutButton();
			}else {
				logAndUpdateStatusWithScreenShot("The contact was not deleted after accept delete confirmation.", "TC_CON_DETAILS_02", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}
		}
		catch(Exception e) {
			logAndUpdateStatusWithScreenShot("Unexpected error when trying to delete the contact or handle the alert.The error message is: "+e.getMessage(), "TC_CON_DETAILS_02", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}
	}
	public void deleteContactButtonFunctionalityAcceptCancel(String validEmail, String validPassword, int index, String contactListPageUrl, String loginPageUrl, String contactDetailsPageUrl) {
		ExtentTest test =createTest("verifyDeleteContactButtonFunctionalityAcceptCancel","ContactDetailsTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			int beforeDeletingContact = contactListPage.returnTable().size();
			contactListPage.clickEditRow(index);
			contactDetailsPage.clickDeleteContactButtonAndCancel();
			if(getCurrentUrl(driver).equals(contactDetailsPageUrl)) {
				contactDetailsPage.clickReturnToContactListButton();
				int afterCalcelDeletingContact = contactListPage.returnTable().size();
				if(getCurrentUrl(driver).equals(contactListPageUrl) && beforeDeletingContact == afterCalcelDeletingContact) {
					logAndUpdateStatusWithScreenShot("The contact was not deleted after canceling the delete confirmation.", "TC_CON_DETAILS_03", TestStatus.PASS, test);
					contactListPage.clickLogoutButton();
				}else {
					logAndUpdateStatusWithScreenShot("The contact was deleted despite canceling the delete confirmation or not redirect to contactlist Page.", "TC_CON_DETAILS_03", TestStatus.FAIL, test);
					driver.navigate().to(loginPageUrl);
				}
			}
			else {
				logAndUpdateStatusWithScreenShot("The contact was not deleted or the user was not redirected to the Contact List page after Cancel deletion", "TC_CON_DETAILS_03", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}
			
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("Unexpected error when attempting to cancel delete the contact or handle the delete confirmation alert.The error is: "+e.getMessage(), "TC_CON_DETAILS_03", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}
	}
	public void returnToContactListButtonFunctionality(String validEmail, String validPassword, int index, String contactListPageUrl, String loginPageUrl, String contactDetailsPageUrl) {
		ExtentTest test =createTest("verifyReturnToContactListButtonFunctionality","ContactDetailsTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickEditRow(index);
			contactDetailsPage.clickReturnToContactListButton();
			if(getCurrentUrl(driver).equals(contactListPageUrl)) {
				logAndUpdateStatusWithScreenShot("It was successfully redirected to the Contact List page.", "TC_CON_DETAILS_04", TestStatus.PASS, test);
				contactListPage.clickLogoutButton();
			}else {
				logAndUpdateStatusWithScreenShot("It was  not redirected to the Contact List page or unexpected changes were detected.", "TC_CON_DETAILS_04", TestStatus.FAIL, test);
				driver.navigate().to(loginPageUrl);
			}
		}
		catch(Exception e) {
			logAndUpdateStatusWithScreenShot("Unexpected error encountered while verifying the functionality of the 'Return to Contact List' button.The error is: "+e.getMessage(), "TC_CON_DETAILS_04", TestStatus.FAIL, test);
			driver.navigate().to(loginPageUrl);
		}
	}
	
	public void logoutButtonRedirectsToLoginPage(String validEmail, String validPassword, int index, String loginPageUrl) {
		ExtentTest test =createTest("verifyLogoutButtonRedirectsToLoginPage","ContactDetailsTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			contactListPage.clickEditRow(index);
			contactDetailsPage.clickLogoutButton();
			if(getCurrentUrl(driver).equals(loginPageUrl)) {
				logAndUpdateStatusWithScreenShot("It was successfully redirected to the  Loginpage.", "TC_CON_DETAILS_05", TestStatus.PASS, test);
			}
			else {
				logAndUpdateStatusWithScreenShot("It was not successfully redirected to the  Loginpage.", "TC_CON_DETAILS_05", TestStatus.FAIL, test);
			}
		}
		catch(Exception e) {
			logAndUpdateStatusWithScreenShot("Unexpected error encountered while verifying the functionality of the logout button.The error is: "+e.getMessage(), "TC_CON_DETAILS_05", TestStatus.FAIL, test);
		}
		closeBrowser(driver); 
	}
	
}
