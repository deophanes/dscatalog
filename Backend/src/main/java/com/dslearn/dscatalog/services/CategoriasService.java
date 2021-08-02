package com.dslearn.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dslearn.dscatalog.dto.CategoriaDTO;
import com.dslearn.dscatalog.models.Categoria;
import com.dslearn.dscatalog.repositories.CategoriaRepository;
import com.dslearn.dscatalog.services.exceptions.DatabaseException;
import com.dslearn.dscatalog.services.exceptions.ServiceNotfoundException;

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

	public Page<CategoriaDTO> findAllPaged(Pageable pageable) {
		Page<Categoria> list = repository.findAll(pageable);
		return list.map(x -> new CategoriaDTO(x));
	}

	public CategoriaDTO findById(Long id) {
		Optional<Categoria> optional = repository.findById(id);
		Categoria categoria = optional.orElseThrow(() -> new ServiceNotfoundException("Categoria não encontrada"));
		return new CategoriaDTO(categoria);
	}

	@Transactional
	public CategoriaDTO insert(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		categoria.setNome(categoriaDTO.getNome());
		categoria = repository.save(categoria);

		return new CategoriaDTO(categoria);
	}

	@Transactional
	public CategoriaDTO update(Long id, CategoriaDTO categoriaDTO) {
		try {
			Categoria categoria = repository.getOne(id);
			categoria.setNome(categoriaDTO.getNome());
			categoria = repository.save(categoria);
			return new CategoriaDTO(categoria);
		} catch (EntityNotFoundException e) {
			throw new ServiceNotfoundException("código não Encontrado: " + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceNotfoundException("Código não Encontrado: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de Integridade");
		}

	}

}
