package packages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Log;

public class LoginPage {

    private WebDriver driver;
    
    

    // Locators
    private final By emailTextBox = By.id("Email");
    private final By passwordTextBox = By.id("Password");
    private final By loginButton = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    

    // Page actions
    public void enterUsername(String username) {
        driver.findElement(emailTextBox).clear();
        driver.findElement(emailTextBox).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordTextBox).clear();
        driver.findElement(passwordTextBox).sendKeys(password);
    }

    public void clickLoginButton() {
        Log.info("Clicking on login button");
        driver.findElement(loginButton).click();
    }
}
