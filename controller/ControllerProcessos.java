package controller;

import java.awt.Component;

import dao.DaoProcesso;
import model.Model;
import utility.EnumAlgoritmo;
import view.ViewManterProcesso;
import view.ViewProcessos;

public class ControllerProcessos {
	private ViewProcessos viewProcessos;
	private ViewManterProcesso viewManter;
	private DaoProcesso dao;
	EnumAlgoritmo algoritmo;
	Integer quantum;
	
	public ControllerProcessos(int numProcessos,EnumAlgoritmo algoritmo,Integer quantum,Component parent) {
		dao = new DaoProcesso(numProcessos);
		this.algoritmo = algoritmo;
		this.quantum = quantum;
		viewProcessos = new ViewProcessos(this,dao.getModels());
		viewProcessos.exec(parent);
	}
	
	public void executarProcessos() {//escalonamento
		new ControllerEscalonamento(algoritmo,dao.getModels(),quantum,viewProcessos);
	}
	
	public void manter(Model model) {
		viewManter = new ViewManterProcesso(model);
		viewManter.exec(viewProcessos);
		
	}
	
}
