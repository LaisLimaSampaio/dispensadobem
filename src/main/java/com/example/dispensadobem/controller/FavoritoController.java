package com.example.dispensadobem.controller;


import com.example.dispensadobem.model.Favorito;
import com.example.dispensadobem.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @PostMapping("/{clienteId}/favoritos")
    public ResponseEntity<Favorito> adicionarFavorito(@PathVariable Long clienteId, @RequestBody Map<String, Long> requestBody)
    {
        Long estabelecimentoId = requestBody.get("estabelecimentoId");
        Favorito novoFavorito = favoritoService.adicionarFavorito(clienteId, estabelecimentoId);
        return ResponseEntity.ok(novoFavorito);
    }


    @GetMapping("/{clienteId}/favoritos")
    public ResponseEntity<List<Favorito>> listarFavoritos(@PathVariable Long clienteId) {
        return ResponseEntity.ok(favoritoService.listarPorCliente(clienteId));
    }

    @GetMapping("/{clienteId}/favoritos/{favoritoId}")
    public ResponseEntity<Favorito> buscarPorId(@PathVariable Long favoritoId) {
        return favoritoService.buscarPorId(favoritoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{clienteId}/favoritos/{favoritoId}")
    public ResponseEntity<Favorito> removerFavorito(@PathVariable Long favoritoId) {
        return favoritoService.buscarPorId(favoritoId)
                .map(fav -> {
                    favoritoService.deletar(favoritoId);
                    return ResponseEntity.ok(fav);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
