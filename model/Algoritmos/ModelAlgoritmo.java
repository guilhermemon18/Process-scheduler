package model.Algoritmos;

import java.util.ArrayList;

import model.Model;
import model.ModelProcesso;

public  abstract class ModelAlgoritmo {//modelo genérico para algoritmo de escalonamento.
	protected ArrayList<Model> processos;//recebe os processos para processar.
	protected Integer time;


	public ModelAlgoritmo(ArrayList<Model> processos) {
		time = 0;
		this.processos = processos;
		this.organizarProcessosChegada();
	}



	public Integer getTime() {
		return time;
	}



	public void setTime(Integer time) {
		this.time = time;
	}

	public void addTime(Integer time) {
		setTime(this.time + time);
	}



	public ArrayList<Model> getProcessos() {
		return processos;
	}

	public void setProcessos(ArrayList<Model> processos) {
		this.processos = processos;
	}

	public abstract void escalonar();

	protected boolean haProcessos() {
		for (Model modelProcesso : processos) {
			ModelProcesso model = (ModelProcesso) modelProcesso;
			if( model.isEncerrado() == false) {
				return true;
			}
		}
		return false;
	}

	protected void addFinishTime(Integer time) {
		for (Model modelProcesso : processos) {
			ModelProcesso model = (ModelProcesso) modelProcesso;
			if(model.isEncerrado() == false) {
				model.setFinishTime(model.getFinishTime() + time);
			}

		}
	}

	protected void descontar(Integer time) {
		for (Model modelProcesso : processos) {
			ModelProcesso model = (ModelProcesso) modelProcesso;
			if(model.isEncerrado() == false) {
				if(model.getTimeArrival() >= time) {
					model.setTimeArrival(model.getTimeArrival() - time);
				}
				else {
					model.setFinishTime(model.getFinishTime() + (time - model.getTimeArrival()));
					model.setTimeArrival(0);
				}
			}
		}
	}

	protected void organizarProcessosChegada() {//organiza processos do menor arrival time para o maior arrivaltime
		for(int i = 0; i < processos.size(); i++) {
			for ( int j=i+1; j<processos.size(); j++ ) {	
				ModelProcesso processI = (ModelProcesso) processos.get(i);
				ModelProcesso processJ = (ModelProcesso) processos.get(j);
				if(processI.getTimeArrival() > processJ.getTimeArrival()) {
					processos.set(i, processJ);
					processos.set(j, processI);					
				}
			}
		}


	}
	protected void organizarProcessosBurstTime() {//organiza processos do menor burst time para o maior burst time, mas mantém o tempo de chegada em ordem.
		for(int i = 0; i < processos.size(); i++) {
			for ( int j=i+1; j<processos.size(); j++ ) {	
				ModelProcesso processI = (ModelProcesso) processos.get(i);
				ModelProcesso processJ = (ModelProcesso) processos.get(j);
				if(processI.getTimeArrival() == processJ.getTimeArrival()) {
					if(processI.getBurstTime() > processJ.getBurstTime()) {
						processos.set(i, processJ);
						processos.set(j, processI);
					}
				}
			}
		}
	}
	

	public void organizarProcessosId() {//organiza os processos do menor Id para o maior
		for(int i = 0; i < processos.size(); i++) {
			for ( int j=i+1; j<processos.size(); j++ ) {	
				ModelProcesso processI = (ModelProcesso) processos.get(i);
				ModelProcesso processJ = (ModelProcesso) processos.get(j);
				if(processI.getId() > processJ.getId()) {
					processos.set(i, processJ);
					processos.set(j, processI);					
				}
			}
		}

	}

	//desconta arrival time de todos os processos.
	protected void descontarArrival(Integer time) {
		for (Model modelProcesso : processos) {
			ModelProcesso model = (ModelProcesso) modelProcesso;
			if(model.getTimeArrival() < time) {
				model.setTimeArrival(0);
			}else {
				model.setTimeArrival(model.getTimeArrival() - time);
			}
		}
	}

//calcula o turnAround dos processos.
	public void calcTurnAround() {
		for (Model model : processos) {
			ModelProcesso processo = (ModelProcesso) model;
			processo.calcTurnAround();
		}
	}


	//obtem o tempo médio que os processos ficaram em Waiting time, em espera.
	public Double getTempoMedio() {
		Double tempoMedio = 0.0;
		for (Model model : processos) {
			ModelProcesso modelProcesso = (ModelProcesso) model;
			tempoMedio += modelProcesso.getWaitingTime();
		}
		return tempoMedio / processos.size();
	}

}
