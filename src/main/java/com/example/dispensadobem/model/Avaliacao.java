package com.example.dispensadobem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacoes")
@Getter
@Setter
@NoArgsConstructor
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnoreProperties({"favoritos", "avaliacoes", "endereco"})
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    @JsonIgnoreProperties({"avaliacoes", "produtos", "categoria", "endereco"})
    private Estabelecimento estabelecimento;

    @Column(nullable = false)
    private Integer nota;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "data", nullable = false)
    private LocalDateTime data = LocalDateTime.now();

    // Construtor auxiliar
    public Avaliacao(Cliente cliente, Estabelecimento estabelecimento, Integer nota, String comentario) {
        this.cliente = cliente;
        this.estabelecimento = estabelecimento;
        this.nota = nota;
        this.comentario = comentario;
        this.data = LocalDateTime.now();
    }

}
