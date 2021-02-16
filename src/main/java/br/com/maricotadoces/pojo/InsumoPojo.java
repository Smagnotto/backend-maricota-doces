package br.com.maricotadoces.pojo;

import br.com.maricotadoces.domain.Insumo;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value="Id do insumo", readOnly = true)
    private Long id;

    @ApiModelProperty(value = "Nome do insumo", required = true, allowEmptyValue = false, dataType = "texto")
    private String nome;

    @ApiModelProperty(value = "Insumo ativo", required = true, allowEmptyValue = false, dataType = "boolean")
    private Boolean ativo;
}
