package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private static WebDriver driver;
	private static WebElement element = null;
	
	public HomePage(WebDriver driverToAssign)
	{
		driver = driverToAssign;
		
	}
	public static WebElement lnk_MyAccount(WebDriver driver){
	
		element = driver.findElement(By.id("account"));
		
		return element;
	
	}
	
	public static WebElement lnk_LogOut(WebDriver driver){
	
		element = driver.findElement(By.id("account_logout"));
		
		return element;
	
	}
	public HomePage WaitTitle(int seconds)
	 {
		 WebDriverWait wait = new WebDriverWait (driver,seconds);
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Conference Rooms")));
		    return this;
	 }
	
	public LocationsPage clickOnLocations() {
		driver.findElement(By.linkText("Locations")).click();
		return new LocationsPage(driver);
		
	}
	public ConferenceRoomPage clickOnRooms() {
		driver.findElement(By.linkText("Conference Rooms")).click();
		return new ConferenceRoomPage(driver);
		
	}

}