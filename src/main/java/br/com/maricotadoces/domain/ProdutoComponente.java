package br.com.maricotadoces.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "produto_componente")
public class ProdutoComponente {

    public ProdutoComponente(Produto produtoPai, Produto produtoFilho, Long quantidade, BigDecimal preco) {
        this.produtoPai = produtoPai;
        this.produtoFilho = produtoFilho;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_pai_id")
    private Produto produtoPai;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_filho_id")
    private Produto produtoFilho;

    private Long quantidade;

    private BigDecimal preco;
}
