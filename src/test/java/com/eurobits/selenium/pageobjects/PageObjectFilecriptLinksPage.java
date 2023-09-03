package com.eurobits.selenium.pageobjects;

import com.eurobits.selenium.pageobjects.base.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PageObjectFilecriptLinksPage extends BasePageObject {


	private static final Logger LOG = LoggerFactory.getLogger(PageObjectFilecriptLinksPage.class);

	// Check Page
	@FindBy (xpath ="//input[@type='text']")
	private WebElement textBoxPassword;

	@FindBy (xpath ="//button[contains(text(),'continuar')]")
	private WebElement buttonContinue;

	final private String PAGE_LOADED_CHECK_XPATH = "//input[@type='text' and @name='password']";


	// Links Page
	@FindBy (xpath ="//button[@data-i18n='Descargar']")
	private List<WebElement> links;

	final private String PAGE_LOADED_XPATH = "//small[contains(text(),'vez que se comprob')]";

	public PageObjectFilecriptLinksPage(WebDriver driver) {
		super(driver);
	}


	// Check Page
	public void waitFilecriptCheckPage() {
		setWaitTimeout(20);
		waitForElementPresentByXpath(PAGE_LOADED_CHECK_XPATH);
	}

	public void clickContinue() {
		buttonContinue.click();
	}

	public void enterPassword(String password) {
		textBoxPassword.sendKeys(password);
	}

	// Links Page
	public void waitFilecriptLinksPage() {
		setWaitTimeout(20);
		waitForElementPresentByXpath(PAGE_LOADED_XPATH);
	}


	// Encuentra los enlaces
	public List findLinks() {
		return links;
//		return findElementsByXpath(str);
	}

	// Click en un enlace
	public void clickLink(WebElement link) {
		link.click();
	}
}
