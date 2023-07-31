package vista;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Conexion;
import controlador.TestConeccion;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Convertir extends JFrame {

	private JPanel contentPane;
	private JTextField txt_cant;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Convertir frame = new Convertir();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Convertir() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_titulo = new JLabel("Conversor de Moneda");
		lbl_titulo.setFont(new Font("Cooper Black", Font.PLAIN, 16));
		lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titulo.setForeground(new Color(64, 0, 64));
		lbl_titulo.setBounds(117, 11, 197, 49);
		contentPane.add(lbl_titulo);
		
		JLabel lblNewLabel = new JLabel("Ingrese la cantidad de dinero que desea convertir:");
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 12));
		lblNewLabel.setBounds(51, 57, 344, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Elije la moneda  a la que deseas convertir tu dinero");
		lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD, 12));
		lblNewLabel_1.setBounds(51, 130, 344, 30);
		contentPane.add(lblNewLabel_1);
		
		txt_cant = new JTextField();
		txt_cant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				
				boolean num =  key >=48 && key <=57;
				
				if(!num) {
					e.consume();
				}
			}
		});
		txt_cant.setBounds(157, 91, 120, 28);
		contentPane.add(txt_cant);
		txt_cant.setColumns(10);
		
		JComboBox CBox_moneda = new JComboBox();
		TestConeccion hp = new TestConeccion();
			CBox_moneda.setModel(hp.getavalues());
			
		
		//CBox_moneda.setModel(new DefaultComboBoxModel(new String[] {"Dolar", "Euro", "Yen"}));
		CBox_moneda.setBounds(168, 171, 109, 22);
		contentPane.add(CBox_moneda);
		
		JButton btnNewButton = new JButton("Convertir");
		btnNewButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				
				String variable = CBox_moneda.getSelectedItem().toString();
				Double cant = Double.parseDouble(txt_cant.getText());
				
				Conexion conexion = new Conexion();
				conexion.conectar();
				Connection cn =null;
				Statement stm =null;
				ResultSet rs =null;
				Double num = null;
				
				try {
					cn = conexion.conectar();
					stm = cn.createStatement();
					rs = stm.executeQuery("SELECT NUM FROM TIPO WHERE ID='"+ variable +"'");

					if(rs.next()) {
						num = rs.getDouble("num");
					}
						//JOptionPane.showMessageDialog(null, num);				
						cn.close();
						rs.close();
				
				} catch (Exception ex) {
					// TODO: handle exception
				}
						
				
				//JOptionPane.showMessageDialog(null, "El tipo de cambio es: "+ cant*num+ " "+variable);
				JOptionPane.showMessageDialog(null, "El tipo de cambio es: "+ cant*num);
			}
		});
		btnNewButton.setBounds(178, 204, 89, 23);
		contentPane.add(btnNewButton);
	}
}
