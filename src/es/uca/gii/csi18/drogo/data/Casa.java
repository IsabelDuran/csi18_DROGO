package es.uca.gii.csi18.drogo.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Casa {
	private int _iId;
	private String _sNombre;
	
	public void setNombre (String sNombre) {
		_sNombre = sNombre;
	}
	
	public String getNombre() {
		return _sNombre;
	}
	
	public int getId() {
		return _iId;
	}
	
	public Casa (int iId) throws Exception {

		Connection con = null;
		ResultSet rs = null;

		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery(
					"SELECT Nombre FROM Casa WHERE Id = " + iId);
			rs.next();
			_sNombre = rs.getString("Nombre");
			_iId = iId;
			
		} catch (SQLException ee) {
			throw ee;
		}
	}
	
	public static ArrayList<Casa> Select() throws Exception {
		ArrayList<Casa> aCasas = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT Id FROM Casa ORDER BY Nombre");
			while (rs.next())
				aCasas.add(new Casa(rs.getInt("Id")));
			return aCasas;

		} catch (SQLException ee) {
			throw ee;
		} finally {
			if(con != null)
				con.close();
			if(rs != null)
				rs.close();
		}
	}
	
	public String toString() {
		return _sNombre;
	}
	
	
}
