package sanity;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import main.BrowserFactory;
import main.TestData;

public class InvokeTest {

	WebDriver driver;
	boolean flag;
	public static TestData data = new TestData();

	@BeforeClass
	public void readTestData() {
		data.readExcelData();
		// System.out.println(data.USERNAME);
		// System.out.println(data.PASSWORD);
	}

	@BeforeTest
	public void init() {
		driver = BrowserFactory.createDriver("chrome");
		if (driver == null) {
			flag = false;
			System.out.println("Unable to load browser, please check configration first");
		}
		System.out.println("Opening Browser");
	}

	@AfterClass
	public void destroyAll() {
		driver.close();
		System.out.println("all driver connection closed");
	}

}
