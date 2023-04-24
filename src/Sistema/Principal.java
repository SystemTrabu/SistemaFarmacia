package Sistema;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.PopupMenu;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JFrame {
	private JPanel panel_1 = new JPanel();
	
	public Principal(String user, int Nivel){
		super();
		BD bd=new BD();
		
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 232, 718);
		getContentPane().add(panel);
		panel.setLayout(null);
		Color verde=new Color(34,35,34);
		panel.setBackground(verde);
		JLabel lblUser = new JLabel("");
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUser.setBounds(37, 22, 185, 50);
		panel.add(lblUser);
		lblUser.setText("     " + user);
		try {
		    URL iconuser= new URL("https://i.imgur.com/Rvrv23r.png");
		    ImageIcon IconPass = new ImageIcon(iconuser);
		    lblUser.setIcon(IconPass);
		    
		    JLabel lblInventario = new JLabel("Inventario");
		    lblInventario.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent e) {
		    		Inventario panel=new Inventario();
		    		CambiarPanel(panel);
		    	}
		    });
		    lblInventario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    lblInventario.setForeground(new Color(255, 255, 255));
		    lblInventario.setBounds(37, 145, 185, 58);
		    panel.add(lblInventario);
		    
		    URL iconInventario=new URL("https://i.imgur.com/rqCBXMw.png");
		    ImageIcon IconInventario=new ImageIcon(iconInventario);
		    lblInventario.setIcon(IconInventario);
		    
		    JLabel lblCaja = new JLabel("Caja");
		    lblCaja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    lblCaja.setForeground(Color.WHITE);
		    lblCaja.setBounds(37, 254, 162, 64);
		    panel.add(lblCaja);
		    
		    URL iconCaja=new URL("https://i.imgur.com/HMCJvNg.png");
		    ImageIcon IconCaja=new ImageIcon(iconCaja);
		    lblCaja.setIcon(IconCaja);
		    
		    JLabel lblVentas = new JLabel("Ventas");
		    lblVentas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    lblVentas.setForeground(Color.WHITE);
		    lblVentas.setBounds(37, 376, 170, 64);
		    panel.add(lblVentas);
		    
		    URL iconVentas=new URL("https://i.imgur.com/rkgzqdJ.png");
		    ImageIcon IconVentas=new ImageIcon(iconVentas);
		    lblVentas.setIcon(IconVentas);
		    
		    JLabel lblMovimientos = new JLabel("Movimientos");
		    lblMovimientos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    lblMovimientos.setForeground(Color.WHITE);
		    lblMovimientos.setBounds(37, 489, 185, 58);
		    panel.add(lblMovimientos);
		    
		    URL iconMovi=new URL("https://i.imgur.com/LVDs6Vj.png");
		    ImageIcon IconMovi=new ImageIcon(iconMovi);
		    lblMovimientos.setIcon(IconMovi);
		    
		    JLabel lblOpciones = new JLabel("Opciones");
		    lblOpciones.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    lblOpciones.setForeground(Color.WHITE);
		    lblOpciones.setBounds(37, 600, 162, 64);
		    panel.add(lblOpciones);
		    
		    URL iconOpciones=new URL("https://i.imgur.com/YUutE55.png");
		    ImageIcon IconOpciones=new ImageIcon(iconOpciones);
		    lblOpciones.setIcon(IconOpciones);
		    
		} catch (MalformedURLException ex) {
		   
		}
		
		
		panel_1.setBounds(232, 21, 1098, 697);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		Color gris=new Color(196,196,196);
		panel_1.setBackground(gris);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(232, 0, 1098, 22);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBackground(gris);
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(204, 51, 0));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(1074, 0, 24, 25);
		panel_2.add(lblNewLabel);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		 Reloj reloj = new Reloj();

	     panel_1.add(reloj);
	     reloj.setBounds(100,100,500,500);
		
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login=new Login();
					
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	


	private void initialize() {
	
		setBounds(100, 100, 1330, 718);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(null);
		
		
	}
public void CambiarPanel(JPanel newPanel){
	panel_1.removeAll();
	panel_1.add(newPanel);
	panel_1.repaint();
	panel_1.revalidate();
}
class Reloj extends Component {
    public Reloj() {
        setSize(100, 50);
        setBackground(Color.WHITE);

        // Crear un temporizador para actualizar el reloj cada segundo
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new RelojTimer(), 0, 1000);
    }

    public void paint(Graphics g) {
    	Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR);
        int minuto = calendario.get(Calendar.MINUTE);
        int segundo = calendario.get(Calendar.SECOND);
        int am_pm = calendario.get(Calendar.AM_PM);

    	  String amPm = am_pm == Calendar.AM ? "AM" : "PM";

    
          SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");

          String horaString = formatoHora.format(calendario.getTime()) + " " + amPm;

          g.setFont(new Font("Arial", Font.PLAIN, 30));
          g.setColor(Color.BLUE);

          FontMetrics fm = g.getFontMetrics();
          int x = (getWidth() - fm.stringWidth(horaString)) / 2;
          int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;

          g.drawString(horaString, x, y);
    }

    class RelojTimer extends java.util.TimerTask {
        public void run() {
           
            repaint();
        }
    }
}
}