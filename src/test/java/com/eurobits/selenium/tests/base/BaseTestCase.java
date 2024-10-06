package com.eurobits.selenium.tests.base;

import com.eurobits.selenium.init.SeleniumDriverOperations;
import com.eurobits.selenium.pageobjects.PageObjectFilecriptLinksPage;
import com.eurobits.selenium.pageobjects.PageObjectGoogleAccountPage;
import com.eurobits.selenium.pageobjects.PageObjectIMDBEpisodesPage;
import com.eurobits.selenium.pageobjects.PageObjectTouPage;
import com.eurobits.selenium.pageobjects.base.BasePageObject;
import com.eurobits.selenium.utils.TestDataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class BaseTestCase extends SeleniumDriverOperations {
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseTestCase.class);


	@Test
	public PageObjectTouPage irCobrandingWeb(String processId, String userId) {

		//String baseUrl= TestDataUtils.getData("baseUrl");
		//TODO cambiar esto para subir a Jenkins
		String baseUrl= TestDataUtils.getSystemData("baseUrl");
		System.out.println("La url base es: "+baseUrl);
		
		//String urlLogin= TestDataUtils.getData("urlOrigin");
		//TODO cambiar esto para subir a Jenkins
		String urlLogin= TestDataUtils.getSystemData("urlOrigin");
		System.out.println("La url personalizada es: "+urlLogin);
		
		//Quitamos caracteres barra "\"
		urlLogin= urlLogin.replace("\\","");
		
		//Creamos url final
		String urlInicio=baseUrl+urlLogin+"?processId="+processId+"&userId="+userId;
		System.out.println("La url de inicio es: "+urlInicio);
		getDriver().get(urlInicio);
		
		return initPageObject(PageObjectTouPage.class);
	}

	@Test
	public BasePageObject irPageObjectPage(Class<BasePageObject> pageObgectClass) {
		return irPageObjectPage(null,pageObgectClass);
	}
	@Test
	public <T extends BasePageObject> BasePageObject irPageObjectPage(String mainTitle, Class<T> pageObgectClass)  {

		if (mainTitle!=null) {
			//String baseUrl= TestDataUtils.getData("baseUrl");
			String baseUrl = TestDataUtils.getSystemData(mainTitle + "_baseUrl");
			System.out.println("La url base es: " + baseUrl);

			//Quitamos caracteres barra "\"
			//		urlLogin= urlLogin.replace("\\","");

			//Creamos url final
            System.out.println("La url de inicio es: " + baseUrl);
			getDriver().get(baseUrl);
		}

		return initPageObject(pageObgectClass);
	}

	@Test
	public PageObjectFilecriptLinksPage irPageObjectFilecriptLinksPage(String mainTitle) {

		//String baseUrl= TestDataUtils.getData("baseUrl");
		String baseUrl= TestDataUtils.getSystemData(mainTitle+ "_baseUrl");
		System.out.println("La url base es: "+baseUrl);

		//Quitamos caracteres barra "\"
//		urlLogin= urlLogin.replace("\\","");

		//Creamos url final
        System.out.println("La url de inicio es: "+ baseUrl);
		getDriver().get(baseUrl);

		return initPageObject(PageObjectFilecriptLinksPage.class);
	}

	@Test
	public PageObjectGoogleAccountPage irPageObjectGoogleDriveLinkPage() {
		return irPageObjectGoogleDriveLinkPage(null);
	}

	@Test
	public PageObjectGoogleAccountPage irPageObjectGoogleDriveLinkPage(String mainTitle) {

		if (mainTitle!=null) {
			//String baseUrl= TestDataUtils.getData("baseUrl");
			String baseUrl = TestDataUtils.getSystemData(mainTitle + "_baseUrl");
			System.out.println("La url base es: " + baseUrl);

			//Quitamos caracteres barra "\"
			//		urlLogin= urlLogin.replace("\\","");

			//Creamos url final
            System.out.println("La url de inicio es: " + baseUrl);
			getDriver().get(baseUrl);
		}

		return initPageObject(PageObjectGoogleAccountPage.class);
	}

	@Test
	public PageObjectIMDBEpisodesPage irIMDBEpisodesPage(String baseUrl) {

		System.out.println("La url base es: "+ baseUrl);

		//Quitamos caracteres barra "\"
//		urlLogin= urlLogin.replace("\\","");

		//Creamos url final
        System.out.println("La url de inicio es: "+ baseUrl);
		try {
			getDriver().get(baseUrl);
		} catch (Exception e) {
			System.out.println("Error habitual");
		}

		return initPageObject(PageObjectIMDBEpisodesPage.class);
	}

	@AfterClass
	public void doFinalizarDriver() {
		
		LOG.debug("Cerrando el driver");

		try{
			//Si aun existe el driver, finaliza todas las ventanas asociadas al driver
			
//			getDriver().quit();
			
		}catch(Exception e){
			LOG.error(e.getLocalizedMessage(), e);
		}
	}
	
	
}
