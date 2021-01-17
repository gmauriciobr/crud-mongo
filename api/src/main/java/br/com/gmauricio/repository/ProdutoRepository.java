package br.com.gmauricio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.gmauricio.model.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String> {

}
