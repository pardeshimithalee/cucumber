package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddcustomerPage {

	public WebDriver ldriver;

	public AddcustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}

	By lnkCustomer_menu = By.xpath("//a[@href='#']//span[contains(text(), 'Customers')]");
	By lnkCustomer_menuitem = By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");

	By btnAddnew = By.xpath("//a[@class='btn bg-blue'] ");

	By txtEmail = By.xpath("//input[@id='Email']");
	By txtpassword = By.xpath("//input[@id='Password']");

	By txtFirstName = By.xpath("//input[@id='FirstName']");
	By txtLastName = By.xpath("//input[@id='LastName']");

	By rdMaleGender = By.xpath("//input[@id='Gender_Male']");
	By rdFemaleGender = By.xpath("//input[@id='Gender_Female']");

	By txtDOB = By.xpath("//input[@id='DateOfBirth']");

	By txtCompany = By.xpath("//input[@id='Company']");

	By chckBoxtax = By.xpath("//input[@id='IsTaxExempt']");

	By txtcustomerRoles = By.xpath("(//div[@class='k-multiselect-wrap k-floatwrap'])[2]");
	By lstitemAdministrators = By.xpath("//li[contains(text(), 'Administrators')]");
	By lstforumModerators = By.xpath("//li[contains(text(), 'Forum Moderators')]");
	By lstGuests = By.xpath("//li[contains(text(), 'Guests')]");
	By lstRegistered = By.xpath("//li[contains(text(), 'Registered')]");
	By lstVendors = By.xpath("//li[contains(text(), 'Vendors')]");

	By drpmgrOfVendor = By.xpath("//*[@id='VendorId']");

	By txtAdminContent = By.xpath("//*[@id='AdminComment']");
	By btnSave = By.xpath("//button[@name='save']");

	// Now we will write Action methods for this
	
	public String getPageTitle()
	{
		return ldriver.getTitle();
	}

	public void clickOnlnkCustomer_menu() {
		ldriver.findElement(lnkCustomer_menu).click();
	}

	public void clickOnlnkCustomer_menuitem() {
		ldriver.findElement(lnkCustomer_menuitem).click();
	}

	public void clickOnbtnAddnew() {
		ldriver.findElement(btnAddnew).click();
	}

	public void settxtEmail(String email) {
		ldriver.findElement(txtEmail).sendKeys(email);
	}

	public void settxtpassword(String password) {
		ldriver.findElement(txtpassword).sendKeys(password);
	}

	public void settxtFirstName(String fname) {
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}

	public void settxtLastName(String lname) {
		ldriver.findElement(txtLastName).sendKeys(lname);
	}

	public void setGender(String gender) {
		if (gender.equals("Male")) {
			ldriver.findElement(rdMaleGender).click();
		} else if (gender.equals("Female")) {
			ldriver.findElement(rdFemaleGender).click();
		} else {
			ldriver.findElement(rdMaleGender).click(); // By default select this if nothing is passed
		}
	}

	public void setDOB(String dob) {
		ldriver.findElement(txtDOB).sendKeys(dob);
	}

	public void setCompanyName(String comname) {
		ldriver.findElement(txtCompany).sendKeys(comname);
	}

	public void setCustomerRoles(String role) throws InterruptedException {
		if (!role.equals("Vendors")) {
			ldriver.findElement(txtcustomerRoles).click();

			WebElement listitem;

			Thread.sleep(3000);

			if (role.equals("Administrators")) {
				listitem = ldriver.findElement(lstitemAdministrators);
			} else if (role.equals("Guests")) {
				listitem = ldriver.findElement(lstGuests);
			} else if (role.equals("Registered")) {
				listitem = ldriver.findElement(lstRegistered);
			} else if (role.equals("Vendors")) {
				listitem = ldriver.findElement(lstVendors);
			} else {
				listitem = ldriver.findElement(lstforumModerators);
			}

			// listitem.click();
			// Thread.sleep(3000);

			// We will use JavascriptExecutor for this
			JavascriptExecutor js = (JavascriptExecutor) ldriver;
			js.executeScript("arguments[0].click();", listitem);
		}
	}

	public void setManagerOfVendor(String value) {
		Select drp = new Select(ldriver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);

	}

	public void setAdminContent(String content) {
		ldriver.findElement(txtAdminContent).sendKeys(content);
	}

	public void clickOnSave() {
		ldriver.findElement(btnSave).click();
	}

}
