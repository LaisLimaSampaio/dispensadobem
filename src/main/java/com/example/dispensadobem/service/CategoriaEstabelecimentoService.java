package com.example.dispensadobem.service;


import com.example.dispensadobem.model.CategoriaEstabelecimento;
import com.example.dispensadobem.repository.CategoriaEstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaEstabelecimentoService {

    @Autowired
    private CategoriaEstabelecimentoRepository categoriaEstabelecimentoRepository;

    public List<CategoriaEstabelecimento> listarTodos() {
        return categoriaEstabelecimentoRepository.findAll();
    }

    public Optional<CategoriaEstabelecimento> buscarPorId(Long id) {
        return categoriaEstabelecimentoRepository.findById(id);
    }

    public CategoriaEstabelecimento salvar(CategoriaEstabelecimento categoria) {
        return categoriaEstabelecimentoRepository.save(categoria);
    }

    public void deletar(Long id) {
        categoriaEstabelecimentoRepository.deleteById(id);
    }
}
