package model.Algoritmos;

import java.util.ArrayList;

import model.Model;
import model.ModelProcesso;

public class ModelShortestJobFirst extends ModelAlgoritmo {



	public ModelShortestJobFirst(ArrayList<Model> processos) {
		super(processos);
		organizarProcessosBurstTime();
	}



	@Override
	protected void organizarProcessosBurstTime() {
		for(int i = 0; i < processos.size(); i++) {
			for ( int j=i+1; j<processos.size(); j++ ) {	
				ModelProcesso processI = (ModelProcesso) processos.get(i);
				ModelProcesso processJ = (ModelProcesso) processos.get(j);
				if(processI.getBurstTime() > processJ.getBurstTime()) {
					processos.set(i, processJ);
					processos.set(j, processI);
				}

			}
		}
	}


	//retorna o processo a ser executado agora neste tempo, retorna NULL se não houver nenhum processo.
	private ModelProcesso nextProcesso(Integer time) {//priorizar a prioridade 1.
		for(int i = 0; i < processos.size();i++) {
			ModelProcesso processo = (ModelProcesso) processos.get(i);
			if(processo.getTimeArrival() <= time && processo.isEncerrado() == false) {
				return processo;
			}

		}
		return null;
	}

	@Override
	public void escalonar() {
		while(haProcessos()) {//escalone enquanto ha processos.
			ModelProcesso processo = nextProcesso(getTime());//pega o novo processo com o novo time.
			if(processo != null) {
				addTime(processo.getBurstTime());
				processo.setBurstTime(0);
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


