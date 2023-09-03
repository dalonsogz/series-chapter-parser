package com.eurobits.selenium.init;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.eurobits.selenium.control.exception.TestAutomatizacionException;
import com.eurobits.selenium.drivers.base.SeleniumDriverProvider;

import java.util.ArrayList;


/**
 * Clase base de los tests de automatización basados en TestNG.
 * --Init driver
 * --Init pageobject
 */
public abstract class SeleniumDriverOperations  {
	private static final Logger LOG = LoggerFactory.getLogger(SeleniumDriverOperations.class);
	
	public static final String DRIVER_PROVIDERS_DEFAULT_PACKAGE = "com.eurobits.selenium.drivers";
	
	/** Driver de Selenium WebDriver. */
	private WebDriver driver;

	private String windowHandleBefore = null;

	
	/**
	 * Método que se ejecutará al inicio de cada test.
	 * Dado el parametro driverProvider, levanta objeto de la clase driver e inicializa el driver
	 * 
	 */

	@BeforeClass
	@Parameters("driverProvider")
	@SuppressWarnings("unchecked")
	public void testBeforeClass(String driverProvider) {
		LOG.debug("Inicializando test {}", this.getClass().getSimpleName());
		
		final String driverProviderClassName = DRIVER_PROVIDERS_DEFAULT_PACKAGE + "." + driverProvider;
		SeleniumDriverProvider provider =null;
			
		
			try {
				final Class<? extends SeleniumDriverProvider> driverProviderClass = (Class<? extends SeleniumDriverProvider>) Class.forName(driverProviderClassName);
				
				try {
					provider = driverProviderClass.newInstance();
					
					/**inicializa el driver*/
					try {
						setDriver(provider.initDriver());
						LOG.debug("Driver inicializado correctamente");
					} catch (TestAutomatizacionException e) {
						LOG.error(e.getLocalizedMessage(), e);
						throw new TestAutomatizacionException("Error al inicializar driver mediante provider [" + provider + "].", e);
					}
						
				} catch (Exception e) {
					LOG.error(e.getLocalizedMessage(), e);
				}
			} catch (Exception e) {
				LOG.error(e.getLocalizedMessage(), e);
			}		

	}

	
	/**
	 * Método ejecutado al final de cada test por TestNG.
	 */
	@AfterClass
	public void testAfterClass() {
		LOG.debug("Finalizando test {}", this.getClass().getSimpleName());
		closeDriverSafe();
	}
	

	/**
	 * Cierra el navegador y el driver actual.
	 */
	protected void closeDriverSafe() {
		if (this.driver != null) {
			this.driver.quit();
			this.driver = null;
		}
	}


	/**
	 * Recupera el driver actual.
	 * @return Driver actual.
	 */
	protected WebDriver getDriver() {
		return this.driver;
	}


	/**
	 * Asigna el driver para todas las operaciones con WebDriver.
	 * @param driver Driver de WebDriver.
	 */
	protected void setDriver(WebDriver driver) {
		closeDriverSafe();
		this.driver = driver;
	}

	protected void switchToNewTab() {
		switchToNewTab(null);
	}
	protected void switchToNewTab(String newWinHandle) {
		windowHandleBefore = driver.getWindowHandle();

		for(String winHandle : driver.getWindowHandles()){
			if (newWinHandle==null) {
				driver.switchTo().window(winHandle);
			} else if (winHandle==newWinHandle) {
				driver.switchTo().window(winHandle);
				break;
			}
		}
	}

	protected void closeCurrentTab() {
		driver.close();
	}

	protected void switchToPreviousWindow() {
		if (windowHandleBefore!=null) {
			driver.switchTo().window(windowHandleBefore);
		}
	}

		/**
         * Inicializa un objeto PageObject.
         * @param <T> Clase del PageObject.
         * @param pageObjectClass Clase del PageObject.
         * @return Objeto PageObject de la clase indicada a partir de la URL actual del driver Selenium.
         */
	public <T> T initPageObject(Class<T> pageObjectClass) {
		return PageFactory.initElements(driver, pageObjectClass);
	}

	public void openNewTab() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}
}
