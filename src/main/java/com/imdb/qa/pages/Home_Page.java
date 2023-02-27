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
