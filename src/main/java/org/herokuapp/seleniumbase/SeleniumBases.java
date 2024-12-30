package org.herokuapp.seleniumbase;

import org.herokuapp.seleniumbase.Base.WaitTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public interface SeleniumBases {
	WebElement returnElementAfterWaits(WebDriver driver, By locator, int timeoutInSeconds, WaitTypes waitType);
	void clickElement(final By locator);
	void clickElement(final WebElement element);
	void enterText(final By locator, final String text);
	void enterText(final WebElement element, final String text);
	String getElementText(final By locator);
	String getElementText(final WebElement element);
	boolean isElementDisplayed(final By locator);
	boolean isElementDisplayed(final WebElement element);	
    void acceptAlert();
    void dismissAlert();
    String getAlertText();
    void refresh();
}
