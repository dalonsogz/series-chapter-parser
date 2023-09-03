package com.eurobits.selenium.tests.imdb;

import com.eurobits.selenium.pageobjects.PageObjectIMDBEpisodesPage;
import com.eurobits.selenium.tests.base.BaseTestCase;
import com.eurobits.selenium.utils.TestDataUtils;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Test
public class IMDBSeries_YojoSenki extends BaseTestCase {

	private static final Logger LOG = LoggerFactory.getLogger(IMDBSeries_YojoSenki.class);

	private final String MAIN_TITLE = "YojoSenki";

	private final String ESPISODES_RESULT_FILE = MAIN_TITLE+"_"+"episodes.txt";

	@Test
	public void writeEpisodesTitles()  {

		List<WebElement> episodesLinks = null;

		/*
		 * Inicializamos el Driver 
		 * Actualizamos baseURL 
		 */
		LOG.debug("INICIO");
		LOG.debug("00 -Caso de Prueba: IMDB Capitulos Series - Yojo Senki: Saga of Tanya the Evil (2017)");

		/* Acceso URL */
		PageObjectIMDBEpisodesPage page = irIMDBEpisodesPage(MAIN_TITLE);
		LOG.debug("01 -Cargamos la página de episodios");

		/*Esperamos que haya cargado*/
		page.waitIMDBEpisodesPage();
		LOG.debug("02 -Esperamos que cargue la página de episodios");

		/*Buscamos la lista de episodios*/
		episodesLinks= page.findLinkEpisodes();
		LOG.debug("02 -Obtenemos el listado de episodios");

		/*Guardamos la lista de episodios*/
		writeEpisodestoFile(episodesLinks);
		LOG.debug("02 -Obtenemos el listado de episodios");

		/*Comprobamos que ha terminado OK*/
		LOG.debug("FIN- TEST OK");
	}

	private void writeEpisodestoFile(List<WebElement> episodesList) {
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(ESPISODES_RESULT_FILE)));
			int counter = 1;
			for (WebElement episode:episodesList)
				dos.writeBytes((String.format(Locale.US, "%02d", counter++) +" - ") + episode.getText() + "\n");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try { dos.close(); } catch (Exception e) {}
		}
	}
}
