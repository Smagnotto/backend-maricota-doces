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

import br.com.maricotadoces.pojo.CreateInsumoPojo;
import br.com.maricotadoces.pojo.InsumoPojo;
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
    }

    public Insumo(CreateInsumoPojo pojo) {
        this.nome = pojo.getNome();
        this.ativo = pojo.getAtivo();
    }

    public Insumo(InsumoPojo pojo) {
        this.id = pojo.getId();
        this.nome = pojo.getNome();
        this.ativo = pojo.getAtivo();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", nullable = false, length = 30, unique = true)
    private String nome;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @OneToMany(mappedBy = "insumo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InsumoProduto> produtos = new HashSet<>();
}