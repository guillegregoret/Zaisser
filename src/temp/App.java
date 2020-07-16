package temp;

import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import Dominio.Camion;
import Gestores.Gestor_Camion;
import database.*;

public class App {
public static void main(String[] args) {
	
	Camion c = new Camion();
	//c.setPatente("AR510S");
	//c.setCosto_por_hora(123.1);
	//c.setCosto_por_km(54.2);
	//Date fecha = new Date(115,6,8);
	//c.setFecha_compra(fecha);
	//c.setKm_recorridos(54654);
	//c.setMarca_modelo("SCANIACAMIONERO");
	c.setKm_recorridos(15000);
	Gestor_Camion gc = new Gestor_Camion();

	ArrayList<Camion> camionsitos = Gestor_Camion.getCamiones(c);
	System.out.println(camionsitos.size());
}
}
