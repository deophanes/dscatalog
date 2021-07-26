package com.dslearn.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

		/*
		 * List<ProdutoDTO> listDTO = new ArrayList<>(); for (Produto cat : list) {
		 * listDTO.add(new ProdutoDTO(cat)); }
		 */
	}

	public Page<ProdutoDTO> findAllPaged(PageRequest pageRequest) {
		Page<Produto> list = repository.findAll(pageRequest);
		return list.map(x -> new ProdutoDTO(x));
	}

	public ProdutoDTO findById(Long id) {
		Optional<Produto> optional = repository.findById(id);
		Produto produto = optional.orElseThrow(() -> new ServiceNotfoundException("Produto não encontrada"));
		return new ProdutoDTO(produto, produto.getCategorias() );
	}

	@Transactional
	public ProdutoDTO insert(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		produto.setNome(produtoDTO.getNome());
		produto = repository.save(produto);

		return new ProdutoDTO(produto);
	}

	@Transactional
	public ProdutoDTO update(Long id, ProdutoDTO produtoDTO) {
		try {
			Produto produto = repository.getOne(id);
			produto.setNome(produtoDTO.getNome());
			produto = repository.save(produto);
		} catch (EntityNotFoundException e) {
			throw new ServiceNotfoundException("código não Encontrado: " + id);
		}
		return null;

	}

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
