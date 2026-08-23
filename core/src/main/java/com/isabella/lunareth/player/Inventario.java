package com.isabella.lunareth.player;

import java.util.ArrayList;
import java.util.List;

import com.isabella.lunareth.coletaveis.catalogo.TipoItem;

public class Inventario {
    
    private final List<TipoItem> itens = new ArrayList<>();

    public void adicionar(TipoItem tipo) {
        itens.add(tipo);
    }

    public List<TipoItem> getItens() {
        return itens;
    }
}
