package com.example.dispensadobem.controller;


import com.example.dispensadobem.model.Cliente;
import com.example.dispensadobem.model.ItemPedido;
import com.example.dispensadobem.model.Pedido;
import com.example.dispensadobem.model.Produto;
import com.example.dispensadobem.service.ClienteService;
import com.example.dispensadobem.service.PedidoService;
import com.example.dispensadobem.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    // Listar todos os pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    // Listar pedidos de um cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> listarPorCliente(@PathVariable Long clienteId) {
        List<Pedido> pedidos = pedidoService.listarPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);
    }

    // Buscar pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        return pedido.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Pedido pedido) {
        // Define status padrão na criação
        pedido.setStatus("AGUARDANDO_PAGAMENTO"); // ou o enum/string que você usar

        // Valida e associa cliente
        Long clienteId = pedido.getCliente().getId();
        Optional<Cliente> cliente = clienteService.buscarPorId(clienteId);
        if (cliente.isEmpty()) {
            return ResponseEntity.badRequest().body("Cliente não encontrado.");
        }
        pedido.setCliente(cliente.get());

        // Processa itens do pedido e calcula total
        BigDecimal total = BigDecimal.ZERO;
        if (pedido.getItens() != null) {
            for (ItemPedido item : pedido.getItens()) {
                item.setPedido(pedido);

                Long produtoId = item.getProduto().getId();
                Optional<Produto> produtoOpt = produtoService.buscarPorId(produtoId);
                if (produtoOpt.isEmpty()) {
                    return ResponseEntity.badRequest().body("Produto com ID " + produtoId + " não encontrado.");
                }

                Produto produto = produtoOpt.get();
                item.setProduto(produto);

                BigDecimal preco = produto.getPrecoDesconto();
                item.setPrecoUnitario(preco);

                total = total.add(preco.multiply(BigDecimal.valueOf(item.getQuantidade())));
            }
        }
        pedido.setTotal(total);

        Pedido novoPedido = pedidoService.salvar(pedido);
        return ResponseEntity.status(201).body(novoPedido);
    }


    // Deletar pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        if (pedido.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

