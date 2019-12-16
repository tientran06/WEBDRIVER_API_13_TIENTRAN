package Webdriver_API;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_popUp_Iframe {
	WebDriver driver;
	WebDriverWait waitExplicit;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_popUpAndIframe() throws Exception {
		driver.get("https://kyna.vn/");

		// Step 02 - kiem tra co pop up xuat hien hay ko
		List<WebElement> fancyPopup = driver.findElements(By.xpath("//div[@class ='fancybox-inner']"));
		System.out.println("Pop up number " + fancyPopup.size());
		Thread.sleep(3000);

		// Step 03 - neu co popup thi dong lai
		if (fancyPopup.size() > 0) {
			System.out.println("Step 03 - check Popup hien thi");
			Assert.assertTrue(fancyPopup.get(0).isDisplayed());
			driver.findElement(By.xpath("//div[@class ='fancybox-skin']//a[@title ='Close']")).click();
		}

		// Step 04 - neu khong co pop up thi qua step 04
		// Switch to Iframe va check display
		System.out.println("Step 04 - check Iframe Facebook is displayed");
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class ='fanpage']//iframe")));

		// Verify Facebook Iframe hien thi
		boolean facebookIframe = driver.findElement(By.cssSelector("#facebook")).isDisplayed();
		System.out.println("Facebook Iframe is displayed " + facebookIframe);
		Assert.assertTrue(facebookIframe);

		// Step 05 - Verfiy Facebook likes = 170k
		String facebookLikes = driver.findElement(By.xpath("//a[text() ='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println("Facebook Likes = " + facebookLikes);
		Assert.assertEquals(facebookLikes, "170K likes");

		// Sau khi work vs Iframe thi switch lai mainpage de work tiep
		driver.switchTo().defaultContent();
		// waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class ='fancybox-inner']")));
		// Step 06 - Click button Dang nhap > input thong tin
		driver.findElement(By.xpath("//li[@class ='login-signup']//a[@class ='button-login']")).click();
		driver.findElement(By.xpath("//input[@id ='user-login']")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id ='user-password']")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id ='btn-submit-login']")).click();

		// Step 07 - Verify dang nhap thanh cong
		WebElement userLogin = driver.findElement(By.xpath("//li[@class = 'account dropdown wrap']//span[@class = 'user']"));
		Assert.assertTrue(userLogin.isDisplayed());
		Assert.assertEquals(userLogin.getText(), "Automation FC");
		System.out.println("User name is: " + userLogin.getText());
	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}