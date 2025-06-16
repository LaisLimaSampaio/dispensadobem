package com.example.dispensadobem.controller;

import com.example.dispensadobem.dto.EstabelecimentoDTO;
import com.example.dispensadobem.dto.LoginDTO;
import com.example.dispensadobem.model.CategoriaEstabelecimento;
import com.example.dispensadobem.model.Estabelecimento;
import com.example.dispensadobem.service.CategoriaEstabelecimentoService;
import com.example.dispensadobem.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;
    @Autowired
    private CategoriaEstabelecimentoService categoriaEstabelecimentoService;

    @GetMapping
    public ResponseEntity<List<Estabelecimento>> listarTodos() {
        return ResponseEntity.ok(estabelecimentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estabelecimento> buscarPorId(@PathVariable Long id) {
        Optional<Estabelecimento> estabelecimento = estabelecimentoService.buscarPorId(id);
        return estabelecimento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estabelecimento> criar(@RequestBody EstabelecimentoDTO dto) {
        try {
            Estabelecimento estabelecimento = estabelecimentoService.fromDTO(dto);
            Estabelecimento salvo = estabelecimentoService.salvar(estabelecimento);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Estabelecimento> atualizar(@PathVariable Long id, @RequestBody Estabelecimento atualizado) {
        return estabelecimentoService.buscarPorId(id)
                .map(estabelecimento -> {
                    estabelecimento.setNomeFantasia(atualizado.getNomeFantasia());
                    estabelecimento.setCnpj(atualizado.getCnpj());
                    estabelecimento.setEmail(atualizado.getEmail());
                    estabelecimento.setSenha(atualizado.getSenha());
                    estabelecimento.setHorarioFuncionamento(atualizado.getHorarioFuncionamento());
                    estabelecimento.setOfereceEntrega(atualizado.getOfereceEntrega());
                    estabelecimento.setTaxaEntrega(atualizado.getTaxaEntrega());
                    estabelecimento.setEntregaGratisAcima(atualizado.getEntregaGratisAcima());
                    estabelecimento.setDistanciaMaxEntregaKm(atualizado.getDistanciaMaxEntregaKm());
                    estabelecimento.setTelefone(atualizado.getTelefone());
                    estabelecimento.setCategoria(atualizado.getCategoria());
                    estabelecimento.setEndereco(atualizado.getEndereco());

                    Estabelecimento salvo = estabelecimentoService.salvar(estabelecimento);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estabelecimento> deletar(@PathVariable Long id) {
        return estabelecimentoService.buscarPorId(id)
                .map(estabelecimento -> {
                    estabelecimentoService.deletar(id);
                    return ResponseEntity.ok(estabelecimento);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDTO loginDTO) {
        return estabelecimentoService.autenticar(loginDTO.getEmail(), loginDTO.getSenha())
                .map(estabelecimento -> {
                    Map<String, Object> resposta = new HashMap<>();
                    resposta.put("id", estabelecimento.getId());
                    resposta.put("nomeFantasia", estabelecimento.getNomeFantasia());
                    return ResponseEntity.ok(resposta);
                })
                .orElseGet(() -> {
                    Map<String, Object> erro = new HashMap<>();
                    erro.put("erro", "Email ou senha inválidos");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
                });
    }
}
