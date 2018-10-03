package pom;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.GetTagName;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import locators.URLLocators;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;

public class HomePage {

	boolean flag = false;
	boolean sliderOneflag = false;
	boolean sliderTwoflag = false;
	boolean sliderThreeflag = false;
	WebDriver driver;
	String clickable_object[], clickable_object_url[];
	SoftAssert softAssert = new SoftAssert();
	String Parent_Window;

	// WebDriverWait wait = new WebDriverWait(driver, 10000);

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.get(URLLocators.HOMEPAGE_URL);
		Parent_Window = driver.getWindowHandle();
		objectFecther();
	}

	// for fetching all clickable link object on current page

	void objectFecther() {
		try {
			int i = 0, count = countClickableLink();
			clickable_object = new String[count];
			clickable_object_url = new String[count];
			for (WebElement webElement : clickableLinks) {
				if (webElement.getText().length() > 0) {
					// this can be use to avoid links in new tab - !&&
					// webElement.getAttribute("target").contains("_blank")
					if (!webElement.getAttribute("href").contains("?")) {
						clickable_object[i] = webElement.getText();
						clickable_object_url[i] = webElement.getAttribute("href");
						i++;
					}
				}
			}
		} catch (NoSuchElementException | IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e);
		}
	}

	@FindBy(xpath = URLLocators.SLIDERFRAME_XPATH)
	WebElement slider;

	@FindBy(xpath = URLLocators.SLIDERARROW_PREVIOUS_XPATH)
	WebElement slider_previous_arrow;

	@FindBy(xpath = URLLocators.SLIDERARROW_NEXT_XPATH)
	WebElement slider_next_arrow;

	@FindBy(xpath = URLLocators.SLIDERONE_XPATH)
	WebElement slider_one;

	@FindBy(xpath = URLLocators.SLIDERTWO_XPATH)
	WebElement slider_two;

	@FindBy(xpath = URLLocators.SLIDERTHREE_XPATH)
	WebElement slider_three;

	@FindBy(xpath = URLLocators.MENUITEM_SHOP_XPATH)
	WebElement menu_shop;

	@FindBy(xpath = URLLocators.HOMELOGO_XPATH)
	WebElement HomeLogo;

	@FindBy(tagName = "a")
	List<WebElement> clickableLinks;

	// WebElement URLobject;

	public int countClickableLink() {
		int count = 0;
		for (WebElement webElement : clickableLinks) {
			if (webElement.getText().length() > 0) {
				if (!webElement.getAttribute("href").contains("?")) {
					// System.out.println(webElement.getText());
					count++;
				}
				//count++;
			}
		}

		return count;
	}

	public boolean checkClickableObject_URL() throws InterruptedException {
		String C_Window = null;
		System.out.println("Parent window -  " + Parent_Window);
		for (int j = 1; j <= clickable_object.length; j++) {
			driver.findElement(By.linkText(clickable_object[j - 1])).click();

			for (String Child_Window : driver.getWindowHandles()) {
				driver.switchTo().window(Child_Window);
				C_Window = Child_Window;
			}

			if (driver.getCurrentUrl().equalsIgnoreCase(clickable_object_url[j - 1])) {
				softAssert.assertTrue(true,"");
				System.out.println("clicking on " + clickable_object[j - 1]);
				if (!C_Window.equalsIgnoreCase(Parent_Window)) {
					driver.close();

				}
				

				driver.switchTo().window(Parent_Window);

			} else {
				softAssert.assertTrue(false);
				System.out.println(clickable_object[j - 1] + "- Object not clickable of - " + getClass());
				driver.switchTo().window(Parent_Window);
			}
			driver.get(URLLocators.HOMEPAGE_URL);
			Thread.sleep(500);
		}
		return flag;
	}

	public int countArrivals() {

		List<WebElement> element = driver.findElements(By.xpath("//ul[@class='products']"));
		return element.size();
	}

	public int countSlider() {

		List<WebElement> element = driver.findElements(By.xpath("//*[@class='row_inner']"));
		return element.size();
	}

	public boolean onlyThisSliderAvailabiltyTest() {

		try {
			Assert.assertTrue(slider.isDisplayed(), "Slider is Not loaded");
			Assert.assertTrue(slider_previous_arrow.isDisplayed(), "Previous arrow not visible, unable to slide");
			Assert.assertTrue(slider_next_arrow.isDisplayed(), "Next arrow not visible, unable to slide");
			for (int i = 1; i < 4; i++) {
				slider_previous_arrow.click();
				Thread.sleep(500);
				if (slider_one.isDisplayed()) {
					sliderOneflag = true;
				}
				if (slider_two.isDisplayed()) {
					sliderTwoflag = true;
				}
				if (slider_three.isDisplayed()) {
					sliderThreeflag = true;
				}
			}
			Assert.assertTrue((sliderOneflag && sliderTwoflag && sliderThreeflag), "Slider is missing");
			flag = true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
