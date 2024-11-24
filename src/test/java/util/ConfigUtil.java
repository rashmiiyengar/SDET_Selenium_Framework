package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    private static final Logger log= LoggerFactory.getLogger(ConfigUtil.class);
    private static final String DEFAULT_PROPERTIES="config/default.properties";
    private static Properties properties;

    public static void initialize(){

        //load default properties
        properties = loadProperties();

        //check for any override
        for(String key:properties.stringPropertyNames()){
            if(System.getProperties().containsKey(key)){
                properties.setProperty(key,System.getProperty(key));

            }
        }
        //print
        log.info("Test properties");
        for(String key:properties.stringPropertyNames()){
            log.info("{}={}",key,properties.getProperty(key));
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }

    public static Properties loadProperties(){
        Properties properties =new Properties();
        try {
          InputStream stream= ResourceLoader.getResource(DEFAULT_PROPERTIES);
          properties.load(stream);
        } catch (Exception e) {
            log.error("unable to load property file {}",DEFAULT_PROPERTIES,e);
            throw new RuntimeException(e);
        }
        return properties;
    }

}
