package prueba;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CopiaNewTest {
	WebDriver driver;
	WebElement elemento;
	
  @Test
  public void f() {
	  elemento = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a"));
	  elemento.click();
  }
  @BeforeClass
  public void beforeClass() {
	  driver =  new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().deleteAllCookies();
	  driver.get(" http://newtours.demoaut.com/");
  }

  @AfterClass
  public void afterClass() {
	  driver.close();
	  
  }
  
  @Test (priority = 1)
  public void uno() {
	  System.out.println("1");
  }
  
  @Test (priority = 0)
  public void dos() {
	  System.out.println("2");
	  
  }
  
  @Test (dependsOnMethods = "uno", priority = 0)
  	public void tres() {
	  System.out.println("3");
	  
  }

}
