package br.com.maricotadoces.pojo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProdutoPojo {

    @ApiModelProperty(value = "Nome", required = true, allowEmptyValue = false, dataType = "java.lang.String")
    private String nome;

    @ApiModelProperty(value = "Ativo", required = true, allowEmptyValue = false, dataType = "java.lang.Boolean")
    private Boolean ativo;

    @ApiModelProperty(value = "Preco", required = true, allowEmptyValue = false, dataType = "java.math.BigDecimal")
    private BigDecimal preco;

    @ApiModelProperty(value = "Insumos", required = true, allowEmptyValue = false)
    private Set<CreateInsumoProdutoPojo> insumos = new HashSet<>();
}
