package Sistema;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class newProovedor extends JPanel {
	private JTextField txtNombre;
	private JTextField txtNum;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	private BD bd=new BD();

	public newProovedor() {
		this.setBounds(0,0 ,723, 675);
		Color gris=new Color(196,196,196);
		setBackground(gris);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(13, 44, 103, 24);
		add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(126, 46, 215, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNumeroDeContacto = new JLabel("Numero de contacto");
		lblNumeroDeContacto.setBounds(13, 130, 153, 14);
		add(lblNumeroDeContacto);
		
		txtNum = new JTextField();
		txtNum.setBounds(126, 127, 215, 20);
		add(txtNum);
		txtNum.setColumns(10);
		
		JLabel lblFarmaceutica = new JLabel("Farmaceutica");
		lblFarmaceutica.setBounds(13, 213, 135, 14);
		add(lblFarmaceutica);
		
		textField = new JTextField();
		textField.setBounds(126, 210, 215, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID del Proovedor");
		lblId.setBounds(13, 308, 135, 14);
		add(lblId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(126, 305, 215, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				
			}
		});
		btnAgregar.setBounds(160, 381, 125, 23);
		add(btnAgregar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(377, 44, 324, 361);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Farmaceutica", "Telefono"
			}
		));
		scrollPane.setViewportView(table);
		
	}
}
