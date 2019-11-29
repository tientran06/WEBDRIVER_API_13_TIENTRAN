package Webdriver_API;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_DropdownList1 {
	WebDriver driver;
	Select select;

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