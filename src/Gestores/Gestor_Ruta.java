package Gestores;

import java.sql.PreparedStatement;

import Dominio.Insumo_general;
import Dominio.Insumo_liquido;
import Dominio.Ruta;
import database.ConnectDatabase;

public class Gestor_Ruta {
	public void alta_ruta(Ruta ruta) {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "INSERT INTO RUTA VALUES(?, ?, ?, ?, ?, ?);";
		//( "ID", "PLANTA_ORIGEN", "PLANTA_DESTINO", "DISTANCIA_KM", "DURACION_HS", "PESO_MAX_KG" )
		PreparedStatement stmt = condb.preparedStatement(query);
		try {
			//stmt.setInt(1, ruta.get);
			//stmt.setString(2, ruta.getDescripcion());
			//stmt.setString(3, ruta.getUnidad_medida());
			//stmt.setDouble(4, ruta.getCosto());
			//stmt.execute();
			//stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
