package com.eurobits.selenium.pageobjects;

import com.eurobits.selenium.pageobjects.base.BasePageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageObjectGoogleAccountPage extends BasePageObject {

//TODO
	private static final Logger LOG = LoggerFactory.getLogger(PageObjectGoogleAccountPage.class);

	// Google Account /////////////////////////////////////////////////////////

	@FindBy (xpath ="//input[@type='email']")
	private WebElement textBoxLogin;

	@FindBy (xpath ="//input[@type='password']")
	private WebElement textBoxPassword;

	final private String PAGE_GOOGLE_LOGIN_LOADED_XPATH = "//footer";


	// Google Drive ///////////////////////////////////////////////////////////

	@FindBy (xpath = "//a[contains(@class,'go-to-drive flat')]")
	private WebElement buttonEnterGoogleDrive;

	@FindBy (xpath ="//div[@data-tooltip='More actions' and @role='button']")
	private WebElement buttonMoreActions;

	@FindBy (xpath ="//div[]")
	private WebElement buttonMoreActionsStar;

	final private String PAGE_GOOGLE_DRIVE_PORTAL_LOADED_XPATH = "//a[contains(@class,'go-to-drive flat')]";
	final private String PAGE_GOOGLE_DRIVE_LOADED_XPATH = "//div[contains(text(),'You can drag files or folders right into Drive')]";
//	final private String PAGE_GOOGLE_DRIVE_LINK_LOADED_XPATH = "//div[contains(text(),'No hay ninguna vista previa disponible')]";
	final private String PAGE_GOOGLE_DRIVE_LINK_LOADED_XPATH = "//div[contains(text(),'No preview available') or contains(text(),'There was a problem with the preview.')]";

	public PageObjectGoogleAccountPage(WebDriver driver) {
		super(driver);
	}

	// Google Account /////////////////////////////////////////////////////////

	public void waitGoogleLoginPageToLoad() {
		setWaitTimeout(20);
		waitForElementPresentByXpath(PAGE_GOOGLE_LOGIN_LOADED_XPATH);
	}

	public void enterLoginUser(String user) {
		textBoxLogin.sendKeys(user+"\n");
	}


	// Google Drive ///////////////////////////////////////////////////////////

	public void waitGoogleDrivePortalToLoad() {
		setWaitTimeout(20);
		waitForElementPresentByXpath(PAGE_GOOGLE_DRIVE_PORTAL_LOADED_XPATH);
	}

	public void clickEnterGoogleDrive() {
		buttonEnterGoogleDrive.click();
	}

	public void waitGoogleDriveToLoad() {
		setWaitTimeout(20);
		waitForElementPresentByXpath(PAGE_GOOGLE_DRIVE_LOADED_XPATH);
	}

	public void waitGoogleDriveLinkPageToLoad() {
		setWaitTimeout(20);
		waitForElementPresentByXpath(PAGE_GOOGLE_DRIVE_LINK_LOADED_XPATH);
	}

	public void clickStar() throws InterruptedException {
		clickMoreActions();
		Thread.sleep(500);
		buttonMoreActions.sendKeys(Keys.DOWN);
		buttonMoreActions.sendKeys(Keys.DOWN);
		Thread.sleep(500);
		buttonMoreActions.sendKeys(Keys.ENTER);
	}

	public void clickMoreActions() {
		buttonMoreActions.click();
	}

}
