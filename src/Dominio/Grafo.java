package Dominio;

import java.util.ArrayList;

import Gestores.Gestor_Planta;
import Gestores.Gestor_Ruta;



public class Grafo {
	private static ArrayList<Ruta> rutas;
	private static ArrayList<Planta> plantas;

public Grafo() {
	rutas = Gestor_Ruta.getRutas();
	plantas = Gestor_Planta.getPlantas();
	
	for (Ruta ruta : rutas) {
		for (Planta planta : plantas) {
			if(planta.getId().equals(ruta.getPlanta_origen().getId())) {
				ruta.setPlanta_origen(planta);
			}
			if(planta.getId().equals(ruta.getPlanta_destino().getId())) {
				ruta.setPlanta_destino(planta);
			}
		}
		
	}
	
	
	
}
public int cantRutas() {
	return rutas.size();
}
public int cantPlantas() {
	return plantas.size();
}
public static void flujo_max(Planta origen,Planta destino,Double peso_max) {
	for(Ruta ruta : Grafo.rutas) {
		if(ruta.getPlanta_origen().equals(origen)) {
			if(ruta.getPlanta_destino().equals(destino)) {
				if(peso_max == 0.0 || peso_max>ruta.getPeso_max_kg()) {
					System.out.println(ruta.getPlanta_origen().getNombre()+" "+ruta.getPlanta_destino().getNombre()+" "+ruta.getPeso_max_kg());
				}else {
					System.out.println(ruta.getPlanta_origen().getNombre()+" "+ruta.getPlanta_destino().getNombre()+" "+peso_max);
				}
			}
			else {
				if(peso_max>ruta.getPeso_max_kg() || peso_max == 0.0) { 
					flujo_max(ruta.getPlanta_destino(),destino,ruta.getPeso_max_kg());
					}
				else {
					flujo_max(ruta.getPlanta_destino(),destino,peso_max);
					}
				
			}
		}
	}
}

}
