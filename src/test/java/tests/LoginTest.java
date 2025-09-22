package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import packages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportsManager;
import org.testng.Assert;

import utils.Log;

public class LoginTest extends BaseTest {
	@DataProvider(name = "LoginTest")
	public Object[][] getLoginData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount = ExcelUtils.getRowsCount();
		Object[][] data = new Object[rowCount - 1][2];

		for (int i = 1; i < rowCount; i++) {
			data[i - 1][0] = ExcelUtils.getCellData(i, 0); // username
			data[i - 1][1] = ExcelUtils.getCellData(i, 1); // password

		}
		ExcelUtils.closeExcel();
		return data;
	}

	@Test(dataProvider = "LoginTest")
	public void testValidLogin(String username, String password) {

		Log.info("This is a login test");

		test = ExtentReportsManager.createTest("LOGIN TEST");

		test.info("nagatt...");

		LoginPage LoginPage = new LoginPage(driver);
		test.info("entering mail");

		/*
		 * LoginPage.enterUserName("admin@yourstore.com");
		 * LoginPage.enterPassWord("admin");
		 */

		
		LoginPage.enterUserName(username);
		LoginPage.enterPassWord(password);

		LoginPage.clickOnLoginButton();

		test.info("Test is pass");

		System.out.println("this is the title: " + driver.getTitle());

		Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration", "Title did not match!");

		test.pass("login succeefuly");

	}

}
