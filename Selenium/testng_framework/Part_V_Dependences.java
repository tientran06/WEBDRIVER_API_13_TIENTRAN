package testng_framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Part_V_Dependences {
	
// groups : chay theo group gom nhom
// priority : set độ ưu tiên cho test case
// enabled : skip (false) / run (true)
// description : testcase meaning
	
  @Test(description = "Create New Customer")
  public void TC_01_createNewCustomer(){
	  System.out.println("Create New Customer");
	  Assert.assertTrue(false);
  }
  
  @Test(description = "Create New Account")
  public void TC_02_createNewAccount(){
	  System.out.println("Create New Account");
	  Assert.assertTrue(false);
  }
  @Test(description = "Edit Customer", dependsOnMethods = "TC_01_createNewCustomer")
  public void TC_03_editCustomer(){
	  System.out.println("Edit Customer");
  }
  @Test(description = "Edit Account", dependsOnMethods = "TC_02_createNewAccount")
  public void TC_04_editAccount(){
	  System.out.println("Edit Account");
  }
  @Test(description = "Delete Customer", dependsOnMethods = "TC_01_createNewCustomer")
  public void TC_05_deleteCustomer(){
	  System.out.println("Delete Customer");
  }
  @Test(description = "Delete Account", dependsOnMethods = "TC_02_createNewAccount")
  public void TC_06_deleteAccount(){
	  System.out.println("Delete Account");
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
