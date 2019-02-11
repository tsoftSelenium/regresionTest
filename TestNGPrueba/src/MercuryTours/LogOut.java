package MercuryTours;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogOut {

///////////////// DESLOGUEA DEL SITIO 
	@Test
	public void deslogearse(WebDriver driver) {
		WebElement logout = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]/a"));
		logout.click();
	}

///////////////// RETORNA UN BOOL INDICANDO SI SE DESLOGUEO
	public boolean seRealizoCorrectamente(WebDriver driver) {
		boolean ret = false;
		if (driver.getTitle().equalsIgnoreCase("Sign-On: Mercury Tours")) {
			ret=true;
		}
		return ret;
	}

}
