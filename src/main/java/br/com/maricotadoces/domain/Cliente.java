package br.com.maricotadoces.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import br.com.maricotadoces.pojo.CreateClientePojo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    public Cliente(Long id, CreateClientePojo pojo) {
        this.id = id;
        this.nome = pojo.getNome();
        this.enderecos = pojo.getEnderecos().stream().map(Endereco::new).collect(Collectors.toSet());
    }

    public Cliente(CreateClientePojo pojo) {
        this.nome = pojo.getNome();
        this.enderecos = pojo.getEnderecos().stream().map(Endereco::new).collect(Collectors.toSet());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", nullable = false, length = 30, unique = true)
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Endereco> enderecos = new HashSet<>();
}
