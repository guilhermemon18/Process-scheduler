package model.Algoritmos;

import java.util.ArrayList;

import model.Model;
import model.ModelProcesso;

/*versão preemptiva do ShortestJobFirst: o escalonador escolhe
 * o processo cujo tempo de execução restante é o mais curto, quando
 * uma nova tarefa chega, seu tempo total é comparado com o tempo restante 
 * do processo atual.
 */
public class ModelShortestRemainingTimeNex extends ModelAlgoritmo {

	public ModelShortestRemainingTimeNex(ArrayList<Model> processos) {
		super(processos);
		organizarProcessosBurstTime();
	}


	private void organizarAux(Integer timeAtual) {
		for(int i = 0; i < processos.size(); i++) {
			for ( int j=i+1; j<processos.size(); j++ ) {	
				ModelProcesso processI = (ModelProcesso) processos.get(i);
				ModelProcesso processJ = (ModelProcesso) processos.get(j);
				if(processI.getTimeArrival() <= timeAtual && processJ.getTimeArrival() <= timeAtual) {
					if(processI.getBurstTime() > processJ.getBurstTime()) {
						processos.set(i, processJ);
						processos.set(j, processI);
					}
				}
			}
		}
	}

	//retorna o processo a ser executado agora neste tempo, retorna NULL se não houver nenhum processo.
	private ModelProcesso nextProcesso(Integer time) {//priorizar a prioridade 1.
		for(int i = 0; i < processos.size();i++) {
			ModelProcesso processo = (ModelProcesso) processos.get(i);
			if(processo.getTimeArrival() <= time && processo.isEncerrado() == false) {//se arrival time desse processo for igual ao time atual,
				//ele chegou agora, se o seu burstTime for menor, chegou agora e tem menor burst, entao ele é o proximo
				return processo;
			}

		}
		return null;
	}
	

	@Override
	public void escalonar() {
		ModelProcesso processo = (ModelProcesso) processos.get(0);
		
		while(haProcessos()) {//escalone enquanto ha processos.
			processo = nextProcesso(getTime());//pega o novo processo com o novo time.
			if(processo != null) {
				processo.setBurstTime(processo.getBurstTime() - 1);
				addTime(1);//adiciona 1 ms,
				if(processo.getBurstTime() <= 0) {//o processo acabou sua execução
					processo.setFinishTime(getTime());
					processo.setEncerrado(true);
				}
				
			}else {
				addTime(1);
			}
			organizarAux(getTime());
		}

	}

	

}
