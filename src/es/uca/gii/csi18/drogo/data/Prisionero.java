package es.uca.gii.csi18.drogo.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author isa
 *
 */
public class Prisionero {
	private int _iEdad;
	private String _sNombre;
	private String _sDni;
	private boolean _bIsDeleted;
	private int _iId;

	/**
	 * @param sDni
	 */
	public Prisionero(int iId) throws Exception {
		_bIsDeleted = false;

		Connection con = null;
		ResultSet rs = null;

		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery(
					"SELECT Dni, Nombre, Edad " + "FROM Prisionero " + "WHERE Id = " + iId);
			rs.next();
			_sNombre = rs.getString("Nombre");
			_iEdad = rs.getInt("Edad");
			_sDni =  rs.getString("Dni");
			
			_iId = iId;
		} catch (SQLException ee) {
			throw ee;
		}
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

	public String getDni() {
		return _sDni;
	}

	public void setDni(String sDni) {
		_sDni = sDni;
	}

	public boolean getIsDeleted() {
		return _bIsDeleted;
	}

	/**
	 * @return sAux
	 */
	public String toString() {
		return super.toString() + ":" + _sDni + ":" + _sNombre + ":" + _iEdad;
	}

	/**
	 * @param sDni
	 * @param sNombre
	 * @param iEdad
	 * @return
	 * @throws Exception
	 */
	public static Prisionero Create(String sDni, String sNombre, int iEdad) throws Exception {
		Connection con = null;
		Statement st = null;

		try {
			con = Data.Connection();
			st = con.createStatement();
			st.executeUpdate(
					"INSERT INTO Prisionero (Dni, Nombre, Edad)" + "VALUES (" + Data.String2Sql(sDni, true, false)
							+ "," + Data.String2Sql(sDni, true, false) + "," + iEdad + ")");
			
			return new Prisionero(Data.LastId(con));
		} catch (SQLException ee) {
			throw ee;
		} finally {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		}
	}

	/**
	 * @throws Exception
	 */
	// Precondiciones: El prisionero a eliminar no debe haber sido borrado con anterioridad.
	// Postcondiciones: Si el prisionero ya estaba borrado, lanza una exepción.
	// Si no, lo borra y asigna el valor TRUE a _bIsDeleted.
	public void Delete() throws Exception {
		Connection con = null;
		Statement st = null;

		try {
			con = Data.Connection();
			st = con.createStatement();

			if (!_bIsDeleted) {
				st.executeUpdate("DELETE FROM Prisionero WHERE Dni = " + Data.String2Sql(_sDni, true, false));
				_bIsDeleted = true;
			} else
				throw new Exception("The prisoner has already been deleted");

		} catch (SQLException ee) {
			throw ee;
		} finally {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		}
	}

	/**
	 * @throws Exception
	 */
	public void Update() throws Exception {
		Connection con = null;
		Statement st = null;

		try {
			con = Data.Connection();
			st = con.createStatement();

			if (!_bIsDeleted) {
				st.executeUpdate("UPDATE Prisionero SET Nombre = " + Data.String2Sql(_sNombre, true, false)
						+ " , Edad = " + _iEdad + " , Dni = " + Data.String2Sql(_sDni, true, false) + " WHERE Id = "
						+ _iId);
			}
			else
				throw new Exception("The prisoner you are trying to update is no longer in prison");

		} catch (SQLException ee) {
			throw ee;
		} finally {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		}
	}

	/**
	 * @param sNombre
	 * @param sDni
	 * @param iEdad
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<Prisionero> Select(String sNombre, String sDni, Integer iEdad) throws Exception {
		ArrayList<Prisionero> aPrisoners = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;

		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT Id FROM Prisionero " + Where(sNombre, sDni, iEdad));
			while (rs.next())
				aPrisoners.add(new Prisionero(rs.getInt("Id")));

		} catch (SQLException ee) {
			throw ee;
		} finally {
			if (con != null)
				con.close();
			if(rs != null)
				rs.close();
		}

		return aPrisoners;
	}

	/**
	 * @param sNombre
	 * @param sDni
	 * @param iEdad
	 * @return
	 */
	private static String Where(String sNombre, String sDni, Integer iEdad) {
		String sReturn = "";

		if (sNombre != null)
			sReturn = sReturn + "Nombre LIKE " + Data.String2Sql(sNombre, true, true) + " AND ";
		if (sDni != null)
			sReturn = sReturn + "Dni LIKE " + Data.String2Sql(sDni, true, true) + " AND ";
		if (iEdad != null)
			sReturn = sReturn + "Edad = " + iEdad + " AND ";

		if (sReturn.length() != 0)
			sReturn = "WHERE " + sReturn + sReturn.substring(0, sReturn.length() - 5);

		return sReturn;
	}
}