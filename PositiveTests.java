package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {
	
	@Test
	public void loginTest() {
		System.out.println("Start login test");
		
		//create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//maximize browser window
		driver.manage().window().maximize();
		
		//open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opened. ");
		
		//enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		
		//enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		
		//click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		
		
		//verification:
			//new url
			String expectedUrl = "http://the-internet.herokuapp.com/secure";
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(actualUrl, expectedUrl, "Actual page is not the same as expected");
			
			//logout button is visible
			WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));;
			Assert.assertTrue(logOutButton.isDisplayed(),"logout button not visible");
						
			//successful login message
			WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
			String expectedMessage = "You logged into a secure area!";
			String actualMessage = successMessage.getText();
			//Assert.assertEquals(actualMessage, expectedMessage, "actual message is not the same as expected");
			Assert.assertTrue(actualMessage.contains(expectedMessage),"actual does not contain expected\n"+actualMessage+expectedMessage);
			
			
			
			//close browser
		driver.quit();
	}

}
