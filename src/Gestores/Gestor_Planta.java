package Gestores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Dominio.Planta;
import database.ConnectDatabase;

public class Gestor_Planta {
	public static int getProxID() {
		 ArrayList<Planta> plantas = Gestor_Planta.getPlantas();
		 int max=0;
		 for (int i = 0; i < plantas.size(); i++) {
			if(plantas.get(i).getId()>max) max=plantas.get(i).getId();
		}
		return max+1;
	}
	public static Integer getID(String nombre_planta) {
		 ArrayList<Planta> plantas = Gestor_Planta.getPlantas();
		 for (int i = 0; i < plantas.size(); i++) {
			if(plantas.get(i).getNombre().equals(nombre_planta)) {
				return plantas.get(i).getId();
			}
		}
		 return -1;
	}
	public static String getNombre(Integer id) {
		 ArrayList<Planta> plantas = Gestor_Planta.getPlantas();
		 for (int i = 0; i < plantas.size(); i++) {
			if(plantas.get(i).getId().equals(id)) {
				return plantas.get(i).getNombre();
			}
		}
		 return "";
	}
	public static void altaPlanta(Planta p) {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "INSERT INTO PLANTA VALUES(?, ?);";
		PreparedStatement stmt = condb.preparedStatement(query);
		try {
			stmt.setInt(2,Gestor_Planta.getProxID());
			stmt.setString(1, p.getNombre());
			stmt.execute();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static ArrayList<Planta> getPlantas() {
		ArrayList<Planta> plantas = new ArrayList<Planta>();
		ConnectDatabase condb = new ConnectDatabase();
		String query = "SELECT * FROM PLANTA";
		PreparedStatement stmt = condb.preparedStatement(query);
		try {
			stmt.execute();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Planta plant = new Planta();
					plant.setNombre(rs.getString("NOMBRE"));
					plant.setId(rs.getInt("ID"));
					plantas.add(plant);
			}
			stmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		

		return plantas;
	}
}
