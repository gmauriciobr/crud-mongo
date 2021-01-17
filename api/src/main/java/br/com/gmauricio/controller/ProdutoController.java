package br.com.gmauricio.controller;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gmauricio.model.Produto;
import br.com.gmauricio.model.dto.ProdutoDTO;
import br.com.gmauricio.model.form.ProdutoForm;
import br.com.gmauricio.model.form.ProdutoUpdateForm;
import br.com.gmauricio.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping
	public ResponseEntity<ProdutoDTO> cadastra(@RequestBody @Valid ProdutoForm produtoForm, UriComponentsBuilder uriBuilder) {
		Produto produto = produtoRepository.save(produtoForm.parseToProduto());
		URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDTO(produto));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProdutoDTO> busca(@PathVariable String id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if(produto.isPresent()) {
			return ResponseEntity.ok(new ProdutoDTO(produto.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> lista(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 5) Pageable page) {
		Page<Produto> produtos = produtoRepository.findAll(page);
		return ResponseEntity.ok(ProdutoDTO.parseList(produtos));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ProdutoDTO> altera(@PathVariable String id, @RequestBody ProdutoUpdateForm produtoUpdateForm) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if(optional.isPresent()) {
			Produto produto = optional.get();
			Method[] methods = produtoUpdateForm.getClass().getMethods();
			List<Method> list = Arrays.asList(methods).stream().filter(a -> a.getName().contains("get")).collect(Collectors.toList());
			for (Method method : list) {
				try {
					Object value = (Object) method.invoke(produtoUpdateForm, null);
					if(value != null) {
						if(value instanceof Double) {
							Double d = (Double) value;
							produto.getClass().getDeclaredMethod(method.getName().replace("get", "set"), Double.class).invoke(produto, d);
						} else if (value instanceof String) {
							String s = (String) value;
							produto.getClass().getDeclaredMethod(method.getName().replace("get", "set"), String.class).invoke(produto, s);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			produtoRepository.save(produto);
			return ResponseEntity.ok(new ProdutoDTO(optional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ProdutoDTO> deleta(@PathVariable String id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if(produto.isPresent()) {
			produtoRepository.delete(produto.get());
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
