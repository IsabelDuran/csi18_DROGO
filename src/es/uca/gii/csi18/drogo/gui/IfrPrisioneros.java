package es.uca.gii.csi18.drogo.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import es.uca.gii.csi18.drogo.data.Casa;
import es.uca.gii.csi18.drogo.data.Prisionero;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

/**
 * @author isa
 *
 */
public class IfrPrisioneros extends JInternalFrame {
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private JTable tabResult;
	private Container pnlParent;
	private JComboBox<Casa> cmbCasa;

	/**
	 * Create the frame.
	 */
	/**
	 * @param frame
	 * @throws Exception
	 */
	public IfrPrisioneros(JFrame frame) throws Exception {

		pnlParent = frame;
		setResizable(true);
		setClosable(true);
		setTitle("Prisioneros");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(4, 0, 0, 0));

		JLabel lblDni = new JLabel("Dni");
		panel.add(lblDni);
		txtDni = new JTextField();
		panel.add(txtDni);
		txtDni.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		panel.add(lblNombre);
		txtNombre = new JTextField();
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblEdad = new JLabel("Edad");
		panel.add(lblEdad);
		txtEdad = new JTextField();
		panel.add(txtEdad);
		txtEdad.setColumns(10);

		JLabel lblCasa = new JLabel("Casa");
		panel.add(lblCasa);
		JComboBox cmbCasa = new JComboBox();
		cmbCasa.setModel(new CasaListModel(Casa.Select()));
		cmbCasa.setEditable(true);
		panel.add(cmbCasa);

		tabResult = new JTable();
		tabResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int iRow = ((JTable) e.getSource()).getSelectedRow();
					Prisionero Prisionero = ((PrisionerosTableModel) tabResult.getModel()).getData(iRow);
					if (Prisionero != null) {
						try {
							IfrPrisionero ifrPrisionero = new IfrPrisionero(Prisionero);
							ifrPrisionero.setBounds(10, 27, 300, 250);
							pnlParent.add(ifrPrisionero, 0);
							ifrPrisionero.setVisible(true);
						} catch (Exception a) {
						}
					}
				}
			}
		});

		getContentPane().add(tabResult, BorderLayout.CENTER);

		JButton btnBuscar = new JButton("Buscar");
		getContentPane().add(btnBuscar, BorderLayout.SOUTH);
		btnBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					String sDni;
					if (!txtDni.getText().isEmpty())
						sDni = txtDni.getText();
					else
						sDni = null;

					String sNombre;
					if (!txtNombre.getText().isEmpty())
						sNombre = txtNombre.getText();
					else
						sNombre = null;

					int iEdad;
					if (!txtEdad.getText().isEmpty())
						iEdad = Integer.parseInt(txtEdad.getText());
					else
						iEdad = (Integer) null;

					tabResult.setModel((TableModel) new PrisionerosTableModel(Prisionero.Select(
							cmbCasa.getSelectedItem() == null ? null : cmbCasa.getSelectedItem().toString(), sNombre,
							sDni, iEdad)));

				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});

	}
}
