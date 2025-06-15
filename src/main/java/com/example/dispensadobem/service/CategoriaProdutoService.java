package com.example.dispensadobem.service;


import com.example.dispensadobem.model.CategoriaProduto;
import com.example.dispensadobem.repository.CategoriaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaProdutoService {
    @Autowired
    private CategoriaProdutoRepository categoriaProdutoRepository;

    public List<CategoriaProduto> listarTodos() {
        return categoriaProdutoRepository.findAll();
    }

    public Optional<CategoriaProduto> buscarPorId(Long id) {
        return categoriaProdutoRepository.findById(id);
    }

    public CategoriaProduto salvar(CategoriaProduto categoria) {
        return categoriaProdutoRepository.save(categoria);
    }

    public void deletar(Long id) {
        categoriaProdutoRepository.deleteById(id);
    }


}
