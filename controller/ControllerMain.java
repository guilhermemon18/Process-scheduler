package controller;
import utility.EnumAlgoritmo;
import view.ViewMain;

public class ControllerMain {
	private ViewMain v;
	private EnumAlgoritmo algoritmo;
	private Integer quantum;
	
	public void setQuantum(Integer quantum) {
		this.quantum = quantum;
	}

	public ControllerMain() {
		v = new ViewMain(this);
	}
	
	public void exec() {
		v.setVisible(true);
	}
	
	public void gerarProcessos(Integer numProcessos) {
		new ControllerProcessos(numProcessos,algoritmo,quantum,v);
	}
	
	public void setAlgoritmo(EnumAlgoritmo algoritmo) {
		this.algoritmo = algoritmo;
	}
	

	
}
