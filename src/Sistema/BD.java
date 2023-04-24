package Sistema;
import java.sql.*;

public class BD {
	private static String user;
	private static String Nombre;
	private static int nivel;
	public BD(){
		
	}
	
 	private Connection conexion=null;
 	private Statement stm=null;
	
	public void conectarBD(){
		String url="jdbc:mysql://localhost:3306/bd_farmacia";
		String user="root";
		 String password="";
		try{
			conexion=DriverManager.getConnection(url,user,password);
			System.out.println("Se pudo conectar");		
		}
		catch(SQLException e){
			System.out.println("Fallo al conectarse");
		}
	}
	
	public void desconectarBD(){
		  try {
			conexion.close();
			System.out.println("Cerro la conexion");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public boolean buscarUser(String user, String password){
		this.user=user;
		boolean esUser=false;
		 try {
			PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM users WHERE user = ? AND password = ?");
			stmt.setString(1, user);
		    stmt.setString(2, password);
		    ResultSet rs = stmt.executeQuery();
		    
		    if(rs.next()){
		    	esUser=true;
		    
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		
		
		return esUser;
	}
	
	public String darNombre(){
		try {
			PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM users WHERE user = ?");
			stmt.setString(1, user);
		    ResultSet rs = stmt.executeQuery();   
		    if(rs.next()){
		    	Nombre = rs.getString("Nombre_User");
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Nombre;
	}
	
	public int darNivel(){
	
		try {
			PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM users WHERE user = ?");
			stmt.setString(1, user);
		    ResultSet rs = stmt.executeQuery();   
		    if(rs.next()){
		    	
		    	nivel = rs.getInt("Nivel");
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nivel;
	}
	
	
}
