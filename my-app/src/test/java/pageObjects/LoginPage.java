package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.Parameters;
 
public class LoginPage {
	
	private static WebDriver driver;
	private static WebElement element = null;
	
	
	public LoginPage(WebDriver driverToAssign)
	{
		driver = driverToAssign;
	}
	 
    public static WebElement inputUserName(){
 
         element = driver.findElement(By.id("loginUsername"));
 
         return element;
 
     }
 
     public static WebElement inputPassword(){
 
         element = driver.findElement(By.id("loginPassword"));
 
         return element;
 
     }
 
     public static WebElement buttonLogIn(){
 
         element = driver.findElement(By.xpath("//button[@type='submit']"));
          return element;
 
      }
     public HomePage signIn() {
    	 inputUserName().clear();
    	 inputUserName().sendKeys(Parameters.Username);
    	 inputPassword().clear();
    	 inputPassword().sendKeys(Parameters.Password);
    	 buttonLogIn().click();
    	 System.out.println(" Login Successfully.");
    	 return new HomePage(driver);
    	}

	public void signOut() {
		WebDriverWait wait = new WebDriverWait (driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'sign out')]/parent::a")));
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'sign out')]/parent::a"));
		Actions actions = new Actions(driver);
		actions.click(element).click().build().perform();
		
	}
 
}