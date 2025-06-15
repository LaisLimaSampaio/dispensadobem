package com.example.dispensadobem.service;


import com.example.dispensadobem.model.EnderecoEstabelecimento;
import com.example.dispensadobem.repository.EnderecoEstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoEstabelecimentoService {
    @Autowired
    private EnderecoEstabelecimentoRepository enderecoEstabelecimentoRepository;

    public Optional<EnderecoEstabelecimento> buscarPorId(Long id) {
        return enderecoEstabelecimentoRepository.findById(id);
    }

    public Optional<EnderecoEstabelecimento> buscarPorEstabelecimentoId(Long estabelecimentoId) {
        return enderecoEstabelecimentoRepository.findByEstabelecimentoId(estabelecimentoId);
    }

    public EnderecoEstabelecimento salvar(EnderecoEstabelecimento enderecoEstabelecimento) {
        return enderecoEstabelecimentoRepository.save(enderecoEstabelecimento);
    }

    public void deletar(Long id) {
        enderecoEstabelecimentoRepository.deleteById(id);
    }
}
