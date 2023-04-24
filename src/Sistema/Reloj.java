package Sistema;

import java.awt.Frame;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Reloj extends Frame {
	
 public Reloj(){
	 
 }

    public void paint(Graphics g) {
        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minuto = calendario.get(Calendar.MINUTE);
        int segundo = calendario.get(Calendar.SECOND);

        String horaString = hora + ":" + minuto + ":" + segundo;
        g.drawString(horaString, 50, 100);
    }

    public  void llamar(){
    	   Reloj reloj = new Reloj();

           TimerTask task = new TimerTask() {
               public void run() {
                   reloj.repaint();
               }
           };

           Timer timer = new Timer();
           timer.schedule(task, 0, 1000);

    }
}