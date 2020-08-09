 package Gestores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
	static public void eliminar_insumo(Insumo i) {
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
	public static ArrayList<Insumo> getInsumo(Insumo i) {
		ArrayList<Insumo> insumos = new ArrayList<Insumo>();
		ConnectDatabase condb = new ConnectDatabase();
		int aux=1;
		String query = "SELECT * FROM INSUMO ";
		/*if(i.getId() >0 ) {
			if(aux==1) query+="WHERE ID LIKE ?";
			query+=" ID LIKE ?";
			aux++;
		}*/
		if(i.getCosto() != null) {
			if(aux==1) query+="WHERE COSTO = ?";
			else query+=" AND COSTO = ?";
			aux++;
			}
		if(i.getDescripcion() != null) {
			if(aux==1) query+="WHERE DESCRIPCION LIKE ?";
			else query+=" AND DESCRIPCION LIKE ?";
			aux++;
			}
		if(i.getUnidad_medida() != null) {
			if(aux==1) query+="WHERE UNIDAD_MEDIDA LIKE ?";
			else query+=" AND UNIDAD_MEDIDA LIKE ?";
			aux++;
		}
		aux=1;
		PreparedStatement stmt = condb.preparedStatement(query);
		
			try {
				/*if(i.getId() == null ) {
					stmt.setInt(aux,i.getId());
					aux++;
				}*/
				if(i.getCosto() != null) {
					stmt.setDouble(aux,i.getCosto());
					aux++;
					}
				if(i.getDescripcion() != null) {
					stmt.setString(aux, i.getDescripcion());
					aux++;
					}
				if(i.getUnidad_medida() != null) {
					stmt.setString(aux, i.getUnidad_medida());
					aux++;
				}
				stmt.execute();
				ResultSet rs = stmt.executeQuery();

				while(rs.next()) {
					//Insumo insumo_temp = new Insumo();
					if(rs.getString("PESO") != null) {
						Insumo_general insumo_temp = new Insumo_general();
						insumo_temp.setCosto(Double.valueOf(rs.getDouble("COSTO")));
						insumo_temp.setDescripcion(rs.getString("DESCRIPCION"));
						insumo_temp.setId(rs.getInt("ID"));
						insumo_temp.setUnidad_medida(rs.getString("UNIDAD_MEDIDA"));
						insumo_temp.setPeso(rs.getDouble("PESO"));
						insumos.add(insumo_temp);
					}
					else {
						Insumo_liquido insumo_temp = new Insumo_liquido();
					insumo_temp.setCosto(Double.valueOf(rs.getDouble("COSTO")));
					insumo_temp.setDescripcion(rs.getString("DESCRIPCION"));
					insumo_temp.setId(rs.getInt("ID"));
					insumo_temp.setUnidad_medida(rs.getString("UNIDAD_MEDIDA"));
					insumo_temp.setDensidad(rs.getDouble("DENSIDAD"));

					insumos.add(insumo_temp);
					}

				}
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
		return insumos;
	}
	public static Integer next_id() {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "SELECT MAX(ID) FROM INSUMO";
		
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
}