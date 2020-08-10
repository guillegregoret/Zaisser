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
			stmt.setString(4, sdf.format(op.getFecha_entrega())); //<___________________-------------------------------------------------------------
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
	

}
