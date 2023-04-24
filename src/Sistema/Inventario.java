package Sistema;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class Inventario extends JPanel {
	private JTable table;

	private static int nivel;
	private JTextField txtNombre;
	private JTextField txtID;
	private JTextField txtCant;
	private JTextField txtPrecio;
	private JTextField txtBuscar;
	private DefaultTableModel model = new DefaultTableModel();

	public Inventario() {
		
		BD bd=new BD();
		setLayout(null);
		this.setBounds(1, 1,1098, 697);
		Color gris=new Color(196,196,196);
		setBackground(gris);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(591, 39, 497, 531);
		add(scrollPane);
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"uWu", new Integer(12), new Float(10.0f), new Integer(12), null},
				{"asd", new Integer(12), new Float(100.0f), new Integer(12), null},
			},
			new String[] {
				"Nombre", "ID", "Precio", "Cantidad", "Proovedor"
			}
		));
		table.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(table);
		bd.conectarBD();
		
		try {
			model=bd.tablaInventario();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table.setModel(model);
		
		table.setEnabled(false);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(36, 68, 72, 14);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(146, 65, 159, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(36, 134, 46, 14);
		add(lblId);
		
		txtID = new JTextField();
		txtID.setBounds(146, 128, 159, 20);
		add(txtID);
		txtID.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(36, 202, 57, 14);
		add(lblCantidad);
		
		txtCant = new JTextField();
		txtCant.setBounds(146, 199, 159, 20);
		add(txtCant);
		txtCant.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(36, 282, 46, 14);
		add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(146, 279, 159, 20);
		add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel lblProovedor = new JLabel("Proovedor");
		lblProovedor.setBounds(36, 370, 90, 14);
		add(lblProovedor);
		
		JComboBox cboxProo = new JComboBox();
		cboxProo.setModel(new DefaultComboBoxModel(new String[] {"Farmacia UwU"}));
		cboxProo.setBounds(146, 367, 159, 20);
		add(cboxProo);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ID=Integer.parseInt(txtID.getText());
				float Precio=Float.parseFloat(txtPrecio.getText());
				int cantidad=Integer.parseInt(txtCant.getText());
				
				
				try {
					bd.agregarInventario(txtNombre.getText(), ID, Precio, cantidad, cboxProo.getSelectedItem().toString());
					model=bd.tablaInventario();
					table.setModel(model);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnAgregar.setBounds(162, 461, 122, 23);
		add(btnAgregar);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(682, 593, 336, 20);
		add(txtBuscar);
		txtBuscar.setColumns(10);
		table.getTableHeader().setReorderingAllowed(false);
       
		nivel=bd.darNivel();
	

	
	
	}
	
	public void objAdmi(){
		
	}
}
