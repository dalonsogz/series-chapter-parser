package com.eurobits.selenium.tests.imdb;

import com.eurobits.selenium.pageobjects.PageObjectIMDBEpisodesPage;
import com.eurobits.selenium.tests.base.BaseTestCase;
import com.eurobits.selenium.utils.TestDataUtils;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

@Test
public class IMDBSeries extends BaseTestCase {

	private static final Logger LOG = LoggerFactory.getLogger(IMDBSeries.class);

	public String MAIN_TITLE = "";
	public String EPISODES_RESULT_FILE = MAIN_TITLE+"_"+"episodes.txt";

	/**
	 * Constructor vacío.
	 */
	public IMDBSeries() {
	}

	/**
	 * Método que se ejecuta antes de cada test para configurar los parámetros.
	 *
	 * @param mainTitle           Título principal de la serie.
	 * @param episodesResultFile  Nombre del archivo de resultados.
	 */
	@Parameters({"MAIN_TITLE", "EPISODES_RESULT_FILE"})
	@Test
	public void setUp(String mainTitle, String episodesResultFile) {
		this.MAIN_TITLE = mainTitle;
		this.EPISODES_RESULT_FILE = episodesResultFile;
	}

	@Test
	public void writeEpisodesTitles()  {

		List<WebElement> episodesLinks = null;

		/*
		 * Inicializamos el Driver 
		 * Actualizamos baseURL 
		 */
		LOG.debug("INICIO");
		LOG.debug("00 -Caso de Prueba: IMDB Capitulos Series - " + MAIN_TITLE);

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
		LOG.debug("03 -Guardamos el listado de episodios");

		/*Comprobamos que ha terminado OK*/
		LOG.debug("FIN- TEST OK");
	}

	private void writeEpisodestoFile(List<WebElement> episodesList) {
		BufferedWriter dos = null;
		String targetDir= TestDataUtils.getSystemData("targetDir");
		try {
			dos = new BufferedWriter(
					new OutputStreamWriter(Files.newOutputStream(
							Paths.get(targetDir + FileSystems.getDefault().getSeparator() + EPISODES_RESULT_FILE))));
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
