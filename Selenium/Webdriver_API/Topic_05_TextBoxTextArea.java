package Webdriver_API;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_TextBoxTextArea {
	WebDriver driver;
	String customerID;
	String userName = "mngr26593";
	String passWordLogin = "ishal!12";

	// Input New Customer form
	String customerName = "Anne Lee";
	String gender = "female";
	String dateOfBirth = "1986-05-23";
	String address = "123 Nguyen Van Troi\n Phu Nhuan\n Tan Binh";
	String city = "HCM";
	String state = "Tan Binh";
	String pin = "700000";
	String phone = "0978965431";
	String email = "selenium" + randomNumber() + "@gmail.com";
	String passWord = "123123";

	// Input Edit Customer form

	String editAddress = "123 Nguyen Van Troi";
	String editCity = "HN";
	String editState = "Ha Dong";
	String editPin = "650000";
	String editPhone = "0978960001";
	String editEmail = "selenium" + randomNumber() + "@hotmail.com";

	// Locator for New/ Edit customer form

	By nameTextbox = By.xpath("//input[@name ='name']");
	By genderRadio = By.xpath("//td/input[@value ='f']");
	By dobTextbox = By.xpath("//input[@name ='dob']");
	By addressTextbox = By.xpath("//textarea[@name ='addr']");
	By cityTextbox = By.xpath("//input[@name ='city']");
	By stateTextbox = By.xpath("//input[@name ='state']");
	By pinTextbox = By.xpath("//input[@name ='pinno']");
	By telephoneTextbox = By.xpath("//input[@name ='telephoneno']");
	By emailTextbox = By.xpath("//input[@name ='emailid']");
	By passwordTextbox = By.xpath("//input[@name ='password']");

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//input[@name ='uid']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name ='password']")).sendKeys(passWordLogin);
		driver.findElement(By.xpath("//input[@name ='btnLogin']")).click();
		String wellComeMsg = driver.findElement(By.xpath("//marquee")).getText();
		Assert.assertEquals(wellComeMsg, "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userName + "']")).isDisplayed());
	}

	@Test
	public void TC_01_createNewcustomer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text() ='New Customer']")).click();
		// input value in all fields
		sendkeyToElement(nameTextbox, customerName);
		driver.findElement(genderRadio).click();
		sendkeyToElement(dobTextbox, dateOfBirth);
		sendkeyToElement(addressTextbox, address);
		sendkeyToElement(cityTextbox, city);
		sendkeyToElement(stateTextbox, state);
		sendkeyToElement(pinTextbox, pin);
		sendkeyToElement(telephoneTextbox, phone);
		sendkeyToElement(emailTextbox, email);
		sendkeyToElement(passwordTextbox, passWord);
		driver.findElement(By.xpath("//input[@name ='sub']")).click();

		// verify output data = input data

		Assert.assertEquals(driver.findElement(By.xpath("//p[@class ='heading3']")).getText(), "Customer Registered Successfully!!!");
		Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text() ='Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text() ='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth, driver.findElement(By.xpath("//td[text() ='Birthdate']/following-sibling::td")).getText());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td[contains(text(),'" + address + "')]")).isDisplayed());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text() ='City']/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text() ='State']/following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text() ='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(phone, driver.findElement(By.xpath("//td[text() ='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text() ='Email']/following-sibling::td")).getText());

		customerID = driver.findElement(By.xpath("//td[text() ='Customer ID']//following-sibling::td")).getText();
		System.out.println("Customer ID is: " + customerID);
		Thread.sleep(2000);

	}

	@Test
	public void TC_02_editCustomer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text() ='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name ='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name ='AccSubmit']")).click();
		// Verify customer name before edit
		Assert.assertEquals(customerName, driver.findElement(nameTextbox).getAttribute("value"));
		Assert.assertEquals(gender, driver.findElement(By.xpath("//input[@name= 'gender']")).getAttribute("value"));
		Assert.assertEquals(dateOfBirth, driver.findElement(dobTextbox).getAttribute("value"));

		System.out.println("Name is: " + driver.findElement(nameTextbox).getAttribute("value"));
		System.out.println("Date of birht: " + driver.findElement(dobTextbox).getAttribute("value"));

		// Edit the data and submit
		clearElement(addressTextbox);
		sendkeyToElement(addressTextbox, editAddress);
		clearElement(cityTextbox);
		sendkeyToElement(cityTextbox, editCity);
		clearElement(stateTextbox);
		sendkeyToElement(stateTextbox, editState);
		clearElement(pinTextbox);
		sendkeyToElement(pinTextbox, editPin);
		clearElement(telephoneTextbox);
		sendkeyToElement(telephoneTextbox, editPhone);
		clearElement(emailTextbox);
		sendkeyToElement(emailTextbox, editEmail);
		driver.findElement(By.xpath("//input[@name ='sub']")).click();

		// Verify data output after edit

		Assert.assertTrue(driver.findElement(By.xpath("//p[text() ='Customer details updated Successfully!!!']")).isDisplayed());
		System.out.println("Customer ID is : " + customerID);
		
		Assert.assertEquals(customerID, driver.findElement(By.xpath("//td[text() = 'Customer ID']/following-sibling::td")).getText());
		Assert.assertEquals(editAddress, driver.findElement(By.xpath("//td[text() ='Address']/following-sibling::td")).getText());
		Assert.assertEquals(editCity, driver.findElement(By.xpath("//td[text() = 'City']/following-sibling::td")).getText());
		Assert.assertEquals(editState, driver.findElement(By.xpath("//td[text() = 'State']/following-sibling::td")).getText());
		Assert.assertEquals(editPin, driver.findElement(By.xpath("//td[text() = 'Pin']/following-sibling::td")).getText());
		Assert.assertEquals(editPhone, driver.findElement(By.xpath("//td[text() = 'Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(editEmail, driver.findElement(By.xpath("//td[text() = 'Email']/following-sibling::td")).getText());

		Thread.sleep(2000);

	}

	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);

	}

	public void clearElement(By by) {
		WebElement element = driver.findElement(by);
		element.clear();
	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(1000);
	}

}