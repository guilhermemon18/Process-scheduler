package controller;

import java.awt.Component;
import java.util.ArrayList;

import model.Model;
import model.ModelProcesso;
import model.Algoritmos.ModelAlgoritmo;
import model.Algoritmos.ModelMultiplasFilas;
import model.Algoritmos.ModelPrioridade;
import model.Algoritmos.ModelRoundRobin;
import model.Algoritmos.ModelShortestJobFirst;
import model.Algoritmos.ModelShortestRemainingTimeNex;
import utility.EnumAlgoritmo;
import view.ViewEscalonamento;
import view.ViewGraficoPizza;

public class ControllerEscalonamento {

	private EnumAlgoritmo algoritmo;
	private ArrayList<Model> processos;
	private ViewEscalonamento view;
	private ViewGraficoPizza viewGrafico;
	private Integer quantum;
	private ModelAlgoritmo escalonamento;
	private Component parentViewProcessos;


	public ControllerEscalonamento(EnumAlgoritmo algoritmo, ArrayList<Model> processos, Integer quantum,Component parent) {
		super();
		this.algoritmo = algoritmo;
		this.processos = processos;
		this.quantum = quantum;
		this.parentViewProcessos = parent;
		executar();
		printResultados();

	}

	public void executar() {

	/*SJF,
	SRTN,
	RR,
	PRIORIDADE,
	MF;*/
		switch(this.algoritmo) {
		case RR:
			escalonamento = new ModelRoundRobin(processos,quantum);
			break;
		case SJF:
			escalonamento = new ModelShortestJobFirst(processos);
			break;
		case SRTN:
			escalonamento = new ModelShortestRemainingTimeNex(processos);
			break;
		case PRIORIDADE:
			escalonamento = new ModelPrioridade(processos);
			break;
		case MF:
			escalonamento = new ModelMultiplasFilas(processos,quantum);
			break;
			
		default:
			escalonamento = new ModelRoundRobin(processos,quantum);//Round Robin é o padrao.
		}
		escalonamento.escalonar();
		escalonamento.calcTurnAround();
		escalonamento.organizarProcessosId();//organiza os processos novamente pelo ID.
	}


	public void printResultados() {
		view = new ViewEscalonamento(this,processos);
		view.exec(parentViewProcessos);
	}

	public void graficoFinishTime() {
		viewGrafico = new ViewGraficoPizza(gerarNomes(),gerarValoresFinishTime(), "Finish Times");
		viewGrafico.exec(view);
	}
	
	public void graficoWaitingTime() {
		viewGrafico = new ViewGraficoPizza(gerarNomes(),gerarValoresWaitingTime(),"Wating Times");
		viewGrafico.exec(view);
	}

	private ArrayList<String> gerarNomes(){
		ArrayList<String> nomes = new ArrayList<String>();
		ModelProcesso modelProcesso;
		for (Model model : processos) {
			modelProcesso = (ModelProcesso) model;
			nomes.add("P" + Integer.toString((modelProcesso.getIdProcesso())));
		}
		return nomes;
	}

	private ArrayList<Integer> gerarValoresFinishTime(){
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ModelProcesso modelProcesso;
		
		for (Model model : processos) {
			modelProcesso = (ModelProcesso) model;
			valores.add(modelProcesso.getFinishTime());
		}
		return valores;
	}
	
	
	private ArrayList<Integer> gerarValoresWaitingTime(){
		ArrayList<Integer> valores = new ArrayList<Integer>();
		ModelProcesso modelProcesso;

		for (Model model : processos) {
			modelProcesso = (ModelProcesso) model;
			valores.add(modelProcesso.getWaitingTime());
		}
		return valores;
	}

	public Double getTempoMedio() {
		return escalonamento.getTempoMedio();
	}





}
