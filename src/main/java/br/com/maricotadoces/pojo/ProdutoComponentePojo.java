package br.com.maricotadoces.pojo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.maricotadoces.domain.ProdutoComponente;
import br.com.maricotadoces.enums.TipoInsumo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoComponentePojo {

    public ProdutoComponentePojo(ProdutoComponente produtoComponente) {
        this.id = produtoComponente.getId();
        this.idProduto = produtoComponente.getProdutoFilho().getId();
        this.nome = produtoComponente.getProdutoFilho().getNome();
        this.quantidade = produtoComponente.getQuantidade();
        this.tipo = produtoComponente.getTipo();
        this.preco = produtoComponente.getPreco();
    }

    @Schema(description = "Id Vinculo", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Id do produto componente", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("id_produto")
    private Long idProduto;

    @Schema(description = "Nome do produto componente", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(description = "Quantidade", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long quantidade;

    @Schema(description = "Tipo", requiredMode = Schema.RequiredMode.REQUIRED)
    private TipoInsumo tipo;

    @Schema(description = "Custo do produto componente no momento do vínculo", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal preco;
}
