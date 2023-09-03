package com.eurobits.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.eurobits.selenium.pageobjects.base.BasePageObject;
import org.openqa.selenium.JavascriptExecutor;


public class PageObjectTouPage extends BasePageObject {
	
	private static final Logger LOG = LoggerFactory.getLogger(PageObjectTouPage.class);
	

	/**Identificador btn continuar*/
	@FindBy(id="continue-button")
	private WebElement btnContinuar;
	
	/**Identificador checkbox*/
	@FindBy(id="checkbox-tou")
	private WebElement checkbox;


	
	
	public PageObjectTouPage(WebDriver driver) {
		super(driver);
	}


	
	/** Espera cargue pagina de login*/
	public void esperaTouPage(){
		setWaitTimeout(30);
		waitForElementVisibleByXpath("//a[contains(text(),'Términos y Condiciones')]");
	}
	

	/**Comprueba que el pagina de Tou*/
	public boolean isPagTou() {
		try {
			getDriver().findElement(By.id("checkbox-tou"));
			return true;
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
			return false;
		}
	
	}
	/** Realiza login*/
	public PageObjectRobotPage doTou(){
		JavascriptExecutor js= (JavascriptExecutor) getDriver();
		js.executeScript("document.getElementById('checkbox-tou').click();");
		//checkbox.click();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		btnContinuar.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return initPageObject(PageObjectRobotPage.class);

	}


}
