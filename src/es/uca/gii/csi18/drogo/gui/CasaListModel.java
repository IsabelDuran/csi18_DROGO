package es.uca.gii.csi18.drogo.gui;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import es.uca.gii.csi18.drogo.data.*;

/**
 * @author isa
 *
 */
public class CasaListModel extends AbstractListModel<Casa> implements ComboBoxModel<Casa> {
	private List<Casa> _aData;
	private Object _selection = null;

	/**
	 * @param aData
	 */
	public CasaListModel(List<Casa> aData) {
		_aData = aData;
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Casa getElementAt(int iIndex) {
		return _aData.get(iIndex);
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return _aData.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#setSelectedItem(java.lang.Object)
	 */
	public void setSelectedItem(Object o) {
		_selection = o;
	}

	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#getSelectedItem()
	 */
	public Object getSelectedItem() {
		return _selection;
	}
}
