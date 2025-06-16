package com.example.dispensadobem.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;


    @Column(nullable = true)
    private String telefone;

    @Column(name = "data_criacao", updatable = false)
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    public Cliente(String nome, String email, String senha, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.dataCriacao = LocalDateTime.now();
    }

    //relacionamentos


    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EnderecoCliente endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Favorito> favoritos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Avaliacao> avaliacoes = new ArrayList<>();
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();


}
