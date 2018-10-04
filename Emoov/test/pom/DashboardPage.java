package pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import locators.DashboardLocators;
import main.StringToDate;

public class DashboardPage {

	WebDriver driver;
	boolean flag = false;
	SoftAssert asert = new SoftAssert();
	int countRecord = 0;
	Long firstdate;
	Long dates[];
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
	List<WebElement> VIEWBUTTONS_LIST;
	
	@FindBy(xpath = DashboardLocators.DDDMMYYYY_XPATH)
	List<WebElement> DDMMYYYY_LIST;

	@FindBy(xpath=DashboardLocators.DATECOLUMN_XPATH)
	WebElement DATECOL;	
	
	
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
			countRecord = 0;

			System.out.println("Testing for 5 records");
			RECORD5.click();
			Thread.sleep(10000);
			for (WebElement webElement : VIEWBUTTONS_LIST) {
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

			countRecord = 0;
			System.out.println("Testing for 10 records");
			RECORD10.click();
			Thread.sleep(10000);
			for (WebElement webElement : VIEWBUTTONS_LIST) {
				countRecord++;
			}
			if (countRecord == 10) {
				flag = true;
				System.out.println("Records are matching");
				asert.assertTrue(flag, "There are only" + countRecord + "Records");

			} else {
				flag = false;
				asert.assertFalse(flag, "There are only" + countRecord + "Records");
				System.out.println("Records are not matching");

			}

			System.out.println("Testing for 20 records");
			RECORD20.click();
			Thread.sleep(10000);
			for (WebElement webElement : VIEWBUTTONS_LIST) {
				countRecord++;
			}
			if (countRecord == 20) {
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

	public void testDateOrder(String order) {

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
			Thread.sleep(10000);
			for (WebElement webElement : VIEWBUTTONS_LIST) {
				countRecord++;
			}
			if (countRecord > 1) {
				flag = true;
				System.out.println("Testing order");
				int i = 0;
				dates = new Long[countRecord];

				if (order.equalsIgnoreCase("Desc")) {
					for (WebElement webElement : DDMMYYYY_LIST) {

						dates[i] = StringToDate.dateToLong(webElement.getText());
						// StringToDate.dateToLong(DDMMYYYY_LIST[j-1].getText())
						System.out.println(dates[i]);
						i++;

					}

					for (int j = 1; j < countRecord; j++) {
						testDescending(dates[j - 1], dates[j]);

					}
				}

				else if (order.equalsIgnoreCase("Asc")) {

					
					DATECOL.click();
					Thread.sleep(5000);
					
					for (WebElement webElement : DDMMYYYY_LIST) {

						dates[i] = StringToDate.dateToLong(webElement.getText());
						// StringToDate.dateToLong(DDMMYYYY_LIST[j-1].getText())
						System.out.println(dates[i]);
						i++;

					}

					for (int j = 1; j < countRecord; j++) {
						testAscending(dates[j - 1], dates[j]);

					}

				}

				
			} else {
				System.out.println("There are no records to compare");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			Assert.assertTrue(false, "Date Order test failed.");
		}

	}

	public void testAscending(Long a, Long b) {

		Assert.assertTrue(a <= b, "Dates are not in ascending order");

	}

	public void testDescending(Long a, Long b) {
		Assert.assertTrue(a >= b, "Dates are not in descending order");
	}

}