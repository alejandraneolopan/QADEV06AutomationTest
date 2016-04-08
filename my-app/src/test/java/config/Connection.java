package config;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Connection {
	public WebDriver Driver;

	public WebDriver initPage()
	{
		Driver = new FirefoxDriver();
	    Driver.manage().timeouts().implicitlyWait(Parameters.ImplicitTimeWait, TimeUnit.SECONDS);
	    Driver.get(Parameters.BaseUrl);
	    return Driver;
	}
}
