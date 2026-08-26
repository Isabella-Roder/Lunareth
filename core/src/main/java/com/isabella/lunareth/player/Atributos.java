package com.isabella.lunareth.player;

import com.isabella.lunareth.coletaveis.comida.EfeitoComida;

public class Atributos {

    private float vidaMaxima = 100f;
    private float vida = vidaMaxima;

    private float fomeMaxima = 100f;
    private float fome = fomeMaxima;

    private float sedeMaxima = 100f;
    private float sede = sedeMaxima;

    private float energiaMaxima = 100f;
    private float energia = energiaMaxima;

    public void aplicarEfeito(EfeitoComida efeito) {
        vida = Math.min(vida + efeito.getCura(), vidaMaxima);
        fome = Math.min(fome + efeito.getFome(), fomeMaxima);
        sede = Math.min(sede + efeito.getSede(), sedeMaxima);
        energia = Math.min(energia + efeito.getEnergia(), energiaMaxima);
    }

    public void receberDano(float dano) {
        vida -= dano;
    }

    public float getVida() {
        return vida;
    }

    public float getSede() {
        return sede;
    }

    public float getFome() {
        return fome;
    }

    public float getEnergia() {
        return energia;
    }
}
