package controlador;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.plaf.synth.SynthOptionPaneUI;

public class TestConeccion extends Conexion{

	public DefaultComboBoxModel getavalues() {
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		Conexion conexion = new Conexion();
		conexion.conectar();
		Connection cn =null;
		Statement stm =null;
		ResultSet rs =null;
		
	try {
		cn = conexion.conectar();
		stm = cn.createStatement();
		rs = stm.executeQuery("SELECT ID FROM TIPO");

		while(rs.next()) {
			modelo.addElement(rs.getString(1));
		}
			cn.close();
			rs.close();
		}catch (Exception e) {
			System.out.println(e);
		}		
		return modelo;
	}
	
	
	
}

/*
	public static void main(String[] args) {
		Conexion conexion = new Conexion();
		conexion.conectar();
		Connection cn =null;
		Statement stm =null;
		ResultSet rs =null;
		
	try {
		cn = conexion.conectar();
		stm = cn.createStatement();
		rs = stm.executeQuery("SELECT ID, NUM FROM TIPO");
//		rs = stm.executeQuery("SELECT NUM FROM TIPO WHERE ID='USD'");
		
		while (rs.next()) {
			String id =  rs.getString(1); 
			Float num = rs.getFloat(2);
			System.out.println(num + " "+ id);
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}
*/