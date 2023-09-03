package com.eurobits.selenium.drivers;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eurobits.selenium.control.exception.TestAutomatizacionException;
import com.eurobits.selenium.drivers.base.SeleniumDriverProvider;
import com.eurobits.selenium.utils.TestDataUtils;

/**
 * Clase de Implementacion del Driver de Google Chrome
 *
 */
public class GoogleChromeDriver  implements SeleniumDriverProvider{
	
	private static final Logger LOG = LoggerFactory.getLogger(GoogleChromeDriver.class);
	
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
		
		return driver;
	}
	
}	

	