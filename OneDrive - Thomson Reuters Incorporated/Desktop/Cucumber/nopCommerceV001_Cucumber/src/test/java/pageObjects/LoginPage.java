package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(id = "Email")
	@CacheLookup 
	WebElement txtemail;

	@FindBy(id = "Password")
	@CacheLookup
	WebElement txtpassword;

	@FindBy(xpath = "//input[@type='submit']")
	@CacheLookup
	WebElement btnlogin;

	@FindBy(xpath = "//a[@href='/logout']")
	@CacheLookup
	WebElement lnklogout;

	public void setUserName(String uname) {
		txtemail.clear();
		txtemail.sendKeys(uname);
	}
	
	public void setPassword(String pwd)
	{
		txtpassword.clear();
		txtpassword.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btnlogin.click();
	}
	
	public void clickLogout()
	{
		lnklogout.click();
	}
	
}
