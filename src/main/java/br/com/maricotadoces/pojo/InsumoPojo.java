package br.com.maricotadoces.pojo;

import br.com.maricotadoces.domain.Insumo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsumoPojo {

    public InsumoPojo(Insumo insumo) {
        this.id = insumo.getId();
        this.nome = insumo.getNome();
        this.ativo = insumo.getAtivo();
    }

    @Schema(description = "Id do insumo", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome do insumo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(description = "Insumo ativo", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean ativo;
}
