package com.dslearn.dscatalog.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.dslearn.dscatalog.models.Categoria;
import com.dslearn.dscatalog.models.Produto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private String imgUrl;
	private Instant data;
	private List<CategoriaDTO> categoriaDTOs = new ArrayList<>();

	public ProdutoDTO() {
	}
	
	public ProdutoDTO(Long id, String nome, String descricao, Double preco, String imgUrl, Instant data) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.imgUrl = imgUrl;
		this.data = data;
	}

	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.imgUrl = produto.getImgUrl();
		this.data = produto.getData();
	}

	public ProdutoDTO(Produto produto, Set<Categoria> categorias) {
		this(produto);
		categorias.forEach(cat -> this.categoriaDTOs.add(new CategoriaDTO(cat)));
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	public List<CategoriaDTO> getCategoriaDTOs() {
		return categoriaDTOs;
	}

	public void setCategoriaDTOs(List<CategoriaDTO> categoriaDTOs) {
		this.categoriaDTOs = categoriaDTOs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
