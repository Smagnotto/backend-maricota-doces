package br.com.maricotadoces.pojo;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrecificacaoPojo {

    @Schema(description = "Custo (soma dos insumos e produtos componentes)", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal custo;

    @Schema(description = "Preco (custo + margem de lucro configurada)", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal preco;
}
