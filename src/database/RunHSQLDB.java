package database;

import java.io.PrintWriter;

import org.hsqldb.server.Server;
public class RunHSQLDB {
	public static Server hsqlServer = new Server();
	public void HSQLDBRunner(PrintWriter pw) {
		pw= new PrintWriter(System.out);
        hsqlServer.setLogWriter(pw);
        hsqlServer.setTrace(false);
        hsqlServer.setSilent(true);
        hsqlServer.setDatabaseName(0, "arkana");
        hsqlServer.setDatabasePath(0, "file:arkana");
        hsqlServer.start();
        
	}
}