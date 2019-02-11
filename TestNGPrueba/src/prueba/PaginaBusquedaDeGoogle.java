package prueba;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PaginaBusquedaDeGoogle {
	
	  @SuppressWarnings("unused")
	private WebElement q;

	  
	    @FindBy(how = How.NAME, using = "q")
	    private WebElement searchBox;

	    public void searchFor(String text) {
	        // We continue using the element just as before
	        searchBox.sendKeys(text);
	        searchBox.submit();
	    }	    
	    
    public static void main(String[] args) {
      WebDriver driver = new ChromeDriver();
// Crea una nueva instancia del navegador
      driver.get("http://www.google.com/");
	// Abre la página correspondiente
        PaginaBusquedaDeGoogle page = PageFactory.initElements(driver, PaginaBusquedaDeGoogle.class);
        // Crea una nueva instancia de la clase de la página
        // Inicializa los WebElement definidos en ella.
        page.searchFor("TSoft");

    }
}
