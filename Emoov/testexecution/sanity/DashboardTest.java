package sanity;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import main.BrowserFactory;
import main.TestData;
import pom.DashboardPage;
import pom.LoginPage;

public class DashboardTest {



	WebDriver driver;
	boolean flag;
	public static TestData data = new TestData();

	@BeforeClass
	public void readTestData() {
		data.readExcelData();
	}

	@BeforeClass
	public void init() {
		driver = BrowserFactory.createDriver("chrome");
		if (driver == null) {
			flag = false;
			System.out.println("Unable to load browser, please check configration first");
		}
		System.out.println("Opening Browser");
	}

	@Test(priority = 1)
	public void LoginPageTest() {
		LoginPage login = new LoginPage(driver);
		Assert.assertTrue(login.ValidUserLogin(data), "User failed to Login");

	}
	
	
	
	@Test(priority = 2)
	public void DashboardPageTest() {
		DashboardPage testDashboard= new DashboardPage(driver);
		testDashboard.testRecordSize();

	}
	

	@AfterClass
	public void destroyAll() {
		driver.close();
		System.out.println("all driver connection closed");
	}



}



