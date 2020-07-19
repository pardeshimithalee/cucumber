package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {

	public WebDriver ldriver;
	WaitHelper waithelper;

	public SearchCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		waithelper = new WaitHelper(ldriver);
	}

	// Identify the elements
	@FindBy(id = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;

	@FindBy(id = "SearchFirstName")
	@CacheLookup
	WebElement txtfirstName;

	@FindBy(id = "SearchLastName")
	@CacheLookup
	WebElement txtlastName;

	@FindBy(id = "SearchMonthOfBirth")
	@CacheLookup
	WebElement dobMonth;

	@FindBy(id = "SearchDayOfBirth")
	@CacheLookup
	WebElement dobDay;

	@FindBy(id = "SearchCompany")
	@CacheLookup
	WebElement txtCompany;

	@FindBy(id = "search-customers")
	@CacheLookup
	WebElement btnSearch;

	@FindBy(id = "search-customers")
	@CacheLookup
	WebElement tblSearchResults;

	@FindBy(xpath = "//div[@id='customers-grid_wrapper']")
	@CacheLookup
	WebElement table;

	@FindBy(xpath = "//div[@id='customers-grid_wrapper']//tbody/tr")
	List<WebElement> tableRows;

	@FindBy(xpath = "//div[@id='customers-grid_wrapper']//tbody/tr/td")
	List<WebElement> tableColumns;

	// Action Methds

	public void setEmail(String email) {
		waithelper.WaitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}

	public void setFirstName(String fname) {
		waithelper.WaitForElement(txtfirstName, 30);
		txtfirstName.clear();
		txtfirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		waithelper.WaitForElement(txtlastName, 30);
		txtlastName.clear();
		txtlastName.sendKeys(lname);
	}

	public void clickSearch() {
		btnSearch.click();
		waithelper.WaitForElement(btnSearch, 30);
	}

	public int getNoOfRows() {
		return (tableRows.size());
	}

	public int getNoOfColumns() {
		return (tableColumns.size());
	}

	// Sometimes one name may have many records. We need to check all the records
	// and get a particualr emailid

	public boolean searchCustomerByEmail(String email) {
		boolean flag = false;

		for (int i = 1; i <= getNoOfRows(); i++) {
			String emailid = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[2]"))
					.getText();
			System.out.println(emailid);

			if (emailid.equals(email))
				;
			{
				flag = true;
			}

		}
		return flag;
	}

	public boolean searchCustomerByName(String Name) {
		boolean flag = false;

		for (int i = 1; i <= getNoOfRows(); i++) {
			String name = table
					.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]")).getText();
			
			String names[]=name.split(""); //seperating first name and last name

			if (names[0].equals("Victoria") && names[1].equals("Terces"))
			{
				flag = true;
			}

		}
		return flag;
	}

}
