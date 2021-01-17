package br.com.gmauricio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("produto")
public class Produto {

	public String id;
	public String nome;
	public String marca;
	public Double preco;
	public String descricao;
	
	public Produto() {}
	
	public Produto(String nome, String marca, Double preco, String descricao) {
		this.nome = nome;
		this.marca = marca;
		this.descricao = descricao;
		this.preco = preco;
		
	}
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	
}
