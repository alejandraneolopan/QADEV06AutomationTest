package automationFramework;
import utils.Generator;
import config.Connection;
import org.testng.Assert;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import pageObjects.*;

public class AddLocationTest {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();
  Generator rand = new Generator();
  LoginPage login;
  HomePage home;
  LocationsPage location;
  
  @BeforeTest
  public void setUp() throws Exception {
	  driver = new Connection().initPage();
	  login = new LoginPage(driver);
	  home = login.signIn();
  }

  @Test(groups={"unit"})
  public void testAddLocation() throws Exception {
	String locationName = rand.getRandString();
	location = home
			.clickOnLocations()
			.WaitForTitle()
			.PressAddButton()
			.typeLocationName(locationName)
			.typeDisplayName(locationName)
			.typeDescription(locationName)
			.clickOnLocationAssociations()
			.waitForRooms()
			.selectOneRoom()
			.pressSaveButton()
			.refreshPage();
    /*Verification*/
   Assert.assertTrue(location.verifyNewLocationPresent(locationName));
    
   location.deleteLocation(locationName);
  }

  @AfterTest
  public void tearDown() throws Exception {
	login.signOut();
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
     System.out.println(verificationErrorString);
    }
  }
  
}

