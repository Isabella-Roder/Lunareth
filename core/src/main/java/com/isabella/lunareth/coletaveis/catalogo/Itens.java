package com.isabella.lunareth.coletaveis.catalogo;

import com.isabella.lunareth.coletaveis.armas.Armas;
import com.isabella.lunareth.coletaveis.comida.Comida;

public class Itens {

    public static final Armas FOICE_MISTICA = new Armas(
        "Foice Mística", "coletaveis/armas/foice_mistica.png", 300, 0, RaridadeItem.DEMONIACO
    );

    public static final Comida MACA = new Comida(
        "Maça", "coletaveis/frutas/maca.png", 7, 3, 3, 10, RaridadeItem.COMUM
    );
    public static final Comida MACA_VERDE = new Comida(
        "Maçã Verde", "coletaveis/frutas/maca_verde.png", 6, 3, 3, 9, RaridadeItem.COMUM
    );
    public static final Comida MORANGO = new Comida(
        "Morango", "coletaveis/frutas/morango.png", 3, 3, 4, 7, RaridadeItem.COMUM    
    );
}
