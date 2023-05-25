package Sistema;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Ventas extends JPanel {
	private JTable table;
	private BD bd=new BD();
	public Ventas() {
		setLayout(null);
		this.setBounds(1, 1,1098, 697);
		Color gris=new Color(196,196,196);
		setBackground(gris);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(294, 91, 571, 527);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(bd.modelVentas());
		
	}
}
