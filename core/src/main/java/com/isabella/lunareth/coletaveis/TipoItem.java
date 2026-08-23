package com.isabella.lunareth.coletaveis;

public class TipoItem {
    
    private final String nome;
    private final String caminhoTextura;

    private final float dano;
    private final float durabilidade;
    private final RaridadeItem raridade;

    public TipoItem(String nome, String caminhoTextura, float dano, float durabilidade, RaridadeItem raridadeItem) {
        this.nome = nome;
        this.caminhoTextura = caminhoTextura;
        this.dano = dano;
        this.durabilidade = durabilidade;
        this.raridade = raridadeItem;
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
}
