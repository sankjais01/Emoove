package sanity;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
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

	@BeforeTest
	public void readTestData() {
		data.readExcelData();
	}

	@BeforeSuite
	public void init() {
		driver = BrowserFactory.createDriver("chrome");
		if (driver == null) {
			flag = false;
			System.out.println("Unable to load browser, please check configration first");
			Assert.assertFalse(true);
		}
		System.out.println("Opening Browser");
	}

	@Test(priority = 1)
	public void LoginPageTest() {
		LoginPage login = new LoginPage(driver);
		Assert.assertTrue(login.ValidUserLogin(data), "User failed to Login");

	}

	@Test(priority = 2)
	public void dashboardRecordCountTest() {
		DashboardPage testDashboard = new DashboardPage(driver);
		Assert.assertTrue(testDashboard.testRecordSize(), "Test Number of records failed");

	}

	@Test(priority = 3)
	public void dashboardDateOrderTest() throws InterruptedException {
		DashboardPage testDashboard = new DashboardPage(driver);
		Assert.assertTrue(testDashboard.testDateDescOrder(), "Records are not order by desc- Test Fail");
		Assert.assertTrue(testDashboard.testDateAscOrder(), "Records are not order by Asc- Test Fail");
		Thread.sleep(2000);
		Assert.assertTrue(testDashboard.logout(), "Failed to logout");
	}

	@AfterClass
	public void destroyAll() throws InterruptedException {

		driver.close();
		System.out.println("all driver connection closed");
	}

}
