package MercuryToursTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import MercuryTours.MercuryToursTest;
import sauceDemo.SauceDemo;

public class Regresion {
		ExtentTest test;
		static ExtentReports report;
		
		
@BeforeClass
public void setUp() {
/////////////// CREA EL RREPORTE Y EL TEST
	String dateNow = LocalDateTime.now().toString().substring(0, 19).replace('.', '-').replace('T', '(').replace(':', '-');
	report = new ExtentReports(System.getProperty("user.dir")+"\\RegresionTest--"+dateNow+").html"); //Nombre del archivo del reporte con la direccion del test
	report.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml")); 				 //Configuracion del reporte
	test = report.startTest("RegresionTime"); 														 //Nombre del test
	test.assignAuthor("Lautaro");																	 //Autor del test
	test.setDescription("Horarios de inicio y finalizacion de los test");							 //Descripcion del test
	 
}
  @SuppressWarnings("static-access")
  
@Test
  public void regresion() throws IOException, InterruptedException {
	
/////////////// INFORMA QUE EMPIEZA EL TEST DE MERCURY TOURS
	  test.log(LogStatus.INFO, "MercuryTest Start");
	  
/* EMPIEZA EL TEST DE MERCURY TOURS, CREANDO UN MERCURYTOURS TEST,
 	PASANDOLE COMO PARAMETROS EL REPORTE Y EL TEST QUE SE ESTA UTILIZANDO */
	  MercuryToursTest sacarPasaje = new MercuryToursTest(this.report,this.test);
	  sacarPasaje.startTest();
	  sacarPasaje.test();
	  sacarPasaje.endTest();
	  
/////////////// INFORMA QUE FINALIZA EL TEST DE MERCURY TOURS
	  test.log(LogStatus.INFO, "MercuryTest Finish");
	 
	  
/////////////// INFORMA QUE EMPIEZA EL TEST DE SAUCE DEMO  
	  test.log(LogStatus.INFO, "SauceDemo Start");

/* EMPIEZA EL TEST DE SAUCE DEMO, CREANDO UN MERCURYTOURS TEST,
PASANDOLE COMO PARAMETROS EL REPORTE Y EL TEST QUE SE ESTA UTILIZANDO */
	  SauceDemo sauceDemo = new SauceDemo(this.report,this.test);
	  sauceDemo.startTest();
	  sauceDemo.test();
	  sauceDemo.endTest();
	  
/////////////// INFORMA QUE FINALIZA EL TEST DE SAUCE DEMO
	  test.log(LogStatus.INFO, "SauceDemo Finish");  
  }
  
  @AfterClass
  public void thearDown() {
/////////////// FINALIZA EL TEST Y LEVANTA EL REPORTE
	  report.endTest(test);
	  report.flush();
  }
}
