package com.imdb.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.imdb.qa.util.Test_Util;

public class Test_Base {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public Test_Base() {
		
		try {
			
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\ABC\\Downloads\\.metadata\\java\\src\\IMDBPushpaMovieAutomationTest\\src\\main\\java\\com\\imdb\\qa\\config\\config.properties");
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() 
	{
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C://Users//ABC//Downloads//.metadata//java//src//IMDBPushpaMovieAutomationTest//Browsers//chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browserName.equals("FireFox"))
		{
			System.setProperty("webdriver.gecko.driver","C://Users//ABC//Downloads//.metadata//java//src//IMDBPushpaMovieAutomationTest//Browsers//geckodriver.exe");
			driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Test_Util.PAGE_LOAD_TIMEOUT,TimeUnit.MILLISECONDS);
		driver.manage().timeouts().implicitlyWait(Test_Util.IMPLICIT_WAIT,TimeUnit.MILLISECONDS);
		driver.get(prop.getProperty("url"));
		
	}
}
