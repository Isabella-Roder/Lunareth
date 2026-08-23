package com.isabella.lunareth.mundo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mapa {

    private static final int GRAMA = 0;
    private static final int PEDRA = 1;
    private static final int AREIA = 2;
    private static final int TERRA = 3;

    private static final int LARGURA = 64;
    private static final int ALTURA = 48;
    private static final float TAMANHO_TILE = 32f;

    private final int[][] grade = new int[ALTURA][LARGURA];

    private final Texture texturaGrama;
    private final Texture texturaPedra;
    private final Texture texturaAreia;
    private final Texture texturaTerra;

    public Mapa() {
        texturaGrama = new Texture("chao/grama.png");
        texturaPedra = new Texture("chao/pedras_grama.png");
        texturaAreia = new Texture("chao/areia.png");
        texturaTerra = new Texture("chao/terra.png");

        construirMapa();
    }

    private void construirMapa() {
        criarLimites();
        criarCaminhos();
        criarPraia();
        criarClareiraDoBosque();
        criarRuinas();
        criarFormacoesDePedra();
    }

    private void criarLimites() {
        preencherRetangulo(0, 0, LARGURA, 1, PEDRA);
        preencherRetangulo(0, ALTURA - 1, LARGURA, 1, PEDRA);
        preencherRetangulo(0, 0, 1, ALTURA, PEDRA);
        preencherRetangulo(LARGURA - 1, 0, 1, ALTURA, PEDRA);
    }

    private void criarCaminhos() {
        // Caminho principal, partindo da região inicial em direção ao norte.
        preencherRetangulo(6, 3, 3, 31, TERRA);
        preencherRetangulo(7, 31, 24, 3, TERRA);

        // Ramificação que leva à praia.
        preencherRetangulo(28, 20, 3, 14, TERRA);
        preencherRetangulo(28, 18, 21, 3, TERRA);

        // Pequenas irregularidades deixam os cruzamentos menos quadrados.
        definirTile(5, 12, TERRA);
        definirTile(9, 13, TERRA);
        definirTile(27, 32, TERRA);
        definirTile(31, 19, TERRA);
    }

    private void criarPraia() {
        preencherRetangulo(48, 3, 15, 17, AREIA);
        preencherRetangulo(45, 6, 3, 11, AREIA);
        preencherRetangulo(42, 9, 3, 5, AREIA);

        // Pedras espalhadas na areia.
        preencherRetangulo(55, 6, 2, 2, PEDRA);
        definirTile(51, 14, PEDRA);
        definirTile(59, 16, PEDRA);
    }

    private void criarClareiraDoBosque() {
        // Contorno de uma clareira ao noroeste, com entradas ao sul e a leste.
        preencherRetangulo(13, 36, 16, 1, PEDRA);
        preencherRetangulo(13, 43, 16, 1, PEDRA);
        preencherRetangulo(13, 36, 1, 8, PEDRA);
        preencherRetangulo(28, 36, 1, 5, PEDRA);

        preencherRetangulo(19, 36, 3, 1, GRAMA);
        preencherRetangulo(28, 41, 1, 2, GRAMA);

        definirTile(17, 39, PEDRA);
        definirTile(24, 41, PEDRA);
    }

    private void criarRuinas() {
        // Ruína aberta próxima ao centro do mapa.
        preencherRetangulo(35, 27, 12, 1, PEDRA);
        preencherRetangulo(35, 36, 12, 1, PEDRA);
        preencherRetangulo(35, 27, 1, 10, PEDRA);
        preencherRetangulo(46, 27, 1, 10, PEDRA);

        preencherRetangulo(39, 27, 3, 1, GRAMA);
        preencherRetangulo(46, 31, 1, 3, GRAMA);
        preencherRetangulo(38, 30, 2, 2, TERRA);
        preencherRetangulo(42, 33, 2, 2, TERRA);
    }

    private void criarFormacoesDePedra() {
        criarGrupoDePedras(15, 8);
        criarGrupoDePedras(22, 16);
        criarGrupoDePedras(53, 27);
        criarGrupoDePedras(5, 40);

        definirTile(11, 6, PEDRA);
        definirTile(18, 25, PEDRA);
        definirTile(33, 12, PEDRA);
        definirTile(57, 39, PEDRA);
        definirTile(59, 42, PEDRA);
    }

    private void criarGrupoDePedras(int coluna, int linha) {
        definirTile(coluna, linha, PEDRA);
        definirTile(coluna + 1, linha, PEDRA);
        definirTile(coluna, linha + 1, PEDRA);
        definirTile(coluna - 1, linha, PEDRA);
        definirTile(coluna, linha - 1, PEDRA);
    }

    private void preencherRetangulo(
        int colunaInicial,
        int linhaInicial,
        int largura,
        int altura,
        int terreno
    ) {
        for (int linha = linhaInicial; linha < linhaInicial + altura; linha++) {
            for (int coluna = colunaInicial; coluna < colunaInicial + largura; coluna++) {
                definirTile(coluna, linha, terreno);
            }
        }
    }

    private void definirTile(int coluna, int linha, int terreno) {
        if (linha >= 0 && linha < ALTURA && coluna >= 0 && coluna < LARGURA) {
            grade[linha][coluna] = terreno;
        }
    }

    public void render(SpriteBatch batch) {
        for (int linha = 0; linha < ALTURA; linha++) {
            for (int coluna = 0; coluna < LARGURA; coluna++) {
                Texture textura = texturaDoTerreno(grade[linha][coluna]);
                float x = coluna * TAMANHO_TILE;
                float y = linha * TAMANHO_TILE;
                batch.draw(textura, x, y, TAMANHO_TILE, TAMANHO_TILE);
            }
        }
    }

    private Texture texturaDoTerreno(int terreno) {
        return switch (terreno) {
            case PEDRA -> texturaPedra;
            case AREIA -> texturaAreia;
            case TERRA -> texturaTerra;
            default -> texturaGrama;
        };
    }

    public boolean solido(float x, float y) {
        int coluna = (int) Math.floor(x / TAMANHO_TILE);
        int linha = (int) Math.floor(y / TAMANHO_TILE);

        if (linha < 0 || linha >= ALTURA || coluna < 0 || coluna >= LARGURA) {
            return true;
        }

        return grade[linha][coluna] == PEDRA;
    }

    public void dispose() {
        texturaGrama.dispose();
        texturaPedra.dispose();
        texturaAreia.dispose();
        texturaTerra.dispose();
    }
}
