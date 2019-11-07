package Webdriver_API;

import java.util.Random;
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
	String FirstName = "Selenium";
	String LastName = "Testing";
	String ValidEmail = "Selenium" + randomNumber() + "@gmail.com";
	String ValidPassword = "123456";

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

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id ='advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");

	}

	// Email invalid
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abcbabhdfd@abcccc");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");

	}

	// Passwork nhỏ hơn 6 ký tự
	@Test
	public void TC_03_LoginWithInvalidPass() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	// Log in với passwork incorrect
	@Test
	public void TC_04_LoginWithIncorrectPass() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("lan@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345678");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class ='error-msg']//span")).getText(), "Invalid login or password.");
	}

	// Log in với Email and Pass valid/ correct
	@Test
	public void TC_05_CreateNewAccount() {

		driver.findElement(By.xpath("//span[text() ='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(ValidEmail);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(ValidPassword);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(ValidPassword);
		driver.findElement(By.xpath("//div[@class ='buttons-set']//button[@title ='Register']")).click();
		System.out.println("random Email :" + ValidEmail);

		Assert.assertTrue(driver.findElement(By.xpath("//span[text() ='Thank you for registering with Main Website Store.']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "MY DASHBOARD");
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text() = 'Hello, " + FirstName + " " + LastName + "!']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class ='box-content']/p[contains(text(),'" + FirstName + " " + LastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class ='box-content']/p[contains(.,'" + ValidEmail + "')]")).isDisplayed());

		// log out
		driver.findElement(By.xpath("//div[@class ='account-cart-wrapper']//span[text() ='Account']")).click();
		driver.findElement(By.xpath("//div[@class ='links']//a[@title ='Log Out']")).click();
	}

	// Log in với Email and Pass valid/ correct
	@Test
	public void TC_06_LoginWithValidEmailandPass() {

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(ValidEmail);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(ValidPassword);
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "MY DASHBOARD");
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text() ='Hello, " + FirstName + " " + LastName + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class ='box-content']/p[contains(text(),'" + FirstName + " " + LastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class ='box-content']/p[contains(.,'" + ValidEmail + "')]")).isDisplayed());

	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}
}