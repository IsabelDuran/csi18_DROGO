package es.uca.gii.csi18.drogo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import es.uca.gii.csi18.drogo.data.Data;

class DataTest {

	/**
	 * @throws Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception { Data.LoadDriver(); }
	
	/**
	 * @throws Exception
	 */
	@Disabled
	@Test
	void testTableAccess() throws Exception {
		
		int iColumns, iElements;
		
	    Connection con = null;
	    ResultSet rs = null;
	    
	    try {
	    	con = Data.Connection();
	    	rs = con.createStatement().executeQuery("SELECT COUNT(*) FROM Prisionero");
	    	
	    	rs.next();
	    	iElements = rs.getInt(1);
	    	
	    	assertEquals(2, iElements);
	    	
	    }
	    catch(SQLException ee) { throw ee; }
	    
	    try {  	
	        rs = con.createStatement().executeQuery("SELECT * FROM Prisionero;");
	        
	        int i = 0;	        
	        while (rs.next()) {	        	
	        	System.out.println(rs.getString("Nombre") + " " + rs.getString("Edad") + " " 
	        			+ rs.getString("Dni"));
	        	i++;
	        }
	        
	        iColumns = rs.getMetaData().getColumnCount();
	        
	        assertEquals(2, i);
	        assertEquals(3, iColumns);
	        
	    }
	    catch (SQLException ee) { throw ee; }
	    finally {
	    	if (rs != null) rs.close();
	    	if (con != null) con.close();
	    }
	}
	
	/**
	 * 
	 */
	@Test
	void String2SqlTest() {
		assertEquals("hola", Data.String2Sql("hola", false, false));
		assertEquals("\'hola\'", Data.String2Sql("hola", true, false));
		assertEquals("%hola%", Data.String2Sql("hola", false, true));
		assertEquals("\'%hola%\'", Data.String2Sql("hola", true, true));
		assertEquals("O\'\'Connell", Data.String2Sql("O'Connell", false, false));
		assertEquals("\'O\''Connell\'", Data.String2Sql("O'Connell", true, false));	
		assertEquals("%\''Smith \''%", Data.String2Sql("\'Smith '", false, true));
		assertEquals("\'''Smith \'''", Data.String2Sql("\'Smith '", true, false));
		assertEquals("\'%\''Smith \''%\'", Data.String2Sql("\'Smith '", true, true));
		
	}
	
	/**
	 * 
	 */
	@Test
	void Boolean2SqlTest() {
		assertEquals(1, Data.Boolean2Sql(true));
		assertEquals(0, Data.Boolean2Sql(false));
	}
	
}