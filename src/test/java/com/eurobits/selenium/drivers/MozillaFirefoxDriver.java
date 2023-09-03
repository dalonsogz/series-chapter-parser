package com.eurobits.selenium.drivers;

import java.io.File;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.eurobits.selenium.control.exception.TestAutomatizacionException;
import com.eurobits.selenium.drivers.base.SeleniumDriverProvider;
import com.eurobits.selenium.utils.TestDataUtils;

/**
 * Clase de Implementacion del Driver de Firefox
 *
 */
public class MozillaFirefoxDriver implements SeleniumDriverProvider {
	
	private static final Logger LOG = LoggerFactory.getLogger(MozillaFirefoxDriver.class);
	
	public WebDriver initDriver() throws TestAutomatizacionException {
		
		WebDriver driver;
		
		final String firefoxServer = TestDataUtils.getData("FirefoxServer");
		final String geckoDriver = TestDataUtils.getData("GeckoDriver");

		LOG.debug("Inicializando driver Firefox");

		System.setProperty("webdriver.gecko.driver", geckoDriver);
		System.setProperty("webdriver.firefox.bin", firefoxServer);
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"C:\\temp\\logs.txt");

		FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(false);

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.private.browsing.autostart",true);


        driver = new FirefoxDriver();
		LOG.debug("levantamos driver con nuestro profile");
		
		//driver.manage().deleteAllCookies();
		
		driver.manage().window().maximize();
		LOG.debug("Maximizamos navegador");
		
		
		return driver;
	}
	

}	

