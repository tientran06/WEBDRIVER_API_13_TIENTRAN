package Webdriver_API;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_UserInteractions {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	JavascriptExecutor javascriptExecutor;
	Actions action;
	Alert alert;
	
	String javascriptPath, jqueryPath;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\Libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		javascriptExecutor = (JavascriptExecutor) driver;
		
		javascriptPath = projectPath + ("\\dragAndDrop\\drag_and_drop_helper.js");
		jqueryPath = projectPath + ("\\dragAndDrop\\jquery_load_helper.js");
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_moveToElement() throws Exception {
		driver.get("http://www.myntra.com/");
		// Hover vao Discover menu vs click American Eagle
		action.moveToElement(findByXpath("//div[@class ='desktop-navLink']/a[text() ='Discover']")).perform();
		findByXpath("//ul[@class ='desktop-navBlock']//a[text() ='American Eagle']").click();

		// Verify click thanh cong
		Assert.assertTrue(isElementDisplayed("//ul[@class ='breadcrumbs-list']//span[text() ='American Eagle']"));
		Assert.assertEquals(findByXpath("//div[@class ='title-container']//h1").getText(), "American Eagle");
		Thread.sleep(3000);
	}

	@Test
	public void TC_02_ClickandHold() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		// Khai bao 1 list Element chua numbers
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id = 'selectable']/li"));
		int numberSize = numbers.size();
		System.out.println("Total number = " + numberSize);
		// Click chon number 1 -> 8
		action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(7)).release().perform();
		Thread.sleep(3000);
		// Verfiy sau khi đã chon thành công
		List<WebElement> selectedNumbers = driver.findElements(By.xpath("//ol[@id = 'selectable']/li[contains(@class,'ui-selected')]"));
		int selectedSize = selectedNumbers.size();
		Assert.assertEquals(selectedSize, 8);
		System.out.println("number is selected " + selectedSize);
	}

	@Test
	public void TC_03_ClickandHoldRandom() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

		// Khai bao 1 list Element chua numbers
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id = 'selectable']/li"));
		int numberSize = numbers.size();
		System.out.println("Total number = " + numberSize);
		// Click chon number 1|3|5|7|8|9
		action.keyDown(Keys.CONTROL).perform(); // giu phim CTRL
		action.click(numbers.get(0)).click(numbers.get(2)).click(numbers.get(4)).click(numbers.get(6)).click(numbers.get(7)).click(numbers.get(8));

		action.keyUp(Keys.CONTROL).perform(); // nha phim CTRL
		Thread.sleep(3000);

		// Verfiy sau khi đã chon thành công
		List<WebElement> selectedNumbers = driver.findElements(By.xpath("//ol[@id = 'selectable']/li[contains(@class,'ui-selected')]"));
		int selectedSize = selectedNumbers.size();
		Assert.assertEquals(selectedSize, 6);
		System.out.println("number is selected " + selectedSize);
	}

	@Test
	public void TC_04_doubleClick() throws Exception {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(findByXpath("//div/button[text() ='Double click me']")).perform();
		Thread.sleep(3000);
		// Verify sau khi double click
		Assert.assertTrue(findByXpath("//p[@id ='demo' and text() ='Hello Automation Guys!']").isDisplayed());
	}

	@Test
	public void TC_05_rightClick() throws Exception {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		// Right click vao 'Right click me' and Hover vao Element Quit > After that Click on Quit
		action.contextClick(findByXpath("//span[text() ='right click me']")).perform();
		// Hover vs verify it
		action.moveToElement(findByXpath("//span[text() ='Quit']")).perform();
		Assert.assertTrue(isElementDisplayed("//ul[contains(@class,'context-menu-list')]//li[contains(@class,'context-menu-visible')]//span[text() ='Quit']"));
		// Click and verify
		action.click(findByXpath("//ul[contains(@class,'context-menu-list')]//li[contains(@class,'context-menu-visible')]//span[text() ='Quit']")).perform();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: quit");
		System.out.println("Alert is: " + alert.getText());
		alert.accept();

	}

	@Test
	public void TC_06_dragAndDrop() throws Exception {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		String sourceCss = "//div[@id ='draggable']";
		String targetCss = "//div[@id ='droptarget']";
		// Drag and Drop
		action.dragAndDrop(findByXpath(sourceCss), findByXpath(targetCss)).perform();
		Thread.sleep(3000);
		System.out.println("Result is :" + findByXpath(targetCss).getText());
		Assert.assertEquals(findByXpath(targetCss).getText(), "You did great!");

	}
	
	@Test
	public void TC_07_Drag_And_Drop_HTML5() throws Exception, IOException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		String java_script = readFile(javascriptPath);

		// Inject Jquery lib to site
		// String jqueryscript = readFile(jqueryPath);
		// javascriptExecutor.executeScript(jqueryscript);

		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));

		// B to A
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-b']/header[text()='B']"));
	}
	
	// Ham Find Element
	public WebElement findByXpath(String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	// Ham Check Element is Displayed
	public boolean isElementDisplayed(String xpathLocator) {
		return findByXpath(xpathLocator).isDisplayed();

	}
	
	// Ham readFile
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}