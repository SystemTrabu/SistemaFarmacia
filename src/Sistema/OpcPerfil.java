package Sistema;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OpcPerfil extends JPanel {
	private JTextField txtUser;
	private JTextField txtNum_Tele;
	public OpcPerfil() {
		setBounds(0, 0, 723, 675);
		Color gris=new Color(196,196,196);
		setBackground(gris);
		setLayout(null);
		
		JLabel Usuario = new JLabel("Usuario");
		Usuario.setBounds(96, 45, 106, 14);
		add(Usuario);
		
		txtUser = new JTextField();
		txtUser.setBounds(240, 42, 180, 20);
		add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblNumeroTelefonico = new JLabel("Numero Telefonico");
		lblNumeroTelefonico.setBounds(96, 125, 118, 14);
		add(lblNumeroTelefonico);
		
		txtNum_Tele = new JTextField();
		txtNum_Tele.setBounds(240, 119, 180, 20);
		add(txtNum_Tele);
		txtNum_Tele.setColumns(10);
		
		
		
	}
}
