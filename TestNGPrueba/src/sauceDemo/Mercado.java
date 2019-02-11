package sauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Mercado {

	public void agregarAlCarrito(WebElement producto) {
		producto.click();		
	}

	public void realizarCompra(WebDriver driver) {
		
		WebElement carrito = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
		carrito.click();								 
		
		WebElement continuar = driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]"));
		continuar.click();		
		
		WebElement input = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/input[1]"));
		input.sendKeys("RiverCampeon");
		
		input = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/input[2]"));
		input.sendKeys("CopaLibertadores");
		
		input = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/input[3]"));	
		input.sendKeys("2018");
		
		continuar = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/input[4]"));
		continuar.click();
		
		continuar = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[2]"));
		continuar.click();
	}

}
