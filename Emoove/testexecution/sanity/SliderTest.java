package sanity;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import main.BrowserFactory;
import pom.HomePage;

public class SliderTest {

	WebDriver driver;
	boolean flag;

	@BeforeTest
	public void init() {

		driver = BrowserFactory.createDriver("chrome");
		if (driver == null) {
			flag = false;
			System.out.println("Unable to load browser, please check configration first");
		}
		System.out.println("Opening Browser");

	}

	@Test
	public void sliderVisibiltyTest() {
		HomePage homepage = new HomePage(driver);
		Assert.assertTrue(homepage.onlyThisSliderAvailabiltyTest(), "3 Sliders are not there- TestFail");

	}

	@AfterTest
	public void destroyAll() {
		driver.close();
		System.out.println("all driver connection closed");
	}

}
