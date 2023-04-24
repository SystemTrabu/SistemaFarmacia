package Sistema;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Inventario extends JPanel {
	private JTable table;

	
	public Inventario() {
		
		BD bd=new BD();
		setLayout(null);
		this.setBounds(1, 1,1098, 697);
		Color gris=new Color(196,196,196);
		setBackground(gris);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(686, 44, 383, 531);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "ID", "Precio", "Cantidad", "Proveedor"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(table);
		bd.conectarBD();
		
		
	
	
	}
}
