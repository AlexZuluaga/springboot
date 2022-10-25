package com.control.kardex.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

	// Inyecto el repositorio. Esto me crea la instancia donde sea necesario
	@Autowired
	private ProductoRepository productoRepositorio;

	public List<Producto> listAll(String palabraClave) {

		if (palabraClave != null) {
			return productoRepositorio.findAll(palabraClave);
		}
		return productoRepositorio.findAll();
	}

	public void save(Producto producto) {
		productoRepositorio.save(producto);
	}

	public Producto get(Integer id) {
		return productoRepositorio.findById(id).get();
	}

	public void delete(Integer id) {
		productoRepositorio.deleteById(id);
	}

	public void restarCantidad( Integer id, Integer cantidad) {
		
		Producto producto = productoRepositorio.findById(id).get();
		float stock = producto.getStock();
		
		if(stock > 0 && stock >= cantidad ) {
			stock = stock - cantidad;
			producto.setStock(stock);
			productoRepositorio.save(producto);
		}
	}
	
	public void sumarCantidad(Integer id, Integer cantidad) {

		Producto producto = productoRepositorio.findById(id).get();
		float stock = producto.getStock();		 
		stock = stock + cantidad;
		producto.setStock(stock);
		productoRepositorio.save(producto);
	}

	public void restarCantidad(Integer id, float cantidad) {
		productoRepositorio.deleteById(id);
	}
}
