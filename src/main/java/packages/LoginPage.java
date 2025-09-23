package packages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BaseTest;
import utils.Log;

public class LoginPage  extends BaseTest{
	
	private WebDriver driver;
	private By EmailTextBox= By.id("Email");
	private By PassWordTextBox = By.id("Password");
	
	private By LoginButton= By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button");
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		
	}
	
	public void enterUserName(String username)  {
		
		driver.findElement(EmailTextBox).clear();
		driver.findElement(EmailTextBox).sendKeys(username);
	}
	
	public void enterPassWord(String password)  {
		driver.findElement(PassWordTextBox).clear();
		driver.findElement(PassWordTextBox).sendKeys(password);
		
	}
	public void clickOnLoginButton() 
	{
		Log.info("Clicking on login button");
		driver.findElement(LoginButton).click();
	}

}
