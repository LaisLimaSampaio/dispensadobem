package com.example.dispensadobem.controller;


import com.example.dispensadobem.model.Avaliacao;
import com.example.dispensadobem.service.AvaliacaoService;
import com.example.dispensadobem.service.ClienteService;
import com.example.dispensadobem.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")

public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;


    @GetMapping
    public List<Avaliacao> listarTodas() {
        return avaliacaoService.listarTodos();
    }

    // GET /avaliacoes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Long id) {
        return avaliacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /avaliacoes/cliente/{clienteId}
    @GetMapping("/cliente/{clienteId}")
    public List<Avaliacao> listarPorCliente(@PathVariable Long clienteId) {
        return avaliacaoService.listarPorCliente(clienteId);
    }

    // GET /avaliacoes/estabelecimento/{estabelecimentoId}
    @GetMapping("/estabelecimento/{estabelecimentoId}")
    public List<Avaliacao> listarPorEstabelecimento(@PathVariable Long estabelecimentoId) {
        return avaliacaoService.listarPorEstabelecimento(estabelecimentoId);
    }

    // POST /avaliacoes
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Avaliacao avaliacao) {
        if (avaliacao.getCliente() == null || avaliacao.getEstabelecimento() == null) {
            return ResponseEntity.badRequest().body("Cliente e Estabelecimento são obrigatórios.");
        }

        var cliente = clienteService.buscarPorId(avaliacao.getCliente().getId());
        var estabelecimento = estabelecimentoService.buscarPorId(avaliacao.getEstabelecimento().getId());

        if (cliente.isEmpty() || estabelecimento.isEmpty()) {
            return ResponseEntity.badRequest().body("Cliente ou Estabelecimento inválido.");
        }

        avaliacao.setCliente(cliente.get());
        avaliacao.setEstabelecimento(estabelecimento.get());
        return ResponseEntity.ok(avaliacaoService.salvar(avaliacao));
    }

    // DELETE /avaliacoes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (avaliacaoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        avaliacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
