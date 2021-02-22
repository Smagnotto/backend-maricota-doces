package br.com.maricotadoces.pojo;

import br.com.maricotadoces.domain.Cliente;
import br.com.maricotadoces.domain.Endereco;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientePojo {

    public ClientePojo(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();

        cliente.getEnderecos().stream().findFirst().ifPresent(x -> this.endereco = new EnderecoClientePojo(x));
    }

    @ApiModelProperty(value="Id", readOnly = true)
    private Long id;

    @ApiModelProperty(value = "Nome", required = true, allowEmptyValue = false)
    private String nome;

    @ApiModelProperty(value="Endereco", readOnly = true)
    private EnderecoClientePojo endereco;

}
