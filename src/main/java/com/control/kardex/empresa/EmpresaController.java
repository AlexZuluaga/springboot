package com.control.kardex.empresa;


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

import com.control.kardex.categoria.Categoria;

@Controller 
public class EmpresaController {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	@GetMapping("/empresas")
	public String ListarCategorias(Model model) {
		
		List<Empresa> empresas = empresaRepository.findAll();
		model.addAttribute("empresas", empresas);
		
		return "empresas";
	}
	
	@GetMapping("/empresas/formulario")
	public String formularioEmpresa(Model model) {
		
		Empresa empresa = new Empresa();
		model.addAttribute("empresa", empresa);
		return "empresas_formulario";
	}
	
	@PostMapping("/empresas/guardar")
	public String empresaGuardar(Empresa empresa) {
		
		empresaRepository.save(empresa);
		
		return "redirect:/empresas";
	}
	
	@RequestMapping("empresas/editar/{id}")
	public ModelAndView empresaEditar(@PathVariable(name = "id") Integer id ) {
		
		ModelAndView modelo = new ModelAndView("empresas_formulario");
		
		
		Empresa empresa = empresaRepository.findById(id).get();
		
		modelo.addObject("empresa", empresa);
		
		return modelo;
	}
	
	
	@RequestMapping("empresas/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id") Integer id ) {
		
		try{
		empresaRepository.deleteById(id);
		} catch (Exception e){
			return "redirect:/empresas";
		}
		return "redirect:/empresas";
	}
	
	
}
