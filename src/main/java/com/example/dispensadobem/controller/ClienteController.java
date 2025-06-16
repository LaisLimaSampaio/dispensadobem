package com.example.dispensadobem.controller;

import com.example.dispensadobem.dto.ClienteDTO;
import com.example.dispensadobem.dto.LoginDTO;
import com.example.dispensadobem.model.Cliente;
import com.example.dispensadobem.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Cliente> criar(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        Cliente salvo = clienteService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        return clienteService.buscarPorId(id)
                .map(cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setEmail(clienteAtualizado.getEmail());
                    cliente.setSenha(clienteAtualizado.getSenha());
                    cliente.setTelefone(clienteAtualizado.getTelefone());
                    // dataCriacao geralmente não atualiza
                    Cliente atualizado = clienteService.salvar(cliente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletar(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(cliente -> {
                    clienteService.deletar(id);
                    return ResponseEntity.ok(cliente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDTO loginDTO) {
        return clienteService.autenticar(loginDTO.getEmail(), loginDTO.getSenha())
                .map(cliente -> {
                    Map<String, Object> resposta = new HashMap<>();
                    resposta.put("id", cliente.getId());
                    resposta.put("nome", cliente.getNome());
                    return ResponseEntity.ok(resposta);
                })
                .orElseGet(() -> {
                    Map<String, Object> erro = new HashMap<>();
                    erro.put("erro", "Email ou senha inválidos");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
                });
    }
}
