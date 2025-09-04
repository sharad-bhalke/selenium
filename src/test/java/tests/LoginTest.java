package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import base.BaseTest;
import packages.LoginPage;
import utils.ExtentReportsManager;
import org.testng.Assert;

import utils.Log;

public class LoginTest extends BaseTest {
	@Test
	public void testValidLogin()  {
		
		Log.info("This is a login test");
		
		test=ExtentReportsManager.createTest("LOGIN TEST");
		
		test.info("nagatt...");

		LoginPage LoginPage = new LoginPage(driver);
		test.info("entering mail");

		LoginPage.enterUserName("admin@yourstore.com");
		LoginPage.enterPassWord("admin");
		LoginPage.clickOnLoginButton();
		
		test.info("Test is pass");

		System.out.println("this is the title: " + driver.getTitle());
		
		Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration", "Title did not match!");
		
		test.pass("login succeefuly");

	}

}
