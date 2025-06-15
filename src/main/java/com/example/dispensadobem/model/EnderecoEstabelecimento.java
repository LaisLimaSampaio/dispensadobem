package com.example.dispensadobem.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "endereco_estabelecimento")
@Getter
@Setter
@NoArgsConstructor
public class EnderecoEstabelecimento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;

    @Column(length = 2)
    private String estado;

    private String cep;

    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(precision = 11, scale = 8)
    private BigDecimal longitude;

    @OneToOne
    @JoinColumn(name = "estabelecimento_id", unique = true)
    @JsonBackReference
    private Estabelecimento estabelecimento;

    // Construtor auxiliar
    public EnderecoEstabelecimento(String rua, String numero, String complemento, String bairro,
                                   String cidade, String estado, String cep, Estabelecimento estabelecimento) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.latitude = BigDecimal.ZERO;
        this.longitude = BigDecimal.ZERO;
        this.estabelecimento = estabelecimento;
    }

}
