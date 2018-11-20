package es.uca.gii.csi18.drogo.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTable;

public class IfrPrisioneros extends JInternalFrame {
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private JTable tabResult;

	/**
	 * Create the frame.
	 */
	public IfrPrisioneros() {
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
		panel.add(btnBuscar);
		
		tabResult = new JTable();
		getContentPane().add(tabResult, BorderLayout.CENTER);

	}
}
