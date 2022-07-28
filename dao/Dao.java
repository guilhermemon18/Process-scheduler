package dao;

import java.util.ArrayList;

import model.Model;


public class Dao {
	
	 protected ArrayList<Model> models;
	 protected int index;
	
	public Dao () {	
		index = 0;
		models = new  ArrayList<Model>();//lista de models
	}
	
	public ArrayList<Model> getModels(){
		return models;
	}
	
	public void setModels(ArrayList<Model> models) {
		this.models = models;
	}
	 protected   int getIndex ( Model model ) {
		 for(int i = 0; i < models.size();i++) {
			 if(models.get(i).equals(model)) {
				 return i;
			 }
		 }
		 return -1;//nao está na lista.
	 }
		
	 protected   int getNextIndex() {
		 return ++this.index;
	 }
	 
	 public void salvar ( Model model ) {
		    if (model.getId() == 0) {
		        model.setId(getNextIndex());
		        models.add(model);
		    }else {
		        int index = getIndex(model);
		        models.get(index).swap(model);

		    }
		}
	

	

	

	
}
