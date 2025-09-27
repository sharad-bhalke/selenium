package base;

import java.lang.reflect.Method;
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

import utils.ExtentReportsManager;
import utils.Log;

public class BaseTest {

    protected WebDriver driver;          // driver accessible in child class
    protected ExtentTest test;           // local test for child class
    protected static ExtentReports BaseTest;

    @BeforeSuite
    public void setupReport() {
    	BaseTest = ExtentReportsManager.getReportInstance();
    }

    @AfterSuite
    public void teardownReport() {
    	BaseTest.flush();
        String reportPath = ExtentReportsManager.reportPath;
        // EmailUtils.sendTestReport(reportPath);
    }

    @BeforeMethod
    public void setUp(Method method) {
    	
        Log.info("Starting WebDriver execution");
      //  driver = new ChromeDriver();
       // WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Create ExtentTest for current method and assign locally
        test = ExtentReportsManager.createTest(method.getName());

        Log.info("Navigating to nopCommerce admin login page");
        driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                String screenshotPath = ExtentReportsManager.captureScreenshot(driver, result.getName());
                ExtentReportsManager.getTest().fail(" Test failed, check screenshot",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

            } else if (result.getStatus() == ITestResult.SUCCESS) {
                ExtentReportsManager.getTest().pass(" Test passed successfully");
            } else if (result.getStatus() == ITestResult.SKIP) {
                ExtentReportsManager.getTest().skip("⚠ Test skipped");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                Log.info("Closing the browser - execution completed");
                driver.quit();
            }
        }
    }
    
}
