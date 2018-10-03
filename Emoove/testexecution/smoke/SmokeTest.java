package smoke;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import locators.*;
import main.BrowserFactory;
import pom.HomePage;

public class SmokeTest {

	WebDriver driver;
	boolean flag;
	boolean flagSlider;
	SoftAssert asert = new SoftAssert();

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
	public void homePageTest() throws InterruptedException {

		HomePage homepage = new HomePage(driver);
		homepage.checkClickableObject_URL();
		System.out.println("Total Clickable Links are - " + homepage.countClickableLink());
		// System.out.println("Total arrivals present are - " +
		// homepage.countArrivals());
		/*
		 * System.out.println("Total slides present inside slider are - " +
		 * homepage.countSlider()); if (homepage.countSlider() == 3) { flagSlider =
		 * true; }
		 * 
		 * Assert.assertTrue(flagSlider, "SliderTestFail"); //
		 * driver.findElement(By.cssSelector("BODY")).sendKeys(Keys.CONTROL+"t");
		 */
		// String Parent_Window = driver.getWindowHandle();
		// System.out.println("Parent window - " + Parent_Window);
		/*
		 * int k = 0; for (String Child_Window : driver.getWindowHandles()) {
		 * System.out.println("child window   -" + k + " " + Child_Window);
		 * driver.switchTo().window(Child_Window); Thread.sleep(1000); if
		 * (!Parent_Window.equalsIgnoreCase(Child_Window)) {
		 * 
		 * driver.close(); System.out.println("closing window -" + k + "   " +
		 * Child_Window); Thread.sleep(500);
		 * 
		 * } k++; } driver.switchTo().window(Parent_Window);
		 */

		// driver.get("https://www.facebook.com/");

		// System.out.println("new tab");
		// Thread.sleep(2000);

		/*
		 * try { List<WebElement> clickableLinks = driver.findElements(By.tagName("a"));
		 * 
		 * //System.out.println(clickableLinks.size());
		 * 
		 * int count = 0;
		 * 
		 * for (int i = 0; i < clickableLinks.size(); i++) {
		 * 
		 * clickableLinks. }
		 * 
		 * for (WebElement webElement : clickableLinks) {
		 * 
		 * // webElement.getAttribute("href"); //
		 * System.out.println(webElement.getText().length()); //
		 * System.out.println(webElement.getText()); if (webElement.getText().length()
		 * != 0) { System.out.println("navigating to link of text - " +
		 * webElement.getText()); webElement.click();
		 * asert.assertFalse(driver.getCurrentUrl().equals(Locators.HOMEPAGE_URL),
		 * "Link not clickable");
		 * 
		 * Thread.sleep(2000); driver.get(Locators.HOMEPAGE_URL); count++; }
		 * 
		 * } System.out.println("total clickable links are  -  " + count); } catch
		 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace();
		 * System.err.println(e); }
		 * 
		 */}

	@AfterTest
	public void destroyAll() {
		driver.close();
		System.out.println("all driver connection closed");
	}

}
