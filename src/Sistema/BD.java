package Sistema;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BD {
	private static String user;
	private static String Nombre;
	private static int nivel;
	private static DefaultTableModel modelCaja=new DefaultTableModel(new Object[]{"Nombre", "ID", "Cantidad", "Precio"}, 0);

	public BD(){
		
	}

 	private Connection conexion=null;
 	private Statement stm=null;
	
	public void conectarBD(){
		
		//METODO QUE CONECTA LA BD
		
		
		String url="jdbc:mysql://localhost:3306/bd_farmacia";
		String user="root";
		 String password="";
		try{
			conexion=DriverManager.getConnection(url,user,password);
			System.out.println("Se pudo conectar");		
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Error al conectarse, compruebe nuevamente o reinicie la aplicacion");
			System.out.println("Fallo al conectarse");
		}
	}
	
	public void desconectarBD(){
		
		//METODO QUE DESCONECTA LA BD
		  try {
			conexion.close();
			System.out.println("Cerro la conexion");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	public boolean buscarUser(String user, String password){
		//METODO QUE BUSCA Y VERIFICA SI EL USUARIO Y CONTRASEÑA ES CORRECTA, SE USA EN EL LOGIN
		
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
		} catch (Exception e) {
			
		}

		
		
		
		return esUser;
	}
	
	public String darNombre(){
		
		//METODO QUE ME DEVUELVE EL NOMBRE DEL USUARIO QUE TIENE INCIADA LA SESION PARA MOSTRARLO EN LA INTERFAZ GRAFICA
		 
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
		//METODO QUE ME DEVUELVE EL NIVEL (1=USUARIO NORMAL, 2=ADMINISTRADOR) PARA DESPUES VERIFICAR Y MOSTRAR COSAS DE ADMINISTRADOR O USUARIO NORMAL
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
		
		//METODO QUE DEVUELVE UN MODELO DE UN JTABLE QUE AGARRA LOS DATOS DE LA BASE DE DATOS PARA DESPUES MOSTRARLO EN LA INTERFAZ
		conectarBD();
		 DefaultTableModel model=new DefaultTableModel(new Object[]{"Nombre", "ID", "Precio", "Cantidad", "Proovedor"}, 0);
		 Statement stm = conexion.createStatement();
         ResultSet rs = stm.executeQuery("SELECT * FROM inventario");
         while (rs.next()) {
        String id = rs.getString("ID");
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
	
	public void AgregarEmpleado(String nombre, String a_p, String a_m, String telef, String direccion){
		
		//METODO QUE SE USA PARA RECIBIR LOS DATOS DE UN NUEVO EMPLEADO Y GUARDARLO EN LA BASE DE DATOS
		
		conectarBD();
		
		String sql="INSERT INTO empleados(Nombre,Apellido_P, Apellido_M, Telefono, Direccion) VALUES(?,?,?,?,?)";
        PreparedStatement sentencia = null;
        
        try {
			sentencia=conexion.prepareStatement(sql);
			
			sentencia.setString(1, nombre);
			sentencia.setString(2, a_p);
			sentencia.setString(3, a_m);
			sentencia.setString(4, telef);
			sentencia.setString(5, direccion);
			 int filasAfectadas = sentencia.executeUpdate();
			 if(filasAfectadas>0){
				 JOptionPane.showMessageDialog(null, "Se agregaron los datos correctamente");
			 }
			 else{
				 JOptionPane.showMessageDialog(null, "Hubo un error al ingresar los datos");
				 
			 }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		desconectarBD();
		
		
	}
	
	public void crearUser(String nombre ,String apellido, boolean admi){
		
		// METODO QUE CREA USUARIOS DE FORMA AUTOMATICA CUANDO SE REGISTRA UN NUEVO EMPLEADO, AL FINAL TE MUESTRA EL USUARIO Y CONTRASEÑA
		conectarBD();
		String User=nombre.replaceAll(" ", "");
		String sql="SELECT*FROM users WHERE user=?";
		PreparedStatement sentencia=null;
		ResultSet result=null;
		int nivel=1;
		boolean existe=false;
		try {
			sentencia=conexion.prepareStatement(sql);
			sentencia.setString(1, User);
			System.out.println(User + " aaaaaaaaaaaaaaa");

			result=sentencia.executeQuery();
			
			
			if(result.next()){
				existe=true;
				System.out.println(User);
			}
			
			if(existe==true){
				
				
				int posicionEspacio = nombre.indexOf(" ");
				sentencia=null;
				String nombreCambiado=nombre.substring(0, posicionEspacio);
				
				posicionEspacio=apellido.indexOf(" ");
				
				String apellidoCambiado=apellido.substring(0, posicionEspacio);
				
				User=nombreCambiado + apellidoCambiado;
				int LONGITUD_CONTRASENA = 10;
				SecureRandom random = new SecureRandom();
		        byte[] bytes = new byte[LONGITUD_CONTRASENA];
		        random.nextBytes(bytes);
		        String contrasena = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
				
		        if(admi==true)nivel=2;
				sql="INSERT INTO users(user, Nombre_User, password,nivel) VALUES(?,?,?,?)";
				sentencia=conexion.prepareStatement(sql);
				sentencia.setString(1, User);
				sentencia.setString(2, nombre);
				sentencia.setString(3, contrasena);
				sentencia.setInt(4, nivel);	
			    sentencia.executeUpdate();
			    JOptionPane.showMessageDialog(null, "User: " + User + ""
		         		+ "\nContraseña: " + contrasena);
			

			}
			else{
				int LONGITUD_CONTRASENA = 10;
				SecureRandom random = new SecureRandom();
		        byte[] bytes = new byte[LONGITUD_CONTRASENA];
		        random.nextBytes(bytes);
		        String contrasena = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
				
		        if(admi==true)nivel=2;
		        
		       
				sql="INSERT INTO users(user,Nombre_User, password,nivel) VALUES(?,?,?,?)";
				sentencia=null;
				sentencia=conexion.prepareStatement(sql);
				sentencia.setString(1, User);
				sentencia.setString(2, nombre);
				sentencia.setString(3, contrasena);
				sentencia.setInt(4, nivel);	
				
				
		        sentencia.executeUpdate();
		         JOptionPane.showMessageDialog(null, "User: " + User + ""
		         		+ "\nContraseña: " + contrasena);

		         
			}
			
		} catch (Exception uwu) {
			
			System.out.println(uwu);
			
		}
		
		desconectarBD();
		
	}
	public void agregarInventario(String Nombre, String ID, float Precio, int cantidad, String proovedor ) throws SQLException{
		
		//METODO QUE RECIBE LOS DATOS QUE SE METEN EN LA INTERFAZ PARA GUARDARLO EN LA BASE DE DATOS
		
		conectarBD();
		 String sql = "INSERT INTO inventario (Nombre_Medicamento, ID, Cantidad, Precio, Proveedor) VALUES (?, ?, ?,?,?)";
	        PreparedStatement sentencia = null;

         sentencia = conexion.prepareStatement(sql);

   
         sentencia.setString(1, Nombre);
         sentencia.setString(2, ID);
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
	
	
	// ----------------------- MOVIMIENTOS------------------------------------------------
public DefaultTableModel tablaMovimientos() throws SQLException{
		
	//METODO QUE DEVUELVE UN MODELO DE UN JTABLE QUE AGARRA LOS DATOS DE UNA TABLA DE BD, PARA POSTERIORMENTE MOSTRARLA EN LA INTERFAZ GRAFICA
		
		conectarBD();
		 DefaultTableModel model=new DefaultTableModel(new Object[]{"Nombre", "ID", "Empleado", "Movimientos", "Fecha"}, 0);
		 Statement stm = conexion.createStatement();
         ResultSet rs = stm.executeQuery("SELECT * FROM movimientos");
         while (rs.next()) {
        
         String Nombre=rs.getString("Nombre");	 
         String id = rs.getString("ID");
         String Empleado=rs.getString("Empleado");
         String Movimiento= rs.getString("Movimiento");
         Date fecha=rs.getDate("Fecha");
         
         
         
        
        Object []  obj={Nombre,id, Empleado,Movimiento ,fecha.toString()};
        
        model.addRow(obj);
			}
         
         desconectarBD();
		return model ;
	}


public void insertarMovimientos(String nombre, String id, String movimiento) throws SQLException{
	
	//METODO QUE GUARDA LA INFORMACION SOBRE LAS ENTRADAS Y SALIDAS DE LOS PRODUCTOS EN LA BASE DE DATOS
	conectarBD();
	
	
	
	String sql = "INSERT INTO movimientos (Nombre, ID, Empleado, Movimiento, Fecha) VALUES (?,?,?,?,?)";
    PreparedStatement sentencia = null;

    sentencia = conexion.prepareStatement(sql);
    
    String Empleado=darNombre();
    java.sql.Date fecha=new java.sql.Date(new java.util.Date().getTime());

    sentencia.setString(1, nombre);
    sentencia.setString(2, id);
    sentencia.setString(3, Empleado);
    sentencia.setString(4, movimiento);
    sentencia.setDate(5,fecha);

    int filasAfectadas = sentencia.executeUpdate();

    
    if (filasAfectadas > 0) {
        System.out.println("Datos agregados correctamente a la tablaaaaaaaaaaaaaaaaaa.");
    } else {
        System.out.println("No se pudo agregar los datos a la tablaaaaaaaaaaaa.");
    }
	desconectarBD();
	
	
}


// ---------------------------------------------- CAJA-------------------------------------------------------------------
public void agregarCaja(String producto, int cantidad){
	conectarBD();
	boolean suficiente=false;
	boolean band=false;
	
	
	String sql = "SELECT Nombre_Medicamento,ID,Precio FROM inventario where Nombre_Medicamento=? or ID=?";
    PreparedStatement sentencia = null;
    
	try {
		
		
	    sentencia = conexion.prepareStatement(sql);
	    sentencia.setString(1, producto);
	    sentencia.setString(2, producto);
	   
		ResultSet rs=sentencia.executeQuery();
	
		while(rs.next()){
			
			
			
			
			
			
			
			
			band=true;
			String nombre=rs.getString("Nombre_Medicamento");
			int ID=rs.getInt("ID");
			Float Precio=rs.getFloat("Precio");
			Object [] obj={nombre, ID, cantidad, Precio};
			modelCaja.addRow(obj);
		}
	} catch (SQLException e) {
	
		e.printStackTrace();
	}
	
	
	
	if(band==true)JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
	else JOptionPane.showMessageDialog(null, "No se pudo agregar el producto");
	
	
	desconectarBD();
	
}

public DefaultTableModel returnModel(){
	return modelCaja;
}
	

}
