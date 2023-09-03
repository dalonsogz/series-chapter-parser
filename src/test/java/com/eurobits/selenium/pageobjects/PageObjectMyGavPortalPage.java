package com.eurobits.selenium.pageobjects;

import com.eurobits.selenium.pageobjects.base.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageObjectMyGavPortalPage extends BasePageObject {

//TODO
	private static final Logger LOG = LoggerFactory.getLogger(PageObjectMyGavPortalPage.class);

	@FindBy (id = "username")
	private WebElement textBoxLogin;

	@FindBy (id = "password")
	private WebElement textBoxPassword;

	@FindBy (xpath ="//input[@name='submit_form']")
	private WebElement loginButton;

	final private String PAGE_LOADED_XPATH = "//h3[contains(text(),'Quick Links:')]";

	public PageObjectMyGavPortalPage(WebDriver driver) {
		super(driver);
	}

	/** Espera cargue pagina */
	public void waitPageToLoad() {
		setWaitTimeout(20);
		waitForElementPresentByXpath(PAGE_LOADED_XPATH);
	}

	public void enterLoginUser(String user) {
		textBoxLogin.clear();
		textBoxLogin.sendKeys(user);
	}

	public void enterLoginPassword(String password) {
		textBoxPassword.sendKeys(password);
	}

	public void clickLogin () {
		loginButton.click();
	}

}
