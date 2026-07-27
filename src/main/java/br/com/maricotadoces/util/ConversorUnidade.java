package br.com.maricotadoces.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;

import br.com.maricotadoces.enums.TipoInsumo;

/**
 * Espelha a função converterQuantidade do frontend (tipos-insumos.ts): converte uma
 * quantidade da unidade escolhida no vínculo (ex: G) para a unidade nativa do insumo
 * no catálogo (ex: KG), já que o preço do insumo é sempre por unidade nativa.
 */
public final class ConversorUnidade {

    private enum GrupoUnidade {
        MASSA, VOLUME
    }

    private static final Map<TipoInsumo, GrupoUnidade> GRUPO_POR_UNIDADE = new EnumMap<>(TipoInsumo.class);
    private static final Map<TipoInsumo, BigDecimal> FATOR_PARA_UNIDADE_BASE = new EnumMap<>(TipoInsumo.class);

    static {
        GRUPO_POR_UNIDADE.put(TipoInsumo.KG, GrupoUnidade.MASSA);
        GRUPO_POR_UNIDADE.put(TipoInsumo.G, GrupoUnidade.MASSA);
        GRUPO_POR_UNIDADE.put(TipoInsumo.L, GrupoUnidade.VOLUME);
        GRUPO_POR_UNIDADE.put(TipoInsumo.ML, GrupoUnidade.VOLUME);

        FATOR_PARA_UNIDADE_BASE.put(TipoInsumo.KG, BigDecimal.ONE);
        FATOR_PARA_UNIDADE_BASE.put(TipoInsumo.G, new BigDecimal("0.001"));
        FATOR_PARA_UNIDADE_BASE.put(TipoInsumo.L, BigDecimal.ONE);
        FATOR_PARA_UNIDADE_BASE.put(TipoInsumo.ML, new BigDecimal("0.001"));
    }

    private ConversorUnidade() {
    }

    /**
     * Se as unidades forem incompatíveis (ex.: massa para volume) ou desconhecidas,
     * retorna a quantidade original, sem converter.
     */
    public static BigDecimal converterQuantidade(BigDecimal quantidade, TipoInsumo de, TipoInsumo para) {
        if (quantidade == null || de == null || para == null || de == para) {
            return quantidade;
        }

        GrupoUnidade grupoDe = GRUPO_POR_UNIDADE.get(de);
        GrupoUnidade grupoPara = GRUPO_POR_UNIDADE.get(para);

        if (grupoDe == null || grupoPara == null || grupoDe != grupoPara) {
            return quantidade;
        }

        return quantidade.multiply(FATOR_PARA_UNIDADE_BASE.get(de))
                .divide(FATOR_PARA_UNIDADE_BASE.get(para), 6, RoundingMode.HALF_UP);
    }
}
