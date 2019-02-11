package MercuryTours;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {	

////////// INGRESA AL SITIO CON EL USUARIO Y PASS INDICADOS, SOBRE EL NAVEGADOR QUE SE DA
	@Test
	public void realizarLogin(String usuario, String password, WebDriver driver) throws IOException {
		WebElement input;
		input = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/input"));
		input.sendKeys("mercury");
		input = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[4]/td/table/tbody/tr[3]/td[2]/input"));
		input.sendKeys("mercury");
		input = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[4]/td/table/tbody/tr[4]/td[2]/div/input"));
		input.click();
	}
	
////////// RETORNA UN BOOL INDICANDO SI INGRESO CORRECTAMENTE
	public boolean seRealizoCorrectamente(WebDriver driver) {
		boolean ret = false;
		if (driver.getTitle().equalsIgnoreCase("Find a Flight: Mercury Tours:")){
			ret = true;
		}
		return ret;
	}
}
