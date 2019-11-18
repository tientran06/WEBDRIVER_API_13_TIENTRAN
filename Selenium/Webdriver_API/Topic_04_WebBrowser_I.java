package Webdriver_API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_WebBrowser_I {
	WebDriver driver;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_VerifyUrl() {
		driver.get("http://live.demoguru99.com/");

		// Verify Login Url
		driver.findElement(By.xpath("//div[@class ='footer']//a[text() ='My Account']")).click();
		String LoginUrl = driver.getCurrentUrl();
		System.out.println("Log in Url is: " + LoginUrl);
		Assert.assertEquals(LoginUrl, "http://live.demoguru99.com/index.php/customer/account/login/");

		// Verify Create an Account Url
		driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
		String CreateAccountUrl = driver.getCurrentUrl();
		System.out.println("Create an Account Url is : " + CreateAccountUrl);
		Assert.assertEquals(CreateAccountUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_VerifyTitle() {
		// Verify Login Title
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class ='footer']//a[text() ='My Account']")).click();
		String LoginTitle = driver.getTitle();
		System.out.println("Log in Title is: " + LoginTitle);
		Assert.assertEquals(LoginTitle, "Customer Login");

		// Verfiy Create an Account Title

		driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
		String CreateAccountTitle = driver.getTitle();
		System.out.println("Create an Account Title is: " + CreateAccountTitle);
		Assert.assertEquals(CreateAccountTitle, "Create New Customer Account");
	}

	@Test
	public void TC_03_NavigateFunction() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class = 'footer']//a[@title ='My Account']")).click();
		driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
		// Verify Url của Create an Account page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		// Verify Url của Log in page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		// Verify Title cua Create an Account page
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_04_GetPageSource() {
		driver.get("http://live.demoguru99.com/");
		// Verify Login page
		driver.findElement(By.xpath("//div[@class = 'footer']//a[@title ='My Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account "));
		// Verify Create an Account page
		driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}