package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import locators.HomePageLocators;
import locators.LoginPageLocators;
import locators.URLLocators;
import sanity.LoginPageTest;

public class LoginPage {

	WebDriver driver;
	boolean flag = false;
	SoftAssert asert = new SoftAssert();
	// WebDriverWait wait = new WebDriverWait(driver, 5000);

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		driver.get(URLLocators.HOMEPAGE_URL);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = LoginPageLocators.USERNAME_XPATH)
	WebElement USERNAME_TEXTFIELD;

	@FindBy(xpath = LoginPageLocators.PASSWORD_XPATH)
	WebElement PASSWORD_TEXTFIELD;

	@FindBy(xpath = LoginPageLocators.FORGOTPASSWORD_LINK_XPATH)
	WebElement FORGOTPASSWORD_TEXTFIELD;

	@FindBy(xpath = LoginPageLocators.LOGIN_BUTTON_XPATH)
	WebElement LOGINBUTTON;

	@FindBy(xpath = LoginPageLocators.SIGNUP_XPATH)
	WebElement SIGNUP_LINK;

	public boolean ValidUserLogin() {

		try {
			USERNAME_TEXTFIELD.sendKeys(LoginPageTest.data.USERNAME);
			PASSWORD_TEXTFIELD.sendKeys(LoginPageTest.data.PASSWORD);
			LOGINBUTTON.click();
			Thread.sleep(5000);
			if (driver.findElement(By.xpath(HomePageLocators.TOPMENU_XPATH)).isDisplayed()) {
				flag = true;
				asert.assertTrue(true);

			} else {
				flag = false;
				asert.assertTrue(false);
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("element not found on page");
			System.err.println(e);
		}

		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Thread time out");
			System.err.println(e);
		}

		return flag;

	}

}