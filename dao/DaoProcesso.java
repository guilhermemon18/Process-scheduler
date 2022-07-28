package dao;

import java.util.Random;

import model.ModelProcesso;

public class DaoProcesso extends Dao{
	
	public DaoProcesso(int numProcessos) {
		ModelProcesso model;
		Random gerador = new Random();
		
		
		for(int i = 0; i < numProcessos; i++) {
			model = new ModelProcesso(gerador.nextInt(50) + 1, gerador.nextInt(100) +1,gerador.nextInt(4) + 1);
			salvar(model);
		}
	}

}
