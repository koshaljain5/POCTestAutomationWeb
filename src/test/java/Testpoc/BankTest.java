package Testpoc;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BankTest {

	WebDriver driver;
	
	@BeforeTest
	public void setUP()
	{
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
		System.setProperty("webdriver.chrome.driver", "D:/Koshal-Practice/chromedriver.exe");
		driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		//options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://dbankdemo.com/login");
	}
	
	@Test
	public void bankTitle()
	{
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Digital Bank","TestPassed");
	}
	
	@Test(dependsOnMethods = "bankTitle")
	public void bankLogin()
	{
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("testuser1@testjmeter.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//button[@id='submit']")).click();
		Boolean b = driver.findElement(By.xpath("//h3[text()='Banking Accounts']")).isDisplayed();
		Assert.assertTrue(b, "TestPassed: Successfuly Logged In");
	}
	
	@Test(dependsOnMethods = "bankLogin")
	public void bankLogout()
	{
		driver.findElement(By.xpath("//div[@class='user-area dropdown']/a/img[@alt='User Avatar']")).click();
		driver.findElement(By.xpath("//a[contains(@href,'logout')]")).click();
		Boolean b = driver.findElement(By.xpath("//span[contains(text(),'Success')]")).isDisplayed();
		Assert.assertTrue(b, "TestPassed: Successfuly Logged Out");
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
	
	
}
