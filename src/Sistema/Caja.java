package Sistema;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Caja extends JPanel {
	private BD bd=new BD();
	private JTable table;

	public Caja() {
		this.setBounds(1, 1,1098, 697);
		Color gris=new Color(196,196,196);
		setBackground(gris);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(689, 97, 343, 414);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Producto", "ID", "Cantidad", "Precio"
			}
		));
		scrollPane.setViewportView(table);
	}
}
