package br.com.maricotadoces.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import br.com.maricotadoces.enums.TipoInsumo;
import br.com.maricotadoces.pojo.CreateInsumoPojo;
import br.com.maricotadoces.pojo.InsumoPojo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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
        this.tipo = pojo.getTipo();
    }

    public Insumo(CreateInsumoPojo pojo) {
        this.nome = pojo.getNome();
        this.ativo = pojo.getAtivo();
        this.preco = pojo.getPreco();
        this.tipo = pojo.getTipo();
    }

    public Insumo(InsumoPojo pojo) {
        this.id = pojo.getId();
        this.nome = pojo.getNome();
        this.ativo = pojo.getAtivo();
        this.preco = pojo.getPreco();
        this.tipo = pojo.getTipo();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", nullable = false, length = 100, unique = true)
    private String nome;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @Column(name="preco", nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoInsumo tipo;

    @OneToMany(mappedBy = "insumo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InsumoProduto> produtos = new HashSet<>();
}