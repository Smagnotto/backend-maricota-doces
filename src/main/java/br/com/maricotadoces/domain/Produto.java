package br.com.maricotadoces.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.maricotadoces.enums.TipoInsumo;
import br.com.maricotadoces.pojo.CreateProdutoPojo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Insumo
 */

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
public class Produto {

    public Produto(Long id, CreateProdutoPojo pojo) {
        this.id = id;
        this.nome = pojo.getNome();
        this.ativo = pojo.getAtivo();
        this.preco = pojo.getPreco();
    }

    public Produto(CreateProdutoPojo pojo) {
        this.nome = pojo.getNome();
        this.ativo = pojo.getAtivo();
        this.preco = pojo.getPreco();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", nullable = false, length = 30, unique = true)
    private String nome;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @Column(name = "preco", precision = 11, scale = 2, nullable = false)
    private BigDecimal preco;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InsumoProduto> insumos = new HashSet<>();

    public void addInsumo(Insumo insumo, Long quantidade, TipoInsumo tipo) {
        InsumoProduto insumoProduto  = new InsumoProduto(this, insumo, quantidade, tipo);

        this.insumos.add(insumoProduto);
        insumo.getProdutos().add(insumoProduto);
    }
}