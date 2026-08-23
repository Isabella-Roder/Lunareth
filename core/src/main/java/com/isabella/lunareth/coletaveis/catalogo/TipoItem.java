package com.isabella.lunareth.coletaveis.catalogo;

public class TipoItem {
    
    private final String nome;
    private final String caminhoTextura;

    private final RaridadeItem raridade;
    private final CategoriaItem categoria;

    public TipoItem(String nome, String caminhoTextura, RaridadeItem raridadeItem, CategoriaItem categoria) {
        this.nome = nome;
        this.caminhoTextura = caminhoTextura;
        this.raridade = raridadeItem;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getCaminhoTextura() {
        return caminhoTextura;
    }

    public RaridadeItem getRaridade() {
        return raridade;
    }

    public CategoriaItem getCategoria() {
        return categoria;
    }
}
