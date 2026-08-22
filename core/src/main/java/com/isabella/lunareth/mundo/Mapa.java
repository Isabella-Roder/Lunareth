package com.isabella.lunareth.mundo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mapa {
    
    private final int[][] grade;
    private final float tamanhoTile = 32f;

    private final Texture texturaGrama;
    private final Texture texturaPedra;

    public Mapa() {
        texturaGrama = new Texture("chao/grama_com_flores.png");
        texturaPedra = new Texture("chao/pedra.png");

        grade = new int[][] {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0}
        };
    }

    public void render(SpriteBatch batch) {
        for (int linha = 0; linha < grade.length; linha++) {
            for (int coluna = 0; coluna < grade[linha].length; coluna++) {
                Texture textura = grade[linha][coluna] == 1 ? texturaPedra : texturaGrama;
                float x = coluna * tamanhoTile;
                float y = linha * tamanhoTile;
                batch.draw(textura, x, y, tamanhoTile, tamanhoTile);
            }
        }
    }

    public void dispose() {
        texturaGrama.dispose();
        texturaPedra.dispose();
    }
}
