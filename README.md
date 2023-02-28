# Com.IMDbPushpaMovieAutomationProject.QA

## This is IMDB Movie Automation Project
- In this project I have used Java as Programming language with selenium webdriver for automating the IMDb Movie Site.
- In this project I have using Maven as Build Management Tool.
- In this Project I have used Page Object Module using Page Factory.
- As Per Page Object Module We have mention a class of every Webpage. Each Webpage has seperate class and class Holds functionality.

- In Maven we are using four Folders Like :-
1) src/main/java :- We are Writing all POM classes here.
                    
   Example of POM using Page Factory:-
   
  package com.imdb.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.imdb.qa.base.Test_Base;

public class Home_Page extends Test_Base {
	
	// Page Factory - Object Repository
	
	@FindBy(xpath="//*[@id=\"suggestion-search\"]")
	WebElement searchBox;
	
	@FindBy(xpath="//*[@id=\"iconContext-magnify\"]")
	WebElement searchButton;
	
	@FindBy(xpath="//*[@id=\"home_img\"]")
	WebElement imdbLogo;
	
	@FindBy(xpath="//*[@id=\"__next\"]/main/div[2]/div[3]/section/div/div[1]/section[2]/div[2]/ul/li[1]/div[2]")
	WebElement PushpaMovieDropDownBtn;
	
	// Initializing the Page Objects
	
	public Home_Page() {
		PageFactory.initElements(driver, this);
	}
	
	
	public String validateHomePageTitle()
	{
		return driver.getTitle();
	}

	public boolean imdbLogo()
	{
		return imdbLogo.isDisplayed();
	}
	
	public Pushpa_Movie_Page movieSearch(String movieName)
	{
		searchBox.sendKeys(movieName);
		searchButton.click();
		PushpaMovieDropDownBtn.click();
		return new Pushpa_Movie_Page();
	}	
}

                    
2) src/test/java :- We are writing Test classes here.

Example of Test Class Using TestNG Annotations:-

package com.imdb.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imdb.qa.base.Test_Base;
import com.imdb.qa.pages.Home_Page;
import com.imdb.qa.pages.Pushpa_Movie_Page;

public class Home_Page_Test extends Test_Base {
	Home_Page homepage;
	Pushpa_Movie_Page pushpaMoviePage;
	
	public Home_Page_Test() {
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		initialization();
		homepage = new Home_Page();
	}
	
	@Test(priority=1)
	public void homePageTitleTest()
	{
		String title=homepage.validateHomePageTitle();
		Assert.assertEquals(title, "IMDb: Ratings, Reviews, and Where to Watch the Best Movies &amp; TV Shows");
	}
	
	@Test(priority=2)
	public void imdbLogoTest()
	{
		boolean img=homepage.imdbLogo();
		Assert.assertTrue(img);
	}
	
	@Test(priority=3)
	public void movieSerchTest()
	{
		pushpaMoviePage=homepage.movieSearch(prop.getProperty("searchText"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}

3) Maven Dependency:- It includes all the jar files needed to execute test script.
4) pom.xml File:- It includes all the dependencies needed.

Example of pom.xml file:-

 <dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.8.1</version>
  </dependency>
    
    <!-- https://mvnrepository.com/artifact/org.testng/testng -->
  <dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.7.1</version>
    <scope>compile</scope>
  </dependency>
  
  <!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.2.3</version>
</dependency>

- In this project I have created different generic functions and folders like config.properties file, Browsers Folder, Utils class, and Base Class.

- Base Class       :- Base class which is extended to POM classes and Test Classes.
                      It includes code structure related to open browser.
                      
 Example of Base Class:-
 
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

               
- Utility Class    :- Used to keep common methods and reusable code. We are using Utility class for maintaining excel file data and staring Screenshots related code.

- Properties File  :- This file includes all the data like URL Browser type and Login Credentials in Key and Value Pairs (Keys are unique but values are dupicates).

In this Project I am using TestNG Unit Test Fremwork for generating test Scripts by using differnt annotations like @BeforeClass, @BeforeMethod, @Test, @AfterMethod, @AfterClass. 
Also by using TestNG we can run multiple test Scripts Parallely, Grouping, priority, Exclude/Include, depends on, etc.
                
