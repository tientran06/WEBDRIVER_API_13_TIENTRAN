package Webdriver_API;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_windowns_Tabs {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	WebDriverWait waitExplixit;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\libraries\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=vi");
		driver = new ChromeDriver(options);
		waitExplixit = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_03_WindowTab() throws InterruptedException {
		driver.get("https://kyna.vn/");

		// close popup if have
		List<WebElement> fancyPopup = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		if (fancyPopup.size() > 0) {
			Assert.assertTrue(fancyPopup.get(0).isDisplayed());
			driver.findElement(By.cssSelector(".fancybox-close")).click();
			Thread.sleep(3000);
		}

	//	String parentID = driver.getWindowHandle();

//		clickElementbyJS(driver.findElement(By.xpath("//a[@title ='IOS']//img")));
//		switchToWindownByTitle("‎KYNA on the App Store");
//		Assert.assertEquals(driver.getTitle(), "‎KYNA on the App Store");

		
	}

	// Hàm Switch to Windown by Title
	// >> Lấy ra tất cả các ID, switch vào từng ID, sau đó getTitle và so sánh nếu đúng Title mong đợi thì Switch vào
	public void switchToWindownByTitle(String expectedTitle) {
		Set<String> allWindowns = driver.getWindowHandles();
		System.out.println("All Windowns Size : " + allWindowns.size());

		for (String termWindown : allWindowns) {
			driver.switchTo().window(termWindown);
			System.out.println("ID is " + driver.getTitle());

			String currentTitle = driver.getTitle();

			if (currentTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	// Ham click by JS
	public void clickElementbyJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}