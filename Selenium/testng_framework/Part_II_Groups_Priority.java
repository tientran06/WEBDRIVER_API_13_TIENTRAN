package testng_framework;

import org.testng.annotations.Test;

public class Part_II_Groups_Priority {
  @Test(groups = "shopping",priority = 2,enabled = false)
  public void TC_04() {
	  System.out.println("Test case 04");
  }
  @Test()
  public void TC_05() {
	  System.out.println("Test case 05");
  }
  @Test(groups = "buying", priority = 1, enabled = true)
  public void TC_06() {
	  System.out.println("Test case 06");
  }
}
