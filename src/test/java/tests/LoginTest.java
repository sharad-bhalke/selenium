package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import packages.LoginPage;

public class LoginTest extends BaseTest {
	@Test
	public void testValidLogin()  {

		LoginPage LoginPage = new LoginPage(driver);

		LoginPage.enterUserName("admin@yourstore.com");
		LoginPage.enterPassWord("admin");
		LoginPage.clickOnLoginButton();

		System.out.println("this is the title: " + driver.getTitle());

	}

}
