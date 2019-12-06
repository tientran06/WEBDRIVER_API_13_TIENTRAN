package Webdriver_API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_ButtonRadioCheckboxAlert {
	WebDriver driver;
	JavascriptExecutor javascript;
	Alert alert;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_clickByJavascript() throws Exception {
		driver.get("http://live.demoguru99.com/");
		WebElement myAccount = driver.findElement(By.xpath("//div[@class ='footer-container']//a[text() ='My Account']"));

		// Click vao My Account bang Javascript vs verify the URL after click
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", myAccount);

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		Thread.sleep(1000);
		// Click vao Create An Account vs verify the URL after click
		WebElement createNewAcc = driver.findElement(By.xpath("//span[text() = 'Create an Account']"));
		clickElementByJavascript(createNewAcc);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		Thread.sleep(1000);

		// Click vao Menu Desktops vs verify page after click
		driver.get("https://demo.nopcommerce.com/");
		WebElement desktopMenu = driver.findElement(By.xpath("//ul[@class ='top-menu notmobile']//a[text() ='Desktops ']"));
		clickElementByJavascript(desktopMenu);
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text() ='Desktops']")).isDisplayed());
		Thread.sleep(1000);
	}

	@Test
	public void TC_02_Checkbox() throws Exception {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");

		// Chon into Dual-zone air conditioning
		WebElement checkbox = driver.findElement(By.xpath("//label[text() ='Dual-zone air conditioning']//preceding-sibling::input[@type = 'checkbox']"));
		clickElementByJavascript(checkbox);
		Assert.assertTrue(isSelected(checkbox));

		// check into Heated front and rear seats
		WebElement checkbox1 = driver.findElement(By.xpath("//label[text() ='Heated front and rear seats']/preceding-sibling::input[@type ='checkbox']"));
		clickElementByJavascript(checkbox1);
		Assert.assertTrue(isSelected(checkbox1));
		Thread.sleep(2000);
		// uncheck and verify again
		clickElementByJavascript(checkbox1);
		Assert.assertFalse(isSelected(checkbox1));

	}

	@Test
	public void TC_03_RadioButton() throws Exception {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");

		// Kiem tra neu chua chon thi Click chon Radio button 2.0 Petrol, 147kW
		WebElement radioButton = driver.findElement(By.xpath("//label[text() ='2.0 Petrol, 147kW']/preceding-sibling::input"));
		Assert.assertFalse(isSelected(radioButton));
		clickElementByJavascript(radioButton);

		// Kiem tra Radio button da dc chon chua
		Assert.assertTrue(isSelected(radioButton));
		Thread.sleep(2000);
	}

	@Test
	public void TC_04_handleAlert() throws Exception {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String resultMessage = "//p[@id ='result']";
		// TC04 - Accept Alert
		WebElement acceptButton = driver.findElement(By.xpath("//button[text() ='Click for JS Alert']"));

		// Verify button Enable truoc khi click
		Assert.assertTrue(isEnable(acceptButton));
		acceptButton.click();

		// Handle Alert
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		Thread.sleep(3000);
		System.out.println("This is your message: " + alert.getText());
		alert.accept();

		// verify after accept Alert
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked an alert successfully");

		// TC05 - Confirm Alert
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text() = 'Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		System.out.println("This is your message: " + alert.getText());
		Thread.sleep(3000);
		// Cancel message and verify
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked: Cancel");

		// TC06 - Prompt Alert - Click for JS Prompt and input text, after that verify it.
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text() ='Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		System.out.println("This is message for you: " + alert.getText());
		alert.sendKeys("Automation Testing");
		alert.accept();
		// Verify after input successfully
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You entered: Automation Testing");
		Thread.sleep(3000);
	}

	// Hàm click by JS
	public void clickElementByJavascript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	// Hàm check xem element co dc Select ko
	public boolean isSelected(WebElement element) {
		if (element.isSelected()) {
			System.out.println("Element is selected" + element.getText());
			return true;
		} else {
			System.out.println("Element is not selected" + element.getText());
			return false;
		}
	}

	// Hàm Enable
	public boolean isEnable(WebElement element) {
		if (element.isEnabled()) {
			System.out.println("Element is enable");
			return true;
		} else {
			System.out.println("Element is not disable");
			return false;
		}
	}

	// Hàm check in checkbox
	public void checkInCheckbox(WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
	}

	// Hàm uncheck in checkbox
	public void unCheckInCheckbox(WebElement element) {
		if (element.isSelected()) {
			element.click();
		}
	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}