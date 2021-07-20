package com.dslearn.dscatalog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dslearn.dscatalog.model.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoryController {

	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		
		List<Categoria> lista = new ArrayList<Categoria>();
		lista.add(new Categoria(1L, "LIVROS"));
		lista.add(new Categoria(2L, "ELETRONICOS"));
		
		return ResponseEntity.ok().body(lista);
	}
}
