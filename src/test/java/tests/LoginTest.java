package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import packages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportsManager;
import org.testng.Assert;

import utils.Log;


public class LoginTest extends BaseTest {
	@DataProvider(name = "LoginTest")
	public Object[][] getLoginData() throws IOException {
		String filePath = System.getProperty(("user.dir") + "/testdata/TestData.xlsx");
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

	    Log.info("Starting login test for user: " + username);

	    // Use the test instance from BaseTest
	    test.info("Testing login for: " + username);

	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.enterUserName(username);
	    loginPage.enterPassWord(password);
	    loginPage.clickOnLoginButton();

	    Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration", "Title did not match!");
	    test.pass("Login successful for user: " + username);
	}


	}


