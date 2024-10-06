package com.eurobits.selenium.tests.imdb;

import com.eurobits.selenium.pageobjects.PageObjectIMDBEpisodesPage;
import com.eurobits.selenium.tests.base.BaseTestCase;
import com.eurobits.selenium.utils.TestDataUtils;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Test
public class IMDBSeries extends BaseTestCase {

	private static final Logger LOG = LoggerFactory.getLogger(IMDBSeries.class);

	protected static String MAIN_TITLE = null;
	protected static String EPISODES_RESULT_FILE = null;

	/**
	 * Constructor vacío.
	 */
	public IMDBSeries() {
	}

	@DataProvider(name = "seriesDataProvider")
	public Object[][] seriesDataProvider() {

		Enumeration<Object> propsElements = TestDataUtils.getProperties("series_config").elements();
		ArrayList<Object> elements = Collections.list(propsElements);
		Object[][] data = new Object[elements.size()][2];
		int index = 0;
		String element = null;
		for (Object objElement:elements) {
			element = objElement.toString();
			data[index][0] = element.substring(0,element.indexOf(","));
			data[index][1] = element.substring(element.indexOf(",")+1);
			index++;
		}

		return data;
	}

	@Test(dataProvider = "seriesDataProvider")
	public void testSeries(String episodesUrl, String episodesResultFile) {
		System.out.println("Episodes Url: " + episodesUrl);
		System.out.println("Episodes Result File: " + episodesResultFile);
		writeEpisodesTitles(episodesUrl,episodesResultFile);
	}

	private void writeEpisodesTitles(String episodesUrl, String episodesResultFile)  {

		List<WebElement> episodesLinks = null;

		LOG.debug("INICIO");
		LOG.debug("00 -Caso de Prueba: IMDB Capitulos Series - " + episodesUrl);

		/* Acceso URL */
		PageObjectIMDBEpisodesPage page = irIMDBEpisodesPage(episodesUrl);
		LOG.debug("01 -Cargamos la página de episodios");

		/*Esperamos que haya cargado*/
		page.waitIMDBEpisodesPage();
		LOG.debug("02 -Esperamos que cargue la página de episodios");

		/*Buscamos la lista de episodios*/
		episodesLinks= page.findLinkEpisodes();
		LOG.debug("02 -Obtenemos el listado de episodios");

		/*Guardamos la lista de episodios*/
		writeEpisodestoFile(episodesLinks,episodesResultFile);
		LOG.debug("03 -Guardamos el listado de episodios");

		/*Comprobamos que ha terminado OK*/
		LOG.debug("FIN- TEST OK");
	}

	private void writeEpisodestoFile(List<WebElement> episodesList, String episodesResultFile) {
		BufferedWriter dos = null;
		String targetDir= TestDataUtils.getSystemData("targetDir");
		try {
			dos = new BufferedWriter(
					new OutputStreamWriter(Files.newOutputStream(
							Paths.get(targetDir + FileSystems.getDefault().getSeparator() + episodesResultFile))));
			int counter = 1;
			for (WebElement episode:episodesList)
				dos.write((String.format(Locale.US, "%02d", counter++) +" - ") +
						episode.getText().substring(episode.getText().indexOf("∙ ") + 1).trim() + "\n");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try { dos.close(); } catch (Exception e) {}
		}
	}
}
