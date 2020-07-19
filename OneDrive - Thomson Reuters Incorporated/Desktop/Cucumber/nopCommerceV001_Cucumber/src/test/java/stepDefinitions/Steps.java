package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {

	@Before
	public void setup() throws IOException {
		// Reading properties file
		configProp = new Properties();
		FileInputStream configPropFile = new FileInputStream(
				"C:\\Users\\u6032884\\OneDrive - Thomson Reuters Incorporated\\Desktop\\Cucumber\\nopCommerceV001_Cucumber\\config.properties");
		configProp.load(configPropFile);

		// Logs for the steps:
		logger = Logger.getLogger("nopCommerce"); // Add logger
		PropertyConfigurator.configure("Log4j.properties");

		//Setting up Browser specifications
		String br = configProp.getProperty("browser");
		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver();

		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		} else if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
			driver = new InternetExplorerDriver();
		}

		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

	}

	@Given("^User Launch Chrome browser$") // Many things setup was taking place in this so we went above and put basic
											// things there in the above method
	public void user_Launch_Chrome_browser() throws Throwable {

		lp = new LoginPage(driver);

	}

	@When("^User opens URL \"([^\"]*)\"$")
	public void user_opens_URL(String url) throws Throwable {
		driver.get(url);
		logger.info("The url is opening");

	}

	@When("^User enters Email as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void user_enters_Email_as_and_Password_as(String email, String password) throws Throwable {
		logger.info("Providing input details");
		lp.setUserName(email);
		lp.setPassword(password);

	}

	@When("^Click on Login$")
	public void click_on_Login() throws Throwable {
		lp.clickLogin();

	}

	@Then("^Page Title should be \"([^\"]*)\"$")
	public void page_Title_should_be(String title) throws Throwable {
		if (driver.getPageSource().contains("Login was unsuccessful")) {
			driver.close();
			Assert.assertTrue(false);
		} else {
			Assert.assertEquals(title, driver.getTitle());

		}

	}

	@When("^User click on Log out link$")
	public void user_click_on_Log_out_link() throws Throwable {
		lp.clickLogout();
		Thread.sleep(3000);

	}

	@Then("^page Title should be \"([^\"]*)\"$")
	public void page_Title_should_be1(String title) throws Throwable {
		if (driver.getPageSource().contains("Login was unsuccessful")) {
			driver.close();
			Assert.assertTrue(false);
		} else {
			Assert.assertEquals(title, driver.getTitle());

		}

	}

	// Customer Feature step definitions

	@Then("^User can view Dashboard$")
	public void user_can_view_Dashboard() throws Throwable {
		addcust = new AddcustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addcust.getPageTitle());

	}

	@When("^User click on Customers Menu$")
	public void user_click_on_Customers_Menu() throws Throwable {
		Thread.sleep(3000);
		addcust.clickOnlnkCustomer_menu();

	}

	@When("^Click on Customers Menu Item$")
	public void click_on_Customers_Menu_Item() throws Throwable {
		Thread.sleep(3000);
		addcust.clickOnlnkCustomer_menuitem();

	}

	@When("^Click on Add New button$")
	public void click_on_Add_New_button() throws Throwable {
		addcust.clickOnbtnAddnew();
		Thread.sleep(3000);

	}

	@Then("^User can view Add new Customer page$")
	public void user_can_view_Add_new_Customer_page() throws Throwable {
		// this is again validation that user can view Add New Customer Page or not.
		Assert.assertEquals("Add a new customer / nopCommerce administration", addcust.getPageTitle());

	}

	@When("^User enter customer info$")
	public void user_enter_customer_info() throws Throwable {
		// Here we call multiple methods and not single methods
		String email = randomstring() + "@gmail.com"; // This is we have created one random string in the Base class
		addcust.settxtEmail(email);
		addcust.settxtpassword("test123");
		addcust.settxtFirstName("Mithalee");
		addcust.settxtLastName("Pardeshi");
		addcust.setGender("Female");
		addcust.setDOB("7/15/1991");
		addcust.setCompanyName("TR");
		// Registered- default
		// the customer cannot be in both 'Guests' and Registered' customer roles
		// Add the Customer to 'Guests' or Registered Customer role
		addcust.setCustomerRoles("Guest");
		addcust.setManagerOfVendor("Vendor 2");
		addcust.setAdminContent("This is for testing whether evrytihg is working or not");

	}

	@When("^Click on Save button$")
	public void click_on_Save_button() throws Throwable {
		addcust.clickOnSave();
		Thread.sleep(3000);

	}

	@Then("^User can view confirmation message \"([^\"]*)\"$")
	public void user_can_view_confirmation_message(String message) throws Throwable {
		// Validation
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully"));

	}

	// Steps for Searching a Cusotmer using Email ID......
	@When("^Enter customer Email$")
	public void enter_customer_Email() throws Throwable {
		searchCust = new SearchCustomerPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");

	}

	@When("^Click on search button$")
	public void click_on_search_button() throws Throwable {
		searchCust.clickSearch();
		Thread.sleep(3000);

	}

	@Then("^User should found Email in the Search table$")
	public void user_should_found_Email_in_the_Search_table() throws Throwable {
		boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);

	}

	@Then("^close browser$")
	public void close_browser() {
		driver.quit();
	}

	// Now searching a customer by using FirstName and LastName

	@When("^Enter Customer FirstName$")
	public void enter_Customer_FirstName() throws Throwable {
		searchCust = new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");

	}

	@When("^Enter Customer LstName$")
	public void enter_Customer_LstName() throws Throwable {
		searchCust.setLastName("Terces");

	}

	@When("^clicked on Search button$")
	public void clicked_on_Search_button() throws Throwable {
		searchCust.clickSearch();
		Thread.sleep(3000);

	}

	@Then("^User should found name in the Search table$")
	public void user_should_found_name_in_the_Search_table() throws Throwable {
		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(false, status);
	}

	@Then("^CLose the Browser$")
	public void close_the_Browser() {
		driver.quit();
	}

}
