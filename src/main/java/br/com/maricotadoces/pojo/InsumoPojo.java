package br.com.maricotadoces.pojo;

import br.com.maricotadoces.domain.Insumo;
import br.com.maricotadoces.enums.TipoInsumo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class InsumoPojo {

    public InsumoPojo(Insumo insumo) {
        this.id = insumo.getId();
        this.nome = insumo.getNome();
        this.ativo = insumo.getAtivo();
        this.preco = insumo.getPreco();
        this.tipo = insumo.getTipo();
    }

    @Schema(description = "Id do insumo", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome do insumo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(description = "Insumo ativo", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean ativo;

    @Schema(description="Valor do Insumo", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal preco;

    @Schema(description = "Tipo do insumo", requiredMode = Schema.RequiredMode.REQUIRED)
    private TipoInsumo tipo;
}
