package dominio;

public class Ruta {
	private Planta planta_origen;
	private Planta planta_destino;
	private Integer distancia_km;
	private Double duracion_horas;
	private Double peso_max_kg;
	public Planta getPlanta_origen() {
		return planta_origen;
	}
	public void setPlanta_origen(Planta planta_origen) {
		this.planta_origen = planta_origen;
	}
	public Planta getPlanta_destino() {
		return planta_destino;
	}
	public void setPlanta_destino(Planta planta_destino) {
		this.planta_destino = planta_destino;
	}
	public Integer getDistancia_km() {
		return distancia_km;
	}
	public void setDistancia_km(Integer distancia_km) {
		this.distancia_km = distancia_km;
	}
	public Double getDuracion_horas() {
		return duracion_horas;
	}
	public void setDuracion_horas(Double duracion_horas) {
		this.duracion_horas = duracion_horas;
	}
	public Double getPeso_max_kg() {
		return peso_max_kg;
	}
	public void setPeso_max_kg(Double peso_max_kg) {
		this.peso_max_kg = peso_max_kg;
	}
}
