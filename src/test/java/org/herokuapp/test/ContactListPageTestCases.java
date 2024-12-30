package org.herokuapp.test;

import java.util.ArrayList;
import java.util.List;

import org.herokuapp.pages.AddContactPage;
import org.herokuapp.pages.ContactDetailsPage;
import org.herokuapp.pages.ContactListPage;
import org.herokuapp.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

public class ContactListPageTestCases extends BrowserTest {
	protected ContactListPage contactListPage;
	protected LoginPage loginPage;
	protected AddContactPage addContactPage;
	protected ContactDetailsPage contactDetailsPage;
	private WebDriver driver;

	public void setUp(ExtentTest test) {
		contactListPage = new ContactListPage(driver, test);
		loginPage = new LoginPage(driver, test);
		addContactPage = new AddContactPage(driver,test);
		contactDetailsPage = new ContactDetailsPage(driver,test);
	}

	public void validateLogoutRedirectsToLoginPage(String validEmail, String validPassword, String contactListPageUrl, String loginPageUrl) {
		driver = setUpBrowser();
		ExtentTest test =createTest("verifyAfterClickingLogoutButtonRedirectsToLoginPage","ContactListTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			String getingContactListUrl = getCurrentUrl(driver);
			contactListPage.clickLogoutButton();
			String getingloginfullUrl = getCurrentUrl(driver);
			System.out.println(getingContactListUrl);
			System.out.println(getingloginfullUrl);
			if(getingContactListUrl.equals(contactListPageUrl) 
					&& getingloginfullUrl.equals(loginPageUrl) ) {
				logAndUpdateStatusWithScreenShot("Logout button clicked successfully, and user is redirected to the login page as expected.", "TC_CON_LIST_00", TestStatus.PASS, test);
			}else {
				logAndUpdateStatusWithScreenShot("Logout button clicked, but the user was not redirected to the login page.", "TC_CON_LIST_00", TestStatus.FAIL, test);
			}

		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("An exception occurred while verifying Logout button clicked  and user is redirected to the login page as expected: " + e.getMessage(), "TC_CON_LIST_00", TestStatus.FAIL, test);
		}
	}

	public void validateAddNewContactRedirectToAddContactPage(String validEmail, String validPassword, String contactListPageUrl, String addContactPageUrl) {
		ExtentTest test =createTest("verifyAfterClickingAddNewContactButtonRedirectToAddContactPage","ContactListTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			String getingContactListUrl = getCurrentUrl(driver);
			contactListPage.clickAddNewContactButton();
			String getingAddContactUrl = getCurrentUrl(driver);
			System.out.println(getingContactListUrl);
			System.out.println(getingAddContactUrl);
			if(getingContactListUrl.equals(contactListPageUrl) 
					&& getingAddContactUrl.equals(addContactPageUrl) ) {
				logAndUpdateStatusWithScreenShot("Add New Contact button clicked, user redirected to Add Contact page successfully.", "TC_CON_LIST_01", TestStatus.PASS, test);
				addContactPage.clickLogoutButton();
			}else {
				logAndUpdateStatusWithScreenShot("Add New Contact button clicked, but user was not redirected to the Add Contact page.", "TC_CON_LIST_01", TestStatus.FAIL, test);
				contactListPage.clickLogoutButton();
			}

		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("Error encountered while attempting to verify redirection to Add Contact page after clicking Add New Contact: " + e.getMessage(), "TC_CON_LIST_01", TestStatus.FAIL, test);
			contactListPage.clickLogoutButton();		
		}
	}

	public void validateRowClickLeadsToContactDetailsPage(String validEmail, String validPassword, int index, String contactListPageUrl, String contactDetailsPageUrl) {
		ExtentTest test =createTest("verifyRowClickRedirectsToContactDetailsPage","ContactListTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail, validPassword);
			String getingContactListUrl = getCurrentUrl(driver);
			contactListPage.clickEditRow(index);
			System.out.println(getingContactListUrl);
			String getingAddContactUrl = getCurrentUrl(driver);
			System.out.println(getingAddContactUrl);
			if(getingContactListUrl.equals(contactListPageUrl) 
					&& getingAddContactUrl.equals(contactDetailsPageUrl)) {
				logAndUpdateStatusWithScreenShot("Row clicked, user successfully redirected to Contact Details page.", "TC_CON_LIST_02", TestStatus.PASS, test);
				addContactPage.clickLogoutButton();
			}
			else {
				logAndUpdateStatusWithScreenShot("Row click did not redirect user to the Contact Details page as expected.", "TC_CON_LIST_02", TestStatus.FAIL, test);
				contactListPage.clickLogoutButton();
			}
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("Error encountered while verifying redirection to Contact Details page after row click." + e.getMessage(), "TC_CON_LIST_02", TestStatus.FAIL, test);
			contactListPage.clickLogoutButton();		
		}
	}
	
	/*When we click any row in the contact list table on the Contact Page, it should redirect to the Contact Details page. 
	The details displayed on the Contact Details page should match the information in the selected row. We need to verify that both sets of data are the same and showing the correct values*/
	public void  validateRowSelectionAndContactDetailsAccuracy(String validEmail, String validPassword, int index) {
		ExtentTest test =createTest("verifyRowClickRedirectsAndMatchesContactDetails","ContactListTestCases");
		setUp(test);
		try {
			loginPage.login(validEmail,validPassword);
			List<WebElement> tableValues = new  ArrayList<>();
			tableValues = contactListPage.returnTableData();
			List<String> contactListPageUserDetails = new  ArrayList<>();
			int i=0;
			for(WebElement contacts:tableValues) {
				if(i<=4) contactListPageUserDetails.add(contacts.getText().toLowerCase());
				else break;
				i++;			
			}
			contactListPage.clickEditRow(index);
			String name =contactDetailsPage.getTextFirstName().toLowerCase()+" "+contactDetailsPage.getTextLastName().toLowerCase();
			String dob = contactDetailsPage.getTextDOB();
			String email = contactDetailsPage.getTextEmail();
			String phoneNo = contactDetailsPage.getTextPhoneNumber();
			if(contactListPageUserDetails.get(1).equals(name)
					&&contactListPageUserDetails.get(2).equals(dob)
					&&contactListPageUserDetails.get(3).equals(email)
					&&contactListPageUserDetails.get(4).equals(phoneNo)
					) {
				logAndUpdateStatusWithScreenShot("Row click redirected to Contact Details page and all details matched the selected row.", "TC_CON_LIST_03", TestStatus.PASS, test);
			}
			else {
				logAndUpdateStatusWithScreenShot("Row click did not redirect to Contact Details page, or contact details did not match the selected row.", "TC_CON_LIST_03", TestStatus.FAIL, test);
			}
		}catch(Exception e) {
			logAndUpdateStatusWithScreenShot("Error encountered while verifying contact details after redirection to Contact Details page: "+ e.getMessage(), "TC_CON_LIST_03", TestStatus.FAIL, test);
		}
		closeBrowser(driver); 
	}


}
