package Sistema;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Movimientos extends JPanel {
	private JTable table;
	private static DefaultTableModel model;
	private BD bd=new BD();

	/**
	 * Create the panel.
	 */
	public Movimientos() {
		setLayout(null);
		this.setBounds(1, 1,1098, 697);
		Color gris=new Color(196,196,196);
		setBackground(gris);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(596, 37, 445, 457);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "ID", "Empleado", "Movimiento", "Fecha"
			}
		));
		scrollPane.setViewportView(table);
		try {
			model=bd.tablaMovimientos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table.setModel(model);

	}
	
}
