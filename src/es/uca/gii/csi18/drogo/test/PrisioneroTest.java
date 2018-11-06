package es.uca.gii.csi18.drogo.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.uca.gii.csi18.drogo.data.Prisionero;
import es.uca.gii.csi18.drogo.data.Data;

/**
 * @author isa
 *
 */
public class PrisioneroTest {

	/**
	 * @throws Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Data.LoadDriver();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testCreate() throws Exception {
		Connection con = null;
		ResultSet rs = null;

		try {
			con = Data.Connection();
			Prisionero prisionero = Prisionero.Create("12345678J", "Pedro", 24);
			rs = con.createStatement()
					.executeQuery("SELECT Nombre, Edad, ID FROM Prisionero WHERE "
								+ "ID=" + Data.String2Sql(prisionero.getID(), true, false));
			rs.next();

			assertEquals(rs.getString(1), prisionero.getNombre());
			assertEquals(rs.getInt(2), prisionero.getEdad());
			assertEquals(rs.getString(3), prisionero.getID());

		} catch (SQLException ee) {
			throw ee;
		} finally {
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		}
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testConstructor() throws Exception {
		Connection con = null;
		ResultSet rs = null;
		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT Nombre, Edad, ID FROM Prisionero");
			while (rs.next()) {
				Prisionero prisionero = new Prisionero(rs.getString(3));

				assertEquals(rs.getString(1), prisionero.getNombre());
				assertEquals(rs.getInt(2), prisionero.getEdad());
				assertEquals(rs.getString(3), prisionero.getID());
			}
		} catch (SQLException ee) {
			throw ee;
		} finally {
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		}
	}
	
	@Test
	public void testSelect() {
		
	}
	
	@Test
	public void testUpdate() {
		//TODO
	}
	
	@Test
	public void testDelete() {
		//TODO
	}

}
