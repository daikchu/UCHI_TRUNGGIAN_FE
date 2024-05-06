package com.vn.osp.common.util;

import java.io.IOException;
import java.util.Properties;


/**
 * SystemMessageProperties
 * 
 * @author Nguyen Thanh Hai
 * @version $Revision: 17051 $
 */
public class SystemMessageProperties {


    public static final String DEFAULT_VALUE = "invalid";


    private static final Properties SYSTEM_PROPERTIES = new Properties();

    static {

        ClassLoader loader = SystemMessageProperties.class.getClassLoader();
        try {
            SYSTEM_PROPERTIES.load(loader
                    .getResourceAsStream("systemMessage.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * getSystemProperty
     * 
     * @param key
     * @return value
     */
    public static String getSystemProperty(String key) {
        String ret = SystemMessageProperties.DEFAULT_VALUE;
        if (SYSTEM_PROPERTIES.containsKey(key)) {
            ret = SYSTEM_PROPERTIES.getProperty(key);
        }

        return ret;
    }

}