package com.eurobits.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eurobits.selenium.pageobjects.base.BasePageObject;
import com.eurobits.selenium.utils.TestDataUtils;

public class PageObjectRobotPage extends BasePageObject {
	
	private static final Logger LOG = LoggerFactory.getLogger(PageObjectRobotPage.class);

	
	/** primer registro combo robots*/
	@FindBy(id="dropdown-banks_button")
	private WebElement comboRobots;

	/** DummyRobot*/
	@FindBy(id="no-logo-entity-PRV77200009992")
	private WebElement dummyRobot;

	/**input user*/
	@FindBy(xpath="//input[@id='user']")
	private WebElement inputUser;

	/**input pass*/
	@FindBy(xpath="//input[@id='password']")
	private WebElement inputPass;

	/**Btn Entrar*/
	@FindBy(id = "continue-button")
	private WebElement btnEntrar;




	/**Elementos del test_data.properties*/
	//private String pRobot = TestDataUtils.getData("robot");
	//private String pUser = TestDataUtils.getData("user");
	//private String pPass = TestDataUtils.getData("password");
	

	public PageObjectRobotPage(WebDriver driver) {
		super(driver);
	}


	/** Espera cargue pagina principal*/
	public void esperaRobotPage(){
		setWaitTimeout(30);
		waitForElementPresent("dropdown-banks_button");
	}
	
	
	
	/**Comprueba que el pagina principal*/
	public boolean isPagBancos() {
		try {
			getDriver().findElement(By.id("dropdown-banks_button"));
			return true;
		} catch (NoSuchElementException e) {
			LOG.error(e.getLocalizedMessage(), e);
			return false;
		}
	
	}


	/**Seleccionar Dummy robot*/
	public void seleccionarDummyRobot(){
		comboRobots.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			LOG.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		dummyRobot.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			LOG.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
	}



	/** click en continuar*/
	public PageObjectResultadosCuentasPage rellenarCredenciales(String user, String pwd){
		//getDriver().switchTo().defaultContent();
		inputUser.click();
		inputUser.sendKeys(user);
		inputPass.click();
		inputPass.sendKeys(pwd);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			LOG.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		btnEntrar.click();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			LOG.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		return initPageObject(PageObjectResultadosCuentasPage.class);

	}

	/** click en continuar*/
	public PageObjectSecondPhase rellenarCredencialesTwoPhases(String user, String pwd){
		//getDriver().switchTo().defaultContent();
		inputUser.click();
		inputUser.sendKeys(user);
		inputPass.click();
		inputPass.sendKeys(pwd);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			LOG.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		btnEntrar.click();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			LOG.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		return initPageObject(PageObjectSecondPhase.class);

	}


}
