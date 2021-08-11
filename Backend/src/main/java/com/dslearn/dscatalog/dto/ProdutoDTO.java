package com.dslearn.dscatalog.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.dslearn.dscatalog.models.Categoria;
import com.dslearn.dscatalog.models.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private String imgUrl;
	private Instant data;
	private List<CategoriaDTO> categoriaDTOs = new ArrayList<>();
	
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

}
