package com.isabella.lunareth.criaturas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.isabella.lunareth.mundo.Bioma;

public class Criatura {
    
    private float x;
    private float y;
    private float tamanho = 32f;
    private float vida = 50;

    private Bioma bioma;
    
    private float dano = 10;

    public Criatura(float x, float y, float vida, Bioma bioma) {
        this.x = x;
        this.y = y;
        this.vida = vida;
        this.bioma = bioma;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.RED);
        renderer.rect(x, y, tamanho, tamanho);
    }

    public void recebeDano(float dano) {
        vida -= dano;
    }

    public boolean estaMorta() {
        return vida <= 0;
    }

    public boolean pertoDoPlayer(float px, float py, float pTamanho) {
        return px < x + tamanho && px + pTamanho > x && py < y + tamanho && py + pTamanho > y;
    }

    public float atacar() {
        return dano;
    }

    public Bioma getBioma() {
        return bioma;
    }
}
