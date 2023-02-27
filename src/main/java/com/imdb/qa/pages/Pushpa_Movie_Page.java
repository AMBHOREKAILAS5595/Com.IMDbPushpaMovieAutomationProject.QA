package com.imdb.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.imdb.qa.base.Test_Base;

public class Pushpa_Movie_Page extends Test_Base {
	
	@FindBy(xpath="//*[@id=\"__next\"]/main/div/section[1]/div/section/div/div[1]/section[10]/div[2]/ul/li[1]/div/ul/li/a")
	WebElement movieReliesDate;
	
	@FindBy(xpath="//*[@id=\"__next\"]/main/div/section[1]/div/section/div/div[1]/section[10]/div[2]/ul/li[2]/div/ul/li/a")
	WebElement country;
	
	

	public Pushpa_Movie_Page()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyMovieRelieseDate()
	{
		return movieReliesDate.isDisplayed();
	}
	
	public boolean VerifyMoviesCountry()
	{
		return country.isDisplayed();
	}
}
