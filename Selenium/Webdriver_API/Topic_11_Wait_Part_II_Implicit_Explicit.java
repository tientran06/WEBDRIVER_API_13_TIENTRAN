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

public class Topic_11_Wait_Part_II_Implicit_Explicit {
	WebDriver driver;
	WebDriverWait waitExplicit;	
	By startButtonBy = By.xpath("//button[text() ='Start']");
	By loadingBarBy = By.xpath("//div[@id ='loading']");
	By helloWordBy = By.xpath("//h4[text() ='Hello World!']");
	By selectedDateBy = By.xpath("//span[@id = 'ctl00_ContentPlaceholder1_Label1']");
	By loadingIconBy = By.xpath("//div[@class ='raDiv']");

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
	
		System.setProperty("webdriver.gecko.driver", ".\\Libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 5);
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_implicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());
		
		System.out.println("Start Click: " +getCurrrentTime());
		startButton.click();
		System.out.println("End Click: " +getCurrrentTime());
		
		//implicit wait -> Wait global > sẽ áp dụng cho tất cả các hàm findelement / findelements từ chỗ Set wait
		//Nếu muốn quay về implicit wait Defaut sau khi Set trong Test case >> thì phải Set lại implicit wait 1 lần nữa
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//Loading bar mất 5s để biến mất >> sau đó Helloword hiển thị
		
		System.out.println("Start find HelloWord: " +getCurrrentTime());
		WebElement hellowWord = driver.findElement(helloWordBy);
		Assert.assertTrue(hellowWord.isDisplayed());
		System.out.println("End find HelloWord: " +getCurrrentTime());
		System.out.println("Text is " +hellowWord.getText());
		
	}

	//@Test
	public void TC_02_explicitWaitInvisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());
		
		System.out.println("Start Click: " +getCurrrentTime());
		startButton.click();
		System.out.println("End Click: " +getCurrrentTime());
		
		// Chờ cho Loading bar biến mất (invisible) - 5s để biến mất
		System.out.println("Start Loading bar: " +getCurrrentTime());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingBarBy));
		System.out.println("End Loading bar: " +getCurrrentTime());
		
		// Check helloWord displayed
		System.out.println("Start find HelloWord: " +getCurrrentTime());
		WebElement hellowWord = driver.findElement(helloWordBy);
		Assert.assertTrue(hellowWord.isDisplayed());
		
		System.out.println("Text is "+hellowWord.getText());
		System.out.println("End find HelloWord: " +getCurrrentTime());
		
	}

	//@Test
	public void TC_03_explicitWaitVisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());
		// Chờ cho Button start có thể click
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButton));
		
		System.out.println("Start Click: " +getCurrrentTime());
		startButton.click();
		System.out.println("End Click: " +getCurrrentTime());
		
		// Chờ cho Hello Word được hiển thị
		//waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingBarBy));
		System.out.println("Start find Hello word: " +getCurrrentTime());
		
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloWordBy));
		
		Assert.assertEquals(driver.findElement(helloWordBy).getText(), "Hello World!");
		
		System.out.println("End find Hello word: " +getCurrrentTime());
		
	}
	
	@Test
	public void TC_04_explicitWait() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		WebElement selectedDate = driver.findElement(By.xpath("//span[@id = 'ctl00_ContentPlaceholder1_Label1']"));
		
		// wait cho "Date Time picker" dc hiển thị
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(selectedDateBy));
		
		// Before selected Date
		Assert.assertEquals(selectedDate.getText(), "No Selected Dates to display.");
		
		// Select Date Aug 22, 2019
		driver.findElement(By.xpath("//span[@id ='ctl00_ContentPlaceholder1_RadCalendar1_Title']")).click();
		driver.findElement(By.xpath("//a[text() ='2019']")).click();
		driver.findElement(By.xpath("//a[text() ='Aug']")).click();
		
		driver.findElement(By.xpath("//a[text() ='OK']")).click();
		WebElement calendarSelected = driver.findElement(By.xpath("//span[@id = 'ctl00_ContentPlaceholder1_RadCalendar1_Title' and text() ='August, 2019']"));
		waitExplicit.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class ='raDiv']"))));
		waitExplicit.until(ExpectedConditions.visibilityOf(calendarSelected));
		
		Assert.assertEquals(calendarSelected.getText(), "August, 2019");
		driver.findElement(By.xpath("//a[text() ='22']")).click();
		
		waitExplicit.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class ='raDiv']"))));
		waitExplicit.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//td[@class ='rcSelected']/a[text() ='22']"))));
		
		// Verify ngay đã chon
		selectedDate = driver.findElement(By.xpath("//span[@id = 'ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(selectedDate.getText(), "Thursday, August 22, 2019");
		
	}
	
	// Hàm get current time
	public String getCurrrentTime() {
		Date date = new Date();
		return date.toString();
		
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}