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
	private DefaultTableModel model=new DefaultTableModel(new Object[]{"Nombre", "ID", "Precio", "Cantidad", "Proovedor"}, 0);

	
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
			PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM users WHERE user = ? AND password = ? AND Activo='S'");
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
		 Statement stm = conexion.createStatement();
         ResultSet rs = stm.executeQuery("SELECT * FROM inventario WHERE Activo='S'");
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
		 DefaultTableModel model=new DefaultTableModel(new Object[]{"Nombre", "ID", "Empleado", "Movimientos","Cantidad", "Fecha"}, 0);
		 Statement stm = conexion.createStatement();
         ResultSet rs = stm.executeQuery("SELECT * FROM movimientos");
         while (rs.next()) {
        
         String Nombre=rs.getString("Nombre");	 
         String id = rs.getString("ID");
         String Empleado=rs.getString("Empleado");
         String Movimiento= rs.getString("Movimiento");
         int cantidad=rs.getInt("Cantidad");
         Date fecha=rs.getDate("Fecha");
         
         
         
        
        Object []  obj={Nombre,id, Empleado,Movimiento , cantidad ,fecha.toString()};
        
        model.addRow(obj);
			}
         
         desconectarBD();
		return model ;
	}


public void insertarMovimientos(String nombre, String id, String movimiento, int Cantidad) throws SQLException{
	
	//METODO QUE GUARDA LA INFORMACION SOBRE LAS ENTRADAS Y SALIDAS DE LOS PRODUCTOS EN LA BASE DE DATOS
	conectarBD();
	
	
	
	String sql = "INSERT INTO movimientos (Nombre, ID, Empleado, Movimiento, Cantidad, Fecha) VALUES (?,?,?,?,?,?)";
    PreparedStatement sentencia = null;

    sentencia = conexion.prepareStatement(sql);
    
    String Empleado=darNombre();
    java.sql.Date fecha=new java.sql.Date(new java.util.Date().getTime());

    sentencia.setString(1, nombre);
    sentencia.setString(2, id);
    sentencia.setString(3, Empleado);
    sentencia.setString(4, movimiento);
    sentencia.setInt(5, Cantidad);
    sentencia.setDate(6,fecha);

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
	int CantidadR=0;
	int cantidadTotal=0;
	try {
		
		  PreparedStatement sentencia = null;
		  sentencia = conexion.prepareStatement("SELECT * FROM inventario WHERE Nombre_Medicamento=? or ID=?");
		   sentencia.setString(1, producto);
		   sentencia.setString(2, producto);
		   ResultSet rs=sentencia.executeQuery();
		   while(rs.next()){
			   CantidadR=rs.getInt("Cantidad");
		   }
		   
		
		
	} catch (SQLException e1) {
		
		e1.printStackTrace();
	}
	
	if(CantidadR>=cantidad){
	
	for(int x=0;x<modelCaja.getRowCount(); x++){
		
		if(modelCaja.getValueAt(x, 1).toString().equals(producto) || modelCaja.getValueAt(x, 2).toString().equals(producto)){
			System.out.println("PRUEBAAAA UWU");
			cantidadTotal=cantidadTotal+(int)modelCaja.getValueAt(x, 2);
			cantidadTotal=cantidad+cantidadTotal;
			
			
		}
	}
	
	
	
	

	
	
	String sql = "SELECT Nombre_Medicamento,ID,Precio, Activo, Cantidad FROM inventario where Nombre_Medicamento=? or ID=? and Activo='S'";
    PreparedStatement sentencia = null;
    
	try {
		
		
	    sentencia = conexion.prepareStatement(sql);
	    sentencia.setString(1, producto);
	    sentencia.setString(2, producto);
	   
		ResultSet rs=sentencia.executeQuery();
		System.out.println(cantidadTotal);
		while(rs.next()){
			int cant=rs.getInt("Cantidad");
			if(cantidadTotal>cant )suficiente=true;
		    
			if(suficiente==false){	
			band=true;
			String nombre=rs.getString("Nombre_Medicamento");
			int ID=rs.getInt("ID");
			Float Precio=rs.getFloat("Precio");
			Object [] obj={nombre, ID, cantidad, Precio};
			modelCaja.addRow(obj);
			}
			
			
		}
	} catch (SQLException e) {
	
		e.printStackTrace();
	}
	}
	else{
	JOptionPane.showMessageDialog(null, "Error");
	}
	
	
	
	if(band==true)JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
	else JOptionPane.showMessageDialog(null, "No se pudo agregar el producto");
	
	
	desconectarBD();
	
}

public DefaultTableModel returnModel(){
	return modelCaja;
}


public void EliminarProducto(String producto){
	
conectarBD();
	
	
	
	String sql = "UPDATE inventario SET Activo='N' WHERE ID=? or Nombre_Medicamento=?" ;
    PreparedStatement sentencia = null;
    
    try {
		sentencia=conexion.prepareStatement(sql);
		sentencia.setString(1, producto);
		sentencia.setString(2, producto);
		
		int filas=sentencia.executeUpdate();
		
		if(filas>0){
			JOptionPane.showMessageDialog(null, "Se borro correctamente");
		}
		else{
			JOptionPane.showMessageDialog(null, "Error");
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	desconectarBD();
	
}

	public void TerminarVenta(){
		conectarBD();
		
		String nombreP="";
		String producto="";
		String idp="";
		float total=0;
		for(int x=0; x<modelCaja.getRowCount(); x++){
			
			System.out.println(modelCaja.getValueAt(x, 2).toString());
			System.out.println(modelCaja.getValueAt(x, 3).toString());
			
			total=total+(Float.parseFloat(modelCaja.getValueAt(x, 2).toString())*(float)modelCaja.getValueAt(x, 3));
			
			
			
		}
		
		float dinero=Float.parseFloat(JOptionPane.showInputDialog("Total: " + total +"\nEscriba el dinero recibido"));
		
		if(dinero>=total){
		JOptionPane.showMessageDialog(null, "El cambio es: " + (dinero-total));
		int cantidadtotal=0;
	    
	    try {
			
			for(int x=0; x<modelCaja.getRowCount(); x++){
				String sql="SELECT*FROM inventario WHERE ID=?";
				PreparedStatement sentencia = null;
				sentencia=conexion.prepareStatement(sql);
				sentencia.setString(1, modelCaja.getValueAt(x, 1).toString());
				
				ResultSet rs=sentencia.executeQuery();
				
				while(rs.next()){
					nombreP=rs.getString("Nombre_Medicamento");
					idp=rs.getString("ID");
					cantidadtotal=rs.getInt("Cantidad");	
				}
				
				
				
				
				 sql="UPDATE inventario SET Cantidad=? WHERE ID=?";
				 
				 sentencia=conexion.prepareStatement(sql);
				 
				 sentencia.setInt(1, (cantidadtotal-(int)modelCaja.getValueAt(x, 2)));
				 
				 sentencia.setString(2, modelCaja.getValueAt(x, 1).toString());
				 
				 sentencia.executeUpdate();
			    
				 insertarMovimientos(nombreP, idp, "Salida", (int)modelCaja.getValueAt(x,2 ));
				 
				 modelCaja.setRowCount(0);
			    
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		else{
			JOptionPane.showMessageDialog(null, "Error, ingrese una cantidad valida...");
		}
		
		
		
		desconectarBD();
	
	}
	
	
	public void AgregarProovedor(String nombre, String ID, String farmaceutica, String Num_Telef){
		
		
		
	}

}
