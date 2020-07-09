package database;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WriteConfig
{
    public static void WTD(String dir)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
        	String Direccion_persistencia = System.getProperty("user.dir") + System.getProperty("file.separator") + "config.ini";
            fichero = new FileWriter(Direccion_persistencia); //"C:\\Users\\guill\\Desktop\\Archivo.txt"
            pw = new PrintWriter(fichero);
            pw.print(dir);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    public static void CambiarDireccion(String dir)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
        	String Direccion_persistencia = System.getProperty("user.dir") + System.getProperty("file.separator") + "config.ini";
            fichero = new FileWriter(Direccion_persistencia); 
            pw = new PrintWriter(fichero);
            pw.print(dir);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    public static void QueryToLog(String Q) {
        FileWriter fichero = null;
        
		//DateTimeFormatter dtf = DateTimeFormatter.of
		LocalDateTime localDate = LocalDateTime.now();		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
		
        try
        {
        	String Direccion_persistencia = System.getProperty("user.dir") + System.getProperty("file.separator") + "QueryLog.txt";
            fichero = new FileWriter(Direccion_persistencia,true); //"C:\\Users\\guill\\Desktop\\Archivo.txt"
            PrintWriter pw = new PrintWriter(fichero);
            pw.print("Attempt to query on: "+localDate.format(myFormatObj)+" query: "+Q);
            pw.append(System.lineSeparator());
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
}