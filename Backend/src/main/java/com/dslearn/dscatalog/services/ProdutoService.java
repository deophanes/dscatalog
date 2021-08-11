package com.dslearn.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dslearn.dscatalog.dto.ProdutoDTO;
import com.dslearn.dscatalog.models.Produto;
import com.dslearn.dscatalog.repositories.ProdutoRepository;
import com.dslearn.dscatalog.services.exceptions.DatabaseException;
import com.dslearn.dscatalog.services.exceptions.ServiceNotfoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public List<ProdutoDTO> findAll() {
		List<Produto> list = repository.findAll();
		return list.stream().map(x -> new ProdutoDTO(x)).collect(Collectors.toList());
	}

	public Page<ProdutoDTO> findAllPaged(Pageable pageable) {
		Page<Produto> list = repository.findAll(pageable);
		return list.map(x -> new ProdutoDTO(x));
	}

	public ProdutoDTO findById(Long id) {
		Optional<Produto> optional = repository.findById(id);
		Produto produto = optional.orElseThrow(() -> new ServiceNotfoundException("Id not found"));
		return new ProdutoDTO(produto, produto.getCategorias());
	}

	@Transactional
	public ProdutoDTO insert(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		BeanUtils.copyProperties(produtoDTO, produto);	
		produto = repository.save(produto);
		return new ProdutoDTO(produto);
	}

	@Transactional
	public ProdutoDTO update(Long id, ProdutoDTO produtoDTO) {
		try {
			Produto produto = repository.getOne(id);
			BeanUtils.copyProperties(produtoDTO, produto);	
			produto = repository.save(produto);
			return new ProdutoDTO(produto);
		} catch (EntityNotFoundException e) {
			throw new ServiceNotfoundException("Id not found: " + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceNotfoundException("Id not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	/*private void copyToProperties(ProdutoDTO dto, Produto produto) {
		produto.setNome(dto.getNome());
		produto.setDescricao(dto.getDescricao());
		produto.setPreco(dto.getPreco());
		produto.setImgUrl(dto.getImgUrl());
		produto.setData(dto.getData());

		produto.getCategorias().clear();
		for (CategoriaDTO catDTO : dto.getCategoriaDTOs()) {
			Categoria categoria = categoriaRepository.getOne(catDTO.getId());
			produto.getCategorias().add(categoria);
		}
	}*/

}
