package com.dslearn.dscatalog.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dslearn.dscatalog.dto.ProdutoDTO;
import com.dslearn.dscatalog.models.Produto;
import com.dslearn.dscatalog.repositories.ProdutoRepository;
import com.dslearn.dscatalog.services.exceptions.DatabaseException;
import com.dslearn.dscatalog.services.exceptions.ServiceNotfoundException;
import com.dslearn.dscatalog.tests.Factory;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	@InjectMocks
	private ProdutoService service;

	@Mock
	private ProdutoRepository repository;

	private Long existindId;
	private Long notExistingId;
	private Long dependentId;
	private PageImpl<Produto> page;
	private Produto produto;
	private ProdutoDTO produtoDTO; 

	@BeforeEach
	void setUp() throws Exception {
		existindId = 1L;
		notExistingId = 30L;
		dependentId = 4L;
		produto = Factory.createProduct();
		page = new PageImpl<>(List.of(produto));

		Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(produto);
		Mockito.when(repository.findById(existindId)).thenReturn(Optional.of(produto));
		Mockito.when(repository.findById(notExistingId)).thenReturn(Optional.empty());

		Mockito.doNothing().when(repository).deleteById(existindId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(notExistingId);
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
	}

	@Test
	public void findByIdShouldReturnProductDTOWhenIdExists() {
		produtoDTO = Factory.createProductDTO(); 
		produtoDTO = service.findById(existindId);
		
		Assertions.assertNotNull(produtoDTO);
		Mockito.verify(repository, Mockito.times(1)).findById(existindId);
	}

	
	@Test
	public void findAllPagesShouldReturnPage() {

		Pageable pageable = PageRequest.of(0, 10);
		Page<ProdutoDTO> result = service.findAllPaged(pageable);

		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
	}

	@Test
	public void deleteShouldThrowDatabaseExceptionIdDoesNotExists() {
		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(dependentId);
	}

	@Test
	public void deleteShouldThrowServiceNotfoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ServiceNotfoundException.class, () -> {
			service.delete(notExistingId);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(notExistingId);
	}

	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existindId);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(existindId);
	}
}
