package testng_framework;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class Part_IV_Loop {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id = 'email']");
	By passwordTextbox = By.xpath("//input[@id = 'pass']");
	By loginButton = By.xpath("//button[@id ='send2']");

	// Pre-Condition
	

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\Libraries\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test(dataProvider = "user_pass", invocationCount = 2)
	public void TC01_LoginToSystem(String username, String password) {

		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'col-1']//p")).getText().contains(username));
		driver.findElement(By.xpath("//div[@class ='account-cart-wrapper']//span[text() ='Account']")).click();
		driver.findElement(By.xpath("//a[text() = 'Log Out']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

	}

	// Data provider : chay qua 1 bộ dữ liệu ( data driven testing)
	@DataProvider(name = "user_pass")
	public Object[][] UserAndPassworkData() {
		return new Object[][] { 
			{ "automation_44@gmail.com", "123456" }
			};
	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
