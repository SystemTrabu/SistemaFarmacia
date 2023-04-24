package Sistema;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JProgressBar;   



public class Login extends JPanel {

	private JFrame frame;
	private JTextField textUsuario;
	private JPasswordField textContraseña;

	BD bd=new BD();
	
	
	public Login() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	
	}


	private void initialize() {


		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("https://i.imgur.com/JvZbQVD.jpg"));
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setSize(401,474);
		frame.setTitle("Farmacia Doña Lupe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnIngresar = new JButton("Ingresar");
		
		btnIngresar.setBackground(SystemColor.desktop);
		btnIngresar.setFont(new Font("Monotype Corsiva", Font.BOLD, 18));
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean esUser=false;
				bd.conectarBD();
				esUser=bd.buscarUser(textUsuario.getText(), textContraseña.getText());
				if(esUser==true){
					String nombre=bd.darNombre();
					int nivel=bd.darNivel();
					bd.desconectarBD();
					Principal principal = new Principal(nombre, nivel);
					frame.dispose();
				}
				else{
					JOptionPane.showMessageDialog(frame, "Error de usuario/contraseña \nIntenta Nuevamente");
					textUsuario.setText("");
					textContraseña.setText("");
				}
			}
		});
		btnIngresar.setBounds(106, 391, 217, 40);
		frame.getContentPane().add(btnIngresar);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Monotype Corsiva", Font.BOLD, 14));
		lblUsuario.setBounds(85, 278, 85, 14);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Monotype Corsiva", Font.BOLD, 14));
		lblContrasea.setBounds(84, 341, 76, 14);
		frame.getContentPane().add(lblContrasea);
		
		textUsuario = new JTextField();
		textUsuario.setBackground(SystemColor.inactiveCaptionBorder);
		textUsuario.setBounds(170, 275, 131, 20);
		frame.getContentPane().add(textUsuario);
		textUsuario.setColumns(10);
		
		textContraseña = new JPasswordField();
		textContraseña.setBackground(SystemColor.inactiveCaptionBorder);
		textContraseña.setBounds(170, 338, 131, 20);
		frame.getContentPane().add(textContraseña);
		
		JLabel UserIcon = new JLabel("");
		
		UserIcon.setBounds(37, 263, 50, 50);
		frame.getContentPane().add(UserIcon);
		try {
		    URL urlIconUser= new URL("https://i.imgur.com/xLHePxN.png");
		    ImageIcon IconUser = new ImageIcon(urlIconUser);
		    UserIcon.setIcon(IconUser);
		} catch (MalformedURLException ex) {
		   
		}
		
		JLabel UserIconPas = new JLabel("");
		
		UserIconPas.setBounds(49, 324, 50, 50);
		frame.getContentPane().add(UserIconPas);
		try {
		    URL urlIconPass= new URL("https://i.imgur.com/v5Rx8mZ.png");
		    ImageIcon IconPass = new ImageIcon(urlIconPass);
		    UserIconPas.setIcon(IconPass);
		} catch (MalformedURLException ex) {
		   
		}
		
		JLabel lblNewLabel = new JLabel("");
		
		lblNewLabel.setBounds(58, 42, 243, 222);
		try {
		    URL urlImgCruz = new URL("https://i.imgur.com/kSC1kbv.png");
		    ImageIcon IconImgCruz = new ImageIcon(urlImgCruz);
		    lblNewLabel.setIcon(IconImgCruz);
		} catch (MalformedURLException ex) {
		   
		}
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSalir = new JLabel("");
		lblSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
	
		lblSalir.setBounds(320, 42, 43, 50);
		try {
		    URL urlSalir = new URL("https://i.imgur.com/eYJWRV6.png");
		    ImageIcon IconSalir = new ImageIcon(urlSalir);
		    lblSalir.setIcon(IconSalir);
		} catch (MalformedURLException ex) {
		   
		}
		frame.getContentPane().add(lblSalir);
		
		
		
		JLabel fondo = new JLabel("Ingresar");
		fondo.setBackground(SystemColor.desktop);
		fondo.setForeground(SystemColor.activeCaption);
		
		
		fondo.setBounds(0, 0, 401, 474);
		frame.getContentPane().add(fondo);
		try {
		    URL urlFondo = new URL("https://i.imgur.com/j50sQXc.jpg");
		    ImageIcon IconFondo = new ImageIcon(urlFondo);
		    fondo.setIcon(IconFondo);
		} catch (MalformedURLException ex) {
		   
		}
	}
	
}