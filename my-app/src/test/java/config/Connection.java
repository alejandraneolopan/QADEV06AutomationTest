package config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Connection {
	private WebDriver driver;
	  private String baseUrl;
	  
	public WebDriver init()
	{
		 driver = new FirefoxDriver();
		    baseUrl = "https://172.20.208.146:4040/admin";
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    //authentication
			driver.get(baseUrl);
			driver.findElement(By.id("loginUsername")).clear();
		    driver.findElement(By.id("loginUsername")).sendKeys("admin");
		    driver.findElement(By.id("loginPassword")).clear();
		    driver.findElement(By.id("loginPassword")).sendKeys("Nexo2010");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    return driver;
	}
}
