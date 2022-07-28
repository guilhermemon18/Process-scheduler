package model.Algoritmos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import model.Model;
import model.ModelProcesso;

public class ModelRoundRobin extends ModelAlgoritmo {
	private Integer quantum = 30;//em ms 
	private Integer atual = 0;
	private Queue <ModelProcesso> filaProcessos;


	public ModelRoundRobin(ArrayList<Model> processos,Integer quantum) {
		super(processos);
		this.quantum = quantum;
		setFilaProcessos(new LinkedList<>());
		filaProcessos.add((ModelProcesso)processos.get(0));//enfileira apenas o primeiro processo na ordem de chegada
	}

	public Integer getQuantum() {
		return quantum;
	}

	public void setQuantum(Integer quantum) {
		this.quantum = quantum;
	}


	public Integer getAtual() {
		return atual;
	}

	public void setAtual(Integer atual) {
		this.atual = atual;
	}
	
	public Queue <ModelProcesso> getFilaProcessos() {
		return filaProcessos;
	}

	public void setFilaProcessos(Queue <ModelProcesso> filaProcessos) {
		this.filaProcessos = filaProcessos;
	}

	private void atualizaFila(ModelProcesso atual,Integer timeAtual) {
		for(int i = 0; i < processos.size(); i++) {
			ModelProcesso processI = (ModelProcesso) processos.get(i);
			if(atual != null) {
				if(processI.isEncerrado() == false && processI.getTimeArrival() <= timeAtual && !processI.equals(atual)) {
					if(!filaProcessos.contains(processI)) {
						filaProcessos.add(processI);//adiciona o processo que nao foi finalizado e que tem o tempo de chegada menor ou igual e diferente do atual.
					}
				}
			}else {
				if(processI.isEncerrado() == false && processI.getTimeArrival() <= timeAtual) {
					if(!filaProcessos.contains(processI)) {
						filaProcessos.add(processI);
					}
				}
			}
		}
	}

	@Override
	public void escalonar() {
		while(haProcessos()) {//escalone enquanto ha processos.
			ModelProcesso processo = filaProcessos.peek();
			if(processo != null && processo.getTimeArrival() <= getTime()) {
				if(processo.getBurstTime() > getQuantum()) {
					processo.setBurstTime(processo.getBurstTime() - getQuantum());
					addTime(getQuantum());
				}else {
					addTime(processo.getBurstTime());
					processo.setBurstTime(0);
				}

				filaProcessos.poll();//tira da fila.
				atualizaFila(processo,getTime());
				if(processo.getBurstTime() <= 0) {//o processo acabou sua execução
					processo.setFinishTime(getTime());
					processo.setEncerrado(true);
				}else {
					filaProcessos.add(processo);//se nao terminou, adiciona ao fim da fila para a proxima execução
				}
			}else {
				addTime(1);
				atualizaFila(processo,getTime());

			}
		}
	}







	
}


