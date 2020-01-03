package Webdriver_API;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.glass.events.KeyEvent;

public class Topic_10_UploadFiles {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String picture1Path = projectPath + "\\uploadFiles\\picture1.jpg";
	String picture2Path = projectPath + "\\uploadFiles\\picture2.jpg";
	String picture3Path = projectPath + "\\uploadFiles\\picture3.jpg";
	String picture4Path = projectPath + "\\uploadFiles\\picture4.jpg";
	String picture5Path = projectPath + "\\uploadFiles\\picture5.jpg";
	
	String chromePath = projectPath + "\\autoIT\\chrome.exe";
	String firefoxPath = projectPath + "\\autoIT\\firefox.exe";

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {

		// FireFox
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\Libraries\\geckodriver.exe");
//		driver = new FirefoxDriver();

		// Chrome
		System.setProperty("webdriver.chrome.driver", projectPath + "\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_sendkeys() throws InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		WebElement uploadFile = findElement("//input[@type ='file']");
		uploadFile.sendKeys(picture1Path + "\n" + picture2Path + "\n" + picture3Path + "\n" + picture4Path + "\n" + picture5Path);
		Thread.sleep(5000);

		List<WebElement> buttonStart = findElements("//table//button[@class ='btn btn-primary start']");

		for (WebElement button : buttonStart) {

			button.click();
			Thread.sleep(2000);
		}

		Assert.assertTrue(findElement("//a[text() ='picture1.jpg']").isDisplayed());
		Assert.assertTrue(findElement("//a[text() ='picture2.jpg']").isDisplayed());
		Assert.assertTrue(findElement("//a[text() ='picture3.jpg']").isDisplayed());
		Assert.assertTrue(findElement("//a[text() ='picture4.jpg']").isDisplayed());
		Assert.assertTrue(findElement("//a[text() ='picture5.jpg']").isDisplayed());
	}

	@Test
	public void TC_02_uploadFilebyRobot() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		WebElement uploadfile = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadfile.click();
		Thread.sleep(2000);
		
		// Specify the file location
		StringSelection select = new StringSelection(picture4Path);
		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		Robot robot = new Robot();
		Thread.sleep(1000);

		// Nhan phim Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Nhan Phim Ctrl + V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// Nha Phim Ctrl + V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		// Nhan phim Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);

		WebElement buttonStart = findElement("//table//button[@class ='btn btn-primary start']");
		buttonStart.click();
		Assert.assertTrue(findElement("//a[text() ='picture4.jpg']").isDisplayed());
	}
	
	//@Test
	public void TC_03_autoIT() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		WebElement uploadfile = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadfile.click();
		Thread.sleep(2000);
		
		if(driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] {firefoxPath, picture2Path});
			
		} else {
		Runtime.getRuntime().exec(new String[] {chromePath, picture2Path});
		}
		Thread.sleep(2000);
		
		WebElement buttonStart = findElement("//table//button[@class ='btn btn-primary start']");
		buttonStart.click();
		
		Thread.sleep(2000);
		Assert.assertTrue(findElement("//a[text() ='picture2.jpg']").isDisplayed());
	}
	// Ham tim element
	public WebElement findElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	// Ham tim List Elements

	public List<WebElement> findElements(String locator) {
		return driver.findElements(By.xpath(locator));
	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}