package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.BaseTest;

public class ExtentReportsManager  extends BaseTest{

    private static ExtentReports extent;   // single ExtentReports instance
    private static ExtentTest test;        // single ExtentTest instance

    public static String reportPath;

    // Get or create ExtentReports
    public static ExtentReports getReportInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            reportPath = "reports/ExtentReport_" + timestamp + ".html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

            reporter.config().setDocumentTitle("Automation Test Report");
            reporter.config().setReportName("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    // Create a new test
    public static ExtentTest createTest(String testName) {
        test = getReportInstance().createTest(testName);
        return test;
    }

    // ✅ Getter for current ExtentTest
    public static ExtentTest getTest() {
        return test;
    }

    // Capture screenshot
    public static String captureScreenshot(WebDriver driver, String screenShotName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/screenshots/" + screenShotName + ".png";

            System.out.println("Screenshot path: " + path);
            FileUtils.copyFile(src, new File(path));
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
