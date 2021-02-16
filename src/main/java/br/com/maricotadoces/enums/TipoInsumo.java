package br.com.maricotadoces.enums;

public enum TipoInsumo {
    KG("kg"), L("l"), ML("ml"), G("g");

    private String descricao;

    TipoInsumo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
