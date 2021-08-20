package com.dslearn.dscatalog.dto;

import java.io.Serializable;

import com.dslearn.dscatalog.models.Category;

import lombok.Data;

@Data
public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	public CategoryDTO(Category entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}
}
