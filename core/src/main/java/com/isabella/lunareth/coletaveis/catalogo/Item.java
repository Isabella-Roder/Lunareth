package com.isabella.lunareth.coletaveis.catalogo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Item {
    
    private final float x;
    private final float y;
    private final float tamanho = 32f;
    
    private final Texture textura;
    private final TipoItem tipo;

    private boolean coletado = false;

    public Item(float x, float y, TipoItem tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        this.textura = new Texture(tipo.getCaminhoTextura());
    }

    public void render(SpriteBatch batch) {
        if (!coletado) {
            batch.draw(textura, x, y, tamanho, tamanho);
        }
    }

    public boolean colideCom(float px, float py, float pTamanho) {
        return px < x + tamanho && px + pTamanho > x && py < y + tamanho && py + pTamanho > y;
    }

    public void coletar() {
        coletado = true;
    }

    public boolean isColetado() {
        return coletado;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void dispose() {
        textura.dispose();
    }
}
