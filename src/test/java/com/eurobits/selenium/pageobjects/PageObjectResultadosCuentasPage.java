package com.eurobits.selenium.pageobjects;

import com.eurobits.selenium.pageobjects.base.BasePageObject;
import org.openqa.selenium.*;

import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageObjectResultadosCuentasPage extends BasePageObject {

	private static final Logger LOG = LoggerFactory.getLogger(PageObjectResultadosCuentasPage.class);

	/**Btn Entrar*/
	@FindBy (id ="finish-button")
	private WebElement btnContinuar;

	/**Btn Cancelar*/
	@FindBy (id ="cancel-button")
	private WebElement btnCancelar;

	public PageObjectResultadosCuentasPage(WebDriver driver) {
		super(driver);
	}

	/** Espera cargue pagina */
	public void esperaResultadosPage(){
		setWaitTimeout(30);
		waitForElementPresent("accounts-list-item-0");
	}

	/**Comprueba que el pagina principal con cuentas*/
	public boolean isPagCuentas() {
		try {
			getDriver().findElement(By.id("accounts-list-item-0"));
			return true;
		} catch (NoSuchElementException e) {
			LOG.error(e.getLocalizedMessage(), e);
			return false;
		}
	}

	/** Espera cargue pagina sin cuentas */
	public void esperaResultadosPageSinCuentas(){
		setWaitTimeout(30);
		waitForElementVisible("no-accounts");
	}


	/**Comprueba que el pagina principal sin cuentas*/
	public boolean isPagCuentasSinCuentas() {
		try {
			getDriver().findElement(By.id("no-accounts"));
			return true;
		} catch (NoSuchElementException e) {
			LOG.error(e.getLocalizedMessage(), e);
			return false;
		}

	}

	/**Seleccionar 1º cuenta Dummy robot*/
	public PageObjectResultCodePage seleccionarPrimeraCuenta(){

		//account0.click();
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("document.getElementById('accounts-list-item-0').click();");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			LOG.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		btnContinuar.click();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			LOG.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		return initPageObject(PageObjectResultCodePage.class);
	}


	/**Pulsar el boton cancelar*/
	public PageObjectResultCodePage cancelarOperacion(){

		btnCancelar.click();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			LOG.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		return initPageObject(PageObjectResultCodePage.class);
	}




}
