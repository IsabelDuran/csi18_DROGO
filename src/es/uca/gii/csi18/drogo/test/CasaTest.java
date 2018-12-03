package es.uca.gii.csi18.drogo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.uca.gii.csi18.drogo.data.Casa;
import es.uca.gii.csi18.drogo.data.Data;
import es.uca.gii.csi18.drogo.data.Prisionero;

public class CasaTest {

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
	public void testConstructor() throws Exception {
		Connection con = null;
		ResultSet rs = null;
		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT Id, Nombre FROM Casa");
			while (rs.next()) {
				Casa casa = new Casa(rs.getInt("Id"));

				assertEquals(rs.getString("Nombre"), casa.getNombre());
				assertEquals(rs.getInt("Id"), casa.getId());
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

	/**
	 * @throws Exception
	 */
	@Test
	public void testSelect() throws Exception {
		ArrayList<Casa> aCasa = Casa.Select();
		assertEquals(3, aCasa.size());
	}
}
