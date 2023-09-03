package com.eurobits.selenium.tests.imdb;

import com.eurobits.selenium.pageobjects.PageObjectFilecriptLinksPage;
import com.eurobits.selenium.pageobjects.PageObjectGoogleAccountPage;
import com.eurobits.selenium.pageobjects.PageObjectMyGavPortalPage;
import com.eurobits.selenium.tests.base.BaseTestCase;
import com.eurobits.selenium.utils.TestDataUtils;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class Filecrypt_Links extends BaseTestCase {

	private static final Logger LOG = LoggerFactory.getLogger(Filecrypt_Links.class);

	private final String MAIN_TITLE_FILECRYPT = "Filecrypt_Links";
	private final String MAIN_TITLE_GOOGLE_DRIVE_ACCOUNT = "Google_Drive";
	private final String MAIN_MYGAVPORTAL = "MyGavPortal";
	private final String GOOGLE_USER = "Google_User";
	private final String GAVILAN_USER = "Gavilan_User";
	private final String GAVILAN_PASSWORD = "Gavilan_Password";
	private final String FILECRYPT_PASSWORD = "Filecrypt_Password";

	@Test
	public void starreGoogleLinks() throws InterruptedException {

		List<WebElement> links = null;

		/*
		 * Inicializamos el Driver 
		 * Actualizamos baseURL 
		 */
		LOG.debug("INICIO");
		LOG.debug("00 -Caso de Prueba: Filecrypt Links");

		// Login into google account
		PageObjectGoogleAccountPage pageGoogle = logIntoGoogle();

//		openNewTab();

		// Acceso URL
		PageObjectFilecriptLinksPage pageFileCriptLinks = (PageObjectFilecriptLinksPage) irPageObjectPage(MAIN_TITLE_FILECRYPT,PageObjectFilecriptLinksPage.class);
		LOG.debug("01 -Cargamos la página de enlaces");


		// Pass fillecryp password checking page
		pageFileCriptLinks.waitFilecriptCheckPage();
		pageFileCriptLinks.enterPassword(TestDataUtils.getSystemData(FILECRYPT_PASSWORD));
		pageFileCriptLinks.clickContinue();


		// Esperamos que haya cargado
		pageFileCriptLinks.waitFilecriptLinksPage();
		LOG.debug("02 -Esperamos que cargue la página de enlaces");

		// Buscamos la lista de episodios
		links= pageFileCriptLinks.findLinks();
		LOG.debug("02 -Obtenemos el listado de enlaces");
		printLinks(links);

		int counter=1;
		for (WebElement link:links) {
//			if (counter<94) {
//				counter++;
//				continue;
//			}

			LOG.debug("loading["+ counter+ "]:"+ link.getAttribute("onclick"));

			Thread.sleep(1000);

			pageFileCriptLinks.clickLink(link);

			switchToNewTab();

			pageGoogle = irPageObjectGoogleDriveLinkPage();

			pageGoogle.waitGoogleDriveLinkPageToLoad();

			LOG.debug("starring["+ counter + "]:" + pageGoogle.toString());

			pageGoogle.clickStar();

			closeCurrentTab();

			switchToPreviousWindow();

			counter++;
		}


		/*Comprobamos que ha terminado OK*/
		LOG.debug("FIN- TEST OK");
	}

	private void printLinks(List<WebElement> links) {
		try {
			for (WebElement link:links)
				LOG.debug(link.getAttribute("onclick"));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

	private PageObjectGoogleAccountPage logIntoGoogle() {

		PageObjectGoogleAccountPage pageGoogle = (PageObjectGoogleAccountPage) irPageObjectPage(MAIN_TITLE_GOOGLE_DRIVE_ACCOUNT,PageObjectGoogleAccountPage.class);
		pageGoogle.waitGoogleDrivePortalToLoad();
		pageGoogle.clickEnterGoogleDrive();
		pageGoogle.waitGoogleLoginPageToLoad();
		pageGoogle.enterLoginUser(TestDataUtils.getSystemData(GOOGLE_USER));

		PageObjectMyGavPortalPage pageMyGavPortalPage = (PageObjectMyGavPortalPage) irPageObjectPage(MAIN_MYGAVPORTAL,PageObjectMyGavPortalPage.class);
		pageMyGavPortalPage.waitPageToLoad();
		pageMyGavPortalPage.enterLoginUser(TestDataUtils.getSystemData(GAVILAN_USER));
		pageMyGavPortalPage.enterLoginPassword(TestDataUtils.getSystemData(GAVILAN_PASSWORD));
		pageMyGavPortalPage.clickLogin();

		pageGoogle.waitGoogleDriveToLoad();
		return pageGoogle;
	}


}
