package br.com.maricotadoces.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "configuracao")
@Getter
@Setter
@NoArgsConstructor
public class Configuracao {

    @Id
    private Long id;

    @Column(name = "margem_percentual", precision = 5, scale = 2, nullable = false)
    private BigDecimal margemPercentual;
}
