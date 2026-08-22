package com.isabella.lunareth.player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
    
    private float x;
    private float y;
    private final float tamanho = 32f;
    private final float velocidade = 200f;

    public Player(float startX, float startY) {
        this.x = startX;
        this.y = startY;
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Keys.W)) y += velocidade * delta;
        if (Gdx.input.isKeyPressed(Keys.S)) y -= velocidade * delta;
        if (Gdx.input.isKeyPressed(Keys.A)) x -= velocidade * delta;
        if (Gdx.input.isKeyPressed(Keys.D)) x += velocidade * delta;
    }

    public void render(ShapeRenderer render) {
        render.setColor(Color.PURPLE);
        render.rect(x, y, tamanho, tamanho);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
