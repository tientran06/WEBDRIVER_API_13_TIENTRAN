package Webdriver_API;

//import static org.junit.Assert.assertTrue;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.server.handler.ClearElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import net.jcip.annotations.ThreadSafe;

public class Topic_04_WebElement {
	WebDriver driver;
	By emailTextboxBy = By.id("mail");
	By passWordTextboxBy = By.id("password");
	By ageUnder18RadioBy = By.id("under_18");
	By ageRadioButtonBy = By.id("radio-disabled");
	By educationTextAreaBy = By.id("edu");
	By biographyBy = By.id("bio");
	By jobRole1By = By.id("job1");
	By jobRole3By = By.id("job3");
	By interests1By = By.id("development");
	By interests2By = By.id("check-disbaled");
	By slider1By = By.id("slider-1");
	By slider2By = By.id("slider-2");

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");
		

	}

	@Test
	public void TC_01_ElementDisplayed() throws InterruptedException {

		// Verify Email | Age (under 18) | Education is displayed and input the information

		WebElement emailTextbox = driver.findElement(emailTextboxBy);
		if (emailTextbox.isDisplayed()) {
			emailTextbox.clear();
			emailTextbox.sendKeys("automationtestting@gmail.com");
		}

		WebElement ageUnder18Radio = driver.findElement(ageUnder18RadioBy);
		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
		}

		WebElement educationTextArea = driver.findElement(educationTextAreaBy);
		if (educationTextArea.isDisplayed()) {
			educationTextArea.clear();
			educationTextArea.sendKeys("Automation Testing");
		}
		Thread.sleep(5000);
	}

	@Test
	public void TC_02_ElementEnabled_Disabled() {

		// Verify Element is Enabled | Disabled
		WebElement emailTextbox = driver.findElement(emailTextboxBy);
		if (emailTextbox.isEnabled()) {
			System.out.println("Element [" + emailTextboxBy + "] is enable");

		} else {
			System.out.println("Element [" + emailTextboxBy + "] is disable");
		}
		WebElement passWordTextbox = driver.findElement(passWordTextboxBy);

		if (passWordTextbox.isEnabled()) {
			System.out.println("Element [" + passWordTextboxBy + "] is enable");

		} else {
			System.out.println("Element [" + passWordTextboxBy + "] is disable");
		}

		WebElement ageUnder18Radio = driver.findElement(ageUnder18RadioBy);

		if (ageUnder18Radio.isEnabled()) {
			System.out.println("Element [" + ageUnder18RadioBy + "] is enable");

		} else {
			System.out.println("Element [" + ageUnder18RadioBy + "] is disable");
		}
		WebElement ageRadiButton = driver.findElement(ageRadioButtonBy);

		if (ageRadiButton.isEnabled()) {
			System.out.println("Element [" + ageRadioButtonBy + "] is enable");

		} else {
			System.out.println("Element [" + ageRadioButtonBy + "] is disable");
		}
		WebElement educationTextArea = driver.findElement(educationTextAreaBy);

		if (educationTextArea.isEnabled()) {
			System.out.println("Element [" + educationTextAreaBy + "] is enable");

		} else {
			System.out.println("Element [" + educationTextAreaBy + "] is disable");
		}
		WebElement biography = driver.findElement(biographyBy);

		if (biography.isEnabled()) {
			System.out.println("Element [" + biographyBy + "] is enable");

		} else {
			System.out.println("Element [" + biographyBy + "] is disable");
		}
		WebElement jobRole1 = driver.findElement(jobRole1By);

		if (jobRole1.isEnabled()) {
			System.out.println("Element [" + jobRole1By + "] is enable");

		} else {
			System.out.println("Element [" + jobRole1By + "] is disable");
		}
		WebElement jobRole2 = driver.findElement(jobRole3By);

		if (jobRole2.isEnabled()) {
			System.out.println("Element [" + jobRole3By + "] is enable");

		} else {
			System.out.println("Element [" + jobRole3By + "] is disable");
		}
		WebElement interests1 = driver.findElement(interests1By);

		if (interests1.isEnabled()) {
			System.out.println("Element [" + interests1By + "] is enable");

		} else {
			System.out.println("Element [" + interests1By + "] is disable");
		}
		WebElement interests2 = driver.findElement(interests2By);

		if (interests2.isEnabled()) {
			System.out.println("Element [" + interests2By + "] is enable");

		} else {
			System.out.println("Element [" + interests2By + "] is disable");
		}
		WebElement slider2 = driver.findElement(slider2By);

		if (slider2.isEnabled()) {
			System.out.println("Element [" + slider2By + "] is enable");

		} else {
			System.out.println("Element [" + slider2By + "] is disable");
		}
		WebElement slider1 = driver.findElement(slider1By);

		if (slider1.isEnabled()) {
			System.out.println("Element [" + slider1By + "] is enable");

		} else {
			System.out.println("Element [" + slider1By + "] is disable");
		}
	}

	@Test
	public void TC_03_ElementSelected() throws InterruptedException {
		// Step 01 - select Radio Age_Under18 and Development checkbox
		WebElement ageUnder18Radio = driver.findElement(ageUnder18RadioBy);
		ageUnder18Radio.click();
		WebElement interests1 = driver.findElement(interests1By);
		interests1.click();
		
		// Step 02 - Verify elements of Step 01 is selected
		Assert.assertTrue(ageUnder18Radio.isSelected());
		Assert.assertTrue(interests1.isSelected());

		// Step 03 - bỏ chọn Development checkbox
		interests1.click();
		Assert.assertFalse(interests1.isSelected());
		}
	
		
	@Test
	public void TC_04_ElementDisplayed_2() throws InterruptedException {

		// Verify Email | Age (under 18) | Education is displayed and input the information
		
		if (isElementDisplayed(emailTextboxBy)) {
			ClearElement(emailTextboxBy);
			sendkeyToElement(emailTextboxBy, "automationtestting@gmail.com");
		}

		
		if (isElementDisplayed(ageUnder18RadioBy)) {
			driver.findElement(ageUnder18RadioBy).click();
		}

		if (isElementDisplayed(educationTextAreaBy)) {
			ClearElement(educationTextAreaBy);
			sendkeyToElement(educationTextAreaBy, "Automation Testing");
		}
		Thread.sleep(5000);
	}
	@Test
	public void TC_05_ElementEnabled_Disabled_2() {
		System.out.println("Result of TC_05 is: ");
		Assert.assertTrue(isElementEnabled(emailTextboxBy));
		Assert.assertTrue(isElementEnabled(ageUnder18RadioBy));
		Assert.assertTrue(isElementEnabled(educationTextAreaBy));
		Assert.assertTrue(isElementEnabled(jobRole1By));
		Assert.assertTrue(isElementEnabled(interests1By));
		Assert.assertTrue(isElementEnabled(slider1By));
		Assert.assertFalse(isElementEnabled(biographyBy));
		Assert.assertFalse(isElementEnabled(passWordTextboxBy));
		Assert.assertFalse(isElementEnabled(ageRadioButtonBy));
		Assert.assertFalse(isElementEnabled(jobRole3By));
		Assert.assertFalse(isElementEnabled(interests2By));
		Assert.assertFalse(isElementEnabled(slider2By));
		
	}
	
	@Test
	public void TC_06_ElementisSelected_2() {
		driver.navigate().refresh();
		//Verify Element is selected
		driver.findElement(ageUnder18RadioBy).click();
		Assert.assertTrue(isElementSelected(ageUnder18RadioBy));
		driver.findElement(interests1By).click();
		Assert.assertTrue(isElementSelected(interests1By));
		
		//Verify Element is Unselected
		driver.findElement(interests1By).click();
		Assert.assertFalse(isElementSelected(interests1By));
		
	}
	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	

	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}
	
	public void ClearElement(By by) {
		WebElement element = driver.findElement(by);
		element.clear();

	}
	
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "] is enable");
			return true;
			
		}else {
			System.out.println("Element [" + by + "] is disable");
			return false;
		}

	}
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
	if (element.isSelected()) {
		return true;
		
	}else {
		return false;
	}

	}
	
	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}