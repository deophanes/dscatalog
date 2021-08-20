package com.dslearn.dscatalog.tests;

import java.time.Instant;

import com.dslearn.dscatalog.dto.ProductDTO;
import com.dslearn.dscatalog.models.Category;
import com.dslearn.dscatalog.models.Product;

public class Factory {

	public static Product createProduct() {
		Product product = new Product(1l, "PHONE", "GOOD PHONE", 800.0, "https://img.com/img.png",
				Instant.parse("2021-08-03T11:45:00Z"), null);
		product.getCategories().add(new Category(2L, "ELETRONICOS", Instant.parse("2021-08-03T11:45:00Z"),
				Instant.parse("2021-08-03T11:45:00Z"), null));
		return product;
	}

	public static ProductDTO createProductDTO() {
		Product product = createProduct();
		return new ProductDTO(product, product.getCategories());
	}
}
