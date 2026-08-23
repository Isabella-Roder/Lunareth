package com.isabella.lunareth.coletaveis.armas;

import com.isabella.lunareth.coletaveis.catalogo.CategoriaItem;
import com.isabella.lunareth.coletaveis.catalogo.RaridadeItem;
import com.isabella.lunareth.coletaveis.catalogo.TipoItem;


public class Armas extends TipoItem {
    
    public Armas(String nome, String caminhoTextura, float dado, float durabilidade, RaridadeItem raridade) {
        super(nome, caminhoTextura, dado, durabilidade, raridade, CategoriaItem.ARMA);

        if (dado < 0) {
            throw new IllegalArgumentException("Dano da arma não pode ser menor que zero: " + nome);
        }

        if (durabilidade < 0) {
            throw new IllegalArgumentException("Durabilidade da arma não pode ser negativa: " + nome);
        }
    }

    public float atacar() {
        return getDano();
    }
}
