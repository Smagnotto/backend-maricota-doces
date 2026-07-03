package br.com.maricotadoces.pojo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.maricotadoces.domain.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPojo {

    public ProdutoPojo(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.ativo = produto.getAtivo();
        this.preco = produto.getPreco();

        insumos = produto.getInsumos().stream().map(InsumoProdutoPojo::new).collect(Collectors.toSet());
    }

    @Schema(description = "Id do insumo", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome do insumo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(description = "Insumo ativo", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean ativo;

    @Schema(description = "Preco", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal preco;

    @Schema(description = "Insumos", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<InsumoProdutoPojo> insumos = new HashSet<>();
}
