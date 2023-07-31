package controlador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public static final String url ="jdbc:mysql://localhost:3306/divisa";
	public static final String Username = "root";
	public static final String psw = "";
	public static final String controlador = "com.mysql.cj.jdbc.Driver";
	static {
		try {
			Class.forName(controlador);
		} catch (ClassNotFoundException e) {
			System.out.println("Error controlador!");
			e.printStackTrace();
		}
	}	
	
	public Connection conectar() {
		Connection conexion = null;
		try {			
			conexion = DriverManager.getConnection(url, Username,psw);
			//System.out.println("Ok!1!");
		} catch (SQLException e) {
			System.out.println("Error conexion");
		}
		return conexion;
	}
	

}
