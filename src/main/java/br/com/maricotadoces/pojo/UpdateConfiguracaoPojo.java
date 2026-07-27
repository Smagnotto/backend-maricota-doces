package br.com.maricotadoces.pojo;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateConfiguracaoPojo {

    @Schema(description = "Margem de lucro (%) aplicada sobre o custo dos produtos", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal margemPercentual;
}
