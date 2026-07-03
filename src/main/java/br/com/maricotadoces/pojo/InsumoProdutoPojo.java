package br.com.maricotadoces.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.maricotadoces.domain.InsumoProduto;
import br.com.maricotadoces.enums.TipoInsumo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsumoProdutoPojo {

    public InsumoProdutoPojo(InsumoProduto insumoProduto) {
        this.id = insumoProduto.getId();
        this.nome = insumoProduto.getInsumo().getNome();
        this.quantidade = insumoProduto.getQuantidade();
        this.tipo = insumoProduto.getTipo();
    }

    @Schema(description = "Id Vinculo", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Nome do insumo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(description = "Quantidade", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long quantidade;

    @Schema(description = "Tipo", requiredMode = Schema.RequiredMode.REQUIRED)
    private TipoInsumo tipo;
}
