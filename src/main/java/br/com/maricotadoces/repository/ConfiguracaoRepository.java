package br.com.maricotadoces.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maricotadoces.domain.Configuracao;

public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {

}
