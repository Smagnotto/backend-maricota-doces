package br.com.maricotadoces.pojo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProdutoPojo {

    @Schema(description = "Nome", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(description = "Ativo", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean ativo;

    @Schema(description = "Preco", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal preco;

    @Schema(description = "Insumos", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<CreateInsumoProdutoPojo> insumos = new HashSet<>();
}
