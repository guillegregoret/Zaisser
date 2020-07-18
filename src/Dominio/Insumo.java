package Dominio;

public class Insumo {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String descripcion;
	private String unidad_medida;
	private Double costo;
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUnidad_medida() {
		return unidad_medida;
	}
	public void setUnidad_medida(String unidad_medida) {
		this.unidad_medida = unidad_medida;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
}
