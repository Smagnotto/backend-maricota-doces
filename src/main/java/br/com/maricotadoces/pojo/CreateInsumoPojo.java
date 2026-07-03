package br.com.maricotadoces.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInsumoPojo {

    @Schema(description = "Nome", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(description = "Ativo", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean ativo;
}
