package com.logistock.preco;

import com.logistock.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "Preco")
@Table(name = "historico_de_precos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Preco {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double preco;
    private LocalDate dataInicio;
    @Setter
    private LocalDate dataFinal;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    public Preco(Double preco, Produto produto) {
        this.preco = preco;
        this.dataInicio = LocalDate.now();
        this.produto = produto;
    }

}
