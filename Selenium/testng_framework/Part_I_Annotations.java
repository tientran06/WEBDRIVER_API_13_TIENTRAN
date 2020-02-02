package testng_framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Part_I_Annotations {
	
// groups : chay theo group gom nhom
// priority : set độ ưu tiên cho test case
// enabled : skip (false) / run (true)
// description : testcase meaning
	
  @Test(groups = "customer", priority = 3, enabled = true, description = "Create New Customer")
  public void TC_01_createNewCustomer(){
	  System.out.println("Test case 01");
  }
  
  @Test(priority = 1)
  public void TC_02(){
	  System.out.println("Test case 02");
  }
  @Test(groups = "customer", priority = 2, enabled = true, description = "Create New Account")
  public void TC_03_createNewAccount(){
	  System.out.println("Test case 03");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("run beforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("run afterMethod");
  }


  @BeforeClass(alwaysRun = true)
  public void beforeClass() {
	  System.out.println("run beforeClass");
  }

  @AfterClass(alwaysRun = true)
  public void afterClass() {
	  System.out.println("run afterClass");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("run beforeTest");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("run afterTest");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("run beforeSuite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("run afterSuite");
  }

}
