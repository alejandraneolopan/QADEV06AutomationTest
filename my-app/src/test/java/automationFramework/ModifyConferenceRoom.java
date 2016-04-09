package automationFramework;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utils.Generator;
import config.Connection;

public class ModifyConferenceRoom {
  private WebDriver driver;
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
  public void testModifyConferenceRoom() throws Exception {
    /*Vars*/
	int iCount = 0;
	String newCode =rand.getRandString();
	String capacity =  String.valueOf(rand.getRandNumber(100));
    driver.findElement(By.linkText("Conference Rooms")).click();
    iCount = driver.findElements(By.xpath("//div[@id='roomsGrid']/div[2]/div/div")).size(); //div[starts-with(@class,'ng-scope ngRow')]
	int randomNum = rand.getRandNumber(iCount);
	/*Test*/
	WebElement roomElement = driver.findElement(By.xpath("//div[@id='roomsGrid']/div[2]/div/div[" + randomNum+ "]/div[3]/div[2]/div/span[2]"));
	roomElement.click();
	doubleclick(driver, roomElement);
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys(newCode);
    driver.findElement(By.xpath("(//input[@type='text'])[6]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys(capacity);
    driver.findElement(By.cssSelector("button.info")).click();
    /*Verification*/
    doubleclick(driver, roomElement);
    try {
    	Assert.assertEquals(newCode, driver.findElement(By.xpath("(//input[@type='text'])[5]")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
    	Assert.assertEquals(capacity, driver.findElement(By.xpath("(//input[@type='text'])[6]")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @AfterTest
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      System.out.println(verificationErrorString);
    }
  }

   private void doubleclick(WebDriver driver, WebElement element) {
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("var evt = document.createEvent('MouseEvents');" +
 		        "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" +
 		        "arguments[0].dispatchEvent(evt);", element);
     
    
  }

  
}

