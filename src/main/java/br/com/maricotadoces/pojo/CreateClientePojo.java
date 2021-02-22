package br.com.maricotadoces.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClientePojo {
    
    @ApiModelProperty(value = "Nome", required = true, allowEmptyValue = false, dataType = "java.lang.String")
    private String nome;

    @ApiModelProperty(value = "Enderecos", required = true, allowEmptyValue=false)
    private CreateEnderecoClientePojo endereco;
}
