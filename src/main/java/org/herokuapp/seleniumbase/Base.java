package org.herokuapp.seleniumbase;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Base{
	public WebDriver driver;
		public  URI uri;
    // Constructor
    public Base(WebDriver driver) {
        this.driver = driver;
    }
    public enum WaitTypes {
        ELEMENT_TO_BE_VISIBLE,
        ELEMENT_TO_BE_CLICKABLE,
        ELEMENT_TO_BE_PRESENT,
        ELEMENT_TO_BE_SELECTED,
        VISIBILITY_OF_ELEMENT
    } 
   
    /**
     * @description Handles different wait types using a switch case and returns the WebElement.
     * @param driver           The WebDriver instance.
     * @param locator          The locator of the element.
     * @param timeoutInSeconds The time in seconds to wait.
     * @param waitType         The type of wait to perform (specified using the WaitType enum).
     * @param additionalInfo   Additional information like text or attribute value for specific wait conditions (optional).
     * @return The WebElement that satisfies the wait condition.
     */
    public WebElement returnElementAfterWaits(By locator, int timeoutInSeconds, WaitTypes waitType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        WebElement element;

        switch (waitType) {
            case VISIBILITY_OF_ELEMENT: // Merged both into one since they have the same ExpectedCondition
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                return element;               
                
            case ELEMENT_TO_BE_CLICKABLE:
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                return element;

            case ELEMENT_TO_BE_PRESENT:
                element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                return element;

            case ELEMENT_TO_BE_SELECTED:
                wait.until(ExpectedConditions.elementToBeSelected(locator));
                element = driver.findElement(locator); // Return element after it's selected
                return element;

            default:
                throw new UnsupportedOperationException("Unsupported wait type: " + waitType);
        }

    }
    // Common method to click on an element
    public void clickElement(final By locator) {
        driver.findElement(locator).click();
    }
    public void clickElement(final WebElement element) {
    	element.click();
    }
    // Common method to enter text in a text box
    public void enterText(final By locator, final String text) {
        final WebElement element = driver.findElement(locator);
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        element.clear();
        element.sendKeys(text);
    }
    public void enterText(final WebElement element, final String text) {
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        element.clear();
        element.sendKeys(text);
    }
    // Common method to get text from an element
    public String getElementText(final By locator) {
        return driver.findElement(locator).getText();
    }
    public String getElementText(final WebElement element) {
        return element.getText();
    }
    // Common method to verify if an element is displayed
    public boolean isElementDisplayed(final By locator) {
        return driver.findElement(locator).isDisplayed();
    }
    public boolean isElementDisplayed(final WebElement element) {
        return element.isDisplayed();
    }
    // Method to accept the alert (click OK)
    public void acceptAlert() {
        Alert alert = driver.switchTo().alert(); 
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// Switch to alert
        alert.accept(); // Accept the alert
    }

    // Method to dismiss the alert (click Cancel)
    public void dismissAlert() {
        Alert alert = driver.switchTo().alert(); // Switch to alert
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        alert.dismiss(); // Dismiss the alert
    }

    // Method to get the alert text
    public String getAlertText() {
        Alert alert = driver.switchTo().alert(); // Switch to alert
        return alert.getText(); // Get alert text
    }
    public void refresh() {
    	 driver.navigate().refresh();
    }
	
    public String currentURL() {
    	String urlString = driver.getCurrentUrl();
		try {
			uri = new URI(urlString);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Get the path part of the URL
        String path = uri.getPath();

        // Split the path by "/" and get the last segment
        String lastSegment = path.substring(path.lastIndexOf('/') + 1);
		return lastSegment;
    }
	
}
