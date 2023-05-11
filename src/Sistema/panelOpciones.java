package Sistema;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;

public class panelOpciones extends JPanel {
	private int nivel;
	BD bd=new BD();
	public panelOpciones() {

		this.setBounds(1, 1,1098, 697);
		setLayout(null);
		Color gris=new Color(196,196,196);
		setBackground(gris);
		nivel=bd.darNivel();
		
		if(nivel==2){
		JButton btnNuevoEmpleado = new JButton("Nuevo Empleado");
		btnNuevoEmpleado.setBounds(54, 100, 161, 23);
		add(btnNuevoEmpleado);
		
		JButton btnNewProo = new JButton("Nuevo Proovedor");
		btnNewProo.setBounds(54, 300, 161, 23);
		add(btnNewProo);
		
		JButton btnOpciones = new JButton("Opciones de perfil");
		btnOpciones.setBounds(54, 500, 161, 23);
		add(btnOpciones);
		
		JPanel panel = new JPanel();
		
		panel.setBounds(328, 11, 760, 675);
		add(panel);
		panel.setLayout(null);
		
		panel.setBackground(gris);
		}
		
	
		
	}
}
