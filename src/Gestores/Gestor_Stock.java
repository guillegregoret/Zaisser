package Gestores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dominio.Insumo;
import Dominio.Insumo_general;
import Dominio.Insumo_liquido;
import Dominio.Stock;
import database.ConnectDatabase;

public class Gestor_Stock {
	public static void guardarStock(Stock s) {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "INSERT INTO STOCK VALUES(?, ?, ?, ?, ?);";
		PreparedStatement stmt = condb.preparedStatement(query);
		try {
			stmt.setInt(1,Gestor_Stock.next_id());
			stmt.setInt(2, s.getInsumo());
			stmt.setInt(3, s.getPlanta());
			stmt.setInt(4, s.getCantidad());
			stmt.setInt(5, s.getPunto_pedido());
			stmt.execute();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Integer next_id() {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "SELECT MAX(ID) FROM STOCK";
		
		PreparedStatement stmt = condb.preparedStatement(query);
		ResultSet rs;
		int aux=-1;
		try {
			rs = stmt.executeQuery();
			while(rs.next()) {
				aux=rs.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			//stmt.execute();
			
		return aux+1;
	}
	public static Integer stock_total(Integer id) {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "SELECT SUM(CANTIDAD) FROM STOCK WHERE INSUMO = ?";
		
		PreparedStatement stmt = condb.preparedStatement(query);
		int aux=0;
		try {
			stmt.setInt(1,id);
			ResultSet rs;
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				aux=rs.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aux;
	}
	public static ArrayList<Stock> getStocks(String planta, String insumo) {
		ArrayList<Stock> st = new ArrayList<Stock>();
		
		ConnectDatabase condb = new ConnectDatabase();
		int aux=1;
		String query = "SELECT * FROM STOCK WHERE CANTIDAD < PUNTO_PEDIDO";
		
		if(!planta.isEmpty()) {
			query+= " AND PLANTA LIKE ?";
			System.out.println(insumo);
		}
		if(!insumo.isEmpty()) {
			query+= " AND INSUMO LIKE ?";
			System.out.println(planta);
		}
		
		
		PreparedStatement stmt = condb.preparedStatement(query);
		
			try {

				if(!planta.isEmpty()) {
					stmt.setInt(aux, Gestor_Planta.getID(planta));
					aux++;
				}
				if(!insumo.isEmpty()) {
					stmt.setInt(aux, Gestor_Insumo.getID(insumo));
				}
				stmt.execute();
				ResultSet rs = stmt.executeQuery();

				while(rs.next()) {
					Stock s_aux = new Stock();
					s_aux.setCantidad(rs.getInt("CANTIDAD"));
					s_aux.setPunto_pedido(rs.getInt("PUNTO_PEDIDO"));
					s_aux.setPlanta(rs.getInt("PLANTA"));
					s_aux.setInsumo(rs.getInt("INSUMO"));
					
					st.add(s_aux);
						
				}
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
		return st;
	}

}

