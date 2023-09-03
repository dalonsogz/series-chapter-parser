package com.eurobits.selenium.pageobjects;

import com.eurobits.selenium.pageobjects.base.BasePageObject;
import com.eurobits.selenium.utils.TestDataUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class PageObjectResultCodePage extends BasePageObject {

	private static final Logger LOG = LoggerFactory.getLogger(PageObjectResultCodePage.class);


	public PageObjectResultCodePage(WebDriver driver) {
		super(driver);
	}


	/** Espera cargue pagina principal*/
	public void waitingResultcodePage(){
		setWaitTimeout(30);
		waitForElementPresent("header_logo");
	}
	
	
	
	/**Comprueba que el pagina principal*/
	public boolean isPagResults() {
		try {
			return getDriver().getCurrentUrl().contains("https://psd2-tpp-callback.eurobits.es/");

		} catch (NoSuchElementException e) {
			LOG.error(e.getLocalizedMessage(), e);
			return false;
		}
	
	}
	public void isResultExpected(String statuscode){
		Assert.assertTrue(getDriver().getCurrentUrl().contains(statuscode));
	}

}
