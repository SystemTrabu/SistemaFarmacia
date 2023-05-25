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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class newEmpleado extends JPanel {
	
	private BD bd=new BD();
	private JTextField txtNombre;
	private JTextField txtA_P;
	private JTextField txtA_M;
	private JTextField txtTelefono;
	private JTextField txtDireccion;

	private JCheckBox chckbxPermisosDeAdministrador = new JCheckBox("Permisos de Administrador");
	private JTable table;

	public newEmpleado() {
		setBounds(0, 0, 723, 675);
		Color gris=new Color(196,196,196);
		setBackground(gris);
		setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(222, 47, 173, 20);
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
		txtA_P.setBounds(222, 135, 173, 20);
		add(txtA_P);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido Materno");
		lblApellidoMaterno.setBounds(97, 224, 151, 14);
		add(lblApellidoMaterno);
		
		txtA_M = new JTextField();
		txtA_M.setColumns(10);
		txtA_M.setBounds(222, 221, 173, 20);
		add(txtA_M);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(97, 316, 151, 14);
		add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(222, 313, 173, 20);
		add(txtTelefono);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(97, 417, 151, 14);
		add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(222, 414, 173, 20);
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
						
						table.setModel(bd.modelEmpleado());
					}
					catch(Exception uwu){
						System.out.println(uwu);
						JOptionPane.showMessageDialog(null, "Error al ingresar los datos, intente de nuevo");
					}
				}
			}
		});
		btnAgregar.setBounds(172, 536, 204, 23);
		add(btnAgregar);
		
		chckbxPermisosDeAdministrador.setBounds(172, 472, 211, 23);
		add(chckbxPermisosDeAdministrador);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(425, 47, 288, 458);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Telefono"
			}
		));
		scrollPane.setViewportView(table);
		
		table.setModel(bd.modelEmpleado());
		
		
		
		
	}
}
