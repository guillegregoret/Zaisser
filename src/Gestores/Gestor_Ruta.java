package Gestores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dominio.Insumo_general;
import Dominio.Insumo_liquido;
import Dominio.Planta;
import Dominio.Ruta;
import database.ConnectDatabase;

public class Gestor_Ruta {
	public static void alta_ruta(Ruta ruta) {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "INSERT INTO RUTA VALUES(?, ?, ?, ?, ?, ?);";
		//( "ID", "PLANTA_ORIGEN", "PLANTA_DESTINO", "DISTANCIA_KM", "DURACION_HS", "PESO_MAX_KG" )
		PreparedStatement stmt = condb.preparedStatement(query);
		try {
			stmt.setInt(1, Gestor_Ruta.getProxID());
			stmt.setInt(2, ruta.getPlanta_origen().getId());
			stmt.setInt(3, ruta.getPlanta_destino().getId());
			stmt.setInt(4, ruta.getDistancia_km());
			stmt.setDouble(5, ruta.getDuracion_horas());
			stmt.setDouble(6, ruta.getPeso_max_kg());
			stmt.execute();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static int getProxID() {

		 int max=0;
			ArrayList<Planta> plantas = new ArrayList<Planta>();
			ConnectDatabase condb = new ConnectDatabase();
			String query = "SELECT * FROM RUTA";
			PreparedStatement stmt = condb.preparedStatement(query);
			try {
				stmt.execute();
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
						if(rs.getInt("ID")>max) max=rs.getInt("ID");
				}
				stmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		return max+1;
	}
	public static ArrayList<Ruta> getRutas() {
		ArrayList<Ruta> r = new ArrayList<Ruta>();
		ConnectDatabase condb = new ConnectDatabase();
		String query = "SELECT * FROM RUTA";
		
		PreparedStatement stmt = condb.preparedStatement(query);
		try {
			stmt.execute();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				
				Ruta rutaaux = new Ruta();
				Planta po=new Planta();
				Planta pd=new Planta();
				rutaaux.setDistancia_km(rs.getInt("DISTANCIA_KM"));
				rutaaux.setDuracion_horas(rs.getDouble("DURACION_HS"));
				rutaaux.setPeso_max_kg(rs.getDouble("PESO_MAX_KG"));
				po.setId(rs.getInt("PLANTA_ORIGEN"));
				pd.setId(rs.getInt("PLANTA_DESTINO"));
				rutaaux.setPlanta_origen(po);
				rutaaux.setPlanta_destino(pd);
				r.add(rutaaux);
			}
			stmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		

		return r;
	}
}
