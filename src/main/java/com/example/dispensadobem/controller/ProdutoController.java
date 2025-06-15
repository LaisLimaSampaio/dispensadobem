package com.example.dispensadobem.controller;


import com.example.dispensadobem.model.CategoriaProduto;
import com.example.dispensadobem.model.Estabelecimento;
import com.example.dispensadobem.model.Produto;
import com.example.dispensadobem.service.CategoriaProdutoService;
import com.example.dispensadobem.service.EstabelecimentoService;
import com.example.dispensadobem.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/estabelecimento/{id}")
    public ResponseEntity<List<Produto>> listarPorEstabelecimento(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.listarPorEstabelecimento(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.buscarPorId(id);
        return produto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto) {
        // Buscar e validar o Estabelecimento
        Long estabelecimentoId = produto.getEstabelecimento().getId();
        Optional<Estabelecimento> estabelecimento = estabelecimentoService.buscarPorId(estabelecimentoId);
        if (estabelecimento.isEmpty()) {
            return ResponseEntity.badRequest().body("Estabelecimento não encontrado.");
        }

        // Buscar e validar a Categoria
        if (produto.getCategoria() != null && produto.getCategoria().getId() != null) {
            Long categoriaId = produto.getCategoria().getId();
            Optional<CategoriaProduto> categoria = categoriaProdutoService.buscarPorId(categoriaId);
            if (categoria.isEmpty()) {
                return ResponseEntity.badRequest().body("Categoria não encontrada.");
            }
            produto.setCategoria(categoria.get());
        } else {
            produto.setCategoria(null); // se categoria for opcional
        }

        // Associar e salvar
        produto.setEstabelecimento(estabelecimento.get());
        Produto novoProduto = produtoService.salvar(produto);
        return ResponseEntity.status(201).body(novoProduto);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Optional<Produto> existente = produtoService.buscarPorId(id);
        if (existente.isPresent()) {
            produtoAtualizado.setId(id);
            return ResponseEntity.ok(produtoService.salvar(produtoAtualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        Optional<Produto> existente = produtoService.buscarPorId(id);
        if (existente.isPresent()) {
            produtoService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
