package br.com.maricotadoces.pojo;

import java.math.BigDecimal;

import br.com.maricotadoces.domain.Configuracao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfiguracaoPojo {

    public ConfiguracaoPojo(Configuracao configuracao) {
        this.margemPercentual = configuracao.getMargemPercentual();
    }

    @Schema(description = "Margem de lucro (%) aplicada sobre o custo dos produtos", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal margemPercentual;
}
