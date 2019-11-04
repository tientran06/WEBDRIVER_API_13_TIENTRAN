package Webdriver_API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Exercise {
	WebDriver driver;
	String username = "lan@gmail.com";
	String passwork = "123456";

	// Pre-Condition >> chạy đầu tiên của class
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// BeforeMethod >> chạy trước mỗi test case
	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class ='footer']//a[@title ='My Account']")).click();

	}

	// Email and passwork empty
	@Test
	public void TC_01_LoginEmpty() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		String ErrorEmailMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(ErrorEmailMsg, "This is a required field.");
		String ErrorPassMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(ErrorPassMsg, "This is a required field.");

	}

	// Email invalid
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abcbabhdfd@abcccc");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String ErrorEmailMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(ErrorEmailMsg, "Please enter a valid email address. For example johndoe@domain.com.");
		String ErrorPassMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(ErrorPassMsg, "This is a required field.");
	}

	// Passwork nhỏ hơn 6 ký tự
	@Test
	public void TC_03_LoginWithInvalidPass() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String ErrorPassMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(ErrorPassMsg, "Please enter 6 or more characters without leading or trailing spaces.");

	}

	// Log in với passwork incorrect
	@Test
	public void TC_04_LoginWithIncorrectPass() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("lan@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345678");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String ErrorPassMsg = driver.findElement(By.xpath("//li[@class ='error-msg']//span[text()]")).getText();
		Assert.assertEquals(ErrorPassMsg, "Invalid login or password.");

	}

	// Log in với Email and Pass valid/ correct
	@Test
	public void TC_05_LoginWithValidEmailandPass() {

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(passwork);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//Verify message say 'MY DASHBOARD'
		String WelcomeMsg = driver.findElement(By.xpath("//div[@class ='page-title']//h1[(text()='My Dashboard')]")).getText();
		Assert.assertEquals(WelcomeMsg, "MY DASHBOARD");
		
		// Verify message say 'Hello'
		String WelcomeMsg1 = driver.findElement(By.xpath("//p[@class ='hello']//strong[contains(.,'Hello')]")).getText();
		Assert.assertTrue(WelcomeMsg1.contains("Hello"));
		// in ra màn hình
		System.out.println("Text =" + WelcomeMsg1);
		
		// Verify Email
		String WElcomeEmail = driver.findElement(By.xpath("//div[@class ='box-content']//p[contains(.,'lan@gmail.com')]")).getText();
		// in result ra màn hình
		System.out.println("Text =" + WElcomeEmail);
		Assert.assertTrue(WElcomeEmail.contains(username));

		// Verify User Name

		String FirstName1 = driver.findElement(By.xpath("//div[@class ='box-content']//p[contains(.,'Lan')]"))
				.getText();
		driver.findElement(By.xpath("//div[@class ='box-title']//a[contains(@href,'http://live.demoguru99.com/index.php/customer/account/edit/')]"))
				.click();
		String FirstName = driver.findElement(By.xpath("//input[@id='firstname']")).getText();
		Assert.assertTrue(FirstName1.contains(FirstName));

	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}