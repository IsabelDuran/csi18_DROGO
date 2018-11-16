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
	private String _sID;
	private boolean _bIsDeleted;
	private String _sOldID;

	/**
	 * @param sID
	 */
	public Prisionero(String sID) throws Exception {
		_sID = sID;
		_sOldID = sID;
		_bIsDeleted = false;

		Connection con = null;
		ResultSet rs = null;

		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery(
					"SELECT Nombre, Edad " + "FROM Prisionero " + "WHERE ID = " + Data.String2Sql(sID, true, false));
			rs.next();
			_sNombre = rs.getString("Nombre");
			_iEdad = rs.getInt("Edad");
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

	public String getID() {
		return _sID;
	}

	public void setID(String sID) {
		_sID = sID;
	}

	public boolean getIsDeleted() {
		return _bIsDeleted;
	}

	/**
	 * @return sAux
	 */
	public String toString() {
		String sAux = super.toString() + ":" + _sID + ":" + _sNombre + ":" + _iEdad;
		return sAux;
	}

	/**
	 * @param sID
	 * @param sNombre
	 * @param iEdad
	 * @return
	 * @throws Exception
	 */
	public static Prisionero Create(String sID, String sNombre, int iEdad) throws Exception {
		Connection con = null;
		Statement st = null;

		try {
			con = Data.Connection();
			st = con.createStatement();
			st.executeUpdate(
					"INSERT INTO Prisionero (Nombre, Edad, ID)" + "VALUES (" + Data.String2Sql(sNombre, true, false)
							+ "," + iEdad + "," + Data.String2Sql(sID, true, false) + ")");
		} catch (SQLException ee) {
			throw ee;
		} finally {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		}
		return new Prisionero(sID);
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
				st.executeUpdate("DELETE FROM Prisionero WHERE ID = " + Data.String2Sql(_sID, true, false));
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
						+ " , Edad = " + _iEdad + " , ID = " + Data.String2Sql(_sID, true, false) + " WHERE ID LIKE "
						+ Data.String2Sql(_sOldID, true, false));
				_sOldID = _sID;
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
	 * @param sID
	 * @param iEdad
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<Prisionero> Select(String sNombre, String sID, Integer iEdad) throws Exception {
		ArrayList<Prisionero> aPrisoners = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;

		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT ID FROM Prisionero " 
													+ Where(sNombre, sID, iEdad));
			while (rs.next())
				aPrisoners.add(new Prisionero(rs.getString("ID")));

		} catch (SQLException ee) {
			throw ee;
		} finally {
			if (con != null)
				con.close();
		}

		return aPrisoners;
	}

	/**
	 * @param sNombre
	 * @param sID
	 * @param iEdad
	 * @return
	 */
	private static String Where(String sNombre, String sID, Integer iEdad) {
		String sReturnValue = "";

		if (sNombre != null)
			sReturnValue = sReturnValue + "Nombre LIKE " + Data.String2Sql(sNombre, true, false) + " AND ";
		if (sID != null)
			sReturnValue = sReturnValue + "ID LIKE " + Data.String2Sql(sID, true, false) + " AND ";
		if (iEdad != null)
			sReturnValue = sReturnValue + "Edad = " + iEdad + " AND ";

		if (sReturnValue.length() != 0)
			sReturnValue = "WHERE " + sReturnValue + sReturnValue.substring(0, sReturnValue.length() - 5);

		return sReturnValue;
	}
}