package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import locators.DashboardLocators;
import locators.LoginPageLocators;
import locators.URLLocators;
import main.TestData;

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
	WebElement username_textfield;

	@FindBy(xpath = LoginPageLocators.PASSWORD_XPATH)
	WebElement password_textfield;

	@FindBy(xpath = LoginPageLocators.FORGOTPASSWORD_LINK_XPATH)
	WebElement forgotpassword_link;

	@FindBy(xpath = LoginPageLocators.LOGIN_BUTTON_XPATH)
	WebElement login_button;

	@FindBy(xpath = LoginPageLocators.SIGNUP_XPATH)
	WebElement signUp_link;

	public boolean ValidUserLogin(TestData data) {

		try {
			username_textfield.sendKeys(data.USERNAME);
			password_textfield.sendKeys(data.PASSWORD);
			login_button.click();
			Thread.sleep(5000);
			if (driver.findElement(By.xpath(DashboardLocators.TOPMENU_XPATH)).isDisplayed()) {
				flag = true;
				asert.assertTrue(true,"User failed Login");
				

			} else {
				flag = false;
				asert.assertTrue(false,"User Failed to Login");
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
