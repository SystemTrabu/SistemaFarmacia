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
		JButton btnNuevoEmpleado = new JButton("Empleados");
		btnNuevoEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEmpleado panel=new newEmpleado();
				CambiarPanel(panel);
				}
			}
			);
		
		btnNuevoEmpleado.setBounds(54, 100,  176, 23);
		add(btnNuevoEmpleado);
		
		JButton btnNewProo = new JButton("Proovedores");
	
		btnNewProo.setBounds(54, 300,  176, 23);
		add(btnNewProo);
		
		btnNewProo.addActionListener(new ActionListener(){

		
			public void actionPerformed(ActionEvent arg0) {
				newProovedor proovedor=new newProovedor();
				CambiarPanel(proovedor);
				
			}
			
		});
		JButton btnOpciones = new JButton("Opciones de perfil");
		btnOpciones.setBounds(54, 500,  176, 23);
		btnOpciones.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				OpcPerfil opcion=new OpcPerfil();
				
				CambiarPanel(opcion);
				
				
			}
			
		});
		JButton btnEliminarEmpleado = new JButton("Eliminar Empleado");
		
		btnEliminarEmpleado.addActionListener(new ActionListener(){

			
			public void actionPerformed(ActionEvent e) {
				deleteEmpleado delete=new deleteEmpleado();
				CambiarPanel(delete);
			}
			
		});
		btnEliminarEmpleado.setBounds(51, 177, 176, 23);
		add(btnEliminarEmpleado);
		
		
		JButton btnEliminarProovedor = new JButton("Eliminar Proovedor");
		btnEliminarProovedor.setBounds(51, 383, 176, 23);
btnEliminarProovedor.addActionListener(new ActionListener(){

			
			public void actionPerformed(ActionEvent e) {
				deleteProovedor delete=new deleteProovedor();
				CambiarPanel(delete);
			}
			
		});
		add(btnEliminarProovedor);
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
