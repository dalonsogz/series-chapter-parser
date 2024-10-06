package com.eurobits.selenium.pageobjects;

import com.eurobits.selenium.pageobjects.base.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PageObjectIMDBEpisodesPage extends BasePageObject {

	private static final Logger LOG = LoggerFactory.getLogger(PageObjectIMDBEpisodesPage.class);

	// <div class="ipc-title__text">S1.E1 ∙ Apéritif</div>
	@FindBy (xpath ="//div[contains(@class, 'ipc-title__text')]")
	private List<WebElement> episodesLinks;

	@FindBy (xpath = "//img[starts-with(@src, 'https://m.media-amazon.com/images/') and @itemprop='image']" )
	private List<WebElement> episodesImages;

	// <h3 class="ipc-title__text"><span id="contribute">Contribute to this page</span>...</h3>
	final private String PAGE_LOADED_XPATH = "//h3//span[text()='Contribuir a esta página']";

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
