package automationFramework;

import utils.Generator;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import pageObjects.*;
import config.Connection;

public class AssociateResourceToRoom {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  Generator rand = new Generator();
  LoginPage login;
  HomePage home;
  
  @BeforeTest
  public void setUp() throws Exception {
	  driver = new Connection().initPage();
	  login = new LoginPage(driver);
	  home = login.signIn();
  }

  @Test
  public void testAssociateResourceToRoom() throws Exception {
	  /*Vars*/
	int iCount, resourcesCount = 0,rowRoomNum,randomResourceNum;
	driver.findElement(By.linkText("Conference Rooms")).click();
	String amount =  String.valueOf(rand.getRandNumber(100));
    iCount = driver.findElements(By.xpath("//div[@id='roomsGrid']/div[2]/div/div")).size();
	rowRoomNum = rand.getRandNumber(1, iCount);
	/*Test*/
	driver.findElement(By.linkText("Conference Rooms")).click();
	WebElement roomElement = driver.findElement(By.xpath("//div[@id='roomsGrid']/div[2]/div/div[" + rowRoomNum+ "]/div[3]/div[2]/div/span[2]"));
	roomElement.click();
	doubleclick(driver, roomElement);
	driver.findElement(By.linkText("Resource Associations")).click();
	//Select a resource --> 
	resourcesCount = driver.findElements(By.xpath("//div[@class='list-group']/div/div[@class='col-xs-8']")).size();
	randomResourceNum = rand.getRandNumber(1, resourcesCount);
	//Add resource 
	String resourceName =driver.findElement(By.xpath("//div[@class='list-group']/div[" + randomResourceNum + "]/div[2]/span")).getText();
    driver.findElement(By.xpath("//div[@class='list-group'][1]/div[" + randomResourceNum + "]/div[3]/button")).click();
    driver.findElement(By.xpath("//div[@class='list-group']/div[last()]/div[3]/input")).clear();
    driver.findElement(By.xpath("//div[@class='list-group']/div[last()]/div[3]/input")).sendKeys(amount);
    driver.findElement(By.cssSelector("button.info")).click();
    /*Verification*/
    int resourceColumn = GetColumn(driver,resourcesCount, resourceName);
    if (resourceColumn < 0)
    {
    	ClickOnFilter(resourceName);
    	resourceColumn = GetColumn(driver,resourcesCount, resourceName);
    }
    
    Assert.assertEquals("x " + amount, driver.findElement(By.xpath("//*[@id='roomsGrid']/div[2]/div/div[" + rowRoomNum + "]/div[" + resourceColumn + "]/div[2]/div/div/div/span[2]")).getText());
   /*Clean*/ 
    roomElement.click();
    doubleclick(driver, roomElement);
  driver.findElement(By.linkText("Resource Associations")).click();
  driver.findElement(By.xpath("//div[@class='list-group']/div[last()]/div[4]/button")).click();
  driver.findElement(By.cssSelector("button.info")).click();
  }

  private void ClickOnFilter(String resourceName) {
	  driver.findElement(By.xpath("//text()[. = '" + resourceName + "']/parent::*")).click();
  }
  
  @AfterTest
  public void tearDown() throws Exception {
	  
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      System.out.println(verificationErrorString);
    }
  }

  private int GetColumn(WebDriver driver,int resourcesNum, String resource) {
	  
	  for(int i = 4; i< resourcesNum + 4; i++){
		  try{
			  WebElement element = driver.findElement(By.xpath("//*[@id='roomsGrid']/div[1]/div[2]/div/div[" + i + "]/div[2]/div[1]/div[1]"));
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
 

  private void doubleclick(WebDriver driver, WebElement element) {
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("var evt = document.createEvent('MouseEvents');" +
 		        "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" +
 		        "arguments[0].dispatchEvent(evt);", element);
    }
  
  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

