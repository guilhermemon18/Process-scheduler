package model.Algoritmos;

import java.util.ArrayList;

import model.Model;
import model.ModelProcesso;

//implementada com roud-robin em cada fila, cada uma pega quantum de acordo com sua prioridade, prioridade 1 pega 1 * quantum, prioridade 2 pega 2 * quantum...
//todos os processos começam com prioridade 1 e executam 1 quantum, se nao der tempo de executar totalmente neste quantum, então o quantum aumenta e a prioridade também.
//processos com maior prioridade sao os com número de prioridade menor.
//processos com menor prioridade sao os com número de prioridade maior.
//as prioridades sempre começam em 1 e vão aumentando indefinidamente até que o processo acabe, justamente,
//para que consequentemente o quantum seja multiplicado pela prioridade, garantindo um bom tempo para ele executar.
public class ModelMultiplasFilas extends ModelAlgoritmo {

	private Integer quantum;
	private Integer prioridadeMinima;
	

	public ModelMultiplasFilas(ArrayList<Model> processos,Integer quantum) {
		super(processos);
		this.setQuantum(quantum);
		this.prioridadeMinima = 1;
		setarPrioridadesProcessos(1);
	}
	
	public Integer getQuantum() {
		return quantum;
	}


	public void setQuantum(Integer quantum) {
		this.quantum = quantum;
	}


	public Integer getPrioridadeMinima() {
		return prioridadeMinima;
	}


	public void setPrioridadeMinima(Integer prioridadeMinima) {
		this.prioridadeMinima = prioridadeMinima;
	}
	
	private void setarPrioridadesProcessos(Integer prioridade) {
		for(int i = 0; i < processos.size();i++) {
			ModelProcesso processo = (ModelProcesso) processos.get(i);
			processo.setPrioridade(prioridade);
		}
	}
	



	//retorna o processo a ser executado agora neste tempo, retorna NULL se não houver nenhum processo.
	private ModelProcesso nextProcesso(Integer time) {//priorizar a prioridade 1.
		for(Integer prioridade = 1; prioridade <= getPrioridadeMinima();prioridade++) {
			for(int i = 0; i < processos.size();i++) {
				ModelProcesso processo = (ModelProcesso) processos.get(i);
				if(processo.getPrioridade() == prioridade) {
					if(processo.getTimeArrival() <= time && processo.isEncerrado() == false) {
						return processo;
					}
				}
			}
		}
		return null;
	}
	


	@Override
	public void escalonar() {

		while(haProcessos()) {//escalone enquanto ha processos.
			ModelProcesso processo = nextProcesso(getTime());//pega o novo processo com o novo time.
			
			if(processo != null) {
				if(processo.getBurstTime() > quantum * processo.getPrioridade()) {
					processo.setBurstTime(processo.getBurstTime() - getQuantum() * processo.getPrioridade());
					addTime(getQuantum() * processo.getPrioridade());
					if(processo.getPrioridade() + 1 > getPrioridadeMinima() ) {
						setPrioridadeMinima(processo.getPrioridade() + 1);
					}
					processo.setPrioridade(processo.getPrioridade() + 1);//diminui a prioridade.
				}else {
					addTime(processo.getBurstTime());
					processo.setBurstTime(0);
				}
				if(processo.getBurstTime() <= 0) {//o processo acabou sua execução
					processo.setFinishTime(getTime());
					processo.setEncerrado(true);
				}
			}else {
				addTime(1);
			}
		}
	}


	



}
