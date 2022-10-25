package com.control.kardex.categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	
	@Query("SELECT p FROM Categoria p WHERE p.empresa LIKE %?1%")
	public List<Categoria> findAll(String palabraClave);
}
