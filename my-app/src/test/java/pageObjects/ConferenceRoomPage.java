package pageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Generator;
import utils.Utils;

public class ConferenceRoomPage {
 private WebDriver Driver;
 private String Menu = "Conference Rooms";
 private Utils utils;
	 
	 public ConferenceRoomPage(WebDriver driver)
	 {
		 Driver = driver;
		 utils = new Utils();
	 }
	 public ConferenceRoomPage ClickOnConferenceRoomMenu()
	 {
		 Driver.findElement(By.linkText(Menu)).click();
		 return this;
	 }
	 
	 public ConferenceRoomPage WaitRooms(int seconds)
	 {
		 WebDriverWait wait = new WebDriverWait (Driver,seconds);
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ngHeaderText.ng-binding.colt0")));
		    return this;
	 }
	 public ConferenceRoomPage ClickOnRoom(String roomName)
	 {
		 WebElement roomElement = Driver.findElement(By.xpath("//span[contains(text(),'" + roomName + "')][2]"));
			roomElement.click();
			doubleclick(Driver, roomElement);
		    return this;
	 }
	 public ConferenceRoomPage SelectOutOfOrder()
	 {
		 Driver.findElement(By.linkText("Out of Order Planning")).click();
		 return this;
	 }
	 public ConferenceRoomPage FillDataOutOfOrder(boolean meeting)
	 {
		 //Set schedule 2 minute
		 
		 List<WebElement> elementsDown = Driver.findElements(By.cssSelector(".glyphicon.glyphicon-chevron-down"));
		 for (byte i = 1; i <= 27; i++)
			 elementsDown.get(3).click();
		 
		 Driver.findElement(By.xpath("//textarea")).clear();
	     Driver.findElement(By.xpath("//textarea")).sendKeys("Out-of-order");
	    if (meeting){
	    	Driver.findElement(By.cssSelector(".fa.fa-calendar")).click();
		    Driver.findElement(By.cssSelector("span.checkbox-label")).click();
	    }
	    return this;
	    
	 }
	 public ConferenceRoomPage PressSaveButton()
	 {
		 Driver.findElement(By.cssSelector("button.info")).click();
		 return this;
	 }
	 public ConferenceRoomPage RefreshPage()
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
	
	 public ConferenceRoomPage SelectResources() {
		Driver.findElement(By.linkText("Resource Associations")).click();
		return this;
	}
	
	public ConferenceRoomPage AddResource(String resourceName) {
		String resourceNameObtained;
		int resourcesCount = 0,resourceNum = 0;
		resourcesCount = Driver.findElements(By.xpath("//div[@class='list-group']/div/div[@class='col-xs-8']")).size();
		for (int i = 1; i <= resourcesCount; i++)
		{
			resourceNameObtained = Driver.findElement(By.xpath("//div[@class='list-group']/div[" + i + "]/div[2]/span")).getText();
			if (resourceNameObtained.equals(resourceName))
			{
				resourceNum = i;
			}
		}
		Driver.findElement(By.xpath("//div[@class='list-group'][1]/div[" + resourceNum + "]/div[3]/button")).click();
		return this;
	}
	public ConferenceRoomPage typeQuantity(String amount) {
		Driver.findElement(By.xpath("//div[@class='list-group']/div[last()]/div[3]/input")).clear();
	    Driver.findElement(By.xpath("//div[@class='list-group']/div[last()]/div[3]/input")).sendKeys(amount);
		return this;
	}
	public String verifyResourceMessage(String resourceName)
	{
		 int resourceColumn = GetColumn(10, resourceName);
		    if (resourceColumn < 0)
		    {
		    	ClickOnFilter(resourceName);
		    	resourceColumn = GetColumn(10, resourceName);
		    }
		return Driver.findElement(By.xpath("//*[@id='roomsGrid']/div[2]/div/div[" + 1 + "]/div[" + resourceColumn + "]/div[2]/div/div/div/span[2]")).getText();
	}
	private int GetColumn(int resourcesNum, String resource) {
		  
		  for(int i = 4; i< resourcesNum + 4; i++){
			  try{
				  WebElement element = Driver.findElement(By.xpath("//*[@id='roomsGrid']/div[1]/div[2]/div/div[" + i + "]/div[2]/div[1]/div[1]"));
				  String elementText = element.getText();
				  if(elementText.equals(resource))
				  {
					  return i;
				  }
			  }
			  catch(Exception e)
			  {
				  break;
			  }
			  
		  }
		  
		  return -1;
	 }
	private void ClickOnFilter(String resourceName) {
		  Driver.findElement(By.xpath("//div[@class='row']//text()[contains(., '" + resourceName + "')]/parent::*")).click();
	  }
	public void deleteResource(String roomToApply) {
		// TODO Auto-generated method stub
		 /*roomElement.click();
		    doubleclick(driver, roomElement);
		  driver.findElement(By.linkText("Resource Associations")).click();
		  driver.findElement(By.xpath("//div[@class='list-group']/div[last()]/div[4]/button")).click();
		  driver.findElement(By.cssSelector("button.info")).click();*/
		
	}
}
