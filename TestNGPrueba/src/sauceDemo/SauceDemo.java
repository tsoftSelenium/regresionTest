package sauceDemo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

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

public class SauceDemo {
	static WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	static String url = "https://www.saucedemo.com/";
	static boolean sinInstanciar = true;

@SuppressWarnings("static-access")
public SauceDemo(ExtentReports report2, ExtentTest test2) {
		this.report=report2;
		this.test=test2;
		sinInstanciar = false;
	}

public SauceDemo() {
	
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
		report = new ExtentReports(System.getProperty("user.dir")+"\\SauceDemo--"+dateNow+").html"); //Nombre del archivo del reporte con la direccion del test
		report.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	}
	test = report.startTest("Sauce Demo"); 		//Nombre del test
	test.assignAuthor("Lautaro");
	test.setDescription("Realiza compra en 'Sauce Demo'");
//	System.out.println(test.getStartedTime().toString());
	driver= new ChromeDriver(); 				// Crea un chrome driver
	driver.get(url);							//Ingresa a la url especificada
	driver.manage().window().maximize();	 	//Maximiza el navegador
	driver.manage().deleteAllCookies();			//Borra todas las cookies
}

///////////////// INICIA EL TEST
	@Test(groups = {"sauceYMercury"})
	public void test() throws IOException, InterruptedException {
////////////////VERIFICA QUE HAYA ENTRADO A LA PAGINA INDICADA
		if (driver.getTitle().equalsIgnoreCase("Swag Labs")) {
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+"Pagina de inicio Sauce Demo valida");
		}else {
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+"Pagina de inicio Sauce Demo erronea");
		}

///////////////// REALIZA LOGIN
/*  Nombres de usuarios	:					pass:
	standard_user
	locked_out_user							secret_sauce
	problem_user		
	performance_glitch_user
*/
		SauceLogin login = new SauceLogin();
		login.realizarLogin("standard_user","secret_sauce",driver);
		
///////////////// VALIDA QUE SE HAYA REALIZADO CORRECTAMENTE
		if(login.seRealizoCorrectamente(driver)) {
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+"Login valido");
		}else {
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+"Login fail");
		}
	
///////////////// CREA LOS OBJETOS DISPONIBLES PARA COMPRAR Y LOS INSTANCIA		
//		WebElement mochila = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button"));
//		WebElement luzBici = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[3]/button")); 
//		WebElement remeraRayo = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[3]/div[3]/button"));
//		WebElement buzoLabs = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[4]/div[3]/button"));
//		WebElement remeraLabsRoja = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[5]/div[3]/button"));
//		WebElement remeraRoja = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[6]/div[3]/button"));
		
///////////////// CREA EL MERCADO NUEVO Y AGREGA AL CARRITO LOS OBJETOS QUE SE QUIERA	
//		
//		mercado.agregarAlCarrito(mochila);
//		mercado.agregarAlCarrito(luzBici);
//		mercado.agregarAlCarrito(remeraRayo);
//		mercado.agregarAlCarrito(buzoLabs);
//		mercado.agregarAlCarrito(remeraLabsRoja);
//		mercado.agregarAlCarrito(remeraRoja);
		
	
///////////////// INGRESA LA CANTIDAD DE PRODUCTOS INGRESADOS AL CARRITO (MAX 6)
	int cantidadDeProductos = 5;
	Mercado mercado = new Mercado();
	WebElement producto;
	for(int i = 1; i < cantidadDeProductos+1; i++) {
		producto = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div["+i+"]/div[3]/button"));
		mercado.agregarAlCarrito(producto);
	}
	
/////////////////VERIFICA QUE LA CANTIDAD DE PRODUCTOS SEA IGUAL A LA CANTIDAD DE PRODUCTOS EN EL CARRITO
	String productosEnCarrito = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
	if((cantidadDeProductos+"").equalsIgnoreCase(productosEnCarrito) ) {
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+"La cantidad de productos en carrito es correcta");
	}else {
		test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+"La cantidad de productos en carrito es errónea, verifique");
	}
	
///////////////// SE REALIZA LA COMPRA
		mercado.realizarCompra(driver);
		
/////////////////VALIDA QUE LA COMPRA SE REALICE
		WebElement compraValida = driver.findElement(By.xpath("//*[@id=\"contents_wrapper\"]/div[2]"));
		if (compraValida.getText().equalsIgnoreCase("Checkout: Complete!")) {
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+"Compra realizada con éxito");
		}else {
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+"La compra falló, verifique");
		}
			
/////////////////CIERRA SESION
		LogOutSauce logOut = new LogOutSauce();
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