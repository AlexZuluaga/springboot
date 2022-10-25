package com.control.kardex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.control.kardex.producto.ProductoRepository;
import com.control.kardex.categoria.Categoria;
import com.control.kardex.categoria.CategoriaRepository;
import com.control.kardex.producto.Producto;


@DataJpaTest
public class ProductoTest {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	
	@Test
	public void testAgregar() {
		Categoria categoria = categoriaRepository.findById(1).get();
		Producto producto = new Producto("Producto test", "Esto es un test", 3000, 22, categoria);
		productoRepository.save(producto);
	}
}

