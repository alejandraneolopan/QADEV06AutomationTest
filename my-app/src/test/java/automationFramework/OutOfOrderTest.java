package automationFramework;
import utils.Generator;
import config.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Date;
import org.openqa.selenium.*;
import pageObjects.*;

public class OutOfOrderTest {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();
  Generator rand = new Generator();
  ConferenceRoomPage Rooms;
  LoginPage login;
  HomePage home;
 
  @BeforeClass
  public void setUp() throws Exception {
	  driver = new Connection().initPage();
	  login = new LoginPage(driver);
	  home = login.signIn();
  }

  @Test(groups={"unit"})
  public void testCreateOutOfOrder() throws Exception {
	/*Vars*/
	String roomToApply="CENTRAL-F1R04";
	/*Test*/
	Rooms = home
			.WaitTitle(10)
			.clickOnRooms()
			.WaitRooms(20)
			.ClickOnRoom(roomToApply)
			.SelectOutOfOrder()
			.FillDataOutOfOrder(true)
			.PressSaveButton()
			.RefreshPage();
	 /*Verify*/
	Assert.assertEquals("Begin: " + getToday("M/d/yyyy") + ",", Rooms.VerifyMessageOutOfOrder(roomToApply));
   
  }

  @AfterClass
  public void tearDown() throws Exception {
  //  driver.findElement(By.cssSelector("a > span")).click();
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
     System.out.println(verificationErrorString);
    }
  }

  private String getToday(String format) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    Date date = new Date();
    return dateFormat.format(date);
     
  }
}

