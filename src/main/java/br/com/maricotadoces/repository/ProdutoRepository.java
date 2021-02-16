package br.com.maricotadoces.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maricotadoces.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
