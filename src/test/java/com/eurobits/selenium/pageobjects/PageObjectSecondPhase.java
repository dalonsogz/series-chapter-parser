package com.eurobits.selenium.pageobjects;

import com.eurobits.selenium.pageobjects.base.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageObjectSecondPhase extends BasePageObject {

	private static final Logger LOG = LoggerFactory.getLogger(PageObjectSecondPhase.class);

	public PageObjectSecondPhase(WebDriver driver) {
		super(driver);
	}

	/**input code sca*/
	@FindBy(id="sca-input")
	private WebElement inputSecondPhase;

	/**Btn continuar*/
	@FindBy(id="continue-button")
	private WebElement btnContinuar;



	/** Espera cargue pagina */
	public void esperaSecondPhasePage(){
		setWaitTimeout(30);
		waitForElementPresent("sca-input");
	}

	/**Comprueba que el pagina principal con cuentas*/
	public boolean isPagSecondPhase() {
		try {
			getDriver().findElement(By.id ("sca-input"));
			return true;
		} catch (NoSuchElementException e) {
			LOG.error(e.getLocalizedMessage(), e);
			return false;
		}

	}

	/** click en continuar*/
	public PageObjectResultadosCuentasPage rellenarCodigo(String code){
		//getDriver().switchTo().defaultContent();
		inputSecondPhase.click();
		inputSecondPhase.sendKeys(code);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			LOG.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		btnContinuar.click();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			LOG.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		return initPageObject(PageObjectResultadosCuentasPage.class);

	}


}
