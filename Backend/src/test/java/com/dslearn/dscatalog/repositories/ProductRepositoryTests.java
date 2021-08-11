package com.dslearn.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.dslearn.dscatalog.models.Produto;
import com.dslearn.dscatalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProdutoRepository repository;

	private Long existindId;
	private Long notExistingId;
	private Long countTotalProducts;

	@BeforeEach
	void setUp() throws Exception {
		existindId = 1L;
		notExistingId = 30L;
		countTotalProducts = 25L;
	}

	@Test
	public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
		Produto produto = Factory.createProduct();
		produto.setId(null);
		produto = repository.save(produto);

		Assertions.assertNotNull(produto.getId());
		Assertions.assertEquals(countTotalProducts + 1, produto.getId());
	}

	@Test
	public void deleteShouldDeleteObjectWhenidExists() {
		repository.deleteById(existindId);

		Optional<Produto> result = repository.findById(existindId);
		Assertions.assertFalse(result.isPresent());
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(notExistingId);
		});
	}
	
	@Test
	public void retornOptionalProductNotEmptyWhenIdExists() {
		Optional<Produto> result = repository.findById(existindId);
		Assertions.assertTrue(result.isPresent());
	}

	@Test
	public void retornOptionalProductEmptyWhenIdDoesNotExists() {
		Optional<Produto> result = repository.findById(notExistingId);
		Assertions.assertTrue(!result.isPresent());
	}

}
