package com.imdb.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imdb.qa.base.Test_Base;
import com.imdb.qa.pages.Home_Page;
import com.imdb.qa.pages.Pushpa_Movie_Page;

public class PushpaMoviePageTest extends Test_Base{

	Home_Page homepage;
	Pushpa_Movie_Page pushpamoviePage;
	
	public PushpaMoviePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		initialization();
		homepage = new Home_Page();
		pushpamoviePage=homepage.movieSearch(prop.getProperty("searchText"));
	}
	
	@Test
	public void verifyMovieRelieseDateTest()
	{
		boolean date=pushpamoviePage.verifyMovieRelieseDate();
		Assert.assertTrue(date);
	}
	
	@Test
	public void verifyMovieCountryTest()
	{
		boolean country=pushpamoviePage.VerifyMoviesCountry();
		Assert.assertTrue(country);
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
