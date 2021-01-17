package br.com.gmauricio.model.form;

import br.com.gmauricio.model.Produto;

public class ProdutoForm {
	
	private String nome;
	private String marca;
	private Double preco;
	private String descricao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Produto parseToProduto() {
		return new Produto(nome, marca, preco, descricao);
	}
	

}
