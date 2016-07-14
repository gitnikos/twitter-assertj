package com.ten10.prometheus.assertj.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

public class TestData {

	private HashMap<String, String> data;
	private Properties properties;
	
	/***
	 * Default constructor only loads the configuration properties as test data.
	 * 
	 * @throws IOException when the configuration properties file does not exist in the classpath
	 */
	public TestData() throws IOException {
		properties  = loadConfigProperties();
		data		= new HashMap<String, String>();
	}
	
	/***
	 * Constructor that loads as test data the configuration properties file and all provided test data files.
	 * Values that are repeated amongst the files will be overwritten.
	 * 
	 * @param testDataFilenames the filenames of the test data files which should be found under the classpath
	 * @throws IOException when any input file (configuration properties or test data file) does not exist or has invalid contents
	 */
	public TestData(String... testDataFilenames) throws IOException {
		this();
		for (String filename : testDataFilenames) {
			loadDataFromFile(filename);
		}
		System.out.println(data);
	}
	
	/***
	 * Loads the configuration properties from the corresponding file that should exist in the class path.
	 * 
	 * @return the configuration properties read
	 * @throws IOException
	 */
	public Properties loadConfigProperties() throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(Constants.PROPERTIES_FILE_NAME);
		if (inputStream != null) {
			properties.load(inputStream);
			return properties;
		} else {
			throw new FileNotFoundException("Error: file with properties with name " + Constants.PROPERTIES_FILE_NAME + " not found in classpath");
		}
	}

	public void loadDataFromFile(String... testDataFilenames) throws IOException {
		for (String filename : testDataFilenames) {
			loadDataFromFile(filename);
		}
		System.out.println(data);
	}

		/***
     * Loads the contents of a file to the test data
     *
     * @param filename the filename that should exist under the classpath in the TestDataDirectory
     * @throws IOException when the contents of the file are not valid
     */
	private void loadDataFromFile(String filename) throws IOException {
		String filenameInTestDataDirectory = get(Constants.TEST_DATA_DIRECTORY_KEY, "") + File.separator + filename;
		System.out.println("Loading file " + filenameInTestDataDirectory);

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filenameInTestDataDirectory);
		if (inputStream != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			String delimiter = get(Constants.CSV_DELIMITER_KEY, Constants.DEFAUT_CSV_DELIMITER_VALUE);
			while ((line = reader.readLine()) != null) {
				if (!line.equals("") && !line.startsWith(Constants.CSV_COMMENT_CHARACTER) && line.contains(delimiter)) {
					String[] lineParts = line.split(delimiter);
					if (lineParts.length == 2) {
						data.put(lineParts[0], lineParts[1]);
					} else if (lineParts.length == 1 && !lineParts[0].equals(line)) {
						data.put(lineParts[0], "");
					} else {
						throw new IOException("Problem reading line '" + line + "' from file " + filenameInTestDataDirectory + " using the delimiter '" + delimiter + "'");
					}
				}
			}
		} else {
			System.out.println("File " + filenameInTestDataDirectory + " not found in classpath");
		}
	}
	
	/***
	 * Returns the value from the test data with the corresponding key.
	 * If the value is not found in the test data, then the value from the configuration properties file is returned.
	 * If no such key exists in the configuration properties file, then an exception is thrown.
	 * 
	 * @param key the key of the value to return
	 * @return the value that matches the key parameter
	 */
	public String get(String key) {
		String testDataValue = data.get(key);
		testDataValue = (testDataValue != null) ? testDataValue : properties.getProperty(key);
		if (testDataValue != null) {
			return testDataValue;
		} else {
			throw new RuntimeException("Parameter with key " + key + " was not found in the test data");
		}
	}
	
	/***
	 * Returns the value from the test data with the corresponding key.
	 * If the value is not found in the test data, then the value from the configuration properties file is returned.
	 * If no such key exists in the configuration properties file, then the provided default value is returned.
	 * 
	 * @param key the key of the value to return
	 * @param defaultValue the default value to be returned if the key is not found in the test data or the properties file
	 * @return the value that matches the key parameter
	 */
	public String get(String key, String defaultValue) {
		String testDataValue = data.get(key);
		testDataValue = (testDataValue != null) ? testDataValue : properties.getProperty(key);
		if (testDataValue != null) {
			return testDataValue;
		} else {
			return defaultValue;
		}
	}
}
