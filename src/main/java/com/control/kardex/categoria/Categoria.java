package com.control.kardex.categoria;

import javax.persistence.*;

import com.control.kardex.empresa.*;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50, nullable = false, unique = true)
	private String nombre;

	//Genero la relacion con la tabla categoria, muchos a uno.
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
		
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Categoria(Integer id, String nombre, Empresa empresa) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.empresa = empresa;
	}

	public Categoria(String nombre, Empresa empresa) {
		super();
		this.nombre = nombre;
		this.empresa = empresa;
	}

	public Categoria() {
		super();
	}

	
	
	
	
}
