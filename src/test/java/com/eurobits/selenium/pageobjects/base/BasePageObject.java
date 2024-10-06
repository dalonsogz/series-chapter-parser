package com.eurobits.selenium.pageobjects.base;

import com.eurobits.selenium.init.SeleniumDriverOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class BasePageObject extends SeleniumDriverOperations {
	
	private static final Logger LOG = LoggerFactory.getLogger(BasePageObject.class);
	
	/** Tiempo de espera por defecto*/
	private int waitTimeout = 10;


	
	public BasePageObject(WebDriver driver) {
		setDriver(driver);
	}

	
	/**
	 * Generar DNI aleatorio
	 */
	public String doGenerarDNIAleatorio() {

		String[] letrasDNI = { "T", "R", "W", "A", "G", "M", "Y", "F", "P",
				"D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C",
				"K", "E" };

		Random randomGenerator = new Random();
		int max = 99999999;
		int min = 80000000;
		int numeroNif = randomGenerator.nextInt(max - min) + min;

		String nif = numeroNif + letrasDNI[numeroNif % 23];

		LOG.debug("El DNI introducido en esta prueba es " + nif);

		return nif;
	}

	/**
	 * Generar CIF aleatorio tipo B (tambien podria ser valido para tipos A , E
	 * y H)
	 */

	public String doGenerarCIFAleatorio() {

		String letrasTipoCIF = "B";

		Random randomGenerator = new Random();
		int max = 9999999;
		int min = 1100000;
		int numeroCif = randomGenerator.nextInt(max - min) + min;
		
		String cif = Integer.toString(numeroCif);

		int parA = 0;

		for (int i = 1; i < 7; i += 2) {
			String letra = cif.substring(i, i + 1);
			int digito = Integer.parseInt(letra);
			parA += digito;
		}

		int nonB = 0;
		int nn = 0;

		for (int j = 0; j < 7; j += 2) {
			String letra2 = cif.substring(j, j + 1);
			int digito2 = Integer.parseInt(letra2);

			nn = 2 * digito2;
			if (nn > 9) {
				nn = 1 + (nn - 10);
			}
			nonB += nn;
		}


		int parcialC = parA + nonB;

		int digitoE = parcialC % 10;

		int digitoD = (digitoE > 0) ? (10 - digitoE) : 0;


		String letraD = Integer.toString(digitoD);

		cif = letrasTipoCIF + cif + letraD;
		
		LOG.debug("El CIF introducido en esta prueba es " + cif);

		return cif;
	}
	
	public void aceptarConfirmacion(){
		//Sale alert con boton aceptar y cancelar
		getDriver().switchTo().alert().accept();	
	}
	
	
	/** 
	 * Pulsamos el boton "Aceptar" de un Alert
	 */
	public void doAceptarAlert(){
		getDriver().switchTo().alert().accept();
	}
	
	public void setWaitTimeout(int seconds) {
		getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	
	public int getWaitTimeout() {
		return this.waitTimeout;
	}
	
	
	/**
	 * Espera a que se cumpla cierta condición.
	 * @param condition Condición a esperar hasta que se cumpla o se llegue al timeout.
	 * @return Elemento retornado en la condición.
	 * @throws TimeoutException si en el tiempo especificado por {@link SeleniumDriverOperations#getWaitTimeout()} no ha econtrado el elemento.
	 */
	protected <T> T waitForCondition(ExpectedCondition<T> condition) {
		final WebDriverWait wdw = new WebDriverWait(getDriver(), getWaitTimeout());
		return wdw.until(condition);
	}
	

	
	/**
	 * Espera a que un elemento esté visible
	 * @param elementId ID del elemento
	 * @return Elemento
	 * @throws TimeoutException si en el tiempo especificado por {@link SeleniumDriverOperations#getWaitTimeout()} no ha econtrado el elemento y además es visible.
	 */
	protected WebElement waitForElementVisible(String elementId) {
		return waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
	}

	/**
	 * Espera a que un elemento esté visible
	 * @param xpath del elemento
	 * @return Elemento
	 * @throws TimeoutException si en el tiempo especificado por {@link SeleniumDriverOperations#getWaitTimeout()} no ha econtrado el elemento y además es visible.
	 */
	protected WebElement waitForElementVisibleByXpath(String xpath) {
		return waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	

	
	/**
	 * Espera a que un elemento esté visible
	 * @param elementName name del elemento
	 * @return Elemento
	 * @throws TimeoutException si en el tiempo especificado por {@link SeleniumDriverOperations#getWaitTimeout()} no ha econtrado el elemento y además es visible.
	 */
	protected WebElement waitForElementVisibleByName(String elementName) {
		return waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.name(elementName)));
	}
	
	/**
	 * Espera a que un elemento esté presente
	 * @param elementId ID del elemento
	 * @return Elemento
	 * @throws TimeoutException si en el tiempo especificado por {@link SeleniumDriverOperations#DEFAULT_WAIT_TIMEOUT_SECONDS} no ha econtrado el elemento.
	 */
	protected WebElement waitForElementPresent(String elementId) {
		return waitForCondition(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
	}


	/**
	 * Espera a que un elemento esté presente
	 * @param elementName name del elemento
	 * @return Elemento
	 * @throws TimeoutException si en el tiempo especificado por {@link SeleniumDriverOperations#DEFAULT_WAIT_TIMEOUT_SECONDS} no ha econtrado el elemento.
	 */
	protected WebElement waitForElementPresentByName(String elementName) {
		return waitForCondition(ExpectedConditions.presenceOfElementLocated(By.name(elementName)));
	}

	/**
	 * Espera a que un elemento esté presente
	 * @param xpath del elemento
	 * @return Elemento
	 * @throws TimeoutException si en el tiempo especificado por {@link SeleniumDriverOperations#DEFAULT_WAIT_TIMEOUT_SECONDS} no ha econtrado el elemento.
	 */
	protected WebElement waitForElementPresentByXpath(String xpath) {
		return waitForCondition(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}

	protected List<WebElement> findElementsByXpath(String xpath) {
		return getDriver().findElements(By.xpath(xpath));
	}
}
