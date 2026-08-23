package com.isabella.lunareth.player;

import com.isabella.lunareth.coletaveis.catalogo.TipoItem;

public class ItemInventario {
    
    private final TipoItem tipo;
    
    private int quantidade = 1;

    public ItemInventario(TipoItem tipo, int quantidade) {
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void adicionarUnidade() {
        quantidade++;
    }
}
