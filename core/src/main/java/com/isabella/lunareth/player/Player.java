package com.isabella.lunareth.player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.isabella.lunareth.mundo.Mapa;

public class Player {
    
    private float x;
    private float y;

    private final float tamanho = 32f;
    private final float velocidade = 200f;

    public Player(float startX, float startY) {
        this.x = startX;
        this.y = startY;
    }

    public void update(float delta, Mapa mapa) {
        float novoX = x;
        float novoY = y;

        if (Gdx.input.isKeyPressed(Keys.W)) novoY += velocidade * delta;
        if (Gdx.input.isKeyPressed(Keys.S)) novoY -= velocidade * delta;
        if (Gdx.input.isKeyPressed(Keys.A)) novoX -= velocidade * delta;
        if (Gdx.input.isKeyPressed(Keys.D)) novoX += velocidade * delta;

        if (!colide(mapa, novoX, y)) {
            x = novoX;
        }
        if (!colide(mapa, x, novoY)) {
            y = novoY;
        }
    }

    public void render(ShapeRenderer render) {
        render.setColor(Color.PURPLE);
        render.rect(x, y, tamanho, tamanho);
    }

    private boolean colide(Mapa mapa, float px, float py) {
        return mapa.solido(px, py)
            || mapa.solido(px + tamanho - 1, py)
            || mapa.solido(px, py + tamanho - 1)
            || mapa.solido(px + tamanho - 1, py + tamanho - 1);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
