package br.com.maricotadoces.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.maricotadoces.pojo.CreateInsumoPojo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Insumo
 */

@Entity
@Table(name = "insumo")
@Getter
@Setter
@NoArgsConstructor
public class Insumo {

    public Insumo(Long id, CreateInsumoPojo pojo) {
        this.id = id;
        this.nome = pojo.getNome();
        this.ativo = pojo.getAtivo();
        this.preco = pojo.getPreco();
    }

    public Insumo(CreateInsumoPojo pojo) {
        this.nome = pojo.getNome();
        this.preco = pojo.getPreco();
        this.ativo = pojo.getAtivo();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @Column(name = "preco", precision = 11, scale = 2, nullable = false)
    private BigDecimal preco;
}