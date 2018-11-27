package es.uca.gii.csi18.drogo.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.omg.PortableServer.AdapterActivator;

import es.uca.gii.csi18.drogo.data.Prisionero;

/**
 * @author isa
 *
 */
public class PrisionerosTableModel extends AbstractTableModel {
	private ArrayList<Prisionero> _aData;
	
	/**
	 * @param aData
	 */
	public PrisionerosTableModel(ArrayList<Prisionero> aData) {
		_aData = new ArrayList<Prisionero>(aData);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return 3;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return _aData.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int iRow, int iCol) {
		switch(iCol) {
		case 0: return _aData.get(iRow).getDni();
		case 1: return _aData.get(iRow).getNombre();
		case 2: return _aData.get(iRow).getEdad();
		}
		
		return null;
	}

	/**
	 * @param iRow
	 * @return
	 */
	public Prisionero getData(int iRow) {
		return _aData.get(iRow);
	}

}
