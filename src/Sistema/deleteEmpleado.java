package Sistema;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class deleteEmpleado extends JPanel {
	private JTextField txtID;
	private BD bd=new BD();
	
	private JTable table;
	
	public deleteEmpleado() {
		
		this.setBounds(0,0 ,723, 675);
		Color gris=new Color(196,196,196);
		setBackground(gris);
		setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 36, 433, 453);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(bd.modelEmpleado());
		
		txtID = new JTextField();
		txtID.setBounds(196, 532, 261, 20);
		add(txtID);
		txtID.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtID.getText().isEmpty()==false)bd.Delete("empleados",txtID.getText());
				else JOptionPane.showMessageDialog(null, "Ingresa algo....");
				table.setModel(bd.modelEmpleado());
				
			}
		});
		btnEliminar.setBounds(282, 577, 89, 23);
		add(btnEliminar);
	}
}
