package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.EmailUtils;
import utils.ExtentReportsManager;
import utils.Log;

public class BaseTest {

    protected static ExtentReports extent;
    protected ExtentTest test;
    protected WebDriver driver;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentReportsManager.getReportInstance();
    }

    @AfterSuite
    public void teardownReport() {
        extent.flush();

        // Send report via email
        String reportPath = ExtentReportsManager.reportPath;
       // EmailUtils.sendTestReport(reportPath);
    }

    @BeforeMethod
    public void setUp() {
        Log.info("Starting WebDriver execution");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        Log.info("Navigating to nopCommerce admin login page");
        driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                String screenshotPath = ExtentReportsManager.captureScreenshot(driver, result.getName());
                ExtentReportsManager.getTest().fail("Test failed ... check screenshot",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (driver != null) {
            Log.info("Closing the browser - execution completed");
            driver.quit();
        }
    }
}
