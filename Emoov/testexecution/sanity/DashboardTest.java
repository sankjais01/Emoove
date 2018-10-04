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

	//@Test(priority = 3)
	public void dashboardRecordCountTest() {
		DashboardPage testDashboard = new DashboardPage(driver);
		Assert.assertTrue(testDashboard.testRecordSize(), "Test Number of records failed");
	
	}
	
	@Test(priority = 2)
	public void dashboardDateOrderTest() {
		DashboardPage testDashboard = new DashboardPage(driver);
		testDashboard.testDateOrder("desc");
		testDashboard.testDateOrder("asc");
		

	}

	@AfterClass
	public void destroyAll() {
		driver.close();
		System.out.println("all driver connection closed");
	}

}
