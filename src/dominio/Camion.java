package dominio;

import java.util.Date;

public class Camion {
	private String patente;
	private String marca_modelo;
	private Integer km_recorridos;
	private Double costo_por_km;
	private Double costo_por_hora;
	private Date fecha_compra;
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getMarca_modelo() {
		return marca_modelo;
	}
	public void setMarca_modelo(String marca_modelo) {
		this.marca_modelo = marca_modelo;
	}
	public Integer getKm_recorridos() {
		return km_recorridos;
	}
	public void setKm_recorridos(Integer km_recorridos) {
		this.km_recorridos = km_recorridos;
	}
	public Double getCosto_por_km() {
		return costo_por_km;
	}
	public void setCosto_por_km(Double costo_por_km) {
		this.costo_por_km = costo_por_km;
	}
	public Double getCosto_por_hora() {
		return costo_por_hora;
	}
	public void setCosto_por_hora(Double costo_por_hora) {
		this.costo_por_hora = costo_por_hora;
	}
	public Date getFecha_compra() {
		return fecha_compra;
	}
	public void setFecha_compra(Date fecha_compra) {
		this.fecha_compra = fecha_compra;
	}
	
}
