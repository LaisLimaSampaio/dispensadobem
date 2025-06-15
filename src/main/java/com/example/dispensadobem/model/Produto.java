package com.example.dispensadobem.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    @JsonBackReference
    private Estabelecimento estabelecimento;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonBackReference
    private CategoriaProduto categoria;

    @Column(name = "foto_url")
    private String fotoUrl;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @Column(name = "preco_original", nullable = false)
    private BigDecimal precoOriginal;

    @Column(name = "preco_desconto")
    private BigDecimal precoDesconto;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm = LocalDateTime.now();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ItemPedido> itensPedido;

    public Produto(Estabelecimento estabelecimento, String nome, String descricao, CategoriaProduto categoria,
                   String fotoUrl, LocalDate dataValidade, BigDecimal precoOriginal, BigDecimal precoDesconto) {
        this.estabelecimento = estabelecimento;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.fotoUrl = fotoUrl;
        this.dataValidade = dataValidade;
        this.precoOriginal = precoOriginal;
        this.precoDesconto = precoDesconto;
        this.criadoEm = LocalDateTime.now();
    }
}
