package br.com.maricotadoces.pojo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.maricotadoces.domain.InsumoProduto;
import br.com.maricotadoces.enums.TipoInsumo;
import br.com.maricotadoces.util.ConversorUnidade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsumoProdutoPojo {

    public InsumoProdutoPojo(InsumoProduto insumoProduto) {
        this.id = insumoProduto.getInsumo().getId();
        this.nome = insumoProduto.getInsumo().getNome();
        this.quantidade = insumoProduto.getQuantidade();
        this.tipo = insumoProduto.getTipo();

        BigDecimal quantidadeNaUnidadeDoInsumo = ConversorUnidade.converterQuantidade(
                BigDecimal.valueOf(insumoProduto.getQuantidade()), insumoProduto.getTipo(),
                insumoProduto.getInsumo().getTipo());
        this.valor = insumoProduto.getInsumo().getPreco().multiply(quantidadeNaUnidadeDoInsumo);
    }

    @Schema(description = "Id do insumo", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Nome do insumo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(description = "Quantidade", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long quantidade;

    @Schema(description = "Tipo", requiredMode = Schema.RequiredMode.REQUIRED)
    private TipoInsumo tipo;

    @Schema(description = "Valor total (quantidade convertida para a unidade nativa x preço atual do insumo)", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal valor;
}
