package es.uca.gii.csi18.drogo.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import es.uca.gii.csi18.drogo.util.Config;

public class Data {
    public static String getPropertiesUrl() { return "./db.properties"; }
    public static Connection Connection() throws Exception {
        try {
            Properties properties = Config.Properties(getPropertiesUrl());
            return DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password"));
       }
       catch (Exception ee) { throw ee; }
	}
    
    public static void LoadDriver() 
        throws InstantiationException, IllegalAccessException, 
        ClassNotFoundException, IOException {
            Class.forName(Config.Properties(Data.getPropertiesUrl()
            ).getProperty("jdbc.driverClassName")).newInstance();
    }
    
    public static String String2Sql(String s, boolean bAddQuotes, boolean bAddWildCards) {
    	int iStringLength = s.length();
    	String sNewString = s;
    		
    	for(int i = 0; i < iStringLength; i++)
    		if(s.charAt(i) == '\'' && i != 0)
    			sNewString = s.substring(0, i) + '\'' + s.substring(i);
    	
    	if(sNewString.charAt(0) == '\'')
    		sNewString = '\'' + sNewString;
    	
    	if(bAddWildCards)
    		sNewString = '%' + sNewString + '%';
    
    	if(bAddQuotes) 
    		sNewString = '\'' + sNewString + '\'';
    	
    	return sNewString;
    } 
    
    public static int Boolean2Sql(boolean b) { 
    	int iBoolean;
    	
    	if(b)
    		iBoolean = 1;
    	else
    		iBoolean = 0;
    	
    	return iBoolean;
    }
}
