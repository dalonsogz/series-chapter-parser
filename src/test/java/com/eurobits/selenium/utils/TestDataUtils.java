package com.eurobits.selenium.utils;

/**import java.io.FileOutputStream;*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utilidades para datos de tests.
 * 
 */
public final class TestDataUtils {
	private static final Logger LOG = LoggerFactory.getLogger(TestDataUtils.class);

	
	private static final String TEST_DATA = "test_data";
	private static final String SYSTEM_DATA = "system_data";
	private static final String PROPERTIES_EXT = ".properties";
	public static final String DATA_FILES_CLASSPATH = "com/eurobits/selenium/";


	private TestDataUtils() {
	}
	

	public static String getData(String key)  {
		
		String fileClasspath = DATA_FILES_CLASSPATH + TEST_DATA + PROPERTIES_EXT;
		
		final InputStream inputStream =TestDataUtils.class.getClassLoader().getResourceAsStream(fileClasspath);
        
		Properties prop = new Properties();
		
		/**load properties file*/
		try {
			prop.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			LOG.error(e.getLocalizedMessage(), e);
		}
		
		String value=null;;
		try {
			value = prop.getProperty(key);

			if (value == null) {
				LOG.debug("No se ha encontrado el dato para la clave "+key);
			}
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
		}

		return value;
	}

	
		/**public static String setData(String key,String value) {
		
		String fileClasspath = DATA_FILES_CLASSPATH + TEST_DATA + PROPERTIES_EXT;
		
		
		final InputStream inputStream =TestDataUtils.class.getClassLoader().getResourceAsStream(fileClasspath);
		
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			LOG.debug("Fallo al realizar la carga del fichero de properties");
			LOG.error(e.getLocalizedMessage(), e);
		}
		
		String propsFileName = "./src/test/resources/com/eurobits/selenium/test_data2.properties";
		try {
			final OutputStream outputStream=new FileOutputStream(propsFileName);
			prop.setProperty(key, value);
			LOG.debug("hemos actualizado el properties");
	        prop.store(outputStream, null);
	        outputStream.close();
			 
		} catch (Exception e) {
			LOG.debug("Fallo al actualizar valores en el fichero de properties");
			LOG.error(e.getLocalizedMessage(), e);
		}

		return value;
	}*/
	
	
	public static String getSystemData(String key)  {
		
		String fileClasspath = DATA_FILES_CLASSPATH + SYSTEM_DATA + PROPERTIES_EXT;
		
		final InputStream inputStream =TestDataUtils.class.getClassLoader().getResourceAsStream(fileClasspath);
        
		Properties prop = new Properties();
		
		/**load properties file*/
		try {
			prop.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			LOG.error(e.getLocalizedMessage(), e);
		}
		
		String value=null;;
		try {
			value = prop.getProperty(key);

			if (value == null) {
				LOG.debug("No se ha encontrado el dato para la clave "+key);
			}
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
		}

		return value;
	}

	public static Properties getProperties(String porpertiesFile) {

		String fileClasspath = DATA_FILES_CLASSPATH + porpertiesFile + PROPERTIES_EXT;

		final InputStream inputStream = TestDataUtils.class.getClassLoader().getResourceAsStream(fileClasspath);

		Properties prop = new Properties();

		/**load properties file*/
		try {
			prop.load(inputStream);
            assert inputStream != null;
            inputStream.close();
		} catch (IOException e) {
			LOG.error(e.getLocalizedMessage(), e);
		}

		return prop;
	}
}

