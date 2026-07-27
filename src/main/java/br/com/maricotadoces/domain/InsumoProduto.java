package br.com.maricotadoces.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

import br.com.maricotadoces.enums.TipoInsumo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "insumo_produto")
public class InsumoProduto {
    public InsumoProduto(Produto produto, Insumo insumo, Long quantidade, TipoInsumo tipo, BigDecimal preco) {
        this.insumo = insumo;
        this.produto = produto;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.preco = preco;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "insumo_id")
    private Insumo insumo;

    private Long quantidade;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private TipoInsumo tipo;
}
