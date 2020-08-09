package Dominio;

public class Stock {
	private Integer planta;
	private Integer insumo;
	private Integer cantidad;
	private Integer punto_pedido;
	public Integer getPlanta() {
		return planta;
	}
	public void setPlanta(Integer planta) {
		this.planta = planta;
	}
	public Integer getInsumo() {
		return insumo;
	}
	public void setInsumo(Integer insumo) {
		this.insumo = insumo;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getPunto_pedido() {
		return punto_pedido;
	}
	public void setPunto_pedido(Integer punto_pedido) {
		this.punto_pedido = punto_pedido;
	}

}
