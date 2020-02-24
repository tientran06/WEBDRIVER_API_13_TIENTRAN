package Webdriver_API;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_JavascriptExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;

	// Khai báo biến

	String userName = "mngr238966";
	String passWordLogin = "emYmEqe";

	String firstName = "Selenium";
	String lastName = "Automation";

	// Input New Customer form
	String customerName = "Anne Lee";
	String gender = "female";
	String dateOfBirth = "1987-05-06";
	String address = "123 Nguyen Van Troi";
	String city = "HCM";
	String state = "Tan Binh";
	String pin = "700000";
	String phone = "0978965431";
	String email = "selenium" + randomNumber() + "@gmail.com";
	String passWord = "123123";

	// Locator for New customer form

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
		// driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();

		// Set driver for JE lib
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_JS() throws Exception {
		navigateToUrlByJS("http://live.guru99.com/");

		// Get ra Domain vs Verify
		String liveDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveDomain, "live.demoguru99.com");

		// Get ra URL vs verify
		String pageUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(pageUrl, "http://live.demoguru99.com/");

		// click to Mobile
		highlightElement("//a[text() ='Mobile']");
		clickToElementByJS("//a[text() ='Mobile']");

		highlightElement("//a[text() ='Samsung Galaxy']/parent::h2//following-sibling::div[@class ='actions']//button");
		clickToElementByJS("//a[text() ='Samsung Galaxy']/parent::h2//following-sibling::div[@class ='actions']//button");

		String pageInnerText = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(pageInnerText.contains("Samsung Galaxy was added to your shopping cart."));

		// Click Customer Service and verify
		highlightElement("//a[text() ='Customer Service']");
		clickToElementByJS("//a[text() ='Customer Service']");

		verifyTextInInnerText("CUSTOMER SERVICE");
		String customerTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(customerTitle, "Customer Service");

		// Scroll to New letter
		scrollToElement("//input[@id='newsletter']");
		Thread.sleep(3000);

		pageInnerText = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(pageInnerText.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));

		// Navigate toi Domain http://demo.guru99.com/v4/
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		liveDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveDomain, "demo.guru99.com");
		Thread.sleep(2000);

	}

	// @Test
	public void TC_02_removeAttribute() throws Exception {
		navigateToUrlByJS("http://demo.guru99.com/v4");
		sendkeyToElementByJS("//input[@name ='uid']", userName);
		sendkeyToElementByJS("//input[@name ='password']", passWordLogin);
		clickToElementByJS("//input[@name ='btnLogin']");

		String wellComeMsg = driver.findElement(By.xpath("//marquee")).getText();
		Assert.assertEquals(wellComeMsg, "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userName + "']")).isDisplayed());

		// Click menu New Customer and input all fields
		clickToElementByJS("//a[text() ='New Customer']");

		sendkeyToElement(nameTextbox, customerName);
		driver.findElement(genderRadio).click();

		removeAttributeInDOM("//input[@name ='dob']", "type");
		sendkeyToElement(dobTextbox, dateOfBirth);

		sendkeyToElement(addressTextbox, address);
		sendkeyToElement(cityTextbox, city);
		sendkeyToElement(stateTextbox, state);
		sendkeyToElement(pinTextbox, pin);
		sendkeyToElement(telephoneTextbox, phone);
		sendkeyToElement(emailTextbox, email);
		sendkeyToElement(passwordTextbox, passWord);

		Thread.sleep(5000);
		highlightElement("//input[@type ='submit']");
		// driver.findElement(By.xpath("//input[@type ='submit']")).click();

		clickToElementByJS("//input[@type ='submit']");

		// verify output data = input data

		verifyTextInInnerText("Customer Registered Successfully!!!");
		System.out.println("Customer ID: " + driver.findElement(By.xpath("//td[text() ='Customer ID']/following-sibling::td")).getText());
		verifyTextInInnerText("" + customerName + "");

		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text() ='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth, driver.findElement(By.xpath("//td[text() ='Birthdate']/following-sibling::td")).getText());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td[contains(text(),'" + address + "')]")).isDisplayed());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text() ='City']/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text() ='State']/following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text() ='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(phone, driver.findElement(By.xpath("//td[text() ='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text() ='Email']/following-sibling::td")).getText());

		Thread.sleep(2000);

	}

	@Test
	public void TC_03_createNewAccount() throws Exception {
		navigateToUrlByJS("http://live.guru99.com/");
		// Click to My Account

		clickToElementByJS("//div[@id ='header-account']//a[text() ='My Account']");

		highlightElement("//a[@title ='Create an Account']");
		clickToElementByJS("//a[@title ='Create an Account']");

		// input data in all fields
		sendkeyToElementByJS("//input[@id='firstname']", firstName);
		sendkeyToElementByJS("//input[@id='lastname']", lastName);
		sendkeyToElementByJS("//input[@id='email_address']", email);
		sendkeyToElementByJS("//input[@id='password']", passWord);
		sendkeyToElementByJS("//input[@id='confirmation']", passWord);

		highlightElement("//button[@title = 'Register']");
		clickToElementByJS("//button[@title = 'Register']");
		Thread.sleep(2000);

		// Verify đăng kí thành công
		String innerText = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(innerText.contains("Thank you for registering with Main Website Store."));
		System.out.println("Contact Information :\n" + driver.findElement(By.xpath("//h3[text() ='Contact Information']/parent::div//following-sibling::div[@class ='box-content']//p")).getText());
		Assert.assertTrue(innerText.contains("" + firstName + " " + lastName + ""));
		Assert.assertTrue(innerText.contains("" + email + ""));

	}

	// Browser
	public Object executeForBrowser(String javaSript) {
		return jsExecutor.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	// Element
	public void highlightElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);

	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Random
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(1000);
	}
}
