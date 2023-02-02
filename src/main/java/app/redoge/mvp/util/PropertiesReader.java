package app.redoge.mvp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertiesReader {
    private static final String propertiesFileName = "filesPath.properties";
    private static String getPropertiesFromProperties(String propName){
        try (InputStream input = FileListExtractor.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find filesPath.properties. Used resources path!");
                return null;
            }
            //load a properties file from class path, inside static method
            prop.load(input);
            //get the property value
            return prop.getProperty(propName);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /**
     * Gets the file path from the properties file.
     * @return The file path
     */
    protected static String getPathFromProperties(){
        return getPropertiesFromProperties("path");
    }

}
