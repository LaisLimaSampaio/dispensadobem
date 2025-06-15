package com.example.dispensadobem.service;

import com.example.dispensadobem.model.Estabelecimento;
import com.example.dispensadobem.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public List<Estabelecimento> listarTodos() {
        return estabelecimentoRepository.findAll();
    }

    public Optional<Estabelecimento> buscarPorId(Long id) {
        return estabelecimentoRepository.findById(id);
    }

    public Optional<Estabelecimento> buscarPorEmail(String email) {
        return estabelecimentoRepository.findByEmail(email);
    }

    public Estabelecimento salvar(Estabelecimento estabelecimento) {
        return estabelecimentoRepository.save(estabelecimento);
    }

    public void deletar(Long id) {
        estabelecimentoRepository.deleteById(id);
    }

    public Optional<Estabelecimento> autenticar(String email, String senha) {
        return estabelecimentoRepository.findByEmail(email)
                .filter(estabelecimento -> estabelecimento.getSenha().equals(senha));
    }

}
