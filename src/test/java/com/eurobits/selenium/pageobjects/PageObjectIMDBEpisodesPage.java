package com.eurobits.selenium.pageobjects;

import com.eurobits.selenium.pageobjects.base.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PageObjectIMDBEpisodesPage extends BasePageObject {

	private static final Logger LOG = LoggerFactory.getLogger(PageObjectIMDBEpisodesPage.class);

	@FindBy (xpath ="//a[@itemprop=\"name\"]")
	private List<WebElement> episodesLinks;

	@FindBy (xpath = "//img[starts-with(@src, 'https://m.media-amazon.com/images/') and @itemprop='image']" )
	private List<WebElement> episodesImages;

	final private String PAGE_LOADED_XPATH = "//h2[text()=\"Contribute to This Page\"]";

	public PageObjectIMDBEpisodesPage(WebDriver driver) {
		super(driver);
	}

	/** Espera cargue pagina */
	public void waitIMDBEpisodesPage() {
		setWaitTimeout(20);
		waitForElementPresentByXpath(PAGE_LOADED_XPATH);
	}

	// Encuentra los elementos
	public List findLinkEpisodes() {
		return episodesLinks;
//		return findElementsByXpath(str);
	}

}
