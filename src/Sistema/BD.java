package Sistema;
import java.sql.*;

import javax.swing.table.DefaultTableModel;

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
		conectarBD();
		
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
		
		desconectarBD();

		return Nombre;
	}
	
	public int darNivel(){
		conectarBD();

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
		desconectarBD();

		return nivel;
	}
	
	public DefaultTableModel tablaInventario() throws SQLException{
		
		conectarBD();
		 DefaultTableModel model=new DefaultTableModel(new Object[]{"Nombre", "ID", "Precio", "Cantidad", "Proovedor"}, 0);
		 Statement stm = conexion.createStatement();
         ResultSet rs = stm.executeQuery("SELECT * FROM inventario");
         while (rs.next()) {
         int id = rs.getInt("ID");
         int cantidad=rs.getInt("Cantidad");
         String nombre_medi = rs.getString("Nombre_Medicamento");
         float Precio = rs.getFloat("Precio");
         String proovedor = rs.getString("Proveedor");
        
        Object []  obj={nombre_medi,id,Precio,cantidad,proovedor};
        
        model.addRow(obj);
			}
         
         desconectarBD();
		return model ;
	}
	
	public void agregarInventario(String Nombre, int ID, float Precio, int cantidad, String proovedor ) throws SQLException{
		
		conectarBD();
		 String sql = "INSERT INTO inventario (Nombre_Medicamento, ID, Cantidad, Precio, Proveedor) VALUES (?, ?, ?,?,?)";
	        PreparedStatement sentencia = null;

   
         sentencia = conexion.prepareStatement(sql);

   
         sentencia.setString(1, Nombre);
         sentencia.setInt(2, ID);
         sentencia.setInt(3, cantidad);
         sentencia.setFloat(4, Precio);
         sentencia.setString(5, proovedor);

       
         int filasAfectadas = sentencia.executeUpdate();

       
         if (filasAfectadas > 0) {
             System.out.println("Datos agregados correctamente a la tabla.");
         } else {
             System.out.println("No se pudo agregar los datos a la tabla.");
         }
         
         
		
	}
}
