package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

	static WebDriver driver=null;

	public static WebDriver createDriver(String type) {
		if (type.contains("chrome") || type.contains("CHROME")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//mylib//chrome//chromedriver.exe");
			driver = new ChromeDriver();
		} else if (type.contains("firefox") || type.contains("FIREFOX")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "//mylib//gecko//geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if (type.contains("IE") || type.contains("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "//mylib//ie//IEDriverServer_x64_3.14.0//IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else
		{
			System.out.println("we have only IE, Chrome, FireFox browser");
		}
		
		driver.manage().window().maximize();
		return driver;

	}

}
