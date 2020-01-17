package Webdriver_API;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Wait_Part_III_Mixing_Implicit_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\Libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

//	@Test
	public void TC_01_Element_Found() {
		driver.get("http://demo.guru99.com/");

		// Implicit wait

		System.out.println("---- CASE 01 - START TC_01_Element_Found" + new Date() + "--------");
		try {
			WebElement element = driver.findElement(By.xpath("//input[@name ='emailid']"));
			Assert.assertTrue(element.isDisplayed());
		} catch (Exception ex) {
			System.out.println("Switch to catch Exception");
			System.out.println(ex.getMessage());
		}
		System.out.println("---- CASE 01 - END TC_01_Element_Found" + new Date() + "--------");

		// Explicit wait
		System.out.println("---- CASE 01 - START TC_01_Element_Found" + new Date() + "--------");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name ='emailid']")));
		} catch (Exception ex) {
			System.out.println("Switch to catch Exception");
			System.out.println(ex.getMessage());
		}
		System.out.println("---- CASE 01 - END TC_01_Element_Found" + new Date() + "--------");
	}

	@Test
	public void TC_02_Element_NotFound() {
		driver.get("http://demo.guru99.com/");

		// Implicit wait

		System.out.println("---- CASE 02 - START TC_02_Element_NotFound" + new Date() + "--------");
		try {
			WebElement element = driver.findElement(By.xpath("//input[@name ='automation']"));
			Assert.assertTrue(element.isDisplayed());
		} catch (Exception ex) {
			System.out.println("Switch to catch Exception");
			System.out.println(ex.getMessage());
		}
		System.out.println("---- CASE 02 - END TC_02_Element_NotFound" + new Date() + "--------");

		// Explicit wait for Find ELEMENT
		System.out.println("---- CASE 02 - START TC_02_Element_NotFound" + new Date() + "--------");
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name ='automation']"))));
		} catch (Exception ex) {
			System.out.println("Switch to catch Exception");
			System.out.println(ex.getMessage());
		}
		System.out.println("---- CASE 02 - END TC_02_Element_NotFound" + new Date() + "--------");

		// Explicit wait for Find BY
		System.out.println("---- CASE 03 - START TC_02_Element_NotFound" + new Date() + "--------");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name ='automation']")));
		} catch (Exception ex) {
			System.out.println("Switch to catch Exception");
			System.out.println(ex.getMessage());
		}
		System.out.println("---- CASE 03 - END TC_02_Element_NotFound" + new Date() + "--------");
		// *** NOTE ***
		// Implicit nếu ko set thì timeout của implicit = 0 
		// Báo ko tìm thấy vs đánh fail test case 0-0.01s  (10/1000s)
	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}