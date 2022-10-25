package com.control.kardex.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.control.kardex.empresa.Empresa;
import com.control.kardex.empresa.EmpresaRepository;
import com.control.kardex.producto.Producto;


@Controller 
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@GetMapping("/categorias")
	public String ListarCategorias(Model model) {
		
		List<Categoria> categorias = categoriaRepository.findAll();
		model.addAttribute("categorias", categorias);
		
		return "categorias";
	}
	
	@GetMapping("/categorias/formulario")
	public String formularioCategoria(Model model) {
		
		List<Empresa> empresas = empresaRepository.findAll();
		
		Categoria categoria = new Categoria();
		model.addAttribute("categoria", categoria);
		model.addAttribute("empresas", empresas);
		
		return "categorias_formulario";
	}
	
	
	@RequestMapping("categorias/editar/{id}")
	public ModelAndView categoriaEditar(@PathVariable(name = "id") Integer id ) {
		
		ModelAndView modelo = new ModelAndView("categorias_formulario");
		List<Empresa> empresas = empresaRepository.findAll();
		
		Categoria categoria = categoriaRepository.findById(id).get();
		
		modelo.addObject("empresas", empresas);
		modelo.addObject("categoria", categoria);
		
		return modelo;
	}
	
	
	
	@PostMapping("/categorias/guardar")
	public String categoriaGuardar(Categoria categoria) {
		
		categoriaRepository.save(categoria);
		
		return "redirect:/categorias";
	}
	
	@RequestMapping("categorias/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id") Integer id ) {
		
		
		categoriaRepository.deleteById(id);
		
		return "redirect:/categorias";
	}
	
}
