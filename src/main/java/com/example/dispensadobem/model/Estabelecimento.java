package com.example.dispensadobem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "estabelecimentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome_fantasia")
    private String nome_fantasia;

    @Column(nullable = false, name = "cnpj", unique = true)
    private String cnpj;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = false, name = "senha")
    private String senha;

    @Column(name = "horario_funcionamento")
    private String horarioFuncionamento;

    @Column(name = "oferece_entrega")
    private Boolean ofereceEntrega = true;

    @Column(name = "taxa_entrega")
    private BigDecimal taxaEntrega;

    @Column(name = "entrega_gratis_acima")
    private BigDecimal entregaGratisAcima;

    @Column(name = "distancia_max_entrega_km")
    private BigDecimal distanciaMaxEntregaKm;

    @Column(name = "telefone")
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEstabelecimento categoria;





}
