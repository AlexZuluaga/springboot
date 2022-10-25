package com.control.kardex.producto;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.control.kardex.categoria.Categoria;
import com.control.kardex.categoria.CategoriaRepository;


@RestController
@RequestMapping("/api/")
public class ProductoControllerRest {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	
	@Autowired
	private ProductoService productoServicio;
	
	@GetMapping("/productos")
	public List<Producto> productos(){
		return productoServicio.listAll(null);
	}
	
	@GetMapping("/productos/{id}")
	public ResponseEntity<Producto> producto( @PathVariable(name = "id") Integer id){
		
		try {
			Producto producto = productoServicio.get(id);
			return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/productos")
	public void guardarProducto(@RequestBody Producto producto) {
		
		if(producto.getId() == null) {
			productoServicio.save(producto);
		}
	}
	
	@PutMapping("/productos/{id}")
	public ResponseEntity<?> editarProducto(@RequestBody Producto producto,  @PathVariable(name = "id") Integer id) {
		try {
			
			Producto productoEncontrado = productoServicio.get(id);	
			productoServicio.save(producto);
			return new ResponseEntity<Producto>( HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> eleminarProducto( @PathVariable(name = "id") Integer id) {
		try {
			productoServicio.delete(id);	
			return new ResponseEntity<Producto>( HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	@GetMapping("/productos/sumar/{id}/{cantidad}")
	public ResponseEntity<?> cantidadSumar(@PathVariable(name = "id") Integer id, @PathVariable(name = "cantidad") Integer cantidad) {
		try {
			
			productoServicio.sumarCantidad(id, cantidad);
			Producto producto = productoServicio.get(id);
			return new ResponseEntity<Producto>(producto, HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/productos/restar/{id}/{cantidad}")
	public ResponseEntity<?> cantidadRestar(@PathVariable(name = "id") Integer id, @PathVariable(name = "cantidad") Integer cantidad) {
		try {
			
			Producto productoProbar = productoServicio.get(id);
			
			if (productoProbar.getStock() >= cantidad) {
				productoServicio.restarCantidad(id, cantidad);
				Producto producto = productoServicio.get(id);
				return new ResponseEntity<Producto>(producto, HttpStatus.OK);
			}else {
				return new ResponseEntity<Producto>(productoProbar, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e){
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
}
