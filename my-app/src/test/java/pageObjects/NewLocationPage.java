package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.Parameters;

public class NewLocationPage {
	private static WebDriver driver;
	private static WebElement element = null;
	
	public NewLocationPage(WebDriver driverToAssign)
	{
		driver = driverToAssign;
	}
	 
	public static WebElement inputUserName(){
 
         element = driver.findElement(By.id("loginUsername"));
 
         return element;
 
     }
	public NewLocationPage typeLocationName(String description)
	{
		driver.findElement(By.id("location-add-name")).clear();
		driver.findElement(By.id("location-add-name")).sendKeys(description);
		return this;
	}
	public NewLocationPage typeDisplayName(String description)
	{
	    driver.findElement(By.id("location-add-display-name")).clear();
	    driver.findElement(By.id("location-add-display-name")).sendKeys(description);
		return this;
	}
	public NewLocationPage typeDescription(String description)
	{
		driver.findElement(By.id("location-add-description")).clear();
	    driver.findElement(By.id("location-add-description")).sendKeys(description);
	    return this;
	}
	public NewLocationPage clickOnLocationAssociations()
	{
		driver.findElement(By.linkText("Location Associations")).click();
		return this;
	}
	
	public NewLocationPage waitForRooms()
	{
		WebDriverWait wait = new WebDriverWait (driver,Parameters.ExplicitTimeWait);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".clickeable>span")));
		return this;
	}
	public NewLocationPage selectOneRoom()
	{
	    driver.findElement(By.xpath("//div[2]/button")).click();
	    return this;
	}
	
	public LocationsPage pressSaveButton()
	{
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
	    return new LocationsPage(driver);
	}
	 
}
