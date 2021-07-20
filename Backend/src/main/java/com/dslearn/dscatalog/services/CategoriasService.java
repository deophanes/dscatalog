package com.dslearn.dscatalog.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.dslearn.dscatalog.models.Categoria;
import com.dslearn.dscatalog.repositories.CategoriaRepository;

@Service
public class CategoriasService {

	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	@Transactional
	public Categoria save(@RequestBody Categoria categoria) {
		return repository.save(categoria);
	}

	@Transactional
	public void deleteById(long id) {
		repository.deleteById(id);
	}
	
}
