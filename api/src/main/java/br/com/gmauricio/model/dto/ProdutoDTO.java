package br.com.gmauricio.model.dto;

import org.springframework.data.domain.Page;

import br.com.gmauricio.model.Produto;

public class ProdutoDTO {
	
	private String id;
	private String nome;
	private String marca;
	private Double preco;
	private String descricao;
	
	public ProdutoDTO() {};
	
	public ProdutoDTO(Produto produto) {
		this.id = produto.id;
		this.nome = produto.nome;
		this.marca = produto.marca;
		this.preco = produto.preco;
		this.descricao = produto.descricao;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getMarca() {
		return marca;
	}

	public Double getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Page<ProdutoDTO> parseList(Page<Produto> produtos) {
		return produtos.map(ProdutoDTO::new);
	}
	
}
