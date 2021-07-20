package com.dslearn.dscatalog.model;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
}
