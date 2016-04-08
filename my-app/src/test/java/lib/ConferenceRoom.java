package lib;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConferenceRoom {
 private WebDriver Driver;
 private String Menu = "Conference Rooms";
	 
	 public ConferenceRoom(WebDriver driver)
	 {
		 Driver = driver;
	 }
	 public ConferenceRoom ClickOnConferenceRoomMenu()
	 {
		 Driver.findElement(By.linkText(Menu)).click();
		 return this;
	 }
	 
	 public ConferenceRoom WaitRooms(int seconds)
	 {
		 WebDriverWait wait = new WebDriverWait (Driver,seconds);
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ngHeaderText.ng-binding.colt0")));
		    return this;
	 }
	 public ConferenceRoom ClickOnRoom(String roomName)
	 {
		 WebElement roomElement = Driver.findElement(By.xpath("//span[contains(text()," + roomName + "')][2]"));
			roomElement.click();
			doubleclick(Driver, roomElement);
		    return this;
	 }
	 public ConferenceRoom SelectOutOfOrder()
	 {
		 Driver.findElement(By.linkText("Out of Order Planning")).click();
		 return this;
	 }
	 public ConferenceRoom FillDataOutOfOrder(boolean meeting)
	 {
		Driver.findElement(By.xpath("//textarea")).clear();
	    Driver.findElement(By.xpath("//textarea")).sendKeys("Out-of-order");
	    if (meeting){
	    	Driver.findElement(By.cssSelector(".fa.fa-calendar")).click();
		    Driver.findElement(By.cssSelector("span.checkbox-label")).click();
	    }
	    return this;
	    
	 }
	 public ConferenceRoom PressSaveButton()
	 {
		 Driver.findElement(By.cssSelector("button.info")).click();
		 return this;
	 }
	 public ConferenceRoom RefreshPage()
	 {
		Driver.navigate().refresh();
		WaitRooms(10);
	    Driver.findElement(By.linkText("Issues")).click();
	    ClickOnConferenceRoomMenu();
		return this;
	 }
	 public String VerifyMessageOutOfOrder(String roomName)
	 {
		 
		 WebElement cellElement = Driver.findElement(By.xpath("//span[contains(text(),'" + roomName + "')][2]/parent::div/parent::div/parent::div/preceding-sibling::div[1]//span"));
		    String message = cellElement.getAttribute("popover");
		    return (message);
		    
	 }
	 private void doubleclick(WebDriver driver, WebElement element) {
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("var evt = document.createEvent('MouseEvents');" +
	 		        "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" +
	 		        "arguments[0].dispatchEvent(evt);", element);
	     
	    
	  }
}
