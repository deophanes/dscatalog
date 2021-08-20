package com.dslearn.dscatalog.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.dslearn.dscatalog.models.Category;
import com.dslearn.dscatalog.models.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	// @Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
	// @NotBlank(message = "Campo requerido")
	private String name;

	// @NotBlank(message = "Campo requerido")
	private String description;

	// @Positive(message = "Preço deve ser um valor positivo")
	private Double price;

	private String imgUrl;

	// @PastOrPresent(message = "A data do produto não pode ser futura")
	private Instant date;

	// @NotEmpty(message = "Produto sem categoria não é permitido")
	private List<CategoryDTO> categories = new ArrayList<>();

	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.imgUrl = entity.getImgUrl();
		this.date = entity.getDate();
	}

	public ProductDTO(Product entity, Set<Category> categories) {
		this(entity);
		categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
	}
}
