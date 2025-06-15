package com.example.dispensadobem.service;


import com.example.dispensadobem.model.Cliente;
import com.example.dispensadobem.model.Estabelecimento;
import com.example.dispensadobem.model.Favorito;
import com.example.dispensadobem.repository.ClienteRepository;
import com.example.dispensadobem.repository.EstabelecimentoRepository;
import com.example.dispensadobem.repository.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritoService {
    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;


    public List<Favorito> listarTodos() {
        return favoritoRepository.findAll();
    }

    public List<Favorito> listarPorCliente(Long clienteId) {
        return favoritoRepository.findByClienteId(clienteId);
    }

    public Optional<Favorito> buscarPorId(Long id) {
        return favoritoRepository.findById(id);
    }

    public Favorito salvar(Favorito favorito) {
        return favoritoRepository.save(favorito);
    }

    public void deletar(Long id) {
        favoritoRepository.deleteById(id);
    }

    public Favorito adicionarFavorito(Long clienteId, Long estabelecimentoId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));

        Favorito favorito = new Favorito();
        favorito.setCliente(cliente);
        favorito.setEstabelecimento(estabelecimento);

        return favoritoRepository.save(favorito);
    }

}
