package Gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Dominio.Camion;

import database.*;

public class Gestor_Camion {
	public static void alta_camion(Camion c) {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "INSERT INTO CAMION VALUES(?, ?, ?, ?, ?, ?);";
		PreparedStatement stmt = condb.preparedStatement(query);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt.setString(1,c.getPatente());
			stmt.setString(2, c.getMarca_modelo());
			stmt.setInt(3,c.getKm_recorridos());
			stmt.setDouble(4, c.getCosto_por_km());
			stmt.setDouble(5,c.getCosto_por_hora());
			stmt.setString(6, sdf.format(c.getFecha_compra()));
			stmt.execute();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void modificar_camion(Camion c) {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "UPDATE CAMION SET MARCA_MODELO = ?,KM_RECORRIDOS = ?, COSTO_POR_KM = ?, COSTO_POR_HORA = ?, FECHA_COMPRA = ? WHERE PATENTE = ?;";

		PreparedStatement stmt = condb.preparedStatement(query);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int i=1;
			if(!c.getMarca_modelo().isEmpty()) {
				stmt.setString(i, c.getMarca_modelo());
				i++;
			}
			if(c.getKm_recorridos() != null) {
				stmt.setInt(i,c.getKm_recorridos());
				i++;
			}
			if(c.getCosto_por_km() != null) {
				stmt.setDouble(i, c.getCosto_por_km());
				i++;
			}
			if(c.getCosto_por_hora() != null) {
				stmt.setDouble(i,c.getCosto_por_hora());
				i++;
			}
			if(c.getFecha_compra() != null) {
				stmt.setString(i, sdf.format(c.getFecha_compra()));
				i++;
			}
			stmt.setString(i,c.getPatente());
			stmt.execute();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void eliminar_camion(Camion c) {
		ConnectDatabase condb = new ConnectDatabase();
		PreparedStatement stmt = condb.preparedStatement("DELETE FROM CAMION WHERE PATENTE LIKE ?;");
		try {
			stmt.setString(1,c.getPatente());
			stmt.execute();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static ArrayList<Camion> getCamiones(Camion c) {
		ArrayList<Camion> camiones = new ArrayList<Camion>();
		ConnectDatabase condb = new ConnectDatabase();
		int i=1;
		String query = "SELECT * FROM CAMION WHERE";
		if(c.getPatente() != null) {
			query+=" PATENTE LIKE ?";
			}
		else {
			query+=" PATENTE LIKE '%'";
		}
		if(c.getMarca_modelo() != null) {
			query+=" AND MARCA_MODELO LIKE ?";
			}
		if(c.getKm_recorridos() != null) {
			query+=" AND KM_RECORRIDOS LIKE ?";
		}
		if(c.getCosto_por_km() != null) {
			query+=" AND COSTO_POR_KM = ?";
		}
		if(c.getCosto_por_hora() != null) {
			query+=" AND COSTO_POR_HORA = ?";
		}
		if(c.getFecha_compra() != null) {
			query+=" AND  FECHA_COMPRA LIKE ?";
		}
		PreparedStatement stmt = condb.preparedStatement(query);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(c.getPatente() != null) {
				stmt.setString(i,c.getPatente());
				i++;
				}
			if(c.getMarca_modelo() != null) {
				stmt.setString(i,c.getMarca_modelo());
				i++;
				}
			if(c.getKm_recorridos() != null) {
				stmt.setInt(i,c.getKm_recorridos());
				i++;
			}
			if(c.getCosto_por_km() != null) {
				stmt.setDouble(i, c.getCosto_por_km());
				i++;
			}
			if(c.getCosto_por_hora() != null) {
				stmt.setDouble(i,c.getCosto_por_hora());
				i++;
			}
			if(c.getFecha_compra() != null) {
				stmt.setString(i, sdf.format(c.getFecha_compra()));
				
			}
			stmt.execute(); //------------------------------------------
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Camion camion_temp = new Camion();
				camion_temp.setPatente(rs.getString("PATENTE"));
				camion_temp.setMarca_modelo(rs.getString("MARCA_MODELO"));
				camion_temp.setKm_recorridos(rs.getInt("KM_RECORRIDOS"));
				camion_temp.setCosto_por_km(rs.getDouble("COSTO_POR_KM"));
				camion_temp.setCosto_por_hora(rs.getDouble("COSTO_POR_HORA"));
				camion_temp.setFecha_compra(rs.getDate("FECHA_COMPRA"));
				
				camiones.add(camion_temp);
			}
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return camiones;
	}
	 
}
