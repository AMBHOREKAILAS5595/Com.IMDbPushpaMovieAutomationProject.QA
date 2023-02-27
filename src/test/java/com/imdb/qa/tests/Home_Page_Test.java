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
