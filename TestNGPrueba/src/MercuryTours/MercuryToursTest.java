package MercuryTours;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//////////////// SE CREA EL WEBDRIVER, EL REPORTE Y SE LE DA EL VALOR A LA URL
public class MercuryToursTest {		
		static WebDriver driver;
		static ExtentTest test;
		static ExtentReports report;
		static String url = "http://newtours.demoaut.com";
		static boolean sinInstanciar = true;
//		static ExtentHtmlReporter reporter;
		
		
//		static TestList testList;


@SuppressWarnings("static-access")
public MercuryToursTest(ExtentReports report, ExtentTest test) {
			this.test=test;
			this.report =report;
//			this.url= "http://newtours.demoaut.com";
//			this.driver = new ChromeDriver();
			this.sinInstanciar = false;
}

@SuppressWarnings("static-access")
public MercuryToursTest() {
	this.sinInstanciar=true;
	
}

	///////////////// SE ELIGE DONDE SE GUARDA EL REPORTE
	@SuppressWarnings("deprecation")
	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("src/../ErrImages/" + System.currentTimeMillis() + ".png"); // el nombre de la captura en millis
		String errflpath = Dest.getAbsolutePath();
		FileUtils.newFileUtils().copyFile(scrFile, Dest);
		return errflpath;
		
	}

///////////////// INICIA EL REPORTE, INSTANCIA EL NAVEGADOR
	@BeforeClass(groups = {"sauceYMercury"})
	public static void startTest(){
		if (sinInstanciar){
			String dateNow = LocalDateTime.now().toString().substring(0, 19).replace('.', '-').replace('T', '(').replace(':', '-');
			report = new ExtentReports(System.getProperty("user.dir")+"\\MercuryTest--"+dateNow+").html"); //Nombre del archivo del reporte con la direccion del test
			report.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
		}
		test = report.startTest("Mercury Tours"); //Nombre del test
		test.assignAuthor("Lautaro");
		driver= new ChromeDriver(); // Crea un chrome driver
		driver.get(url);	//Ingresa a la url especificada
		driver.manage().window().maximize(); //Maximiza el navegador
		driver.manage().deleteAllCookies(); //Borra todas las cookies
		test.setDescription("Realiza compra en mercury tours");
		
	}

	
///////////////// INICIA EL TEST
	@Test(groups = {"sauceYMercury"})
	public void test() throws IOException {

//////////////// VERIFICA QUE HAYA ENTRADO A LA PAGINA INDICADA
		if (driver.getTitle().equalsIgnoreCase("Welcome: Mercury Tours")) {
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+"Pagina de inicio Mercury Tours valida");
		}else {
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+"Pagina de inicio Mercury Tours erronea");
		}

///////////////// REALIZA LOGIN
		Login login = new Login();
		login.realizarLogin("mercury","mercury",driver);

///////////////// VALIDA QUE SE HAYA REALIZADO CORRECTAMENTE
		if(login.seRealizoCorrectamente(driver)) {
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+"Login check");
		}else {
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+"Login fail");
		}

///////////////// INSTANCIA DATOS DE PASAJE Y CANTIDAD DE PASAJEROS
		int cantDePasajeros = 4;
		String desde = "London";
		String hacia = "New York";
		int diaIda = 21;
		int mesIda = 10;
		int diaVuelta = 31;
		int mesVuelta= 10;
		int aerolinea = 2;
		int tipoDeClase= 2;

/////////////////SACA PASAJES
		SacarPasaje sacarPasaje = new SacarPasaje();
		sacarPasaje.sacarPasaje(driver, cantDePasajeros, desde, hacia, diaIda, mesIda, diaVuelta, mesVuelta, aerolinea,tipoDeClase);

///////////////// VALIDA QUE SE HAYA REALIZADO CORRECTAMENTE Y LOS DATOS SEAN CORRECTOS
		if (sacarPasaje.vueloIda(driver).equalsIgnoreCase(desde+" to "+hacia)) {
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+"Datos correctos");
		}else {
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+"Error en los datos");
		}
		if (sacarPasaje.vueloVuelta(driver).equalsIgnoreCase(hacia+" to "+desde)) {
			test.log(LogStatus.PASS,"Datos correctos");
		}else {
			test.log(LogStatus.FAIL,"Error en los datos");
		}

///////////////// CLICK EN SIGUIENTE
		WebElement siguiente = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/p/input"));
		siguiente.click();

///////////////// TOMA EL STRING DEL TOTAL DEL PRECIO DE LOS PASAJES
		String precioStr = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[9]/td[2]/font/b")).getText();

///////////////// AGREGA PASAJEROS A LA LISTA DE PASAJEROS
		ArrayList<String> pasajeros = new ArrayList<String>();
		pasajeros.add("JuanFer"); 	//NOMBRE	#1
		pasajeros.add("Quintero");	//APELLIDO	#1
		pasajeros.add("Lucas");		//NOMBRE	#2
		pasajeros.add("Pratto");	//APELLIDO	#2
		pasajeros.add("Javier");	//NOMBRE	#3
		pasajeros.add("Pinola");	//APELLIDO	#3
		pasajeros.add("Marcelo");	//NOMBRE	#4
		pasajeros.add("Gallardo");	//APELLIDO	#4

/////////////////INGRESA LOS DATOS DE PASAJEROS
		IngresarDatosPasajeros datosPasajeros = new IngresarDatosPasajeros();
		datosPasajeros.ingresarPasajeros(cantDePasajeros, pasajeros,driver);

///////////////// VERIFICA QUE EL PRECIO SEA EL CORRECTO
		WebElement precio = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[12]/td/table/tbody/tr[2]/td[2]/font/b/font/font/b/font"));
		if (precio.getText().equalsIgnoreCase(precioStr+" USD")) {
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+"Precio correcto");
		}else {
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+"Error con el precio");
		}

/////////////////CIERRA SESION
		LogOut logOut = new LogOut();
		logOut.deslogearse(driver);

///////////////// VERIFICA QUE SE HAYA REALIZADO CORRECTAMENTE EL LOGOUT
		if (logOut.seRealizoCorrectamente(driver)) {
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+"LogOut Valido");
		}else {
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+"Error logout");
		}
	}

///////////////// CIERRA EL NAVEGADOR Y TERMINA EL REPORTE
	@AfterClass(groups = {"sauceYMercury"})
	public static void endTest(){
		report.endTest(test);
		driver.close();
		if (sinInstanciar) {
			report.flush();
		}
	}
}