package com.isabella.lunareth.coletaveis.catalogo;

public class TipoItem {
    
    private final String nome;
    private final String caminhoTextura;

    private final float dano;
    private final float durabilidade; // 0 significa durabilidade infinita (nunca quebra)
    private final RaridadeItem raridade;
    private final CategoriaItem categoria;

    public TipoItem(String nome, String caminhoTextura, float dano, float durabilidade, RaridadeItem raridadeItem, CategoriaItem categoria) {
        this.nome = nome;
        this.caminhoTextura = caminhoTextura;
        this.dano = dano;
        this.durabilidade = durabilidade;
        this.raridade = raridadeItem;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getCaminhoTextura() {
        return caminhoTextura;
    }

    public float getDano() {
        return dano;
    }

    public float getDurabilidade() {
        return durabilidade;
    }

    public RaridadeItem getRaridade() {
        return raridade;
    }

    public CategoriaItem getCategoria() {
        return categoria;
    }
}
