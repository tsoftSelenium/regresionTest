package sauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOutSauce {

	public void deslogearse(WebDriver driver) throws InterruptedException {
		WebElement botonMenu = driver.findElement(By.xpath("//*[@id=\"menu_button_container\"]/div/div[3]/div/button"));
		botonMenu.click();
		WebElement logout = (new WebDriverWait(driver, 10)) .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"logout_sidebar_link\"]")));
		logout.click();
	}
	
	public boolean seRealizoCorrectamente(WebDriver driver) {
		boolean ret = false;
		if (driver.getTitle().equalsIgnoreCase("Swag Labs")){
			ret = true;
		}
		return ret;
	}

}
