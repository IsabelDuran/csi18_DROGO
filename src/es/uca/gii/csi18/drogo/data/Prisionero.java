package es.uca.gii.csi18.drogo.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author isa
 *
 */
public class Prisionero {
	private int _iEdad;
	private String _sNombre;
	private String _sID;
	
	
	/**
	 * @param sID
	 */
	public Prisionero(String sID) throws Exception {
		_sID = sID;
		
		Connection con = null;
	    ResultSet rs = null;
	    
	    try {
	    	con = Data.Connection();
	    	rs = con.createStatement().executeQuery("SELECT Nombre, Edad "
	    											+ "FROM Prisionero "
	    											+ "WHERE ID = " + Data.String2Sql(sID, true, false));
	    	rs.next();
	    	_sNombre = rs.getString("Nombre");
	    	_iEdad = rs.getInt("Edad");
	    }
	    catch(SQLException ee) { throw ee; }
	}

	public int getEdad() {
		return _iEdad;
	}
	
	public void setEdad(int iEdad) {
		_iEdad = iEdad;
	}
	
	public String getNombre() {
		return _sNombre;
	}
	
	public void setNombre(String sNombre) {
		_sNombre = sNombre;
	}
	
	public String getID() {
		return _sID;
	}
	
	public void setID(String sID) {
		_sID = sID;
	}
	
	
	/** 
	 * @return sAux
	 */
	public String toString() {
		String sAux = super.toString() + ":" + _sID + ":" + _sNombre + ":" + _iEdad;
		return sAux;
	}
	
	
	public static Prisionero Create(String sID, String sNombre, int iEdad) throws Exception {
		Connection con = null;
		Statement st = null;
		
		try {
			con = Data.Connection();
			st = con.createStatement();
			st.executeUpdate("INSERT INTO Prisionero (Nombre, Edad, ID)"
							+ "VALUES (" + Data.String2Sql(sNombre, true, false) 
							+ "," + iEdad + "," + Data.String2Sql(sID, true, false) + ")");
		}
		catch(SQLException ee) { throw ee; }
		return new Prisionero(sID);
	}
}
