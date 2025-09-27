package tests;

import org.testng.annotations.Test;

//import base.BaseTest;
import base.BaseTest;   

import packages.LoginPage;

public class LoginTest extends BaseTest {  // extend BaseTest

    @Test
    public void testValidLogin() {
        LoginPage login = new LoginPage(driver); // driver is inherited from BaseTest
        login.enterUsername("admin@example.com");
        login.enterPassword("admin123");
        login.clickLoginButton();
    }
}
