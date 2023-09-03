package com.eurobits.selenium.drivers.base;

import org.openqa.selenium.WebDriver;
import com.eurobits.selenium.control.exception.TestAutomatizacionException;

/**
 * Proveedor de drivers de Selenium.
 */
public interface SeleniumDriverProvider {
	WebDriver initDriver() throws TestAutomatizacionException;
}
