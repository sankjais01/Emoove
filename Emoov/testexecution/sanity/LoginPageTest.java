package sanity;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import main.BrowserFactory;
import main.TestData;
import pom.LoginPage;

public class LoginPageTest {

	WebDriver driver;
	boolean flag;
	public static TestData data = new TestData();

	@BeforeTest
	public void readTestData() {
		data.readExcelData();
		// System.out.println(data.USERNAME);
		// System.out.println(data.PASSWORD);
	}

	@BeforeSuite
	public void init() {
		driver = BrowserFactory.createDriver("chrome");
		if (driver == null) {
			flag = false;
			System.out.println("Unable to load browser, please check configration first");
		}
		System.out.println("Opening Browser");
	}

	@Test
	public void LoginTest() {
		LoginPage login = new LoginPage(driver);
		Assert.assertTrue(login.ValidUserLogin(data), "User failed to Login");

	}

	@AfterClass
	public void destroyAll() {
		driver.close();
		System.out.println("all driver connection closed");
	}

}
