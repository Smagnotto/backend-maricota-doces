package br.com.maricotadoces.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.maricotadoces.enums.TipoInsumo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInsumoProdutoPojo {
    
    @Schema(description = "Id do Insumo", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("id_insumo")
    private Long idInsumo;

    @Schema(description = "Quantidade do Insumo", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long quantidade;

    @Schema(description = "Tipo do Insumo", requiredMode = Schema.RequiredMode.REQUIRED)
    private TipoInsumo tipo;
}
