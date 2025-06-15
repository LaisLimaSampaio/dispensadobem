package com.example.dispensadobem.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private String nomeFantasia;

    @Column(nullable = false, name = "cnpj", unique = true)
    private String cnpj;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = false, name = "senha")
    private String senha;

    @Column(name = "horario_funcionamento")
    private String horarioFuncionamento;

    @Column(name = "oferece_entrega")
    private Boolean ofereceEntrega = false;

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
    @JsonBackReference
    private CategoriaEstabelecimento categoria;

    // Relacionamentos

    @OneToOne(mappedBy = "estabelecimento", cascade = CascadeType.ALL)
    @JsonManagedReference
    private EnderecoEstabelecimento endereco;

    @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Produto> produtos = new ArrayList<>();

    @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Favorito> favoritos = new ArrayList<>();


}
