package Sistema;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Caja extends JPanel {
	private JTextField txtNom;
	private JTextField txtCant;
	private JTable table;
	private BD bd=new BD();
	private DefaultTableModel modelCaja=null;


	public Caja() {
		modelCaja=bd.returnModel();
		setLayout(null);
		this.setBounds(1, 1,1098, 697);
		Color gris=new Color(196,196,196);
		setBackground(gris);
		txtNom = new JTextField();
		txtNom.setBounds(172, 105, 251, 20);
		add(txtNom);
		txtNom.setColumns(10);
		
		txtCant = new JTextField();
		txtCant.setBounds(172, 209, 251, 20);
		add(txtCant);
		txtCant.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bd.agregarCaja(txtNom.getText(), Integer.parseInt(txtCant.getText()));
				modelCaja=bd.returnModel();
				table.setModel(modelCaja);
				
				txtCant.setText("");
				txtNom.setText("");
			}
		});
		btnAgregar.setBounds(225, 333, 146, 23);
		add(btnAgregar);
		
		JLabel lblNombreOId = new JLabel("Nombre o ID");
		lblNombreOId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreOId.setBounds(28, 107, 134, 14);
		add(lblNombreOId);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCantidad.setBounds(28, 211, 134, 14);
		add(lblCantidad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(611, 90, 454, 460);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(modelCaja);

		scrollPane.setViewportView(table);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				modelCaja.removeRow(table.getSelectedRow());
				}
				catch(Exception unu){
					JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
				}
				}
		});
		btnEliminar.setBounds(632, 601, 127, 23);
		add(btnEliminar);
		
		JButton btnTotal = new JButton("Terminar Venta");
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(table.getRowCount()>0){
						bd.TerminarVenta();
					}
					else{
						JOptionPane.showMessageDialog(null, "No hay nada en Caja...");
					}
			}
		});
		btnTotal.setBounds(890, 601, 151, 23);
		add(btnTotal);
		
		
		
		
		
	}
}
