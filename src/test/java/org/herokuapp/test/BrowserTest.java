package org.herokuapp.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class BrowserTest {
    private URI uri;
    protected static ExtentReports extent;  // Declared as static for shared instance across tests
    protected WebDriver driver;
    private final String URL = "https://thinking-tester-contact-list.herokuapp.com/";
    private static final String EXCEL_FILE_NAME = "testStatusData.xlsx";
    private static Map<String, TestStatus> testStatusMap = new LinkedHashMap<>();

    // Enum for test statuses
    public enum TestStatus {
        PASS, FAIL, INFO, WARNING
    }
    
    public synchronized void writeMapToExcel(Map<String, TestStatus> results) {
        try (FileInputStream fis = new FileInputStream(EXCEL_FILE_NAME);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(0); // Assumes the first sheet is used for results
            if (sheet == null) {
                sheet = workbook.createSheet("Test Results");
            }
            int rowCount = sheet.getLastRowNum() + 1; // Start after the last existing row

            for (Entry<String, TestStatus> entry : results.entrySet()) {
                Row row = sheet.createRow(rowCount++);
                row.createCell(0).setCellValue(entry.getKey()); // Test case name
                row.createCell(1).setCellValue(entry.getValue().toString()); // Test status
            }

            try (FileOutputStream fos = new FileOutputStream(EXCEL_FILE_NAME)) {
                workbook.write(fos);
            }
        } catch (FileNotFoundException e) {
            createNewExcelFile(); // Create a new file if it doesn't exist
            writeMapToExcel(results); // Retry writing after creating
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewExcelFile() {
        try (Workbook workbook = new XSSFWorkbook()) {
            workbook.createSheet("Test Results"); // Create initial sheet
            try (FileOutputStream fos = new FileOutputStream(EXCEL_FILE_NAME)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    // Method to add a test result to the map (for example purpose)
    public void addTestResult(String testCase, TestStatus status) {
        testStatusMap.put(testCase, status);
    }


    /**
     * Method to update test status in the map.
     *
     * @param testCaseId ID of the test case.
     * @param status     Status of the test case (e.g., "PASS" or "FAIL").
     */
    public void updateTestStatus(String testCaseId, TestStatus status) {
        testStatusMap.put(testCaseId, status);
        
    }
    
    public ExtentTest createTest(String description, String category) {
        if (extent == null) {
            throw new IllegalStateException("ExtentReports is not initialized.");
        }
        ExtentTest test = extent.createTest(description).assignCategory(category);
        return test;
    }
    /**
     * Logs a message to the test report and updates the test status in the map.
     *
     * @param test       ExtentTest object for logging.
     * @param message    Message to log in the test report.
     * @param testCaseId ID of the test case.
     * @param status     Status of the test case (e.g., PASS or FAIL).
     */
    public void logAndUpdateStatus(String message,String testId, TestStatus status, ExtentTest test) {
        if (status == TestStatus.PASS) {
            test.pass(message);
        } else if (status == TestStatus.FAIL) {
            test.fail(message);
        } else if (status == TestStatus.INFO) {
            test.info(message);
        } else if (status == TestStatus.WARNING) {
            test.warning(message);
        }
        updateTestStatus(testId,status);
    }
    public void logAndUpdateStatusWithScreenShot(String message, String testId, TestStatus status, ExtentTest test) {
        // Capture a screenshot for each log update
        String screenshotURL = captureScreenshot(driver, testId);

        try {
            // Check if the screenshot was successfully captured
            if (screenshotURL != null) {
            	String screenshotPath = "/myapp/" + screenshotURL;
                switch (status) {
                    case PASS:
                        test.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                        break;
                    case FAIL:
                        test.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                        break;
                    case WARNING:
                        test.warning(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                        break;
                    case INFO:
                        test.info(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                        break;
                }
            } else {
                // Log message without screenshot if capture failed
                switch (status) {
                    case PASS:
                        test.pass(message);
                        break;
                    case FAIL:
                        test.fail(message);
                        break;
                    case WARNING:
                        test.warning(message);
                        break;
                    case INFO:
                        test.info(message);
                        break;
                }
            }
        } catch (Exception e) {
            test.warning("Failed to attach screenshot: " + e.getMessage());
        }

        // Update the test status in your tracking method
        updateTestStatus(testId, status);
    }

    public String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
      
            // Save screenshot in Tomcat’s accessible folder
            String destination = "C:/Program Files/Apache Software Foundation/Tomcat 11.0/webapps/myapp/images/" + screenshotName + ".png";
            File finalDestination = new File(destination);
            
            // Copy the screenshot to the specified location
            FileUtils.copyFile(source, finalDestination);
            
            return  "images/" + screenshotName + ".png"; 
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return null;
        }
    }

 

    /**
     * Initializes the ExtentReports instance.
     */
    @BeforeSuite(alwaysRun = true)
    public void setupExtentReport() {
    	System.out.println("it is comming this part");
    	ExtentSparkReporter reporter = new ExtentSparkReporter("C:\\Program Files\\Apache Software Foundation\\Tomcat 11.0\\webapps\\myapp\\testreport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    /**
     * Initializes the WebDriver and launches the browser.
     *
     * @return WebDriver instance
     */
    public WebDriver setUpBrowser() {
    	 String url = "https://www.google.co.in";
         String nodeUrl = "http://192.168.0.107:4444";

         // Set desired capabilities for Microsoft Edge on Windows 11
         DesiredCapabilities capabilities = new DesiredCapabilities();
         capabilities.setBrowserName("chrome");
         capabilities.setPlatform(Platform.WIN10);

         // Initialize the Remote WebDriver instance
		try {
			driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//          Navigate to the desired URL
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.get(URL);
        return driver;
    }

    /**
     * Method to retrieve the last segment of the current URL.
     */
    public String getCurrentUrlSegment(WebDriver driver) {
        String urlString = driver.getCurrentUrl();
        try {
            uri = new URI(urlString);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
        // Get the last segment of the URL path
        return uri != null ? uri.getPath().substring(uri.getPath().lastIndexOf('/') + 1) : "";
    }

    public String getCurrentUrl(WebDriver driver) {
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return driver.getCurrentUrl();
    }

    /**
     * Closes the WebDriver and prints the test results.
     */
    public void closeBrowser(WebDriver driver) {
        if (driver != null) {
        	System.out.println("it is comming close browser report part part");
            driver.quit();
        }
        printTestResults();
    }
    
    @AfterSuite(alwaysRun = true)
    public void saveReport() {
    	System.out.println("it is comming save report part part");
    	 if (extent != null) {
             extent.flush();
         }
    	 writeMapToExcel(testStatusMap);
    }

    public void printTestResults() {
        System.out.println("Test Results:");
        for (Entry<String, TestStatus> entry : testStatusMap.entrySet()) {
            System.out.println("Test Case: " + entry.getKey() + " - Status: " + entry.getValue());
        }
    }
}

