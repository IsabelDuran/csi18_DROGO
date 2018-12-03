package es.uca.gii.csi18.drogo.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author isa
 *
 */
public class FrmMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain window = new FrmMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public FrmMain() throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Gestion de la carcel");
		frame.setBounds(200, 200, 800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mitNuevo = new JMenu("Nuevo");
		menuBar.add(mitNuevo);

		JMenuItem mitNuevoPrisionero = new JMenuItem("Prisionero");
		mitNuevoPrisionero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IfrPrisionero ifrPrisionero;
				try {
					ifrPrisionero = new IfrPrisionero(null);
					ifrPrisionero.setBounds(0, 0, 650, 500);
					frame.getContentPane().add(ifrPrisionero);
					ifrPrisionero.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mitNuevo.add(mitNuevoPrisionero);

		JMenu mitBuscar = new JMenu("Buscar");
		menuBar.add(mitBuscar);

		JMenuItem mitBuscarPrisionero = new JMenuItem("Prisionero");
		mitBuscarPrisionero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IfrPrisioneros ifrPrisioneros;
				try {
					ifrPrisioneros = new IfrPrisioneros(frame);
					ifrPrisioneros.setBounds(12, 28, 244, 192);
					frame.getContentPane().add(ifrPrisioneros, 0);
					ifrPrisioneros.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mitBuscar.add(mitBuscarPrisionero);
		frame.getContentPane().setLayout(null);
	}

}