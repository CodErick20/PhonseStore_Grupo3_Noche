package com.phoneapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;
    private String Nombre;
    private String Url_imagen;

	/*objeto no nullo*/
	@NotNull(message = "no puede estar vacio")
    private Date Fecha_Ingreso;

    private double Precio_Compra;
    private  Integer Stock;
    private String Estado;

//	@PrePersist
//	public void  prePersist(){
//		Fecha_Ingreso = new Date();
//	}

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Detalle_Venta> listaDetallePedido;

    @OneToMany(mappedBy = "producto",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Carrito>listaCarrito;

    private static  final  long serialVersionUID = 1L;

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getUrl_imagen() {
		return Url_imagen;
	}

	public void setUrl_imagen(String url_imagen) {
		Url_imagen = url_imagen;
	}

	public Date getFecha_Ingreso() {
		return Fecha_Ingreso;
	}

	public void setFecha_Ingreso(Date fecha_Ingreso) {
		Fecha_Ingreso = fecha_Ingreso;
	}

	public double getPrecio_Compra() {
		return Precio_Compra;
	}

	public void setPrecio_Compra(double precio_Compra) {
		Precio_Compra = precio_Compra;
	}

	public Integer getStock() {
		return Stock;
	}

	public void setStock(Integer stock) {
		Stock = stock;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Detalle_Venta> getListaDetallePedido() {
		return listaDetallePedido;
	}

	public void setListaDetallePedido(List<Detalle_Venta> listaDetallePedido) {
		this.listaDetallePedido = listaDetallePedido;
	}

	public List<Carrito> getListaCarrito() {
		return listaCarrito;
	}

	public void setListaCarrito(List<Carrito> listaCarrito) {
		this.listaCarrito = listaCarrito;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    


}
