package com.dslearn.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.dslearn.dscatalog.dto.CategoriaDTO;
import com.dslearn.dscatalog.models.Categoria;
import com.dslearn.dscatalog.repositories.CategoriaRepository;

@Service
public class CategoriasService {

	@Autowired
	private CategoriaRepository repository;

	public List<CategoriaDTO> findAll() {
		List<Categoria> list = repository.findAll();
		return list.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());

		/*
		 * List<CategoriaDTO> listDTO = new ArrayList<>(); for (Categoria cat : list) {
		 * listDTO.add(new CategoriaDTO(cat)); }
		 */
	}
	
	public CategoriaDTO findById(Long id) {
		Optional<Categoria> optional = repository.findById(id);
		Categoria categoria = optional.get();
		return new CategoriaDTO(categoria);
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
