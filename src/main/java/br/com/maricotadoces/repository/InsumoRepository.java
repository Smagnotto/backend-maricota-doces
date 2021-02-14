package br.com.maricotadoces.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maricotadoces.domain.Insumo;

public interface InsumoRepository extends JpaRepository<Insumo, Long> {

}
