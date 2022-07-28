package view.tableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Model;
import model.ModelProcesso;

public class TableModelProcesso extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String headers[] = {"ID", "Arrival Time","Burst Time", "Prioridade"};
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
		case 1: return processo.getTimeArrival();
		case 2: return processo.getBurstTime();
		case 3: return processo.getPrioridade();
		}
		return null; 
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ModelProcesso processo = (ModelProcesso) array.get(rowIndex);
		switch(columnIndex) {
		case 0: processo.setId(Integer.parseInt( (String) aValue)); break;
		case 1: processo.setTimeArrival(Integer.parseInt((String) aValue)); 
		processo.setTimeArrivalOriginal(Integer.parseInt((String) aValue));
		break;
		case 2: processo.setBurstTime(Integer.parseInt( (String)aValue)); 
		processo.setBurstTimeOriginal(Integer.parseInt( (String)aValue)); 
		break;
		case 3: processo.setPrioridade(Integer.parseInt( (String)aValue));
		break;
		}
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if(column != 0) {
			return true;
		}
		return false;
	}



}
