package com.dslearn.dscatalog.tests;

import java.time.Instant;

import com.dslearn.dscatalog.dto.ProdutoDTO;
import com.dslearn.dscatalog.models.Categoria;
import com.dslearn.dscatalog.models.Produto;

public class Factory {

	public static Produto createProduct() {
		Produto produto = new Produto(1l, "PHONE", "GOOD PHONE", 800.0, "https://img.com/img.png",
				Instant.parse("2021-08-03T11:45:00Z"));
		produto.getCategorias().add(new Categoria(2L, "ELETRONICOS", Instant.parse("2021-08-03T11:45:00Z"),
				Instant.parse("2021-08-03T11:45:00Z")));
		return produto;
	}

	public static ProdutoDTO createProductDTO() {
		Produto produto = createProduct();
		return new ProdutoDTO(produto, produto.getCategorias());
	}
}
