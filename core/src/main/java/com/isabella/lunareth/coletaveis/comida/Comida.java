package com.isabella.lunareth.coletaveis.comida;

import com.isabella.lunareth.coletaveis.catalogo.CategoriaItem;
import com.isabella.lunareth.coletaveis.catalogo.RaridadeItem;
import com.isabella.lunareth.coletaveis.catalogo.TipoItem;

public class Comida extends TipoItem {
    
    private float cura;
    private float fome;
    private float sede;
    private float energia;

    public Comida(
        String nome,
        String caminhoTextura,
        float cura,
        float fome,
        float sede, 
        float energia,
        RaridadeItem raridade
    ) {
        super(nome, caminhoTextura, raridade, CategoriaItem.COMIDA);

        this.cura = cura;
        this.fome = fome;
        this.sede = sede;
        this.energia = energia;
    }

    public float getCura() {
        return cura;
    }

    public float getFome() {
        return fome;
    }

    public float getSede() {
        return sede;
    }

    public float getEnergia() {
        return energia;
    }

    public EfeitoComida comer() {
        return new EfeitoComida(cura, fome, sede, energia);
    }
}
