package Sistema;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;

public class panelOpciones extends JPanel {
	private int nivel;
	private BD bd=new BD();
	private JPanel panel = new JPanel();
	public panelOpciones() {

		this.setBounds(1, 1,1098, 697);
		setLayout(null);
		Color gris=new Color(196,196,196);
		setBackground(gris);
		nivel=bd.darNivel();
		
		if(nivel==2){
		JButton btnNuevoEmpleado = new JButton("Nuevo Empleado");
		btnNuevoEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEmpleado panel=new newEmpleado();
				CambiarPanel(panel);
				}
			}
			);
		
		btnNuevoEmpleado.setBounds(54, 100, 161, 23);
		add(btnNuevoEmpleado);
		
		JButton btnNewProo = new JButton("Nuevo Proovedor");
	
		btnNewProo.setBounds(54, 300, 161, 23);
		add(btnNewProo);
		JButton btnOpciones = new JButton("Opciones de perfil");
		btnOpciones.setBounds(54, 500, 161, 23);
		add(btnOpciones);
		panel.setBounds(365, 11, 723, 675);
		add(panel);
		panel.setLayout(null);
		panel.setBackground(gris);
		
		}
		
		else{
			objUser();
		}
	}
	
	
	public void objUser(){
		
	}
	
	public void CambiarPanel(JPanel newPanel){
		panel.removeAll();
		panel.add(newPanel);
		panel.repaint();
		panel.revalidate();
	}
}
