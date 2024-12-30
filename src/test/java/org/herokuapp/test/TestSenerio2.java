//package org.herokuapp.test;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.StaleElementReferenceException;
//import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.WebElement;
//import java.util.List;
//import org.herokuapp.pages.AddContactPage;
//import org.herokuapp.pages.ContactDetailsPage;
//import org.herokuapp.pages.ContactListPage;
//import org.herokuapp.pages.EditContactPage;
//import org.herokuapp.pages.LoginPage;
//import org.herokuapp.pages.SignupPage;
//import org.herokuapp.test.TestInputs.ContactField;
//
//public class TestSenerio2 extends BrowserTest {
//	private String firstname="Naveen";
//	private String secondname="kumar";
//	private String email = "uit2011223@rmd.ac.in";
//	private String password = "naveen@19";
//	private TestInputs inputs;
//	private EditContactPage editContact;
//	private ContactListPage contactListPages;
//	private ContactDetailsPage contactDetails;
//	private AddContactPage addContact;
//
//	@BeforeClass
//	public void setUp() {
//		inputs = new TestInputs();
//		editContact = new EditContactPage(driver, test);
//		contactListPages = new ContactListPage(driver, test);
//		contactDetails = new ContactDetailsPage(driver, test);
//		addContact = new AddContactPage(driver, test);
//	}
//	@Test(priority=0)
//	public void SingUp() {
//		new LoginPage(driver, test).clickSignUpButton();
//		new SignupPage(driver, test)
//		.enterFirstName(firstname)
//		.enterSecondName(secondname)
//		.enterEmail(email)
//		.enterPassword(password)
//		.clickSubmitButton();
//		 logout();
//	}
//	
//	public void login() {
//		try {
//			new LoginPage(driver, test)
//			.enterEmail(email)
//			.enterPassword(password)
//			.clickSubmitButton();
//			System.out.println("Login Successfully!");
//		} catch (Exception e) {
//			System.err.println("Login failed: " + e.getMessage());
//		}
//	}
//	public void logout() {
//        try {
//            contactListPages.clickLogoutButton();
//            System.out.println("Logout Successfully!");
//        } catch (Exception e) {
//            System.err.println("Logout failed: " + e.getMessage());
//        }
//    }
//
//	@Test(priority = 2)s
//	public void addContacts() {
//		try {
//			login();
//			 createTest("TC002");
//			for (String[] contact : inputs.contactsList) {
//				addContact(contact);
//			}
//			System.out.println("Successfully created " + inputs.contactsList.size() + " contacts");
//		} catch (Exception e) {
//			System.err.println("Error while adding contacts: " + e.getMessage());
//		}
//	}
//
//	private void addContact(String[] contact) {
//		try {
//			contactListPages.clickAddNewContactButton();
//			addContact.enterFirstName(contact[0])
//			.enterLastName(contact[1])
//			.enterDOB(contact[2])
//			.enterEmail(contact[3])
//			.enterPhoneNumber(contact[4])
//			.enterAddressOne(contact[5])
//			.enterAddressTwo(contact[6])
//			.enterCityName(contact[7])
//			.enterStateName(contact[8])
//			.enterPostalCodeNumber(contact[9])
//			.enterCountryName(contact[10])
//			.clickSubmitButton();
//		} catch (StaleElementReferenceException e) {
//			addContact(contact); // Retry on stale element
//		}
//	}
//
//	@Test(priority = 3)
//	public void editContactDetails() {
//		for (int i = 0; i < inputs.findingValues.size(); i++) {
//			updateContactDetails(i);
//		}
//		logout();
//	}
//	@Test(priority=4)
//	public void confirmEditedContact() {
//		login();
//		for(int i=0;i<inputs.findingValues.size();i++) {
//			boolean EditedContact=true;
//			List<WebElement> contactNames = contactListPages.returnTableData();
//			for(WebElement contacts:contactNames) {
//				if(contacts.getText().toLowerCase().equals(inputs.findingValues.get(i).toLowerCase())){
//					EditedContact=false;
//				}
//			}
//			if(EditedContact) {
//				System.out.println("The Contact "+inputs.findingValues.get(i)+" is Edited SuccessFully!!!");
//			}
//		}
//	}
//	@Test(priority = 5)
//	public void deleteParticularContact() {
//		for (int i = 0; i < inputs.deleteValues.size(); i++) {
//			deleteContactDetails(i);
//		}
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		logout();
//	}
//
//	@Test(priority = 6)
//	public void confirmDeletedContact() {
//		login();
//		for(int i=0;i<inputs.deleteValues.size();i++) {
//			boolean deletedContact=true;
//			List<WebElement> contactNames = contactListPages.returnTableData();
//			for(WebElement contacts:contactNames) {
//				if(contacts.getText().toLowerCase().equals(inputs.deleteValues.get(i).toLowerCase())){
//					deletedContact=false;
//				}
//			}
//			if(deletedContact) {
//				System.out.println("The Contact "+inputs.deleteValues.get(i)+" is deleted SuccessFully!!!");
//			}
//		}
//	}
//	
//	@Test(priority=7)
//	public void printingRemainingContacts() {
//		List<WebElement> allRows = contactListPages.returnTable();
//		System.out.println("The Contacts counts is "+allRows.size());
//		for (int rowIndex = 0; rowIndex < allRows.size(); rowIndex++) {
//			WebElement row = allRows.get(rowIndex);
//			List<WebElement> allColumns = row.findElements(By.tagName("td"));
//			for (int col=0;col<allColumns.size();col++) {
//				if(col==1) {
//					System.out.println(allColumns.get(col).getText());					
//				}
//			}
//		}
//		logout();
//	}
//	
//	@Test(priority=8)
//	public void deleteAllContacts() {
//		try {
//			login();
//			List<WebElement> allRows = contactListPages.returnTable();
//			int size =allRows.size()-1;
//			System.out.println("delete all contacts" + size );
//			for (int rowIndex = 1; rowIndex < allRows.size(); rowIndex++) {
//				contactListPages.clickEditRow(1);
//				contactDetails.clickDeleteContactButton();
//				}
//		}
//		catch(TimeoutException e) {
//			System.out.println("There is no contect in this ContactList.");
//		}
//		logout();
//	}
//
//	private void deleteContactDetails(int index) {
//		String deleteContact = inputs.deleteValues.get(index).toLowerCase();
//		System.out.println("Now going to delete this "+deleteContact+" in this ContactList");
//		boolean deleted = false;
//		List<WebElement> allRows = contactListPages.returnTable();
//		for (int rowIndex = 0; rowIndex < allRows.size(); rowIndex++) {
//			WebElement row = allRows.get(rowIndex);
//			List<WebElement> allColumns = row.findElements(By.tagName("td"));
//
//			for (WebElement column : allColumns) {
//				if (column.getText().toLowerCase().equals(deleteContact)) {
//					contactListPages.clickEditRow(rowIndex);
//					contactDetails.clickDeleteContactButton();
//					System.out.println("Successfully Deleted the contact "+deleteContact);
//					deleted = true;
//					break;
//				}
//			}
//			if (deleted) {
//				break;
//			}
//		}
//
//	}
//
//	private void updateContactDetails(int index) {
//		List<String> details = inputs.contactDetail.get(index);      
//		// Extract contact details
//		String firstName = details.get(0);
//		String lastName = details.get(1);
//		String dob = details.get(2);
//		String email = details.get(3);
//		String phone = details.get(4);
//		String address1 = details.get(5);
//		String address2 = details.get(6);
//		String city = details.get(7);
//		String state = details.get(8);
//		String postalCode = details.get(9);
//		String country = details.get(10);
//
//		String inputValueToUpdate = inputs.findingValues.get(index).toLowerCase();
//		System.out.println("Updating contact for: " + inputValueToUpdate);
//
//		boolean foundAndUpdated = false;
//		List<WebElement> allRows = contactListPages.returnTable();
//		for (int rowIndex = 0; rowIndex < allRows.size(); rowIndex++) {
//			WebElement row = allRows.get(rowIndex);
//			List<WebElement> allColumns = row.findElements(By.tagName("td"));
//			for (WebElement column : allColumns) {
//				if (updateIfMatch(inputValueToUpdate, column, rowIndex, firstName, lastName, dob, email, phone, address1, address2, city, state, postalCode, country)) {
//					foundAndUpdated = true;
//					break;
//				}
//			}
//			if (foundAndUpdated) {
//				break;
//			}
//		}
//		// Refetch the contact list for the next iteration
//		contactListPages.returnTable();
//	}
//
//
//	private boolean updateIfMatch(String inputValueToUpdate, WebElement column, int rowIndex, String firstName, String lastName, String dob, String email, String phone, String address1, String address2, String city, String state, String postalCode, String country) {
//		String cellText = column.getText().toLowerCase();
//		if (inputValueToUpdate.equals(cellText)) {
//			System.out.println("Column matching name: " + cellText);
//			System.out.println("Updating contact for: " + inputValueToUpdate);
//			contactListPages.clickEditRow(rowIndex);
//			contactDetails.clickEditContactButton();
//			// Update fields conditionally
//			updateField(ContactField.FIRST_NAME, firstName);
//			updateField(ContactField.LAST_NAME, lastName);
//			updateField(ContactField.DOB, dob);
//			updateField(ContactField.EMAIL, email);
//			updateField(ContactField.PHONE_NUMBER, phone);
//			updateField(ContactField.ADDRESS_ONE, address1);
//			updateField(ContactField.ADDRESS_TWO, address2);
//			updateField(ContactField.CITY, city);
//			updateField(ContactField.STATE, state);
//			updateField(ContactField.POSTAL_CODE, postalCode);
//			updateField(ContactField.COUNTRY, country);
//			editContact.clickSubmitButton();
//			contactDetails.clickReturnToContactListButton();
//			return true; // Indicate an update was made
//		}
//		return false; // No match found
//	}
//
//
//	private void updateField(ContactField field, String value) {    	
//		if (value != null) {
//			switch (field) {
//			case FIRST_NAME:
//				editContact.enterFirstName(value);
//				break;
//			case LAST_NAME:
//				editContact.enterLastName(value);
//				break;
//			case DOB:
//				editContact.enterDOB(value);
//				break;
//			case EMAIL:
//				editContact.enterEmail(value);
//				break;
//			case PHONE_NUMBER:
//				editContact.enterPhoneNumber(value);
//				break;
//			case ADDRESS_ONE:
//				editContact.enterAddressOne(value);
//				break;
//			case ADDRESS_TWO:
//				editContact.enterAddressTwo(value);
//				break;
//			case CITY:
//				editContact.enterCityName(value);
//				break;
//			case STATE:
//				editContact.enterStateName(value);
//				break;
//			case POSTAL_CODE:
//				editContact.enterPostalCodeNumber(value);
//				break;
//			case COUNTRY:
//				editContact.enterCountryName(value);
//				break;
//			default:
//				System.out.println("No action defined for field: " + field);
//			}
//			System.out.println("Updated " + field + " with value: " + value + " successfully");
//		}
//	}
//}

