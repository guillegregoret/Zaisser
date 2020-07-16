package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {
	Connection con;
	PreparedStatement stmt;
	public ConnectDatabase() {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			String[] conn = ReadConfig.RFD();         
			con = DriverManager.getConnection(conn[0],conn[1],conn[2]);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}  
	}
	public PreparedStatement preparedStatement(String query) {
		try {
			stmt = con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	public void commit() {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void query(String query) {
		Connection con = null;
		Statement stmt = null;
  
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			String[] conn = ReadConfig.RFD();         
			con = DriverManager.getConnection(conn[0],conn[1],conn[2]);
			stmt = con.createStatement();
			WriteConfig.QueryToLog(query);
			stmt.executeUpdate(query);	
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}   
	}

	public ResultSet querySelect(String query) {
		Connection con = null;
		Statement stmt = null;
	   
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			String[] conn = ReadConfig.RFD();       
			con = DriverManager.getConnection(conn[0],conn[1],conn[2]);
			stmt = con.createStatement();
			WriteConfig.QueryToLog(query);
			return stmt.executeQuery(query);
		}  catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return null;
	}
}