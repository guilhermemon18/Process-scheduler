package view.tableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Model;
import model.ModelProcesso;

public class TableModelEscalonamento  extends AbstractTableModel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String headers[] = {"ID", "Prioridade", "Arrival Time","Burst Time", "Finish Time", "Turnaround"};
	private ArrayList<Model> array = new ArrayList<Model>();
	
	
	public void setArray(ArrayList<Model> array) {
		this.array = array;
		fireTableDataChanged();//diz para tabela que os dados foram atualizados.
	}

	public Model getModel(int row) {
		return array.get(row);
	}
	@Override
	public String getColumnName(int column) {
		return headers[column];
		
	}
	
	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public int getRowCount() {
		return array.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		ModelProcesso processo = (ModelProcesso) array.get(row);
		switch(column) {
		case 0: return processo.getId();
		case 1: return processo.getPrioridade();
		case 2: return processo.getTimeArrivalOriginal();
		case 3: return processo.getBurstTimeOriginal();
		case 4: return processo.getFinishTime();
		case 5: return processo.getTurnAround();
		}
		return null; 
	}

	
}
