package com.vn.osp.common.util;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;


/**
 * SystemProperties
 * 
 * @author Nguyen Thanh Hai
 * @version $Revision: 17051 $
 */
public class SystemProperties {
    public static final String DEFAULT_VALUE = "invalid";
    private static final Properties SYSTEM_PROPERTIES = new Properties();
    
    static {

        ClassLoader loader = SystemProperties.class.getClassLoader();
        try {
            SYSTEM_PROPERTIES.load(loader.getResourceAsStream("system.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * getProperty
     * 
     * @param key
     * @return value
     */
    public static String getProperty(String key) {

        String ret = key;
        if (SYSTEM_PROPERTIES.containsKey(key)) {
            ret = SYSTEM_PROPERTIES.getProperty(key);
        }
        return ret;
    }
    
    /**
     * setProperty
     * 
     * @param key
     * @return value
     */
    public static void setProperty(String key, String value) {
        if (SYSTEM_PROPERTIES.containsKey(key)) {
	    	try {
	    		ClassLoader classLoader = SystemProperties.class.getClassLoader();
				URL url = classLoader.getResource("system.properties");
	    		SYSTEM_PROPERTIES.setProperty(key , value);
	    		OutputStream  outPut = new FileOutputStream(url.toURI().getPath()) ; 
				SYSTEM_PROPERTIES.store(outPut , "## " + key + " update ##");
				outPut.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    public static String getSystemPropertyFromClassPath(String fileName,String key) {
        String ret = SystemProperties.DEFAULT_VALUE;
        Properties prop = new Properties();
        InputStream input = null;
        try {
            //String filename = "system.properties";
            input = SystemProperties.class.getClassLoader().getResourceAsStream(fileName);
            if (input == null) {
                return ret;
            }
            prop.load(input);
            ret = prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
}
