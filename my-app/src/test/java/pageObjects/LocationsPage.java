package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.Parameters;

public class LocationsPage {
	private static WebDriver driver;
	WebDriverWait wait;
	
	public LocationsPage(WebDriver driverToAssign)
	{
		driver = driverToAssign;
		
	}
	 
    public LocationsPage WaitForTitle() {
    	wait= new WebDriverWait (driver,Parameters.ExplicitTimeWait);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ngLabel.ng-binding")));
        return this;
	}

     public NewLocationPage PressAddButton(){
 
         driver.findElement(By.xpath("//div[4]/div/button")).click();
          return new NewLocationPage(driver);
 
      }
     public LocationsPage refreshPage()
     {
    	 wait= new WebDriverWait (driver,20);
    	 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Issues")));
    	 driver.findElement(By.linkText("Issues")).click();
    	 driver.findElement(By.linkText("Locations")).click();
    	 wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ngLabel.ng-binding")));
    	 return this;
     }
     public boolean verifyNewLocationPresent(String locationName) {
    	    try {
    	      driver.findElement(By.xpath("//div[contains(text(),'" + locationName + "')]"));
    	      return true;
    	    } catch (NoSuchElementException e) {
    	      return false;
    	    }
    	  }

	public void deleteLocation(String locationName) {
		 driver.findElement(By.xpath("//div[contains(text(),'" + locationName + "')]/parent::div/parent::div/preceding-sibling::div//input")).click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementsByClassName('btn btn-default btn-sm')[1].removeAttribute('disabled')");
			driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm'][2]")).click();
			driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		
	}
     

}
