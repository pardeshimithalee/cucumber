package com.freecrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPageNew {
	
	WebDriver driver;
	public LoginPageNew(WebDriver driver) {
		this.driver=driver;
	}
	
	
	
	@FindBy(xpath="//*[@id=\"loginForm\"]/div/input[1]")
	WebElement	username;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"loginForm\"]/div/input[2]")
	WebElement password;
	
	@FindBy(how=How.NAME,using="btn btn-small")
	WebElement sign_in_button;
	
	public void login_freecrm(String uid, String pass) {
		username.sendKeys(uid);
		password.sendKeys(pass);
		sign_in_button.click();
	}
	
	
}
