package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	public LocationsPage clickOnLocations() {
		driver.findElement(By.linkText("Locations")).click();
		return new LocationsPage(driver);
		
	}
	public ConferenceRoomPage clickOnRooms() {
		driver.findElement(By.linkText("Conference Rooms")).click();
		return new ConferenceRoomPage(driver);
		
	}

}