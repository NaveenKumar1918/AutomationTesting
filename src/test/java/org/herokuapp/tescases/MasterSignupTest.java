package org.herokuapp.tescases;
import java.util.Random;

import org.herokuapp.test.BrowserTest;
import org.herokuapp.test.SignupPageTestCases;
import org.testng.annotations.Test;

public class MasterSignupTest extends BrowserTest {

	private SignupPageTestCases singupTestes = new SignupPageTestCases();
	private final String fname = "Naveen";
	private final String lname = "Kumar";
	private final String validEmail = generateUniqueEmail() ;
	private final String invalidEmailFormate="uit201287654@.com";
	private final String Password = "naveen@19";
	private final String emptyField="";
	private final String invalidPasswordLength="naveen";
	private final String errorMessageForAllEmptyFields = "User validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required., email: Email is invalid, password: Path `password` is required.";
	private final String errorMessageForPasswordLengthLessThanSeven = "User validation failed: password: Path `password` (`"+invalidPasswordLength+"`) is shorter than the minimum allowed length (7).";
	private final String errorMessageForEmptyFirstNameField = "User validation failed: firstName: Path `firstName` is required.";
	private final String errorMessageForEmptyLastNameField = "User validation failed: lastName: Path `lastName` is required.";
	private final String errorMessageForEmptyEmailField = "User validation failed: email: Email is invalid";
	private final String errorMessageForEmptyPasswordField = "User validation failed: password: Path `password` is required.";
	private final String errorMessageForIvalidEmailFormate ="User validation failed: email: Email is invalid";

	public  String generateUniqueEmail() {
		int randomNum = new Random().nextInt(10000);
		long timestamp = System.currentTimeMillis();      
		return "user" + randomNum + timestamp + "@example.com";
	}
	@Test(priority = 1)
	public void verifySignupAndRedirectToContactList() {
		singupTestes.verifySignupAndRedirectToContactList(fname, lname, validEmail, Password);	// TC_ADD_USER_00 & 09 & 01
	}
	@Test(priority = 2)
	public void verifySignupWithoutFirstName() {
		singupTestes.signupWithoutFirstName(emptyField, lname, validEmail, Password,errorMessageForEmptyFirstNameField);// TC_ADD_USER_02
	}
	@Test(priority = 3)
	public void verifySignupWithoutLastName() {
		singupTestes.signupWithoutLastName(fname, emptyField, validEmail, Password,errorMessageForEmptyLastNameField);// TC_ADD_USER_03
	}
	@Test(priority = 4)
	public void verifySignupWithoutEmail() {
		singupTestes.signupWithoutEmail(fname, lname, emptyField, Password,errorMessageForEmptyEmailField);// TC_ADD_USER_04
	}
	@Test(priority = 5)
	public void verifySignupWithoutPassword() {
		singupTestes.signupWithoutPassword(fname, lname, validEmail, emptyField,errorMessageForEmptyPasswordField);// TC_ADD_USER_05
	}
	@Test(priority = 6)
	public void VerifyCheckPasswordLengthValidation(){
		singupTestes.checkPasswordLengthValidation(fname, lname, validEmail, invalidPasswordLength,errorMessageForPasswordLengthLessThanSeven);// TC_ADD_USER_06
	}
	@Test(priority = 7)
	public void verifySubmitWithoutAllFields() {
		singupTestes.submitWithoutAllFields(errorMessageForAllEmptyFields);// TC_ADD_USER_07
	}
	@Test(priority = 8)
	public void verifyInvalidEmailFormat() {
		singupTestes.invalidEmailFormat(fname, lname, invalidEmailFormate, Password,errorMessageForIvalidEmailFormate);// TC_ADD_USER_08
	}

}
