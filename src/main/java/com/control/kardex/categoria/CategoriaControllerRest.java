package com.control.kardex.categoria;

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
public class CategoriaControllerRest {

	@Autowired
	private CategoriaRepository CategoriaRepository;

	@GetMapping("/categorias")
	public List<Categoria> categorias() {
		return CategoriaRepository.findAll();
	}

	@GetMapping("/categorias/{id}")
	public ResponseEntity<Categoria> categoria(@PathVariable(name = "id") Integer id) {
		try {
			Categoria categoria = CategoriaRepository.findById(id).get();
			return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/categorias")
	public ResponseEntity<?> crearEmpresa(@RequestBody Categoria categoria) {
		try {
			if (categoria.getId() == null) {
				CategoriaRepository.save(categoria);
				return new ResponseEntity<Categoria>(HttpStatus.OK);
			}
			return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/categorias/{id}")
	public ResponseEntity<?> editarCategoria(@RequestBody Categoria categoria,  @PathVariable(name = "id") Integer id) {
		try {
			
			Categoria categoriaEcontrada = CategoriaRepository.findById(id).get();
			CategoriaRepository.save(categoria);
			return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<?> eliminarEmpresa( @PathVariable(name = "id") Integer id) {
		try {
			CategoriaRepository.deleteById(id);	
			return new ResponseEntity<Categoria>(HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		}
	}

}
