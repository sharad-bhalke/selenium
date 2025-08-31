package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	
	protected WebDriver driver;
	 @BeforeTest 
	public void setUp() {
		
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		
	}
	 @AfterTest
	 public void closeBrowser() throws InterruptedException {
		 Thread.sleep(4000);
		 driver.quit();
		 
	 }
	

}
