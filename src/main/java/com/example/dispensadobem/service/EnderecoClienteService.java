package com.example.dispensadobem.service;


import com.example.dispensadobem.model.EnderecoCliente;
import com.example.dispensadobem.repository.EnderecoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoClienteService {
    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    public Optional<EnderecoCliente> buscarPorId(Long id) {
        return enderecoClienteRepository.findById(id);
    }

    public Optional<EnderecoCliente> buscarPorClienteId(Long clienteId) {
        return enderecoClienteRepository.findByClienteId(clienteId);
    }

    public EnderecoCliente salvar(EnderecoCliente enderecoCliente) {
        return enderecoClienteRepository.save(enderecoCliente);
    }

    public void deletar(Long id) {
        enderecoClienteRepository.deleteById(id);
    }
}
