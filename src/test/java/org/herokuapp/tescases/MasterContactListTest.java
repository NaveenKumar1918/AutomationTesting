package org.herokuapp.tescases;

import org.herokuapp.test.BrowserTest;
import org.herokuapp.test.ContactListPageTestCases;
import org.testng.annotations.Test;

public class MasterContactListTest extends BrowserTest {

	private ContactListPageTestCases contactListTestes = new ContactListPageTestCases();
	private final String validEmail = "uit20128@rmd.ac.in";
	private final String validPassword = "naveen@19";
	private final String contactListPageUrl = "https://thinking-tester-contact-list.herokuapp.com/contactList";
	private final String loginPageUrl = "https://thinking-tester-contact-list.herokuapp.com/";
	private final String addContactPageUrl ="https://thinking-tester-contact-list.herokuapp.com/addContact";
	private final String contactDetailsPageUrl ="https://thinking-tester-contact-list.herokuapp.com/contactDetails";
	private final int index = 1;

	//TC_CON_LIST_00 
	@Test(priority=1)
	public void verifyAfterClickLogoutButtonRedirectsToLoginPage() {
		contactListTestes.validateLogoutRedirectsToLoginPage(validEmail,validPassword,contactListPageUrl,loginPageUrl);
	}
	//TC_CON_LIST_01
	@Test(priority=2)
	public void verifyAfterClickAddNewContactButtonRedirectToAddContactPage() {
		contactListTestes.validateAddNewContactRedirectToAddContactPage(validEmail,validPassword,contactListPageUrl,addContactPageUrl);
	}
	//TC_CON_LIST_02
	@Test(priority=3)
	public void verifyRowClickRedirectsToContactDetailsPage() {
		contactListTestes.validateRowClickLeadsToContactDetailsPage(validEmail,validPassword,index,contactListPageUrl,contactDetailsPageUrl);
	}
	//TC_CON_LIST_03
	@Test(priority=4)
	public void verifyRowClickRedirectsAndMatchesContactDetails() {
		contactListTestes.validateRowSelectionAndContactDetailsAccuracy(validEmail,validPassword,index);
	}
}
