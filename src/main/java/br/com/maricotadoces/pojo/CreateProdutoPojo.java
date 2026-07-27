package br.com.maricotadoces.pojo;

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

    @Schema(description = "Insumos", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<CreateInsumoProdutoPojo> insumos = new HashSet<>();

    @Schema(description = "Produtos componentes (ex: uma massa usada dentro de um bolo)", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<CreateProdutoComponentePojo> componentes = new HashSet<>();
}
