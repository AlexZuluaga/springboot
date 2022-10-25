package com.control.kardex.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import com.control.kardex.categoria.Categoria;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	@Query("SELECT p FROM Producto p WHERE p.nombre LIKE %?1%")
	public List<Producto> findAll(String palabraClave);
}
