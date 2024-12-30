package org.herokuapp.tescases;

import org.herokuapp.test.BrowserTest;
import org.herokuapp.test.EditContactPageTestCases;
import org.testng.annotations.Test;

public class MasterEditContactTest extends BrowserTest{
	private final String validEmail = "uit20128@rmd.ac.in";
	private final String validPassword = "naveen@19";
	private final String invalidFirstNameField ="asdfghjklkjhggfdsasdf";
	private final String invalidSecondNameFiled="asdfghjklkjhggfdsasdf";
	private final String invalidDob="19-04-2003";
	private final String invalidEmail="nav@.co";
	private final String invalidPhone = "9876554321234567654";
	private final String invalidAddressOne="asdfghjklkjhggfdsasdfasdfghjklkjhggfdsasdf";
	private final String invalidAddressTwo="asdfghjklkjhggfdsasdfasdfghjklkjhggfdsasdf";
	private final String invalidCity="asdfghjklkjhggfdsasdfasdfghjklkjhggfdsasdf";
	private final String invalidState ="asdfghjklkjhggfdsasdf";
	private final String invalidPostalCode="asdfghjklkjhggfdsasdf";
	private final String invalidContry = "asdfghjklkjhggfdsasdfasdfghjklkjhggfdsasdf";
	private final String loginPageUrl="https://thinking-tester-contact-list.herokuapp.com/";
	private final String contactDetailPageUrl= "https://thinking-tester-contact-list.herokuapp.com/contactDetails";
	private final String emptyFirstNameField ="";
	private final String emptyLastNameField="";
	private final String updateFirstName="Thraveen";
	private final String updateSecondName="Deepi";
	private final String updatePhoneNumber="9342156668";
	private EditContactPageTestCases editContactTest = new EditContactPageTestCases();
	private final int index=1;
	
	@Test(priority=1)
	public void verifyLogoutButtonRedirectsToLoginPage() {
		editContactTest.validateLogoutFunctionalityRedirectsToLoginPage(validEmail,validPassword,index,loginPageUrl);//TC_EDIT_CONTACT_05
	}
	
	@Test(priority=2)
	public void verifyAllFieldValidationsInEditContactPage() {
		editContactTest.validateFieldValidationsInEditContactPage(validEmail,validPassword,index,invalidFirstNameField,invalidSecondNameFiled,invalidDob,invalidEmail,invalidPhone
				,invalidAddressOne,invalidAddressTwo,invalidCity,invalidState,invalidPostalCode,invalidContry,loginPageUrl);//TC_EDIT_CONTACT_04
	}
	@Test(priority=3)
	public void verifySubmitWithClearedDetailsInEditContactPage() {
		editContactTest.validateSubmitAfterClearingAllDetails(validEmail,validPassword,index,emptyFirstNameField,emptyLastNameField,loginPageUrl);//TC_EDIT_CONTACT_03
	}
	@Test(priority=4)
	public void verifyCancelButtonFunctionalityOnEditContactPage() {
		editContactTest.checkEditContactPageCancelButtonRedirect(validEmail,validPassword,index,loginPageUrl);//TC_EDIT_CONTACT_02
	}
	@Test(priority=5)
	public void verifyEditContactAllowsSavingWithoutChanges() {
		editContactTest.checkEditContactAllowsSavingWithoutChanges(validEmail,validPassword,index,loginPageUrl,contactDetailPageUrl);//TC_EDIT_CONTACT_01
	}
	@Test(priority=6)
	public void verifyEditContactSavesChangesAndRedirectsContactDetails() {
		editContactTest.checkEditContactUpdatesAndRedirectsToContactDetails(validEmail,validPassword,index,contactDetailPageUrl,loginPageUrl,updateFirstName,updateSecondName,updatePhoneNumber);//TC_EDIT_CONTACT_00
	}
}
