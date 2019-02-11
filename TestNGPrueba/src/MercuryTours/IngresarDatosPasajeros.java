package MercuryTours;

import java.util.ArrayList;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IngresarDatosPasajeros {
	
////////// DEPENDIENDO LA CANT DE PASAJEROS HACE UN FOR CON UN ARRAY LST DE PASAJEROS Y UN NAVEGADOR Y LOS VA AGREGANDO A LA LISTA 
////////// DE PASAJEROS EN LOS CAMPOS NOMBRE Y APELLIDO
	@Test
	public void ingresarPasajeros(int cantDePasajeros, ArrayList<String> pasajeros, WebDriver driver) {
		WebElement nombre;
		WebElement apellido;
		for(int i=0;i< cantDePasajeros;i++) {
			i = i + 4;
			 nombre = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr["+i+"]/td/table/tbody/tr[2]/td[1]/input"));
			 apellido = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr["+i+"]/td/table/tbody/tr[2]/td[2]/input"));
			 i = i - 4;
			 
			 nombre.sendKeys(pasajeros.get(0));
			 pasajeros.remove(0);
			 apellido.sendKeys(pasajeros.get(0));
			 pasajeros.remove(0);
		}
		
///////////////// CLICK EN SECURE PURCHASE				
		WebElement siguiente = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr["+(22+cantDePasajeros)+"]/td/input"));
		siguiente.click();
	}

}
