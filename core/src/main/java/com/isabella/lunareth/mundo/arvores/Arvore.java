package com.isabella.lunareth.mundo.arvores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Arvore {
    
    private float x, y;
    private float largura = 32f;
    private float altura;
    private Texture textura;

    public Arvore(float x, float y, String caminhoTextura, float largura) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.textura = new Texture(caminhoTextura);
        this.altura = largura * (textura.getHeight() / (float) textura.getHeight());
    }

    public void render(SpriteBatch batch) {
        batch.draw(textura, x, y, largura, altura);
    }

    public boolean colideCom(float px, float py, float ptamanho) {
        return px < x + largura && px + ptamanho > x && py < y + altura && py + ptamanho > y;
    }

    public void dispose() {
        textura.dispose();
    }
}
