package org.herokuapp.tescases;

import org.herokuapp.test.BrowserTest;
import org.herokuapp.test.ContactDetailsPageTestCases;
import org.testng.annotations.Test;

public class MasterContactDetailsTest extends BrowserTest {
	ContactDetailsPageTestCases contactDetailsTest = new ContactDetailsPageTestCases();
	private final String validEmail = "uit20128@rmd.ac.in";
	private final String validPassword = "naveen@19";
	private final String contactListPageUrl = "https://thinking-tester-contact-list.herokuapp.com/contactList";
	private final String loginPageUrl = "https://thinking-tester-contact-list.herokuapp.com/";
	private final String contactDetailsPageUrl ="https://thinking-tester-contact-list.herokuapp.com/contactDetails";
	private final String editContactPageUrl="https://thinking-tester-contact-list.herokuapp.com/editContact";
	private final int index = 1;
	@Test(priority=1)
	public void verifyContactDetailsElementLoadOnRowClick() {
		contactDetailsTest.validateAllContactDetailsElementLoaded(validEmail,validPassword,index,contactDetailsPageUrl,loginPageUrl);//	TC_CON_DETAILS_00
	}
	@Test(priority=2)
	public void verifyEditContactButtonRedirectsToEditContactPage() {
		contactDetailsTest.checkEditContactButtonNavigatesToEditPage(validEmail,validPassword,index,contactDetailsPageUrl,loginPageUrl,editContactPageUrl);//TC_CON_DETAILS_01
	}
	@Test(priority=3)
	public void verifyDeleteContactButtonFunctionalityAcceptOk() {
		contactDetailsTest.deleteContactButtonFunctionalityAcceptOk(validEmail,validPassword,index,contactListPageUrl,loginPageUrl,editContactPageUrl);//TC_CON_DETAILS_02
	}
	@Test(priority=4)
	public void verifyDeleteContactButtonFunctionalityAcceptCancel() {
		contactDetailsTest.deleteContactButtonFunctionalityAcceptCancel(validEmail,validPassword,index,contactListPageUrl,loginPageUrl,contactDetailsPageUrl);//TC_CON_DETAILS_03
	}
	@Test(priority=5)
	public void verifyReturnToContactListButtonFunctionality() {
		contactDetailsTest.returnToContactListButtonFunctionality(validEmail,validPassword,index,contactListPageUrl,loginPageUrl,contactDetailsPageUrl);//TC_CON_DETAILS_04
	}
	@Test(priority=6)
	public void verifyLogoutButtonRedirectsToLoginPage() {
		contactDetailsTest.logoutButtonRedirectsToLoginPage(validEmail,validPassword,index,loginPageUrl);//TC_CON_DETAILS_05	
	}
}
