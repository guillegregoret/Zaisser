package Dominio;

import java.util.ArrayList;



public class Grafo {
	private static ArrayList<Ruta> rutas;
public static void main(String[] args) {
	rutas = new ArrayList<Ruta>();
	Planta p1 = new Planta();
	p1.setNombre("Planta 1 - Avda");
	Planta p2 = new Planta();
	p2.setNombre("Planta 2 - Santa Fe");
	Planta p3 = new Planta();
	p3.setNombre("Planta 3 - Rosario");
	Planta p4 = new Planta();
	p4.setNombre("Planta 4 - Bs. As.");
	
	Ruta r1 = new Ruta();
	rutas.add(r1);
	r1.setDistancia_km(310);
	r1.setPeso_max_kg(11000.0);
	r1.setPlanta_origen(p1);
	r1.setPlanta_destino(p2);

	
	Ruta AU1 = new Ruta();
	rutas.add(AU1);
	AU1.setDistancia_km(170);
	AU1.setPeso_max_kg(22000.0);
	AU1.setPlanta_origen(p2);
	AU1.setPlanta_destino(p3);
	
	Ruta r11 = new Ruta();
	rutas.add(r11);
	r11.setDistancia_km(500);
	r11.setPeso_max_kg(15000.0);
	r11.setPlanta_origen(p1);
	r11.setPlanta_destino(p3);
	
	Ruta AU2 = new Ruta();
	rutas.add(AU2);
	AU2.setDistancia_km(170);
	AU2.setPeso_max_kg(5000.0);
	AU2.setPlanta_origen(p3);
	AU2.setPlanta_destino(p4);
	Grafo.flujo_max(p1,p4,0.0);

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
