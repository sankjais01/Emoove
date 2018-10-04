package pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import locators.DashboardLocators;

public class DashboardPage {

	WebDriver driver;
	boolean flag = false;
	SoftAssert asert = new SoftAssert();
	int countRecord;
	// WebDriverWait wait = new WebDriverWait(driver, 5000);

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = DashboardLocators.TOPMENU_XPATH)
	WebElement MENUBAR;

	@FindBy(xpath = DashboardLocators.TOPMENU_PROPETIES_XPATH)
	WebElement MENU_PROPERTY;

	@FindBy(xpath = DashboardLocators.TOPMENU_VIEWINGS_XPATH)
	WebElement MENU_VIEWINGS;

	@FindBy(xpath = DashboardLocators.TOPMENU_OFFERS_XPATH)
	WebElement MENU_OFFERS;

	@FindBy(xpath = DashboardLocators.TOPMENU_PACKAGES_ADDONS_XPATH)
	WebElement MENU_PACKAGES;

	@FindBy(xpath = DashboardLocators.TOPMENU_REFERFREIEND_XPATH)
	WebElement MENU_REFERFRIEND;

	@FindBy(xpath = DashboardLocators.BUYER_AUTOMATIONTEST_XPATH)
	WebElement MENU_BUYER_AUTOMATIONTEST;

	@FindBy(xpath = DashboardLocators.LOGOUT_XPATH)
	WebElement MENU_LOGOUT;

	@FindBy(xpath = DashboardLocators.MANAGEVIEWINGS_XPATH)
	WebElement MENU_MANAGEVIEWINGS;

	@FindBy(xpath = DashboardLocators.LISTVIEW_BUTTON_XPATH)
	WebElement BUTTON_LISTVIEW;

	@FindBy(xpath = DashboardLocators.GRIDVIEW_BUTTON_XPATH)
	WebElement BUTTON_GRIDVIEW;

	@FindBy(xpath = DashboardLocators.VIEW_SELECTOR_ARRORW_XPATH)
	WebElement ARROW;

	@FindBy(xpath = DashboardLocators.VIEW_SELECTOR_AS_ALL_XPATH)
	WebElement SELECT_ALL;

	@FindBy(xpath = DashboardLocators.RECORD_SIZE_5)
	WebElement RECORD5;

	@FindBy(xpath = DashboardLocators.RECORD_SIZE_10)
	WebElement RECORD10;

	@FindBy(xpath = DashboardLocators.RECORD_SIZE_20)
	WebElement RECORD20;

	@FindBy(xpath = DashboardLocators.RECORD_VIEWBUTTONS)
	List<WebElement> list;
	// @FindBy(xpath = DashboardLocators.VIEW_SELECTOR_XPATH)
	// Select VIEW_SELECTOR;

	public boolean testRecordSize()

	{
		try {
			MENU_VIEWINGS.click();
			MENU_MANAGEVIEWINGS.click();
			ARROW.click();
			SELECT_ALL.click();
			Thread.sleep(8000);
			BUTTON_LISTVIEW.click();
			Thread.sleep(3000);
			countRecord=0;

			System.out.println("Test for 5 records");
			RECORD5.click();
			Thread.sleep(10000);
			for (WebElement webElement : list) {
				countRecord++;
			}
			if (countRecord == 5) {
				flag = true;
				System.out.println("Records are matching");
				asert.assertTrue(flag, "There are only" + countRecord + "Records");

			} else {
				flag = false;
				asert.assertFalse(flag, "There are only" + countRecord + "Records");
				System.out.println("Records are not matching");

			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	public void testDateOrder() {

		try {
			MENU_VIEWINGS.click();
			MENU_MANAGEVIEWINGS.click();
			ARROW.click();
			Thread.sleep(500);
			SELECT_ALL.click();
			Thread.sleep(7000);
			BUTTON_LISTVIEW.click();
			Thread.sleep(2000);
			RECORD5.click();

			Thread.sleep(1000);
			/*
			 * List<WebElement> selectorList = VIEW_SELECTOR.getOptions(); for (WebElement
			 * webElement : selectorList) { System.out.println(webElement.getText()); }
			 */

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}

	}

}