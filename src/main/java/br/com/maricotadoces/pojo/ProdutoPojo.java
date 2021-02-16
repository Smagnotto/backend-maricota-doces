package br.com.maricotadoces.pojo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.maricotadoces.domain.InsumoProduto;
import br.com.maricotadoces.domain.Produto;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "Id do insumo", readOnly = true)
    private Long id;

    @ApiModelProperty(value = "Nome do insumo", required = true, allowEmptyValue = false, dataType = "texto")
    private String nome;

    @ApiModelProperty(value = "Insumo ativo", required = true, allowEmptyValue = false, dataType = "boolean")
    private Boolean ativo;

    @ApiModelProperty(value = "Preco", required = true, allowEmptyValue = false, dataType = "java.math.BigDecimal")
    private BigDecimal preco;

    @ApiModelProperty(value = "Insumos", required = true, allowEmptyValue = false)
    private Set<InsumoProdutoPojo> insumos = new HashSet<>();
}
