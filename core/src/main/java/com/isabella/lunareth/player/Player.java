package com.isabella.lunareth.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.isabella.lunareth.mundo.Mapa;

public class Player {
    
    private float x;
    private float y;

    private final float tamanho = 32f;
    private final float velocidade = 200f;

    private Animation<TextureRegion> animacaoParado;
    private Animation<TextureRegion> animacaoAndandoDireita;
    private Animation<TextureRegion> animacaoAndandoEsquerda;
    private float tempoAnimacao = 0f;
    private boolean viradoEsquerda = false;

    private Animation<TextureRegion> carregarAnimacao(float duracaoFrame, String... caminhos) {

        TextureRegion[] frames = new TextureRegion[caminhos.length];
        
        for (int i = 0; i < caminhos.length; i++) {
            frames[i] = new TextureRegion(new Texture(caminhos[i]));
        }

        return new Animation<>(duracaoFrame, frames);
    }

    public Player(float startX, float startY) {
        this.x = startX;
        this.y = startY;

        animacaoParado = carregarAnimacao(0.8f,
            "player/parado/parado.png", "player/parado/parado2.png"
        );

        animacaoAndandoDireita = carregarAnimacao(0.13f, 
            "player/andando/andando_direita.png", "player/andando/andando_direita2.png",
            "player/andando/andando_direita3.png", "player/andando/andando_direita4.png",
            "player/andando/andando_direita5.png"
        );

        animacaoAndandoEsquerda = carregarAnimacao(0.13f, 
            "player/andando/andando_esquerda.png", "player/andando/andando_esquerda2.png",
            "player/andando/andando_esquerda3.png", "player/andando/andando_esquerda4.png",
            "player/andando/andando_esquerda5.png"
        );
    }

    public void update(float delta, Mapa mapa) {
        tempoAnimacao += delta;

        float novoX = x;
        float novoY = y;

        if (Gdx.input.isKeyPressed(Keys.W)) novoY += velocidade * delta;
        if (Gdx.input.isKeyPressed(Keys.S)) novoY -= velocidade * delta;
        if (Gdx.input.isKeyPressed(Keys.A)) { novoX -= velocidade * delta; viradoEsquerda = true;}
        if (Gdx.input.isKeyPressed(Keys.D)) { novoX += velocidade * delta; viradoEsquerda = false;}

        if (!colide(mapa, novoX, y)) {
            x = novoX;
        }
        if (!colide(mapa, x, novoY)) {
            y = novoY;
        }
    }

    public void render(SpriteBatch batch) {

        boolean andando = Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.S)
            || Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.D);

        Animation<TextureRegion> animacaoAtual;
        if (!andando) {
            animacaoAtual = animacaoParado;
        } else if (viradoEsquerda) {
            animacaoAtual = animacaoAndandoEsquerda;
        } else {
            animacaoAtual = animacaoAndandoDireita;
        }
        
        TextureRegion frame = animacaoAtual.getKeyFrame(tempoAnimacao, true);

        float largura = tamanho;
        float altura = tamanho * (frame.getRegionHeight() / (float) frame.getRegionWidth());
        batch.draw(frame, x, y, largura, altura);
    }

    public void dispose() {

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

    public void setPosicao(float novoX, float novoY) {
        x = novoX;
        y = novoY;
    }
}
