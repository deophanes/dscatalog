package com.dslearn.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dslearn.dscatalog.models.Categoria;
import com.dslearn.dscatalog.repositories.CategoriaRepository;

@Service
public class CategoriasService {

	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	
}
