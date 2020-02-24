package Webdriver_API;

import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Element {
	WebDriver driver;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_FindElement() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

		// <input id="email"
		// class="input-text required-entry validate-email"
		// type="email"
		// title="Email Address" value=""
		// name="login[username]"
		// spellcheck="false"
		// autocorrect="off"
		// autocapitalize="off">

		// ID | NAME | CLASSNAME | LINKTEXT | PARTIAL LINKTEXT | TAGNAME | XPATH | CSS
		// SELECTOR

		// ID
		driver.findElement(By.id("email")).sendKeys("idtest@gmail.com");
		// NAME
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		driver.findElement(By.name("send")).click();
		// CLASSNAME
		driver.findElement(By.className("validate-email")).clear();
		driver.findElement(By.className("validate-email")).sendKeys("classname@gmail.com");
		// LINKTEXT
		driver.findElement(By.linkText("ORDERS AND RETURNS")).click();
		// PARTIAL LINKTEX
		driver.findElement(By.partialLinkText("ORDERS AND")).click();
		// TAGNAME
		// tìm xem có bao nhiêu đường link o page này và in nó ra

		List<WebElement> links = driver.findElements(By.tagName("a"));
		int linkNumber = links.size();
		System.out.println("Tổng số link :" + linkNumber);
		for (WebElement link : links) {
			System.out.println("Value :" + link.getAttribute("href"));
		}
		// CSS SELECTOR
		driver.findElement(By.cssSelector("#oar_order_id")).sendKeys("C456784");
		driver.findElement(By.cssSelector(".input-text.required-entry")).sendKeys("Nguyen Nam");
		driver.findElement(By.cssSelector("input[id='oar_email']")).sendKeys("namnguyen@gmail.com");
		// size : đếm tổng số
		System.out.println("tổng số link" + driver.findElements(By.cssSelector("a")).size());
		// driver.findElement(By.cssSelector("a[href='http://live.demoguru99.com/index.php/sales/guest/form/']")).click();

		// XPATH
		driver.findElement(By.xpath("//input[@id='oar_order_id']")).sendKeys("B3000000");
		driver.findElement(By.xpath("//input[@class ='input-text required-entry']")).sendKeys("Nguyen Nam");
		driver.findElement(By.xpath("//input[@id='oar_email']")).sendKeys("namnguyen@gmail.com");
		// size : đếm tổng số
		System.out.println("tổng số link" + driver.findElements(By.xpath("a")).size());
		driver.findElement(By.xpath("//a[@href='http://live.demoguru99.com/index.php/sales/guest/form/']")).click();

	}

	// Post-Condition
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}