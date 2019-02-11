package prueba;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;


public class Clase2 {
	
	WebDriver driver;
	
	@DataProvider(name = "usuarioYPassword")
	public static Object[][] crearDatos() {
		return new Object[][] { { "testuser_1", "pass123" }, { "testuser_2", "pass123" }};
	  };
  
   @Test(dataProvider = "usuarioYPassword")
  public void pruebaTest(String usuario, String password) {
	  WebElement miCuenta = driver.findElement(By.xpath("//*[@id=\"account\"]/a"));
	  miCuenta.click();
	  WebElement setUsuario = driver.findElement(By.xpath("//*[@id=\"log\"]"));
	  WebElement setPass = driver.findElement(By.xpath("//*[@id=\"pwd\"]"));
	  WebElement login = driver.findElement(By.xpath("//*[@id=\"login\"]"));
	  
	  
	  setUsuario.sendKeys(usuario);
	  setPass.sendKeys(password);
	  login.click();
  };
  
  @BeforeClass
  public void beforeClass() {
	  driver =  new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().deleteAllCookies();
	  driver.get("http://store.demoqa.com/");
  }

  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}
