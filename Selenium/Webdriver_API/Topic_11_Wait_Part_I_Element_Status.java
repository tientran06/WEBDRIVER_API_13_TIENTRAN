package Webdriver_API;

import java.util.List;
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


public class Topic_11_Wait_Part_I_Element_Status {
	WebDriver driver;
	Boolean status;
	WebDriverWait waitExplicit;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\Libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_ElementDisplayedOrVisible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		// **** DK Bắt buộc : Element hiển thị trên UI + có trong DOM ****

		// 1 - ĐK 1 - Element hiển thị trên UI + Có trong DOM - PASS

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id ='SubmitLogin']"))); // Chờ cho Element displayed / visible
		status = driver.findElement(By.xpath("//button[@id ='SubmitLogin']")).isDisplayed(); // Check displayed / visible
		System.out.println("Element hiển thị trên UI + Có trong DOM =" + status); // Status = True

		// 2 - ĐK 2 - Element không hiển thị trên UI + Có trong DOM - Pass / Fail

		// waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'create_account_error']"))); // Fail
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id = 'create_account_error']"))); // Pass
		status = driver.findElement(By.xpath("//div[@id = 'create_account_error']")).isDisplayed();
		System.out.println("Element không hiển thị trên UI + Có trong DOM = " + status); // status = False

		// 3 - ĐK 3 - Element không hiển thị trên UI + Không có trong DOM - Fail
		// waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name ='id_order']")));
		status = driver.findElement(By.xpath("//input[@name ='id_order']")).isDisplayed();
		System.out.println("Element không hiển thị trên UI + Không có trong DOM = " + status);
	}

	// @Test
	public void TC_02_ElementUndisplayedOrInvisible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		// **** DK Bắt buộc : Không hiển thị trên UI (Không quan tâm Element có or ko trong DOM) ****

		// 1 - ĐK 1 - Element hiển thị trên UI + Có trong DOM - FAIL

		// waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@id ='SubmitLogin']"))); // Chờ cho Element undisplayed / invisible
		status = driver.findElement(By.xpath("//button[@id ='SubmitLogin']")).isDisplayed(); // Check displayed / visible
		System.out.println("Element hiển thị trên UI + Có trong DOM =" + status); // Status = True

		// 2 - ĐK 2 - Element không hiển thị trên UI + Có trong DOM - PASS

		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id = 'create_account_error']"))); // Pass
		status = driver.findElement(By.xpath("//div[@id = 'create_account_error']")).isDisplayed();
		System.out.println("Element không hiển thị trên UI + Có trong DOM = " + status); // status = False

		// 3 - ĐK 3 - Element không hiển thị trên UI + Không có trong DOM - PASS
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name ='id_order']"))); // Chờ cho Element undisplayed / invisible
//		status = driver.findElement(By.xpath("//input[@name ='id_order']")).isDisplayed();
//		System.out.println("Element không hiển thị trên UI + Không có trong DOM = " + status);
	}

	//@Test
	public void TC_03_ElementPresence() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		// **** DK Bắt buộc : Element có trong DOM ( Không quan tâm có hiển thị trên UI or không)

		// 1 - ĐK 1 - Element hiển thị trên UI + Có trong DOM - PASS

		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id ='SubmitLogin']"))); // Chờ cho Element có trong DOM / Check
		status = driver.findElement(By.xpath("//button[@id ='SubmitLogin']")).isDisplayed(); // Check displayed / visible
		System.out.println("Element hiển thị trên UI + Có trong DOM =" + status); // Status = True

		// 2 - ĐK 2 - Element không hiển thị trên UI + Có trong DOM - PASS

		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id = 'create_account_error']"))); // Chờ cho Element có trong DOM / Check
		status = driver.findElement(By.xpath("//div[@id = 'create_account_error']")).isDisplayed();
		System.out.println("Element không hiển thị trên UI + Có trong DOM = " + status); // status = False

		// 3 - ĐK 3 - Element không hiển thị trên UI + Không có trong DOM - FAIL

		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name ='id_order']"))); // Chờ cho Element có trong DOM / Check
//		status = driver.findElement(By.xpath("//input[@name ='id_order']")).isDisplayed();
//		System.out.println("Element không hiển thị trên UI + Không có trong DOM = " + status);
	}

	//@Test
	public void TC_04_findElement() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// Case 1 - Đi tìm Element nhưng không tìm thấy
		//driver.findElement(By.xpath("//input[@name ='id_order']")).sendKeys("12345678");
		
		// Case 2 - Tìm thấy 1 matching node
		
		driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys("abc@gmail.com");
		
		// Case 3 - Tìm thấy  nhiều hơn 1 matching node >> working với element đầu tiên dc tìm thấy
		
		driver.findElement(By.xpath("//button[@type = 'submit']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Please enter a search keyword')]")).isDisplayed());
		
	}
	
	@Test
		public void TC_05_findElements() {
			driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
			
			// Case 1 - Đi tìm Element nhưng không tìm thấy
			List<WebElement> elements = driver.findElements(By.xpath("//input[@name ='id_order']"));
			System.out.println("List of element = " + elements.size());
			
			Assert.assertTrue(elements.isEmpty());
			Assert.assertEquals(elements.size(), 0);
			
			// Case 2 - Tìm thấy 1 matching node
			elements = driver.findElements(By.xpath("//input[@id = 'email']"));
			System.out.println("List of Elements = " +elements.size());
			Assert.assertEquals(elements.size(), 1);
			elements.get(0).sendKeys("email@gmail.com");
			
			// Case 3 - Tìm thấy  nhiều hơn 1 matching node >> 
			
			elements = driver.findElements(By.xpath("//button[@type = 'submit']"));
			System.out.println("List of elements = " +elements.size());
			Assert.assertEquals(elements.size(), 4);
			elements.get(1).click();
			
			Assert.assertTrue(driver.findElement(By.xpath("//li[text() ='Invalid email address.']")).isDisplayed());
					
		}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}