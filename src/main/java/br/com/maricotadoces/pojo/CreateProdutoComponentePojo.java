package br.com.maricotadoces.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.maricotadoces.enums.TipoInsumo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProdutoComponentePojo {

    @Schema(description = "Id do Produto componente", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("id_produto")
    private Long idProduto;

    @Schema(description = "Quantidade do Produto componente", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long quantidade;

    @Schema(description = "Tipo do Produto componente", requiredMode = Schema.RequiredMode.REQUIRED)
    private TipoInsumo tipo;
}
