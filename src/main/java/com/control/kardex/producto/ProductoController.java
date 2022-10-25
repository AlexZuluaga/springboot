package com.control.kardex.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.control.kardex.categoria.Categoria;
import com.control.kardex.categoria.CategoriaRepository;


@Controller 
public class ProductoController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	
	@Autowired
	private ProductoService productoServicio;
	
	
	@RequestMapping("/productos")
	public String verPaginaDeInicio(Model modelo, @Param("palabraClave") String palabraClave ) {
		
		
		List<Producto> ListaProductos = productoServicio.listAll(palabraClave);
		modelo.addAttribute("listaProductos", ListaProductos);
		modelo.addAttribute("palabraClave", palabraClave);
		
		return "productos";
	}
	
	@GetMapping("/productos/formulario")
	public String mostrarFormularioDeRegistrarProducto(Model modelo) {
		
		List<Categoria> categorias = categoriaRepository.findAll();
		Producto producto = new Producto();
		modelo.addAttribute("producto", producto);
		modelo.addAttribute("categorias", categorias);
		
		return "producto_formulario";
	}
	
	
	
	@PostMapping("/productos/guardar")
	public String productoGuardar(Producto producto) {
		productoServicio.save(producto);
		return "redirect:/productos";
	}
	
	@RequestMapping("/productos/restar/{id}/{cantidad}")
	public String restar(@PathVariable(name = "id") Integer id, @PathVariable(name = "cantidad") Integer cantidad ) {
		productoServicio.restarCantidad(id, cantidad);
		return "redirect:/productos";
	}
	
	@RequestMapping("/productos/sumar/{id}/{cantidad}")
	public String sumar(@PathVariable(name = "id") Integer id, @PathVariable(name = "cantidad") Integer cantidad ) {
		productoServicio.sumarCantidad(id, cantidad);
		return "redirect:/productos";
	}
	
	
	
	@RequestMapping("productos/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id") Integer id ) {

		productoServicio.delete(id);
		
		return "redirect:/productos";
	}
	
	
	@RequestMapping("productos/editar/{id}")
	public ModelAndView mostrarFormularioEditar(@PathVariable(name = "id") Integer id ) {
		
		ModelAndView modelo = new ModelAndView("producto_formulario");
		List<Categoria> categorias = categoriaRepository.findAll();
		
		Producto producto = productoServicio.get(id);
		
		modelo.addObject("categorias", categorias);
		modelo.addObject("producto", producto);
		
		return modelo;
	}
	
	
	
}
