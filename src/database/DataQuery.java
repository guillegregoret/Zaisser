package database;

import java.util.ArrayList;

public class DataQuery {
	private ArrayList<ArrayList<Object>> data;
	private ArrayList<String> columnNames;
	
	public ArrayList<ArrayList<Object>> getData() {
		return data;
	}
	public void setData(ArrayList<ArrayList<Object>> data) {
		this.data = data;
	}
	public ArrayList<String> getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(ArrayList<String> columnNames) {
		this.columnNames = columnNames;
	}
}
