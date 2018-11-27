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

import es.uca.gii.csi18.drogo.data.Prisionero;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

	/**
	 * Create the frame.
	 */
	/**
	 * @param frame
	 */
	public IfrPrisioneros(JFrame frame) {
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

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tabResult.setModel(new PrisionerosTableModel(Prisionero.Select(txtNombre.getText().isEmpty() ? null : txtNombre.getText()
																,txtDni.getText().isEmpty() ? null : txtDni.getText(), 
																txtEdad.getText().isEmpty() ? null : Integer.parseInt(txtEdad.getText()))));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(btnBuscar);

		tabResult = new JTable();
		tabResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int iRow = ((JTable)e.getSource()).getSelectedRow();
					
					Prisionero prisionero = ((PrisionerosTableModel)tabResult.getModel()).getData(iRow);
					
					if(prisionero != null) {
						IfrPrisionero ifrPrisionero = new IfrPrisionero(prisionero);
						ifrPrisionero.setBounds(10, 27, 244, 192);
						pnlParent.add(ifrPrisionero, 0);
						ifrPrisionero.setVisible(true);
					}
				}
			}
		});
		getContentPane().add(tabResult, BorderLayout.CENTER);

	}
}
