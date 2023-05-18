package Sistema;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class newEmpleado extends JPanel {
	
	private BD bd=new BD();
	private JTextField txtNombre;
	private JTextField txtA_P;
	private JTextField txtA_M;
	private JTextField txtTelefono;
	private JTextField txtDireccion;

	private JCheckBox chckbxPermisosDeAdministrador = new JCheckBox("Permisos de Administrador");

	public newEmpleado() {
		setBounds(0, 0, 723, 675);
		Color gris=new Color(196,196,196);
		setBackground(gris);
		setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(274, 47, 204, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(97, 50, 86, 14);
		add(lblNombre);
		
		JLabel lblA_P = new JLabel("Apellido Paterno");
		lblA_P.setBounds(97, 138, 151, 14);
		add(lblA_P);
		
		txtA_P = new JTextField();
		txtA_P.setColumns(10);
		txtA_P.setBounds(274, 135, 204, 20);
		add(txtA_P);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido Materno");
		lblApellidoMaterno.setBounds(97, 224, 151, 14);
		add(lblApellidoMaterno);
		
		txtA_M = new JTextField();
		txtA_M.setColumns(10);
		txtA_M.setBounds(274, 221, 204, 20);
		add(txtA_M);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(97, 316, 151, 14);
		add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(274, 313, 204, 20);
		add(txtTelefono);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(97, 417, 151, 14);
		add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(274, 414, 309, 20);
		add(txtDireccion);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtNombre.getText().isEmpty()==false || txtA_P.getText().isEmpty()==false || txtA_M.getText().isEmpty()==false || txtTelefono.getText().isEmpty()==false || txtDireccion.getText().isEmpty()==false){
					
					try{
						String nombre=txtNombre.getText();
						String a_p=txtA_P.getText();
						String a_m=txtA_M.getText();
						String telef=txtTelefono.getText();
						String direccion=txtDireccion.getText();				
						bd.AgregarEmpleado(nombre, a_p, a_m, telef, direccion);
						
						txtNombre.setText("");
						txtA_P.setText("");
						txtA_M.setText("");
						txtTelefono.setText("");
						txtDireccion.setText("");
						
						bd.crearUser(nombre, a_p,chckbxPermisosDeAdministrador.isSelected());
					}
					catch(Exception uwu){
						System.out.println(uwu);
						JOptionPane.showMessageDialog(null, "Error al ingresar los datos, intente de nuevo");
					}
				}
			}
		});
		btnAgregar.setBounds(254, 534, 204, 23);
		add(btnAgregar);
		
		chckbxPermisosDeAdministrador.setBounds(254, 473, 211, 23);
		add(chckbxPermisosDeAdministrador);
		
		
		
		
	}
}
