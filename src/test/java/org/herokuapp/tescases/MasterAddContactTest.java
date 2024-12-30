package org.herokuapp.tescases;

import org.herokuapp.test.BrowserTest;
import org.herokuapp.test.AddContactPageTestCases;
import org.testng.annotations.Test;

public class MasterAddContactTest extends BrowserTest {
	private AddContactPageTestCases addContactPageTest = new AddContactPageTestCases();
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
	private final String FirstNameField ="Naveen";
	private final String SecondNameFiled="Kumar";
	private final String FirstNameFieldTwo ="Deepak";
	private final String SecondNameFiledTwo="Kumar";
	private final String Dob="2003-04-19";
	private final String Email="naveen@gmail.com";
	private final String Phone = "7708680240";
	private final String AddressOne="21/B Anna Nagar";
	private final String AddressTwo="3rd  street";
	private final String City="Chennai";
	private final String State ="TN";
	private final String PostalCode="631003";
	private final String country ="India";
	private final String addContactUrl="https://thinking-tester-contact-list.herokuapp.com/addContact";
	private final String loginPageUrl="https://thinking-tester-contact-list.herokuapp.com/";
	private final String contactListUrl="https://thinking-tester-contact-list.herokuapp.com/contactList";
	
	@Test(priority=1)	
	public void verifyFormFieldValidationsForAddContact() {
		addContactPageTest.validateAllFieldsInAddContactPage(validEmail,validPassword,invalidFirstNameField,invalidSecondNameFiled,invalidDob,invalidEmail,invalidPhone
				,invalidAddressOne,invalidAddressTwo,invalidCity,invalidState,invalidPostalCode,invalidContry);//TC_ADD_CON_04
	}
	
	@Test(priority=2)
	public void verifyErrorOnEmptySubmitInAddContactPage() {
		addContactPageTest.validateErrorOnBlankSubmissionInAddContactPage(validEmail,validPassword,loginPageUrl);//TC_ADD_CON_05
	}

	@Test(priority=3)
	public void verifyContactCreationWithValidDetails() {
		addContactPageTest.validateSuccessfulSubmissionWithAllFieldsFilled(validEmail,validPassword,FirstNameField,SecondNameFiled,Dob,Email,Phone
				,AddressOne,AddressTwo,City,State,PostalCode,country,loginPageUrl);	//TC_ADD_CON_06
	}

	@Test(priority=4)
	public void verifySubmissionWithOnlyFirstNameFilled() {
		addContactPageTest.validateErrorOnPartialFirstNameSubmission(validEmail,validPassword,FirstNameField,loginPageUrl);	//TC_ADD_CON_07
	}
	
	@Test(priority=5)
	public void verifySumbmissionWithOnlyLastNameFielled() {
		addContactPageTest.validateErrorOnPartialLastNameSubmission(validEmail,validPassword,SecondNameFiled,loginPageUrl);//TC_ADD_CON_08
	}
	@Test(priority=6)
	public void verifyContactCreationWithFirstAndLastNameOnlyRequiredFieldsCheck() {
		addContactPageTest.validateRequiredFieldsContactCreationWithFirstAndLastName(validEmail,validPassword,FirstNameFieldTwo,SecondNameFiledTwo,loginPageUrl);//TC_ADD_CON_09
	}
	@Test(priority=7)
	public void verifyCancelButtonNavigateToContactList() {
		addContactPageTest.validateCancelButtonNavigatesToContactList(validEmail,validPassword,contactListUrl,loginPageUrl);//TC_ADD_CON_10
	}
	@Test(priority=8)	
	public void verifyLogoutButtonRedirectsToLoginPage() {
		addContactPageTest.validateLogoutButtonAction(validEmail, validPassword,addContactUrl,loginPageUrl);//TC_ADD_CON_12
	}
	
	
}
