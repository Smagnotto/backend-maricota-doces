package br.com.maricotadoces.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.maricotadoces.pojo.CreateEnderecoClientePojo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endereco_cliente")
@Getter
@Setter
@NoArgsConstructor
public class Endereco {

    public Endereco(CreateEnderecoClientePojo endereco) {
        this.cep = endereco.getCep();
        this.complemento = endereco.getComplemento();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
    }

    public Endereco(Long id, CreateEnderecoClientePojo endereco) {
        this.cep = endereco.getCep();
        this.complemento = endereco.getComplemento();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "logradouro", length = 100, nullable = false)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "complemento", length = 60)
    private String complemento;

    @Column(name = "cep", length = 8, nullable = false)
    private String cep;
}