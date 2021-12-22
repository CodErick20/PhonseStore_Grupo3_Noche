package com.phoneapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_categoria;

    @NotEmpty
    @Size(min = 2, max = 12)
    @Column(nullable = false, unique = true)
    private String Nombre;

    @NotEmpty
    @Column(nullable = false)
    private String Estado;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Producto> listarProducto;

    public Categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    private static final long serialVersionUID = 1L;

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public List<Producto> getListarProducto() {
		return listarProducto;
	}

	public void setListarProducto(List<Producto> listarProducto) {
		this.listarProducto = listarProducto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
