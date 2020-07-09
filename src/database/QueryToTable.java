package database;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class QueryToTable{
	private static QueryToTable single;
	
	private QueryToTable() {}
	
	public static QueryToTable getSingle() {
		if(!(single != null))	single = new QueryToTable();	
		return single;
	}
	
	public DataQuery queryToTable(ResultSet rs){
		try{
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			DataQuery result = new DataQuery();
			//Vector<String> columnNames = new Vector<String>();
			ArrayList<String> columnNames = new ArrayList<String>();
			for(int i = 1 ; i <= columnCount ; i++) {
				columnNames.add(metaData.getColumnName(i));
			}
			//Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
			while(rs.next()) {
				ArrayList<Object> arrayAux = new ArrayList<Object>();
				for(int i = 1 ; i <= columnCount ; i++) {
					arrayAux.add(rs.getObject(i));
				}
				data.add(arrayAux);
			}
			result.setColumnNames(columnNames);
			result.setData(data);
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}