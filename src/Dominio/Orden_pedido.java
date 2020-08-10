package Dominio;

import java.util.ArrayList;
import java.util.Date;

public class Orden_pedido {
	private Integer id;
	private Integer planta_destino_id;
	private Date fecha_solicitud;
	private Date fecha_entrega;
	private String estado;
	private ArrayList insumos;
	private Integer id_camion;
	private Ruta ruta;
	private Double costo_envio;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPlanta_destino_id() {
		return planta_destino_id;
	}
	public void setPlanta_destino_id(Integer planta_destino_id) {
		this.planta_destino_id = planta_destino_id;
	}
	public Date getFecha_solicitud() {
		return fecha_solicitud;
	}
	public void setFecha_solicitud(Date fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}
	public Date getFecha_entrega() {
		return fecha_entrega;
	}
	public void setFecha_entrega(Date fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public ArrayList getInsumos() {
		return insumos;
	}
	public void setInsumos(ArrayList insumos) {
		this.insumos = insumos;
	}
	public Integer getId_camion() {
		return id_camion;
	}
	public void setId_camion(Integer id_camion) {
		this.id_camion = id_camion;
	}
	public Ruta getRuta() {
		return ruta;
	}
	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	public Double getCosto_envio() {
		return costo_envio;
	}
	public void setCosto_envio(Double costo_envio) {
		this.costo_envio = costo_envio;
	}
	

}
