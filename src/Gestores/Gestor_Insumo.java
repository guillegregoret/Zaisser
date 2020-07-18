 package Gestores;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import Dominio.*;
import database.ConnectDatabase;

public class Gestor_Insumo {
	public static void alta_insumo(Insumo i) {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "INSERT INTO INSUMO VALUES(?, ?, ?, ?,";
		
		if(i instanceof Insumo_liquido) {
			query+="null,?);";
		}
		if(i instanceof Insumo_general) {
			query+="?,null);";
		}
		PreparedStatement stmt = condb.preparedStatement(query);
		try {
			stmt.setInt(1, i.getId());
			stmt.setString(2, i.getDescripcion());
			stmt.setString(3, i.getUnidad_medida());
			stmt.setDouble(4, i.getCosto());
			if(i instanceof Insumo_liquido) {
				stmt.setDouble(5,((Insumo_liquido) i).getDensidad());
			}
			if(i instanceof Insumo_general) {
				stmt.setDouble(5,((Insumo_general) i).getPeso());
			}
			stmt.execute();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
}
	public static void modificar_insumo(Insumo i) {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "UPDATE INSUMO SET DESCRIPCION=?, UNIDAD_MEDIDA=?, COSTO=?,";
		if(i instanceof Insumo_liquido) {
			query+="PESO=? WHERE ID=?;";
		}
		if(i instanceof Insumo_general) {
			query+="DENSIDAD=? WHERE ID=?;";
		}
		PreparedStatement stmt = condb.preparedStatement(query);
		try {
			stmt.setInt(5, i.getId());
			stmt.setString(1, i.getDescripcion());
			stmt.setString(2, i.getUnidad_medida());
			stmt.setDouble(3, i.getCosto());
			if(i instanceof Insumo_liquido) {
				stmt.setDouble(4,((Insumo_liquido) i).getDensidad());
			}
			if(i instanceof Insumo_general) {
				stmt.setDouble(4,((Insumo_general) i).getPeso());
			}
			stmt.execute();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void eliminar_insumo(Insumo i) {
		ConnectDatabase condb = new ConnectDatabase();
		PreparedStatement stmt = condb.preparedStatement("DELETE FROM INSUMO WHERE ID LIKE ?;");
		try {
			stmt.setInt(1,i.getId());
			stmt.execute();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}