package com.dslearn.dscatalog.models;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant creatAt;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updateAt;
	
	@PrePersist
	public void prePersist() {
		creatAt = Instant.now();
	}

	@PreUpdate
	public void preUpdate() {
		updateAt = Instant.now();
	}

	public Categoria() {
		
	}
	
	public Categoria(Long id, String nome, Instant creatAt, Instant updateAt) {
		this.id = id;
		this.nome = nome;
		this.creatAt = creatAt;
		this.updateAt = updateAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
