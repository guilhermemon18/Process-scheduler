package model.Algoritmos;

import java.util.ArrayList;
import model.Model;
import model.ModelProcesso;


//executa os processos por ordem de prioridade sempre, n�o possui round-robin nas prioridades, portanto � FIRST-COME FIRST-SERVED.
//se chegar algum processo com maior prioridade ele troca e coloca o de maior prioridade para executar.
//sempre o processo a ser executado � de maior prioridade, n�o leva em conta burst-time.
//processos com maior prioridade sao os com n�mero de prioridade menor.
//processos com menor prioridade sao os com n�mero de prioridade maior.
// as prioridades s�o geradas em daoProcesso, de 1 a 4.
public class ModelPrioridade extends ModelAlgoritmo {

	public ModelPrioridade(ArrayList<Model> processos) {
		super(processos);
		organizarProcessosPrioridade();
	}

	private void organizarProcessosPrioridade() {
		for(int i = 0; i < processos.size(); i++) {
			for ( int j=i+1; j<processos.size(); j++ ) {	
				ModelProcesso processI = (ModelProcesso) processos.get(i);
				ModelProcesso processJ = (ModelProcesso) processos.get(j);
				if(processI.getTimeArrival() == processJ.getTimeArrival()) {
					if(processI.getPrioridade() > processJ.getPrioridade()) {
						processos.set(i, processJ);
						processos.set(j, processI);	
					}
				}
			}
		}
	}


	//retorna o processo a ser executado agora neste tempo, retorna NULL se n�o houver nenhum processo.
	private ModelProcesso nextProcesso(Integer time) {//priorizar a prioridade 1.
		for(Integer prioridade = 1; prioridade < 5;prioridade++) {
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
				processo.setBurstTime(processo.getBurstTime() - 1);
				addTime(1);//adiciona 1 ms,
				if(processo.getBurstTime() <= 0) {//o processo acabou sua execu��o
					processo.setFinishTime(getTime());
					processo.setEncerrado(true);
				}
			}else {
				addTime(1);
			}
		}

	}






}
