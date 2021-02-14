package br.com.maricotadoces.pojo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInsumoPojo {

    @ApiModelProperty(value = "Nome", required = true, allowEmptyValue = false, dataType = "java.lang.String")
    private String nome;

    @ApiModelProperty(value = "Ativo", required = true, allowEmptyValue = false, dataType = "java.math.Boolean")
    private Boolean ativo;

    @ApiModelProperty(value = "Preço", required = true, allowEmptyValue = false, dataType = "java.math.BigDecimal")
    private BigDecimal preco;
}
