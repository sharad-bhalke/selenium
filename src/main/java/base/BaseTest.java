package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import utils.Log;

public class BaseTest {
	
	protected WebDriver driver;
	 @BeforeTest 
	public void setUp() {
		Log.info("Starting the exiscution WebDriver");
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		
		Log.info("navigation to the browser and the browser is open");
		
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		
	}
	 @AfterTest
	 public void closeBrowser() throws InterruptedException {
		 Thread.sleep(4000);
		 
		 Log.info("closing the browser the execution is completed");
		 driver.quit();
		 
		 
		 
	 }
	

}
