//package org.herokuapp.pages;
//import java.util.ArrayList;
//import java.util.List;
//import org.herokuapp.seleniumbase.Base;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//public class ContactListPage extends Base {
//
//    // Constructor
//    public ContactListPage(WebDriver driver) {
//        super(driver);
//    }
//    
////    @FindBy(id="add-contact")
////    public static WebElement addnewcotact; 
//    
//    // Locators
//    private By addNewContactButton = By.id("add-contact");
//    private By logoutButton = By.id("logout");
//    private By table = By.id("myTable");
//    private By tableRow = By.tagName("tr");
//    
//    WebElement findingElements(By locator) {
//		return returnElementAfterWaits(locator,30,WaitTypes.VISIBILITY_OF_ELEMENT);
//	}
//    
//    // Actions
//    public boolean logoutButtonIsDisplayed() {
//    	return isElementDisplayed(findingElements(logoutButton));
//    }
//     
//    public ContactListPage clickAddNewContactButton() {
//        clickElement(findingElements(addNewContactButton));
//        return this;
//    }
//    
//    public ContactListPage clickLogoutButton() {
//        clickElement(findingElements(logoutButton));
//        return this;
//    }
//
//    public ContactListPage clickEditRow(int index) {
//        WebElement tableElement = findingElements(table);
//        List<WebElement> allRows = tableElement.findElements(tableRow);
//
//        // Check if the index is within bounds
//        if (index >= 0 && index < allRows.size()) {
//            WebElement row = allRows.get(index);
//            row.click();  // Perform click on the row
//        } else {
//            // Handle invalid index
//            throw new IndexOutOfBoundsException("Row index " + index + " is out of bounds for table with " + allRows.size() + " rows.");
//        }
//        return this;
//    }
//
//    public List<WebElement> returnTable() {
//        WebElement tableElement = findingElements(table);
//        return tableElement.findElements(tableRow);
//    }
//    public List<WebElement> returnTableData() {
//        WebElement tableElement = findingElements(table);
//        List<WebElement> allRows = tableElement.findElements(tableRow); // Find all rows (<tr>)
//        List<WebElement> allDataCells = new ArrayList<>(); // List to store all <td> elements
//        for (WebElement row : allRows) {
//            List<WebElement> dataCells = row.findElements(By.tagName("td")); // Find <td> elements in each row
//            allDataCells.addAll(dataCells); // Add all <td> elements to the final list
//        }
//        return allDataCells; // Return the list of <td> elements
//    }
//
//}



package org.herokuapp.pages;

import java.util.ArrayList;
import java.util.List;
import org.herokuapp.seleniumbase.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;

public class ContactListPage extends Base {
    private ExtentTest test; // ExtentTest instance for logging

    // Constructor
    public ContactListPage(WebDriver driver, ExtentTest test) {
        super(driver);
        this.test = test; // Initialize the test instance
    }
    
    // Locators
    private By addNewContactButton = By.id("add-contact");
    private By logoutButton = By.id("logout");
    private By table = By.id("myTable");
    private By tableRow = By.tagName("tr");

    // Method to find elements with wait
    private WebElement findingElements(By locator) {
        return returnElementAfterWaits(locator, 30, WaitTypes.VISIBILITY_OF_ELEMENT);
    }
    
    // Actions
    public boolean logoutButtonIsDisplayed() {
        boolean displayed = isElementDisplayed(findingElements(logoutButton));
        test.info("Logout button displayed: " + displayed); // Log the action
        return displayed;
    }
     
    public ContactListPage clickAddNewContactButton() {
        clickElement(findingElements(addNewContactButton));
        test.info("Clicked on 'Add New Contact' button"); // Log the action
        return this;
    }
    
    public ContactListPage clickLogoutButton() {
        clickElement(findingElements(logoutButton));
        test.info("Clicked on 'Logout' button"); // Log the action
        return this;
    }

    public ContactListPage clickEditRow(int index) {
        WebElement tableElement = findingElements(table);
        List<WebElement> allRows = tableElement.findElements(tableRow);

        // Check if the index is within bounds
        if (index >= 0 && index < allRows.size()) {
            WebElement row = allRows.get(index);
            row.click();  // Perform click on the row
            test.info("Clicked on row index: " + index); // Log the action
        } else {
            // Handle invalid index
            throw new IndexOutOfBoundsException("Row index " + index + " is out of bounds for table with " + allRows.size() + " rows.");
        }
        return this;
    }
    
    public List<WebElement> getRowDetails(int index) {
        // Find the table element and get all rows within it
        WebElement tableElement = findingElements(table);
        List<WebElement> allRows = tableElement.findElements(tableRow);

        // Check if the provided index is within bounds
        if (index >= 0 && index < allRows.size()) {
            WebElement row = allRows.get(index); // Get the specific row by index

            // Retrieve all <td> elements within the row
            List<WebElement> dataCells = row.findElements(By.tagName("td"));

            // Log information about the data cells
            test.info("Retrieved " + dataCells.size() + " cells from row at index " + index + ".");

            return dataCells; // Return the list of <td> elements
        } else {
            // Handle invalid index by throwing an exception
            throw new IndexOutOfBoundsException("Row index " + index + " is out of bounds for table with " + allRows.size() + " rows.");
        }
    }


    public List<WebElement> returnTable() {
        WebElement tableElement = findingElements(table);
        return tableElement.findElements(tableRow);
    }
    
    public List<WebElement> returnTableData() {
        WebElement tableElement = findingElements(table);
        List<WebElement> allRows = tableElement.findElements(tableRow); // Find all rows (<tr>)
        List<WebElement> allDataCells = new ArrayList<>(); // List to store all <td> elements
        for (WebElement row : allRows) {
            List<WebElement> dataCells = row.findElements(By.tagName("td")); // Find <td> elements in each row
            allDataCells.addAll(dataCells); // Add all <td> elements to the final list
        }
        test.info("Returned table data with " + allDataCells.size() + " cells."); // Log the action
        return allDataCells; // Return the list of <td> elements
    }
}

