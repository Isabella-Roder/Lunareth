package com.isabella.lunareth.criaturas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Criatura {
    
    private float x;
    private float y;
    private float tamanho = 32f;
    private float vida = 50;

    public Criatura(float x, float y, float vida) {
        this.x = x;
        this.y = y;
        this.vida = vida;
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
}
