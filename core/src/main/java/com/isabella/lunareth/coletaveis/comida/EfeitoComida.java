package com.isabella.lunareth.coletaveis.comida;

public class EfeitoComida {
    
    private float cura;
    private float fome;
    private float sede;
    private float energia;

    public EfeitoComida(float cura, float fome, float sede, float energia) {
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
}
