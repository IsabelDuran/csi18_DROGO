package es.uca.gii.csi18.drogo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import es.uca.gii.csi18.drogo.data.Prisionero;

public class IfrPrisionero extends JInternalFrame {
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private Prisionero _prisionero = null;

	/**
	 * Create the frame.
	 */
	public IfrPrisionero() {
		setResizable(true);
		setClosable(true);
		setTitle("Prisionero");
		setBounds(100, 100, 650, 500);
		getContentPane().setLayout(null);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(10, 11, 46, 14);
		getContentPane().add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(10, 36, 86, 20);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 67, 46, 14);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 92, 86, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(10, 123, 46, 14);
		getContentPane().add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setBounds(10, 148, 86, 20);
		getContentPane().add(txtEdad);
		txtEdad.setColumns(10);
		
		JButton butGuardar = new JButton("Guardar");
		butGuardar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
					if(_prisionero == null) {
						_prisionero = Prisionero.Create(txtDni.getText(), txtNombre.getText(), Integer.parseInt(txtEdad.getText()));
					}else {
						_prisionero.setDni(txtDni.getText());
						_prisionero.setNombre(txtNombre.getText());
						_prisionero.setEdad(Integer.parseInt(txtEdad.getText()));
						_prisionero.Update();
					}
				}catch (Exception ee) {
				JOptionPane.showMessageDialog(null, "No se ha creado el objeto prisionero", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
			
		});
		butGuardar.setBounds(335, 236, 89, 23);
		getContentPane().add(butGuardar);

	}

}
