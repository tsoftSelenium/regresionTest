package sauceDemo;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SauceLogin {

//////////INGRESA AL SITIO CON EL USUARIO Y PASS INDICADOS, SOBRE EL NAVEGADOR QUE SE DA
	@Test
	public void realizarLogin(String usuario, String password, WebDriver driver) {
		WebElement input= driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
		input.sendKeys(usuario);
		input = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		input.sendKeys(password);
		
		WebElement login = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/input[3]"));
		login.click();									
	}
	
//////////RETORNA UN BOOL INDICANDO SI INGRESO CORRECTAMENTE
	public boolean seRealizoCorrectamente(WebDriver driver) {
		boolean ret = false;
		if (driver.getTitle().equalsIgnoreCase("Swag Labs")){
			ret = true;
		}
		return ret;
	}

}
