package automationFramework;

import utils.Generator;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import pageObjects.*;
import config.Connection;

public class AssociateResourceToRoom {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();
  Generator rand = new Generator();
  LoginPage login;
  HomePage home;
  ConferenceRoomPage rooms;
  @BeforeTest
  public void setUp() throws Exception {
	  driver = new Connection().initPage();
	  login = new LoginPage(driver);
	  home = login.signIn();
  }

  @Test
  public void testAssociateResourceToRoom() throws Exception {
	  /*Vars*/
	String amount =  String.valueOf(rand.getRandNumber(100));
    String roomToApply = "CENTRAL-F1R04";
    String resourceName = "Radio"; 
	/*Test*/
	rooms = home
			.clickOnRooms()
			.WaitRooms(20)
			.ClickOnRoom(roomToApply)
			.SelectResources()
			.AddResource(resourceName)
			.typeQuantity(amount)
			.PressSaveButton()
			.RefreshPage();
	/*Verification*/
    Assert.assertEquals("x " + amount, rooms.verifyResourceMessage(resourceName));

    rooms.deleteResource(roomToApply);
   
  }
  @AfterTest
  public void tearDown() throws Exception {
	  
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      System.out.println(verificationErrorString);
    }
  }
 
}

