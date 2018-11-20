package es.uca.gii.csi18.drogo.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import es.uca.gii.csi18.drogo.util.Config;

public class Data {
	/**
	 * @return
	 */
	public static String getPropertiesUrl() {
		return "./db.properties";
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public static Connection Connection() throws Exception {
		try {
			Properties properties = Config.Properties(getPropertiesUrl());
			return DriverManager.getConnection(properties.getProperty("jdbc.url"),
					properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
		} catch (Exception ee) {
			throw ee;
		}
	}

	/**
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void LoadDriver()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		Class.forName(Config.Properties(Data.getPropertiesUrl()).getProperty("jdbc.driverClassName")).newInstance();
	}

	/**
	 * @param s
	 * @param bAddQuotes
	 * @param bAddWildCards
	 * @return
	 */
	public static String String2Sql(String s, boolean bAddQuotes, boolean bAddWildCards) {
		s = s.replace("'", "''");

		if (bAddWildCards)
			s = '%' + s + '%';

		if (bAddQuotes)
			s = '\'' + s + '\'';

		return s;
	}

	/**
	 * @param b
	 * @return
	 */
	public static int Boolean2Sql(boolean b) {
		if (b)
			return 1;
		else
			return 0;
	}

	/**
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public static int LastId(Connection con) throws SQLException {
		try {
			ResultSet rs = con.createStatement().executeQuery("jdbc.lastIdSentence");
			
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw e;
		}
		
	}
}
