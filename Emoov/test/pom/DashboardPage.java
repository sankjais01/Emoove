package pom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import locators.DashboardLocators;
import locators.URLLocators;
import main.StringToDate;

public class DashboardPage {

	WebDriver driver;
	boolean recortest_flag = false;
	boolean asc_order_flag = false;
	boolean desc_order_flag = false;

	SoftAssert asert = new SoftAssert();
	int countRecord = 0;
	Long firstdate;
	Long dates[];
	// WebDriverWait wait = new WebDriverWait(driver, 10000);

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = DashboardLocators.TOPMENU_XPATH)
	WebElement menubar;

	@FindBy(xpath = DashboardLocators.TOPMENU_PROPETIES_XPATH)
	WebElement menu_property;

	@FindBy(xpath = DashboardLocators.TOPMENU_VIEWINGS_XPATH)
	WebElement menu_viewings;

	@FindBy(xpath = DashboardLocators.TOPMENU_OFFERS_XPATH)
	WebElement menu_offers;

	@FindBy(xpath = DashboardLocators.TOPMENU_PACKAGES_ADDONS_XPATH)
	WebElement menu_packages;

	@FindBy(xpath = DashboardLocators.TOPMENU_REFERFREIEND_XPATH)
	WebElement menu_referfriend;

	@FindBy(xpath = DashboardLocators.BUYER_AUTOMATIONTEST_XPATH)
	WebElement menu_buyer_automationtest;

	@FindBy(xpath = DashboardLocators.LOGOUT_XPATH)
	WebElement menu_logout;

	@FindBy(xpath = DashboardLocators.MANAGEVIEWINGS_XPATH)
	WebElement menu_manageViewings;

	@FindBy(xpath = DashboardLocators.LISTVIEW_BUTTON_XPATH)
	WebElement button_listviews;

	@FindBy(xpath = DashboardLocators.GRIDVIEW_BUTTON_XPATH)
	WebElement button_gridview;

	@FindBy(xpath = DashboardLocators.VIEW_SELECTOR_ARRORW_XPATH)
	WebElement arrow;

	@FindBy(xpath = DashboardLocators.VIEW_SELECTOR_AS_ALL_XPATH)
	WebElement select_all;

	@FindBy(xpath = DashboardLocators.RECORD_SIZE_5)
	WebElement record_5;

	@FindBy(xpath = DashboardLocators.RECORD_SIZE_10)
	WebElement record_10;

	@FindBy(xpath = DashboardLocators.RECORD_SIZE_20)
	WebElement record_20;

	@FindBy(xpath = DashboardLocators.RECORD_VIEWBUTTONS)
	List<WebElement> viewbuttons_list;

	@FindBy(xpath = DashboardLocators.DDDMMYYYY_XPATH)
	List<WebElement> ddmmyyyy_list;

	@FindBy(xpath = DashboardLocators.DATECOLUMN_XPATH)
	WebElement date_cols;

	public boolean testRecordSize()

	{
		try {
			// driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
			// wait.until(ExpectedConditions.visibilityOfAllElements(MENU_VIEWINGS));
			driver.navigate().refresh();
			Thread.sleep(5000);
			menu_viewings.click();
			menu_manageViewings.click();
			arrow.click();
			select_all.click();
			Thread.sleep(8000);
			button_listviews.click();
			Thread.sleep(3000);
			countRecord = 0;
			// pre condition- records should be more than 20 in database

			// **************************************************
			System.out.println("Testing for 5 records");
			record_5.click();
			Thread.sleep(10000);
			for (WebElement webElement : viewbuttons_list) {
				countRecord++;
			}
			if (countRecord == 5) {
				recortest_flag = true;
				System.out.println("Records are matching");
				asert.assertTrue(recortest_flag, "There are only" + countRecord + "Records");

			} else {
				recortest_flag = false;
				asert.assertFalse(recortest_flag, "There are only" + countRecord + "Records");
				System.out.println("Records are not matching");

			}
			// ********************************************************
			countRecord = 0;
			System.out.println("Testing for 10 records");
			record_10.click();
			Thread.sleep(10000);
			for (WebElement webElement : viewbuttons_list) {
				countRecord++;
			}
			if (countRecord == 10) {
				recortest_flag = true;
				System.out.println("Records are matching");
				asert.assertTrue(recortest_flag, "There are only" + countRecord + "Records");

			} else {
				recortest_flag = false;
				asert.assertFalse(recortest_flag, "There are only" + countRecord + "Records");
				System.out.println("Records are not matching");

			}

			// ******************************************************
			System.out.println("Testing for 20 records");
			record_20.click();
			Thread.sleep(12000);
			for (WebElement webElement : viewbuttons_list) {
				countRecord++;
			}
			if (countRecord == 20) {
				recortest_flag = true;
				System.out.println("Records are matching");
				asert.assertTrue(recortest_flag, "There are only" + countRecord + "Records");

			} else {
				recortest_flag = false;
				asert.assertFalse(recortest_flag, "There are only" + countRecord + "Records");
				System.out.println("Records are not matching");

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			asert.assertTrue(false, "Failed to test Record size due to Exception occurs");
		}

		return recortest_flag;
	}

	public boolean testDateDescOrder() {

		try {
			countRecord = 0;
			driver.navigate().refresh();
			Thread.sleep(5000);
			menu_viewings.click();
			menu_manageViewings.click();
			arrow.click();
			Thread.sleep(500);
			select_all.click();
			Thread.sleep(7000);
			button_listviews.click();
			Thread.sleep(2000);
			record_5.click();
			Thread.sleep(10000);
			for (WebElement webElement : viewbuttons_list) {
				countRecord++;
			}

			if (countRecord > 1) {
				desc_order_flag = true;
				System.out.println("Testing Desc order");
				int i = 0;
				dates = new Long[countRecord];
				for (WebElement webElement : ddmmyyyy_list) {
					dates[i] = StringToDate.dateToLong(webElement.getText());
					// StringToDate.dateToLong(DDMMYYYY_LIST[j-1].getText())
					// System.out.println(dates[i]);
					i++;

				}

				for (int j = 1; j < countRecord; j++) {
					desc_order_flag = testDescending(dates[j - 1], dates[j]);

				}

			} else {
				System.out.println("There are no records to compare");
				return desc_order_flag;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			Assert.assertTrue(false, "Date Desc Order test failed due to Exception.");
		}

		return desc_order_flag;
	}

	public boolean testDateAscOrder() {

		try {
			countRecord = 0;
			driver.navigate().refresh();
			Thread.sleep(5000);
			menu_viewings.click();
			menu_manageViewings.click();
			arrow.click();
			Thread.sleep(500);
			select_all.click();
			Thread.sleep(7000);
			button_listviews.click();
			Thread.sleep(2000);
			record_5.click();
			Thread.sleep(10000);
			for (WebElement webElement : viewbuttons_list) {
				countRecord++;
			}

			if (countRecord > 1) {
				recortest_flag = true;
				System.out.println("Testing Ascending order");
				int i = 0;
				dates = new Long[countRecord];

				date_cols.click();
				Thread.sleep(10000);

				for (WebElement webElement : ddmmyyyy_list) {
					dates[i] = StringToDate.dateToLong(webElement.getText());
					// StringToDate.dateToLong(DDMMYYYY_LIST[j-1].getText())
					// System.out.println(dates[i]);
					i++;

				}

				for (int j = 1; j < countRecord; j++) {
					asc_order_flag = testAscending(dates[j - 1], dates[j]);

				}

			} else {
				System.out.println("There are no records to compare");
				asc_order_flag = false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			Assert.assertTrue(false, "Date Asc Order test failed due to exception.");
		}
		return asc_order_flag;

	}

	public boolean testAscending(Long a, Long b) {

		if (a <= b) {
			asert.assertTrue(true, "Dates are not in Ascending order");
			return true;
		} else {
			asert.assertTrue(false, "Dates are not in Ascending order");
			return false;
		}

	}

	public boolean testDescending(Long a, Long b) {
		if (a >= b) {
			asert.assertTrue(true, "Dates are not in descending order");
			return true;
		} else {
			asert.assertTrue(false, "Dates are not in descending order");
			return false;
		}

	}

	public boolean logout() throws InterruptedException {

		menu_buyer_automationtest.click();
		Thread.sleep(500);
		menu_logout.click();
		if (driver.getCurrentUrl().equalsIgnoreCase(URLLocators.LOGINPAGE_URL)) {
			return true;
		} else {
			return false;
		}
	}

}