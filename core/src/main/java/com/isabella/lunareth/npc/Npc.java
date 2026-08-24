package com.isabella.lunareth.npc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Npc {
    
    protected float x;
    protected float y;
    
    private float tamanho = 32f;

    private Texture texture;

    private Falas falaInicial;

    public Npc(float x, float y, String caminhoTextura, Falas falaInicial) {
        this.x = x;
        this.y = y;
        this.texture = new Texture(caminhoTextura);
        this.falaInicial = falaInicial;
    }

    public Falas getFalaInicial() {
        return falaInicial;
    }

    public void render(SpriteBatch batch) {
        float largura = tamanho;
        float altura = tamanho * (texture.getHeight() / (float) texture.getWidth());
        batch.draw(texture, x, y, largura, altura);
    }

    public void atualizar(float delta) {
        
    }

    public boolean pertoDoPlayer(float px, float py, float pTamanho) {
        return px < x + tamanho && px + pTamanho > x && py < y + tamanho && py + pTamanho > y;
    }

    public void dispose() {
        texture.dispose();
    }

}
