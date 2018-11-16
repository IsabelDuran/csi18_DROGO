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
			Prisionero prisionero = Prisionero.Create("12345678J", "Pedro", 24);
			rs = con.createStatement().executeQuery("SELECT Nombre, Edad, ID FROM Prisionero WHERE " + "ID="
					+ Data.String2Sql(prisionero.getID(), true, false));
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
		ArrayList<Prisionero> aPrisionero = new ArrayList<>();

		try {
			aPrisionero = new ArrayList<>(Prisionero.Select("Claudia", null, null));

			for (Prisionero aPrisioneroIterator : aPrisionero)
				assertEquals("Claudia", aPrisioneroIterator.getNombre());

			aPrisionero = new ArrayList<>(Prisionero.Select(null, "12345678A", null));

			for (Prisionero aPrisioneroIterator : aPrisionero)
				assertEquals("12345678A", aPrisioneroIterator.getID());

			aPrisionero = new ArrayList<>(Prisionero.Select(null, null, 21));

			for (Prisionero aPrisioneroIterator : aPrisionero)
				assertEquals(21, aPrisioneroIterator.getEdad());

			aPrisionero = new ArrayList<>(Prisionero.Select("Isabel", "12345678B", null));

			for (Prisionero aPrisioneroIterator : aPrisionero) {
				assertEquals("Isabel", aPrisioneroIterator.getNombre());
				assertEquals("12345678B", aPrisioneroIterator.getID());
			}

			aPrisionero = new ArrayList<>(Prisionero.Select("Isabel", null, 21));

			for (Prisionero aPrisioneroIterator : aPrisionero) {
				assertEquals("Isabel", aPrisioneroIterator.getNombre());
				assertEquals(21, aPrisioneroIterator.getEdad());
			}

			aPrisionero = new ArrayList<>(Prisionero.Select(null, "12345678B", 21));

			for (Prisionero aPrisioneroIterator : aPrisionero) {
				assertEquals("12345678B", aPrisioneroIterator.getID());
				assertEquals(21, aPrisioneroIterator.getEdad());
			}

			aPrisionero = new ArrayList<>(Prisionero.Select("Claudia", "12345678A", 21));

			for (Prisionero aPrisioneroIterator : aPrisionero) {
				assertEquals("Claudia", aPrisioneroIterator.getNombre());
				assertEquals("12345678A", aPrisioneroIterator.getID());
				assertEquals(21, aPrisioneroIterator.getEdad());
			}

			aPrisionero = new ArrayList<>(Prisionero.Select(null, null, null));

			assertEquals(3, aPrisionero.size());
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
				Prisionero prisionero = Prisionero.Create("12345678G", "Carlos", 23);
				prisionero.setEdad(24);
				prisionero.setNombre("Lola Flores");
				prisionero.setID("12345678W");
				prisionero.Update();

				rs = con.createStatement()
						.executeQuery("SELECT Nombre, Edad, ID FROM Prisionero WHERE ID = '12345678W'");
				rs.next();

				assertEquals(24, rs.getInt("Edad"));
				assertEquals("Lola Flores", rs.getString("Nombre"));
				assertEquals("12345678W", rs.getString("ID"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException ee) {
			throw ee;
		} finally {
			con.close();
			rs.close();
		}
	}

	@Test
	public void testDelete() throws Exception {
		Prisionero prisionero = Prisionero.Create("12345678G", "Carlos", 23);
		prisionero.Delete();
		assertEquals(true, prisionero.getIsDeleted());

	}
}
