package com.eurobits.selenium.drivers;


import com.eurobits.selenium.control.exception.TestAutomatizacionException;
import com.eurobits.selenium.drivers.base.SeleniumDriverProvider;
import com.eurobits.selenium.utils.TestDataUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de Implementacion del Driver de Google Chrome
 *
 */
	public class GoogleChromeDriver  implements SeleniumDriverProvider{
	
	private static final Logger LOG = LoggerFactory.getLogger(GoogleChromeDriver.class);
	private final List<WebDriver> activeDrivers = new ArrayList<>();

	public WebDriver initDriver() throws TestAutomatizacionException {
		
		WebDriver driver ;
		
		final String chromeServer = TestDataUtils.getData("ChromeServer");
		final String chromeProfile = TestDataUtils.getData("ChromeProfile");
		
		LOG.debug("Inicializando driver Google Chrome.");
	
		System.setProperty("webdriver.chrome.driver", chromeServer);
		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir="+chromeProfile);
		options.addArguments("--start-maximized");
		options.addArguments("--test-type");
        options.addArguments("--incognito");
		options.addArguments("--no-sandbox");
//		options.addArguments("--remote-debugging-port=9222");
//		options.addArguments("--disable-dev-shm-usage");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		activeDrivers.add(driver);

		return driver;
	}


	public GoogleChromeDriver() {
		// Registrar un shutdown hook para cerrar todos los drivers al finalizar la JVM
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			LOG.info("Cerrando todos los drivers activos al finalizar la ejecución.");
			activeDrivers.forEach(driver -> {
				if (driver != null) {
					driver.quit();
				}

			});

			String os = System.getProperty("os.name").toLowerCase();
			String[] command;
			if (os.contains("win")) {
				command = new String[]{"taskkill", "/F", "/IM", "chromedriver.exe"};
			} else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
				command = new String[]{"pkill", "-f", "chromedriver"};
			} else {
				LOG.warn("Sistema operativo no soportado para cierre de procesos de chromedriver.");
				return;
			}

			try {
				ProcessBuilder processBuilder = new ProcessBuilder(command);
				processBuilder.start();
				LOG.info("Se han cerrado todos los procesos de chromedriver en el sistema.");
			} catch (IOException e) {
				LOG.error("Error al intentar cerrar los procesos de chromedriver: ", e);
			}
		}));


	}


}	

	