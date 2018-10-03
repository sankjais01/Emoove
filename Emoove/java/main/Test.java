package main;

import org.openqa.selenium.WebDriver;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("os.name"));
		WebDriver driver=BrowserFactory.createDriver("chrome");
		driver.get("https://www.google.co.in/");

	}

}
