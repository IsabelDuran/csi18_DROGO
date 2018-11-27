package es.uca.gii.csi18.drogo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.uca.gii.csi18.drogo.data.Data;
import es.uca.gii.csi18.drogo.data.Prisionero;

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
			Prisionero prisionero = Prisionero.Create("12345670I", "Laura", 24);
			rs = con.createStatement().executeQuery("SELECT Nombre, Edad, Dni FROM Prisionero WHERE " + "Dni="
					+ Data.String2Sql(prisionero.getDni(), true, false));
			rs.next();

			assertEquals(rs.getString(1), prisionero.getNombre());
			assertEquals(rs.getInt(2), prisionero.getEdad());
			assertEquals(rs.getString(3), prisionero.getDni());

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
			rs = con.createStatement().executeQuery("SELECT Id, Dni, Nombre, Edad FROM Prisionero");
			while (rs.next()) {
				Prisionero prisionero = new Prisionero(rs.getInt("Id"));

				assertEquals(rs.getString(1), prisionero.getNombre());
				assertEquals(rs.getInt(2), prisionero.getEdad());
				assertEquals(rs.getString(3), prisionero.getDni());
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
		ArrayList<Prisionero> aPrisionero = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;
		try {
			con = Data.Connection();
			aPrisionero = new ArrayList<>(Prisionero.Select("Claudia", null, null));

			for (Prisionero aPrisioneroIterator : aPrisionero)
				assertEquals("Claudia", aPrisioneroIterator.getNombre());

			aPrisionero = new ArrayList<>(Prisionero.Select(null, "12345678A", null));

			for (Prisionero aPrisioneroIterator : aPrisionero)
				assertEquals("12345678A", aPrisioneroIterator.getDni());

			aPrisionero = new ArrayList<>(Prisionero.Select(null, null, 21));

			for (Prisionero aPrisioneroIterator : aPrisionero)
				assertEquals(21, aPrisioneroIterator.getEdad());

			aPrisionero = new ArrayList<>(Prisionero.Select("Isabel", "12345678B", null));

			for (Prisionero aPrisioneroIterator : aPrisionero) {
				assertEquals("Isabel", aPrisioneroIterator.getNombre());
				assertEquals("12345678B", aPrisioneroIterator.getDni());
			}

			aPrisionero = new ArrayList<>(Prisionero.Select("Isabel", null, 21));

			for (Prisionero aPrisioneroIterator : aPrisionero) {
				assertEquals("Isabel", aPrisioneroIterator.getNombre());
				assertEquals(21, aPrisioneroIterator.getEdad());
			}

			aPrisionero = new ArrayList<>(Prisionero.Select(null, "12345678B", 21));

			for (Prisionero aPrisioneroIterator : aPrisionero) {
				assertEquals("12345678B", aPrisioneroIterator.getDni());
				assertEquals(21, aPrisioneroIterator.getEdad());
			}

			aPrisionero = new ArrayList<>(Prisionero.Select("Claudia", "12345678A", 21));

			for (Prisionero aPrisioneroIterator : aPrisionero) {
				assertEquals("Claudia", aPrisioneroIterator.getNombre());
				assertEquals("12345678A", aPrisioneroIterator.getDni());
				assertEquals(21, aPrisioneroIterator.getEdad());
			}

			aPrisionero = new ArrayList<>(Prisionero.Select(null, null, null));
			rs = con.createStatement().executeQuery("SELECT COUNT(*) FROM Prisionero");
			rs.next();

			assertEquals(rs.getInt(1), aPrisionero.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() throws Exception {
		Connection con = null;
		ResultSet rs = null;
		try {
			con = Data.Connection();
			try {
				Prisionero prisionero = Prisionero.Create("45667823S", "Clara", 23);
				prisionero.setEdad(24);
				prisionero.setNombre("Lola Flores");
				prisionero.setDni("12345678W");
				prisionero.Update();

				rs = con.createStatement()
						.executeQuery("SELECT Nombre, Edad, Dni FROM Prisionero WHERE Dni = '12345678W'");
				rs.next();

				assertEquals(24, rs.getInt("Edad"));
				assertEquals("Lola Flores", rs.getString("Nombre"));
				assertEquals("12345678W", rs.getString("Dni"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException ee) {
			throw ee;
		} finally {
			if (con != null)
				con.close();
			if (rs != null)
				rs.close();
		}
	}

	@Test
	public void testDelete() throws Exception {
		Prisionero prisionero = Prisionero.Create("12345679D", "Carlos", 23);
		prisionero.Delete();
		assertEquals(true, prisionero.getIsDeleted());

	}
}
