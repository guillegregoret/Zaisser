package Dominio;

import java.util.ArrayList;
import java.util.stream.Collectors;

import Gestores.Gestor_Planta;
import Gestores.Gestor_Ruta;



	public class Grafo {
		private static ArrayList<Ruta> rutas;
		public static ArrayList<Ruta> getRutas() {
			return rutas;
		}

		public static void setRutas(ArrayList<Ruta> rutas) {
			Grafo.rutas = rutas;
		}

		public static ArrayList<Planta> getPlantas() {
			return plantas;
		}

		public static void setPlantas(ArrayList<Planta> plantas) {
			Grafo.plantas = plantas;
		}
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
	
	public Ruta getRuta(Planta origen, Planta destino){
		for (Ruta r : rutas) {
			if(r.getPlanta_origen().getId()==origen.getId() && r.getPlanta_destino().getId()==destino.getId()) {
				return r;
			}
		}
		return null;
		
	}
	
	public void caminosAux(Planta origen, Planta destino, ArrayList<Planta> marcados, ArrayList<ArrayList<Planta>> resultados){

		ArrayList<Planta> copiamarcados = null;
		ArrayList<Planta> adyacentes = this.getAdyacentes(origen);
		for (Planta planta : adyacentes) {
			copiamarcados = (ArrayList<Planta>) marcados.stream().collect(Collectors.toList());
			if(planta.getId()==destino.getId()) {
				copiamarcados.add(destino);
				resultados.add(new ArrayList<Planta>(copiamarcados));
			}else {
				if(!copiamarcados.contains(planta)) {
					copiamarcados.add(planta);
					this.caminosAux(planta, destino,copiamarcados,resultados);
				}
			}
			
		}
		
	}
	public  ArrayList<ArrayList<Planta>> caminos(Planta origen, Planta destino){
		ArrayList<Planta> marcados = new ArrayList<Planta>();
		ArrayList<ArrayList<Planta>> resultados = new ArrayList<ArrayList<Planta>>();
		marcados.add(origen);
		caminosAux(origen, destino, marcados, resultados);
		return resultados;
	}
	
	public ArrayList<ArrayList<Planta>> betterCaminos(Planta origen, Planta destino){
    	Double mejorLong = Double.MAX_VALUE;
    	Double mejorTiem = Double.MAX_VALUE;
    	ArrayList<ArrayList<Planta>> mejoresCaminos = new ArrayList<ArrayList<Planta>>();
    	ArrayList<ArrayList<Planta>> caminos = caminos(origen, destino);
    	mejoresCaminos.add(0, new ArrayList<Planta>());
    	mejoresCaminos.add(1, new ArrayList<Planta>());
		for(ArrayList<Planta> ruta : caminos) {
    		Double longitudAux = new Double(0);
    		Double tiempoAux = new Double(0);
    		for(int i = 0 ; i < ruta.size()-1 ; i++) {
				
				Ruta rutaAux = this.getRuta(ruta.get(i), ruta.get(i+1));
				longitudAux += rutaAux.getDistancia_km();
				tiempoAux += rutaAux.getDuracion_horas();
			}
			if(longitudAux < mejorLong) {
				mejorLong = longitudAux;
				mejoresCaminos.remove(0);
				mejoresCaminos.add(0, ruta);
			}
			if(tiempoAux < mejorTiem) {
				mejorTiem = tiempoAux;
				mejoresCaminos.remove(1);
				mejoresCaminos.add(1, ruta);
			}
    	}
		return mejoresCaminos;
	}
	public Double betterLongitud(Planta origen, Planta destino){
    	Double mejorLong = Double.MAX_VALUE;
    	Double mejorTiem = Double.MAX_VALUE;
    	ArrayList<ArrayList<Planta>> mejoresCaminos = new ArrayList<ArrayList<Planta>>();
    	ArrayList<ArrayList<Planta>> caminos = caminos(origen, destino);
    	mejoresCaminos.add(0, new ArrayList<Planta>());
    	mejoresCaminos.add(1, new ArrayList<Planta>());
		for(ArrayList<Planta> ruta : caminos) {
    		Double longitudAux = new Double(0);
    		Double tiempoAux = new Double(0);
    		for(int i = 0 ; i < ruta.size()-1 ; i++) {
				
				Ruta rutaAux = this.getRuta(ruta.get(i), ruta.get(i+1));
				longitudAux += rutaAux.getDistancia_km();
				tiempoAux += rutaAux.getDuracion_horas();
			}
			if(longitudAux < mejorLong) {
				mejorLong = longitudAux;
				mejoresCaminos.remove(0);
				mejoresCaminos.add(0, ruta);
			}
			if(tiempoAux < mejorTiem) {
				mejorTiem = tiempoAux;
				mejoresCaminos.remove(1);
				mejoresCaminos.add(1, ruta);
			}
    	}
		return mejorLong;
	}
	public Double betterTiempo(Planta origen, Planta destino){
    	Double mejorLong = Double.MAX_VALUE;
    	Double mejorTiem = Double.MAX_VALUE;
    	ArrayList<ArrayList<Planta>> mejoresCaminos = new ArrayList<ArrayList<Planta>>();
    	ArrayList<ArrayList<Planta>> caminos = caminos(origen, destino);
    	mejoresCaminos.add(0, new ArrayList<Planta>());
    	mejoresCaminos.add(1, new ArrayList<Planta>());
		for(ArrayList<Planta> ruta : caminos) {
    		Double longitudAux = new Double(0);
    		Double tiempoAux = new Double(0);
    		for(int i = 0 ; i < ruta.size()-1 ; i++) {
				
				Ruta rutaAux = this.getRuta(ruta.get(i), ruta.get(i+1));
				longitudAux += rutaAux.getDistancia_km();
				tiempoAux += rutaAux.getDuracion_horas();
			}
			if(longitudAux < mejorLong) {
				mejorLong = longitudAux;
				mejoresCaminos.remove(0);
				mejoresCaminos.add(0, ruta);
			}
			if(tiempoAux < mejorTiem) {
				mejorTiem = tiempoAux;
				mejoresCaminos.remove(1);
				mejoresCaminos.add(1, ruta);
			}
    	}
		return mejorTiem;
	}

	
	private ArrayList<Planta> getAdyacentes(Planta p){ 
		ArrayList<Planta> salida = new ArrayList<Planta>();
		rutas = Gestor_Ruta.getRutas();
		for(Ruta enlace : Grafo.rutas){
	
			if( enlace.getPlanta_origen().getId()==p.getId()){
				salida.add(enlace.getPlanta_destino());
				
			}
		}
		
		return salida;
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
	/*
	public ArrayList<Object> pagerank(){
		ArrayList<Object> aux = new ArrayList<Object[]>();
		
		
		rutas = Gestor_Ruta.getRutas();
		plantas = Gestor_Planta.getPlantas();
		for (int i = 0; i < plantas.size(); i++) {
			Object[] grado_planta = new Object[2];
			aux.add(grado_planta);
		}
		
		for (Ruta ruta : rutas) {
			aux.get(ruta.getPlanta_origen().getId())[0]=1;
			aux.get(ruta.getPlanta_origen().getId())[1]=1;
			//grado_planta[ruta.getPlanta_origen().getId()]
			ruta.getPlanta_origen().getId();
			ruta.getPlanta_destino().getId();
			
		}
		
		return aux;
	}
	*/
	}
