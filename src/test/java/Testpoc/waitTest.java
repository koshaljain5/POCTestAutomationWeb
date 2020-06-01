package Testpoc;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class waitTest {

	
	@Test
	public void waittestmethod()
	{
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
		System.setProperty("webdriver.chrome.driver", "E:/Koshal-Practice/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement username = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement password = driver.findElement(By.xpath("//input[@id='pass']"));	
		WebElement loginBtn = driver.findElement(By.xpath("//input[@value='Log In']"));
		
		sendKeysWithWait(driver, username, 5, "testuser");
		sendKeysWithWait(driver, password, 1, "testpassword");
		clickWithWait(driver, loginBtn, 1);
		
		driver.quit();
	}
	
	public void sendKeysWithWait(WebDriver driver, WebElement element, int timeout, String value)
	{
		new WebDriverWait(driver, timeout)
		.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}
	
	public void clickWithWait(WebDriver driver, WebElement element, int timeout)
	{
		new WebDriverWait(driver, timeout)
		.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
}
