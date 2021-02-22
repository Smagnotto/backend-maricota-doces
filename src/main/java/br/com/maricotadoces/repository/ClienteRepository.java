package br.com.maricotadoces.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maricotadoces.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeLike(String nome);

}
