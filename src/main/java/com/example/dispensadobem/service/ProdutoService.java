package com.example.dispensadobem.service;

import com.example.dispensadobem.dto.ProdutoDTO;
import com.example.dispensadobem.model.Produto;
import com.example.dispensadobem.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {


    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public List<Produto> listarPorEstabelecimento(Long estabelecimentoId) {
        return produtoRepository.findByEstabelecimentoId(estabelecimentoId);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto fromDTO(ProdutoDTO dto) {
        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setFotoUrl(dto.getFotoUrl());
        produto.setDataValidade(dto.getDataValidade());
        produto.setPrecoOriginal(dto.getPrecoOriginal());
        produto.setPrecoDesconto(dto.getPrecoDesconto());

        // Estabelecimento
        if (dto.getEstabelecimento() != null && dto.getEstabelecimento().getId() != null) {
            estabelecimentoService.buscarPorId(dto.getEstabelecimento().getId())
                    .ifPresent(produto::setEstabelecimento);
        }

        // Categoria
        if (dto.getCategoria() != null && dto.getCategoria().getId() != null) {
            categoriaProdutoService.buscarPorId(dto.getCategoria().getId())
                    .ifPresent(produto::setCategoria);
        }

        return produto;
    }
}
