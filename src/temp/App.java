package temp;

import java.io.PrintWriter;
import java.sql.*;


import database.*;

public class App {
public static void main(String[] args) {
	//PrintWriter pw = new PrintWriter(System.out);
	//RunHSQLDB Database = new RunHSQLDB();
	//Database.HSQLDBRunner(pw);
	
	
	try {
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		Connection conn= DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1/Zaisser","SA","");
		Statement stmt = conn.createStatement();
		//stmt.execute("CREATE TABLE AUTOR ( ID INT, NOMBRE VARCHAR(45) )");
		//stmt.execute("INSERT INTO PUBLIC.AUTOR VALUES('8' , 'ABER')");

		PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM PLANTA  WHERE ID BETWEEN ? AND ?");
		stmt2.setInt(1, 0);
		stmt2.setInt(2, 700);
		ResultSet rs = stmt2.executeQuery();
    	while(rs.next()) {
			String nombre = rs.getString("NOMBRE");
		Integer id = rs.getInt("ID");
			System.out.println("[planta] "+nombre+", [id] "+id);
		}
		rs.close();
		stmt.close();
		conn.close();
		//conn.setAutoCommit(false);
		// insert1 
		// injsert 2
		//if(false) conn.rollback();
		//else conn.commit();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
