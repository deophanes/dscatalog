package com.dslearn.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.dslearn.dscatalog.models.Produto;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProdutoRepository repository;

	@Test
	public void deleteShouldDeleteObjectWhenidExists() {

		Long existindId = 1L;
		repository.deleteById(existindId);

		Optional<Produto> result = repository.findById(existindId);
		Assertions.assertFalse(result.isPresent());
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			Long notExistingId = 30L;
			repository.deleteById(notExistingId);
		});
	}

}
