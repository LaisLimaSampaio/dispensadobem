package com.example.dispensadobem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;


    @Column(nullable = false)
    private String telefone;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    public Cliente(String nome, String email, String senha, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.dataCriacao = LocalDateTime.now();
    }

    //relacionamentos

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private EnderecoCliente endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Favorito> favoritos;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;


}
