package com.example.dispensadobem.controller;


import com.example.dispensadobem.model.CategoriaEstabelecimento;
import com.example.dispensadobem.service.CategoriaEstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias-estabelecimento")
public class CategoriaEstabelecimentoController {

    @Autowired
    private CategoriaEstabelecimentoService categoriaEstabelecimentoService;

    @GetMapping
    public ResponseEntity<List<CategoriaEstabelecimento>> listarTodas() {
        List<CategoriaEstabelecimento> categorias = categoriaEstabelecimentoService.listarTodos();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEstabelecimento> buscarPorId(@PathVariable Long id) {
        return categoriaEstabelecimentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
