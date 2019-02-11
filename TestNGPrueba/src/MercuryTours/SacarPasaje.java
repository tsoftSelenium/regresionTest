package MercuryTours;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SacarPasaje {

	@Test
	public void sacarPasaje(WebDriver driver,int cantDePasajeros,String desde, String hacia, int diaIda, int mesIda, int diaVuelta, int mesVuelta, int aerolinea,int tipoDeClase) {
		
////////// CREA LOS SELECTS		
		Select selectPasajeros = new Select(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[3]/td[2]/b/select")));
		Select selectDepart = new Select(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td[2]/select")));
		Select selectMes= new Select(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[5]/td[2]/select[1]")));
		Select selectDia= new Select(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[5]/td[2]/select[2]")));
		Select selectArriving= new Select(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[6]/td[2]/select")));
		Select selectMesVuelta= new Select(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td[2]/select[1]")));
		Select selectDiaVuelta= new Select(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td[2]/select[2]")));
		Select selectAerolinea= new Select(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[10]/td[2]/select")));

////////// LE PONE EL VALOR A LOS SELECTS 		
		selectPasajeros.selectByValue(new String(""+cantDePasajeros));
		selectDepart.selectByValue(desde);;
		selectMes.selectByIndex(mesIda-1);
		selectDia.selectByIndex(diaIda-1);
		selectArriving.selectByValue(hacia);
		selectMesVuelta.selectByIndex(mesVuelta-1);
		selectDiaVuelta.selectByIndex(diaVuelta-1);
		selectAerolinea.selectByIndex(aerolinea);

/////////  HACE CLICK EN EL TIPO DE CLASE DEL VUELO		
		WebElement elemento;
		elemento = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/font/font/input["+tipoDeClase+"]"));
		elemento.click();					

///////// CLICK EN SIGUIENTE
		WebElement siguiente= driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[14]/td/input"));
		siguiente.click();
	}

////////// RETORNA EL STRING VUELO DE IDA "X" TO "Y"	
	public String vueloIda(WebDriver driver) {
		WebElement elemento = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]/tbody/tr[1]/td/table/tbody/tr[2]/td[1]/b/font"));
		return elemento.getText();
		
	}

////////// RETORNA EL STRING DEL VUELO DE VUELTA "Y" TO "X"
	public String vueloVuelta(WebDriver driver) {
		WebElement elemento = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td[1]/b/font"));
		return elemento.getText();
	}

}
