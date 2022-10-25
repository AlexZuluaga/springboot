package com.control.kardex.empresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/")
public class EmpresaControllerRest {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@GetMapping("/empresas")
	public List<Empresa> empresas(){
		return empresaRepository.findAll();
	}
	
	
	
	@GetMapping("/empresas/{id}")
	public ResponseEntity<Empresa> empresa( @PathVariable(name = "id") Integer id){
		try {
			Empresa empresa = empresaRepository.findById(id).get();
			return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Empresa>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/empresas")
	public void crearEmpresa(@RequestBody Empresa empresa) {
		
		if(empresa.getId() == null) {
			empresaRepository.save(empresa);
		}
	}
	
	
	@PutMapping("/empresas/{id}")
	public ResponseEntity<?> editarEmpresa(@RequestBody Empresa empresa,  @PathVariable(name = "id") Integer id) {
		try {
			
			Empresa empresaEcontrada = empresaRepository.findById(id).get();
			empresaRepository.save(empresa);
			return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<Empresa>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/empresas/{id}")
	public ResponseEntity<?> eliminarEmpresa( @PathVariable(name = "id") Integer id) {
		try {
			empresaRepository.deleteById(id);	
			return new ResponseEntity<Empresa>(HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<Empresa>(HttpStatus.NOT_FOUND);
		}
	}
	
}
