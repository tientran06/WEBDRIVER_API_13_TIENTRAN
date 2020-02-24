package Webdriver_API;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_11_Wait_Part_III_FluentWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\Libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
		fluentDriver = new FluentWait<WebDriver>(driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Fluent_Driver() {
		driver.get("http://demo.guru99.com/");

		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
		.pollingEvery(200, TimeUnit.MILLISECONDS)
		.ignoring(NoSuchElementException.class);

		WebElement submitButton = (WebElement) fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				System.out.println(new Date());
				return driver.findElement(By.xpath("//input[@type ='automation']"));
			}

		});

		submitButton.click();
	}

	//@Test
	public void TC_02_Fluent_Element() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement countDown = driver.findElement(By.xpath("//div[@id ='javascript_countdown_time']"));
		explicitWait.until(ExpectedConditions.visibilityOf(countDown));

		fluentElement = new FluentWait<WebElement>(countDown);

		fluentElement.withTimeout(15, TimeUnit.SECONDS)
		.pollingEvery(200, TimeUnit.MILLISECONDS)
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				boolean flag = element.getText().endsWith("02");
				System.out.println("Time = " +element.getText());
				return flag;
			}
		});

	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}