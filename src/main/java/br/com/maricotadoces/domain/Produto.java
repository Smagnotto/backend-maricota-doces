package br.com.maricotadoces.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

import br.com.maricotadoces.enums.TipoInsumo;
import br.com.maricotadoces.pojo.CreateProdutoPojo;
import br.com.maricotadoces.util.ConversorUnidade;
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
    }

    public Produto(CreateProdutoPojo pojo) {
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

    @Column(name = "preco", precision = 11, scale = 2, nullable = false)
    private BigDecimal preco;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InsumoProduto> insumos = new HashSet<>();

    @OneToMany(mappedBy = "produtoPai", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProdutoComponente> componentes = new HashSet<>();

    public void addInsumo(Insumo insumo, Long quantidade, TipoInsumo tipo, BigDecimal preco) {
        InsumoProduto insumoProduto  = new InsumoProduto(this, insumo, quantidade, tipo, preco);

        this.insumos.add(insumoProduto);
        insumo.getProdutos().add(insumoProduto);
    }

    public void addComponente(Produto produtoFilho, Long quantidade, TipoInsumo tipo, BigDecimal preco) {
        ProdutoComponente produtoComponente = new ProdutoComponente(this, produtoFilho, quantidade, tipo, preco);

        this.componentes.add(produtoComponente);
    }

    public BigDecimal calcularCusto() {
        BigDecimal custoInsumos = insumos.stream()
                .map(ip -> {
                    BigDecimal quantidadeNaUnidadeDoInsumo = ConversorUnidade.converterQuantidade(
                            BigDecimal.valueOf(ip.getQuantidade()), ip.getTipo(), ip.getInsumo().getTipo());

                    return ip.getInsumo().getPreco().multiply(quantidadeNaUnidadeDoInsumo);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal custoComponentes = componentes.stream()
                .map(pc -> pc.getProdutoFilho().calcularCusto().multiply(BigDecimal.valueOf(pc.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return custoInsumos.add(custoComponentes);
    }

    public BigDecimal calcularPreco(BigDecimal margemPercentual) {
        BigDecimal margem = margemPercentual == null ? BigDecimal.ZERO : margemPercentual;
        BigDecimal fator = BigDecimal.ONE.add(margem.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));

        return calcularCusto().multiply(fator).setScale(2, RoundingMode.HALF_UP);
    }
}
