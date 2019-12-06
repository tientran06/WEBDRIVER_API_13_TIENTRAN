package Webdriver_API;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_DropdownList {
	WebDriver driver;
	WebDriverWait waitExplicit;
	Actions action;
	Select select;
	JavascriptExecutor javascript;
	By numberAllItems = By.xpath("//ul[@id ='number-menu']/li");

	// Input data for TC02
	String firstName = "Automation";
	String lastName = "Testing";
	String email = "automationtesting" + randomNumber() + "@gmail.com";
	String companyName = "Automation Testing Ltd";
	String passWord = "123456";
	String confirmPassWord = "123456";

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascript = (JavascriptExecutor) driver;
		waitExplicit = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_defaultDropDownList() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.xpath("//select[@id ='job1']")));

		// Verify that JobRole 1 is not multiple
		System.out.println("value is:" + select.isMultiple());
		Assert.assertFalse(select.isMultiple());

		// Choose Mobile testing vs verify it
		select.selectByVisibleText("Mobile Testing");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
		Thread.sleep(2000);

		// Choose Manual testing vs verify it
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
		Thread.sleep(2000);

		// Choose Functional UI testing vs verify it
		select.selectByIndex(9);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
		Thread.sleep(2000);

		// Verify that JobRole 1 have 10 option
		List<WebElement> jobRole1 = select.getOptions();
		for (WebElement list : jobRole1) {
			System.out.println(list.getText());

		}
		int jobRole1Total = jobRole1.size();
		System.out.println("JobRole 1 have:" + jobRole1Total + " options");
		Assert.assertEquals(jobRole1Total, 10);

		// Verify Job Role 2 - Multiple dropdown có hỗ trợ multiple select
		select = new Select(driver.findElement(By.xpath("//select[@id ='job2']")));
		Assert.assertTrue(select.isMultiple());
		System.out.println("Value is " + select.isMultiple());

		// Choosse multi value
		select.selectByVisibleText("Automation");
		Thread.sleep(2000);
		select.selectByVisibleText("Mobile");
		Thread.sleep(2000);
		select.selectByVisibleText("Desktop");
		Thread.sleep(2000);
		select.selectByVisibleText("Security");
		Thread.sleep(2000);

		// Kiểm tra các optioned đã dc chọn // chỗ này chưa hiểm lắm
		List<WebElement> optionSelected = select.getAllSelectedOptions();
		List<String> arraySelected = new ArrayList<String>();
		for (WebElement select : optionSelected) {
			System.out.println(select.getText());
			arraySelected.add(select.getText());

		}
		Assert.assertTrue(arraySelected.contains("Automation"));
		Assert.assertTrue(arraySelected.contains("Mobile"));
		Assert.assertTrue(arraySelected.contains("Desktop"));
		Assert.assertTrue(arraySelected.contains("Security"));

		// bỏ chọn all , verify các phần tử đã dc bỏ chọn

		select.deselectAll();
		List<WebElement> optionedUnselected = select.getAllSelectedOptions();
		List<String> arrayUnselected = new ArrayList<String>();

		for (WebElement select : optionedUnselected) {
			// System.out.println(select.getText());
			arrayUnselected.add(select.getText());

		}
		Assert.assertFalse(arrayUnselected.contains("Automation"));
		Assert.assertFalse(arrayUnselected.contains("Mobile"));
		Assert.assertFalse(arrayUnselected.contains("Desktop"));
		Assert.assertFalse(arrayUnselected.contains("Security"));
	}

	@Test
	public void TC_02_defaultDropDownList2() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[text() ='Register']")).click();
		driver.findElement(By.xpath("//input[@name ='Gender' and @value ='F']")).click();

		// input information in all fields
		driver.findElement(By.xpath("//input[@id ='FirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id ='LastName']")).sendKeys(lastName);

		// Choose Day = 5 and verify total items = 32
		Select dateOfBirthDay = new Select(driver.findElement(By.xpath("//select[@name ='DateOfBirthDay']")));
		dateOfBirthDay.selectByVisibleText("5");

		Assert.assertEquals(dateOfBirthDay.getFirstSelectedOption().getText(), "5");
		int totalDayItems = dateOfBirthDay.getOptions().size();
		System.out.println("Tổng số items của ngày : " + totalDayItems);
		Assert.assertEquals(totalDayItems, 32);

		// Choose Month = June and Verify total items = 12
		Select dateOfBirthMonth = new Select(driver.findElement(By.xpath("//select[@name ='DateOfBirthMonth']")));
		dateOfBirthMonth.selectByVisibleText("June");

		Assert.assertEquals(dateOfBirthMonth.getFirstSelectedOption().getText(), "June");

		int totalMonthItems = dateOfBirthMonth.getOptions().size();
		System.out.println("Tổng số items of tháng :" + totalMonthItems);
		Assert.assertEquals(totalMonthItems, 13);

		// Choose Year = 1982 and verify total items = 112
		Select dateOfBirthYear = new Select(driver.findElement(By.xpath("//select[@name ='DateOfBirthYear']")));
		dateOfBirthYear.selectByVisibleText("1982");
		Assert.assertEquals(dateOfBirthYear.getFirstSelectedOption().getText(), "1982");

		int totalYearItems = dateOfBirthYear.getOptions().size();
		System.out.println("Tổng số items của năm: " + totalYearItems);
		Assert.assertEquals(totalYearItems, 112);

		driver.findElement(By.xpath("//input[@id ='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id ='Company']")).sendKeys(companyName);
		driver.findElement(By.xpath("//input[@id ='Password']")).sendKeys(passWord);
		driver.findElement(By.xpath("//input[@id ='ConfirmPassword']")).sendKeys(confirmPassWord);

		// In ra information đăng kí
		System.out.println("Họ Tên: " + firstName + " " + lastName);
		System.out.println("DOB " + dateOfBirthDay.getFirstSelectedOption().getText() + "/" + dateOfBirthMonth.getFirstSelectedOption().getText() + "/" + dateOfBirthYear.getFirstSelectedOption().getText());
		System.out.println("Email đăng kí: " + email);

		driver.findElement(By.xpath("//input[@id ='register-button']")).submit();

		// Verify Registration succesfully

		Assert.assertTrue(driver.findElement(By.xpath("//div[text() = 'Your registration completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class ='ico-account' and text() ='My account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class = 'ico-logout' and text() = 'Log out']")).isDisplayed());

	}

	@Test
	public void TC_03_dropDownListJQuery() {

		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		// 1 - Click vào thẻ chứa dropdown để nó xổ ra hết tất cả item
		driver.findElement(By.xpath("//span[@id = 'number-button']")).click();

		// 2 - Khai báo 1 List WebElement chứa all các items bên trong
		List<WebElement> allItems = driver.findElements(numberAllItems);

		// 3 - Wait cho tất cả item (List WebElement) được xuất hiện dưới DOM
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(numberAllItems));

		// 4 - Get text từng item sau đó so sánh vs item mình cần chọn
		for (WebElement item : allItems) {
			System.out.println("Text =" + item.getText());

			// 5 - Kiểm tra item nào đúng vs cái mình cần chọn thì click vào
			if (item.getText().equals("8")) {
				item.click();
				break;
			}
		}

		// 6 - Kiểm tra Item đã dc chọn thành công

		Assert.assertTrue(driver.findElement(By.xpath("//span[@id ='number-button']/span[@class ='ui-selectmenu-text' and text()='8']")).isDisplayed());

	}

	@Test
	public void TC_03_JQuery2() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		// chon Item 19
		selectCustomDropdownList("//span[@id = 'number-button']", "//ul[@id = 'number-menu']/li", "19");

		// 6 - Kiểm tra Item đã dc chọn thành công
		Assert.assertTrue(isDisplayed("//span[@id ='number-button']/span[@class ='ui-selectmenu-text' and text()='19']"));
		
	}

	@Test
	public void TC_04_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectCustomDropdownList("//div[@role = 'listbox']", "//div[@role ='option']", "Christian");
		Assert.assertTrue(isDisplayed("//div[@role ='listbox']/div[text() ='Christian']"));
		
	}

	// Editable Dropdown
	@Test
	public void TC_05_ReactJSEditable() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		// Input Algeria
		inputItemInCustomDropdown("//i[@class = 'dropdown icon']", "//input[@class = 'search']", "Algeria");
		System.out.println("text = " + driver.findElement(By.xpath("//div[@role ='alert' and text()= 'Algeria']")).getText());
		Assert.assertTrue(isDisplayed("//div[@role ='alert' and text()= 'Algeria']"));
		Thread.sleep(3000);
	}
	@Test
	public void TC_05_JQueryEditable() throws InterruptedException {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		inputItemInCustomDropdown("//div[@id ='default-place']/input", "//div[@id ='default-place']/input", "Alfa Romeo");
		Assert.assertTrue(isDisplayed("//ul[@class ='es-list']/li[@class = 'es-visible selected' and text() ='Alfa Romeo']"));
		
	}
	@Test
	public void TC_06_Angular() throws Exception {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		
		selectCustomDropdownList("// div[@id ='local']//span[contains(@class,'e-search-icon')]", "//ul[@id ='games_options']/li", "Basketball");
		String dropDownText = getTextbyJS("#games_hidden > option");
		Assert.assertEquals(dropDownText, "Basketball");
		Thread.sleep(3000);
		

	}	
	
	public void selectCustomDropdownList(String parentXpath, String allItemsXpath, String expectedText) {

		// 1 - Click vào thẻ chứa dropdown để nó xổ ra hết tất cả item
		driver.findElement(By.xpath(parentXpath)).click();

		// 2 - Khai báo 1 List WebElement chứa all các items bên trong
		List<WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));

		// 3 - Wait cho tất cả item (List WebElement) được xuất hiện dưới DOM
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));

		// 4 - Get text từng item sau đó so sánh vs item mình cần chọn
		for (WebElement item : allItems) {
			System.out.println("Text =" + item.getText());

			// 5 - Kiểm tra item nào đúng vs cái mình cần chọn thì click vào
			if (item.getText().equals(expectedText)) {
				item.click();
				break;
			}
		}

	}

	public void inputItemInCustomDropdown(String parentXpath, String inputXpath, String expectedText) {
		// 1 - Click vao the chua Dropdown de no xo xuong
		driver.findElement(By.xpath(parentXpath)).click();

		// 2 - input Value vao o search
		driver.findElement(By.xpath(inputXpath)).sendKeys(expectedText);

		// 3 - Truyen phim ENTER vao sau khi input
		action.sendKeys(driver.findElement(By.xpath(inputXpath)), Keys.ENTER).perform();
	}

	public String getTextbyJS(String locator) {
		return (String) javascript.executeScript("return document.querySelector('" + locator + "').text");
		
	}

	public boolean isDisplayed(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(100);
	}
}