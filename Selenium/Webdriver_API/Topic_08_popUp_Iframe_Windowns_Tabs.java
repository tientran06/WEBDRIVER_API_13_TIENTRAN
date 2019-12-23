package Webdriver_API;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_popUp_Iframe_Windowns_Tabs {
	WebDriver driver;
	WebDriverWait waitExplicit;
	// JavascriptExecutor javascript;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=vi");
		driver = new ChromeDriver(options);

		waitExplicit = new WebDriverWait(driver, 10);
		// javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

	}

	// @Test
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
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class ='fancybox-inner']")));
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

	// @Test
	public void TC_02_handleWindowns_Tabs() throws Exception {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		String parentID = driver.getWindowHandle();
		System.out.println("Parent ID : " + parentID);

		driver.findElement(By.xpath("//a[text() ='GOOGLE']")).click();
		Thread.sleep(2000);

		switchToWindowByID(parentID);
		// Verify đẫ Switch qua tab Google thành công
		System.out.println("Title is : " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Google");
		Thread.sleep(2000);

		// Quay về tab chính
		driver.switchTo().window(parentID);
		Thread.sleep(2000);

		// Click FaceBook and verify switch qua Tab thành công

		driver.findElement(By.xpath("//a[text() ='FACEBOOK']")).click();
		switchToWindownByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");

		// Quay về parent Windown > switch qua TIKI và verify
		switchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.xpath("//a[text() ='TIKI']")).click();
		switchToWindownByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");

		// Đóng all các tabs lại và quay về parentID

		closeAllWindownswithoutParentID(parentID);
		Thread.sleep(2000);

		driver.switchTo().window(parentID);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

	}

	//@Test
	public void TC_03_handleWindowns_Tabs2() throws Exception {
		driver.get("https://kyna.vn/");
		String parentID = driver.getWindowHandle();

		// Step 02 - Verify neu co popup xuat hien, thi close lai

		List<WebElement> fancyPopup = driver.findElements(By.xpath("//div[@class = 'fancybox-inner']"));
		System.out.println("Pop up number is " + fancyPopup.size());

		if (fancyPopup.size() > 0) {
			Assert.assertTrue(fancyPopup.get(0).isDisplayed());
			driver.findElement(By.xpath("//div[@class ='fancybox-skin']//a[@title ='Close']")).click();
		}

		// Chờ cho popup dc đóng
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class ='fancybox-inner']")));

		// Step 03 - Click vào link Facebook | Youtube | Zalo | App Store | Google Player | FanPage |

		// Click Facebook
		Thread.sleep(3000);
		// driver.findElement(By.xpath("//div[@class = 'social']//a[contains(@href,'facebook')]")).click();
		clickIntoLocator("//div[@class = 'social']//a[contains(@href,'facebook')]");

		switchToWindownByTitle("Kyna.vn - Trang chủ | Facebook");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id ='entity_sidebar']//span[text() ='Kyna.vn']")).isDisplayed());

		switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
		// Click Youtube
		clickIntoLocator("//div[@class = 'social']//a[contains(@href, 'youtube')]");
		switchToWindownByTitle("Kyna.vn - YouTube");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id ='text-container']//yt-formatted-string[text() ='Kyna.vn']")).isDisplayed());

		switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
		// Click Zalo
		clickIntoLocator("//div[@class = 'social']//a[contains(@href,'zalo')]");
		switchToWindownByTitle("Kyna.vn");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn");

		Assert.assertEquals(driver.getCurrentUrl(), "https://zalo.me/1985686830006307471");
		// Switch to Iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'zalo')]")));
		boolean zaloIframe = driver.findElement(By.xpath("//span[@class = 'title-main']/h1[text() ='Kyna.vn']")).isDisplayed();
		System.out.println("Zalo Iframe is displayed " + zaloIframe);
		Assert.assertTrue(zaloIframe);

		switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
		// click App Store

		clickIntoLocator("//a[@title ='IOS']//img");
		switchToWindownByTitle("‎KYNA on the App Store");
		System.out.println("Current Title is " + driver.getTitle());

		// Assert.assertEquals(driver.getTitle(), "KYNA on the App Store");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(., 'KYNA')]")).isDisplayed());

		switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
		clickIntoLocator("//a[@title ='Android']/img[@alt ='android-app-icon']");
		System.out.println("Title is " + driver.getTitle());
		switchToWindownByTitle("KYNA - Học online cùng chuyên gia - Ứng dụng trên Google Play");
		Assert.assertEquals(driver.getTitle(), "KYNA - Học online cùng chuyên gia - Ứng dụng trên Google Play");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class ='sIskre']//span[text() ='KYNA - Học online cùng chuyên gia']")).isDisplayed());

		switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
		// Switch to fanpage & click

		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class ='fanpage']//iframe")));
		driver.findElement(By.xpath("//a[text() ='Kyna.vn']")).click();
		driver.switchTo().defaultContent();
		switchToWindownByTitle("Kyna.vn - Trang chủ");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		//Assert.assertTrue(driver.findElement(By.xpath("//a[text() ='@kyna.vn']")).isDisplayed());
		
		switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
		
		closeAllWindownswithoutParentID(parentID);
		Thread.sleep(2000);

	}

	@Test
	public void TC_04_handleWindowns_Tabs3() throws Exception {
		driver.get("http://live.guru99.com/index.php/");
		clickIntoLocator("//a[text() ='Mobile']");
		clickIntoLocator("//a[text() ='Sony Xperia']/parent::h2//following-sibling::div[@class ='actions']//a[text() ='Add to Compare']");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() ='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		
		clickIntoLocator("//a[text() ='Samsung Galaxy']/parent::h2//following-sibling::div[@class ='actions']//a[text() ='Add to Compare']");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() ='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		
		clickIntoLocator("//button[@title ='Compare']");
		switchToWindownByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		driver.close();
		Thread.sleep(2000);
		switchToWindownByTitle("Mobile");
		
		clickIntoLocator("//a[text() ='Clear All']");
		driver.switchTo().alert().accept();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() ='The comparison list was cleared.']")).isDisplayed());
	}
	
	// Hàm Switch to Windown by ID >> Lấy ra tất cả các ID, sau đó đi so sánh nếu khác parentID thì switch vào

	public void switchToWindowByID(String parentID) {
		// Lấy ra tất cả các ID đang có
		Set<String> allWindowns = driver.getWindowHandles();
		System.out.println("All Windowns size : " + allWindowns.size());
		// Dung vong lặp duyệt qua từng ID

		for (String termWindown : allWindowns) {

			System.out.println("ID : " + termWindown);
			if (!termWindown.equals(parentID)) {
				driver.switchTo().window(termWindown);
				break;
			}
		}
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
	// Hàm close tất cả các tabs trừ parent Windown

	public void closeAllWindownswithoutParentID(String parentID) {
		Set<String> allWindowns = driver.getWindowHandles();

		System.out.println("Cac tabs cần đóng : ");
		for (String termWindown : allWindowns) {
			driver.switchTo().window(termWindown);

			if (!termWindown.equals(parentID)) {
				System.out.println("Title is : " + driver.getTitle());
				driver.close();
			}
		}
	}

	// Ham click vao Locator
	public void clickIntoLocator(String locator) {
		driver.findElement(By.xpath(locator)).click();
	}

// Ham click by JS
//	public void clickElementbyJS(WebElement element) {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", element);
//	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
