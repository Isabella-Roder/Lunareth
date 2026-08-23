package com.isabella.lunareth.player;

import java.util.ArrayList;
import java.util.List;

import com.isabella.lunareth.coletaveis.catalogo.TipoItem;

public class Inventario {
    
    private final List<ItemInventario> itens = new ArrayList<>();

    public void adicionar(TipoItem tipo) {

        for (ItemInventario item : itens) {

            if (item.getTipo() == tipo) {
                item.adicionarUnidade();
                return;
            }
        }

        itens.add(new ItemInventario(tipo, 1));
    }

    public List<ItemInventario> getItens() {
        return itens;
    }
}
