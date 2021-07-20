package com.dslearn.dscatalog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dslearn.dscatalog.models.Categoria;
import com.dslearn.dscatalog.services.CategoriasService;

@RestController
@RequestMapping("/categorias")
public class CategoryController {

	@Autowired
	private CategoriasService service;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	
}
