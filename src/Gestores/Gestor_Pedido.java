package Gestores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Dominio.*;
import database.ConnectDatabase;

public class Gestor_Pedido {
	public static Integer next_nro_orden() {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "SELECT MAX(NRO_ORDEN) FROM ORDEN_PEDIDO";
		
		PreparedStatement stmt = condb.preparedStatement(query);
		ResultSet rs;
		int aux=-1;
		try {
			rs = stmt.executeQuery();
			while(rs.next()) {
				aux=rs.getInt(1);
				
			}
			if(aux <=0) aux=1;
			stmt.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		
			
		}
			//stmt.execute();
			
		return aux+1;
	}
	public static void cancelar_pedido(Integer nro_pedido) {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "UPDATE ORDEN_PEDIDO SET ESTADO = 'CANCELADO' WHERE NRO_ORDEN = ?;";

		PreparedStatement stmt = condb.preparedStatement(query);
		try {

			stmt.setInt(1,nro_pedido);
			stmt.execute();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void registrarPedido(Orden_pedido op, ArrayList<Object> aux_insumos) {
		ConnectDatabase condb = new ConnectDatabase();
		//( "NRO_ORDEN", "PLANTA_DESTINO", "FECHA_SOLICITUD", "FECHA_ENTREGA", "ESTADO", "CAMION", "RUTA", "COSTO_ENVIO" )
		String query = "INSERT INTO ORDEN_PEDIDO VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		Integer next_nro_orden = Gestor_Pedido.next_nro_orden();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement stmt = condb.preparedStatement(query);
		try {
			stmt.setInt(1,next_nro_orden);
			stmt.setInt(2, op.getPlanta_destino_id());
			stmt.setString(3, sdf.format(op.getFecha_entrega()));
			stmt.setString(4, sdf.format(op.getFecha_entrega()));
			stmt.setString(5, "CREADA");
			
			stmt.setNull(6, -1);
			stmt.setNull(7, -1);
			stmt.setNull(8, -1);
			
			stmt.execute();
			//stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//( "NRO_ORDEN", "INSUMO", "CANTIDAD" )
		query = "INSERT INTO ORDEN_PEDIDO_ITEMS VALUES(?, ?, ?);";
		stmt = condb.preparedStatement(query);
		try {
			for (int i = 0; i < aux_insumos.size(); i++) {
				ArrayList<Object> temp = (ArrayList<Object>) aux_insumos.get(i);
				stmt.setInt(1,next_nro_orden);
				stmt.setInt(2,(int) temp.get(0));
				stmt.setInt(3,(int) temp.get(1));

				stmt.execute();
			}
			
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void procesarPedido(Orden_pedido op) {
		ConnectDatabase condb = new ConnectDatabase();
		//( "NRO_ORDEN", "PLANTA_DESTINO", "FECHA_SOLICITUD", "FECHA_ENTREGA", "ESTADO", "CAMION", "RUTA", "COSTO_ENVIO" )
		String query = "UPDATE ORDEN_PEDIDO SET ESTADO = 'PROCESADO', CAMION = ?, COSTO_ENVIO = ? WHERE NRO_ORDEN = ?;";
		Integer next_nro_orden = Gestor_Pedido.next_nro_orden();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement stmt = condb.preparedStatement(query);
		try {

			stmt.setString(1, op.getId_camion());
			//stmt.setNull(7, -1);
			stmt.setDouble(2, op.getCosto_envio());
			stmt.setInt(3,op.getId());
			
			stmt.execute();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static ArrayList<Orden_pedido> getPedidos() {
		ArrayList<Orden_pedido> aux = new ArrayList<Orden_pedido>();
		
		
		ConnectDatabase condb = new ConnectDatabase();
		String query = "SELECT * FROM ORDEN_PEDIDO WHERE ESTADO LIKE 'CREADA' ";

		PreparedStatement stmt = condb.preparedStatement(query);
		
			try {
				stmt.execute();
				ResultSet rs = stmt.executeQuery();

				while(rs.next()) {
					Orden_pedido op = new Orden_pedido();
					op.setEstado(rs.getString("ESTADO"));
					op.setFecha_entrega(rs.getDate("FECHA_ENTREGA"));
					op.setFecha_solicitud(rs.getDate("FECHA_SOLICITUD"));
					op.setId(rs.getInt("NRO_ORDEN"));
					op.setPlanta_destino_id(rs.getInt("PLANTA_DESTINO"));

					aux.add(op);
				}
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		
		return aux;
	}
	public static ArrayList<ArrayList<Object>> getInsumoCantidad(Integer nro_orden) {
		ConnectDatabase condb = new ConnectDatabase();
		String query = "SELECT * FROM ORDEN_PEDIDO_ITEMS WHERE NRO_ORDEN = ?";

		PreparedStatement stmt = condb.preparedStatement(query);
		ArrayList<ArrayList<Object>> temp = new ArrayList<ArrayList<Object>>(); 
			try {
				stmt.setInt(1, nro_orden);
				stmt.execute();
				ResultSet rs = stmt.executeQuery();
				
				
				
				while(rs.next()) {
					ArrayList<Object> insumo_cantidad = new ArrayList<Object>();
					insumo_cantidad.add(rs.getInt("INSUMO"));
					insumo_cantidad.add(rs.getInt("CANTIDAD"));
					temp.add(insumo_cantidad);
				
				}
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		
		return temp;
	}
	

}
