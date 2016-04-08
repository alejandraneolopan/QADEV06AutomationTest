package com.mycompany.app;
import utils.Generator;
import config.Connection;

import org.testng.Assert;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AddLocationTest {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();
  Generator rand = new Generator();
  
  @BeforeSuite
  public void setUp() throws Exception {
	  driver = new Connection().init();
  }

  @Test(groups={"unit"})
  public void testAddLocation() throws Exception {
	//String locationName = rand.getRandString();
	String locationName = "Location X";
    driver.findElement(By.linkText("Locations")).click();
    WebDriverWait wait = new WebDriverWait (driver,10);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ngLabel.ng-binding")));
    
    driver.findElement(By.xpath("//div[4]/div/button")).click();
    driver.findElement(By.id("location-add-name")).clear();
    driver.findElement(By.id("location-add-name")).sendKeys(locationName);
    driver.findElement(By.id("location-add-display-name")).clear();
    driver.findElement(By.id("location-add-display-name")).sendKeys(locationName);
    driver.findElement(By.id("location-add-description")).clear();
    driver.findElement(By.id("location-add-description")).sendKeys(locationName);
    driver.findElement(By.linkText("Location Associations")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".clickeable>span")));
    
    driver.findElement(By.xpath("//div[2]/button")).click();
    driver.findElement(By.cssSelector(".btn.btn-primary")).click();
    /*Verification*/
 
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".clickeable.ng-binding")));
    driver.findElement(By.linkText("Issues")).click();
    driver.findElement(By.linkText("Locations")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ngLabel.ng-binding")));
    Assert.assertTrue(isElementPresent(By.xpath("//div[contains(text(),'" + locationName + "')]")));
    
    /*driver.findElement(By.xpath("//div[contains(text(),'" + locationName + "')]/parent::div/parent::div/preceding-sibling::div//input")).click();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("document.getElementsByClassName('btn btn-default btn-sm')[1].removeAttribute('disabled')");
	driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm'][2]")).click();
	driver.findElement(By.cssSelector(".btn.btn-primary")).click();*/
   
  }
  

  @AfterSuite
  public void tearDown() throws Exception {
	
	
	driver.findElement(By.cssSelector("a > span")).click();
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
     // fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
  
}

