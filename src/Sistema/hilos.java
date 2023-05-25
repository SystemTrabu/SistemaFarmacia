package Sistema;

public class hilos extends Thread {
	private int tipo;
	private BD bd=new BD();
	private String uno, dos;
	public hilos(int tipo, String uno, String dos){
		this.tipo=tipo;
		this.uno=uno;
		this.dos=dos;
	}
	
	public void run(){
		if(tipo==1)EjecutarBusqueda();
		if(tipo==2)Eliminar();
		
	}
	
	public void EjecutarBusqueda(){
		bd.agregarCaja(uno, Integer.parseInt(dos));
		
	}
	public void Eliminar(){
		bd.Delete(uno, dos);
	}
	
}
