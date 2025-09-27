package base;                           // have changes in this file BaseTest are gatting error.............(Now I am on new Stage)

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.ScreenshotUtils;


import org.openqa.selenium.WebDriver;
// ... other imports

public class BaseTest {
    public static WebDriver driver;

  //  public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    

    @BeforeSuite
    public void setupSuite() {
        // Initialize ExtentReports
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setup(Method method) {
//        // Initialize test for ExtentReports
//     //   test = extent.createTest(method.getName());
//    	
//
//        // Setup ChromeDriver
//     //   System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
//        WebDriver driver=new ChromeDriver();
//      //  driver = new ChromeDriver();
    	WebDriver driver = new ChromeDriver();
    	driver.get("https://www.flipkart.com/");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    

    
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
            test.fail(result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
        }
    }
}
