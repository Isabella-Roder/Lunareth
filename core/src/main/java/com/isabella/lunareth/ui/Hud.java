package com.isabella.lunareth.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.isabella.lunareth.mundo.Mundo;
import com.isabella.lunareth.npc.Falas;
import com.isabella.lunareth.player.Inventario;
import com.isabella.lunareth.player.ItemInventario;
import com.isabella.lunareth.player.Player;

public class Hud {

    private final OrthographicCamera cameraUI;
    private final BitmapFont fonte;

    public Hud() {
        cameraUI = new OrthographicCamera();
        cameraUI.setToOrtho(false, 1080, 720);
        fonte = new BitmapFont();
    }

    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer, Player player, Inventario inventario, Mundo mundo, Falas falaAtual, boolean inventarioAberto) {
        desenharNoite(shapeRenderer, mundo);
        desenharBarrasDeAtributos(shapeRenderer, player);
        desenharTexto(batch, player, inventario, mundo, falaAtual, inventarioAberto);
    }

    private void desenharNoite(ShapeRenderer shapeRenderer, Mundo mundo) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.setProjectionMatrix(cameraUI.combined);
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, mundo.getRelogio().getOpacidadeNoite());
        shapeRenderer.rect(0, 0, 1080, 720);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void desenharBarrasDeAtributos(ShapeRenderer shapeRenderer, Player player) {
        shapeRenderer.setProjectionMatrix(cameraUI.combined);
        shapeRenderer.begin(ShapeType.Filled);

        float yBarra = 700;
        desenharBarra(shapeRenderer, 10, yBarra, 150, 12, player.getAtributos().getVida(), 100, Color.RED);
        yBarra -= 18;
        desenharBarra(shapeRenderer, 10, yBarra, 150, 12, player.getAtributos().getFome(), 100, Color.ORANGE);
        yBarra -= 18;
        desenharBarra(shapeRenderer, 10, yBarra, 150, 12, player.getAtributos().getSede(), 100, Color.BLUE);
        yBarra -= 18;
        desenharBarra(shapeRenderer, 10, yBarra, 150, 12, player.getAtributos().getEnergia(), 100, Color.YELLOW);

        shapeRenderer.end();
    }

    private void desenharBarra(ShapeRenderer shapeRenderer, float x, float y, float largura, float altura, float valorAtual, float valorMaximo, Color cor) {
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x, y, largura, altura);

        float larguraPreenchida = largura * (valorAtual / valorMaximo);
        shapeRenderer.setColor(cor);
        shapeRenderer.rect(x, y, larguraPreenchida, altura);
    }

    private void desenharTexto(SpriteBatch batch, Player player, Inventario inventario, Mundo mundo, Falas falaAtual, boolean inventarioAberto) {
        batch.setProjectionMatrix(cameraUI.combined);
        batch.begin();

        if (inventarioAberto) {
            
            float y = 610;
                for (ItemInventario itemInv : inventario.getItens()) {
                    batch.draw(itemInv.getTipo().getTextura(), 10, y - 16, 16, 16);
                    fonte.draw(batch, itemInv.getTipo().getNome() + " x" + itemInv.getQuantidade(), 30, y);
                    y -= 20;
                }
        }

        fonte.draw(batch, "Dia " + mundo.getRelogio().getDiaAtual() + "-" + (int) mundo.getRelogio().getHoraAtual() + "h", 10, 30);

        if (falaAtual != null) {
            fonte.draw(batch, falaAtual.getTexto(), 400, 400);

            if (falaAtual.temEscolha()) {
                fonte.draw(batch, "1) Sim   2) Não", 400, 370);
            }
        }

        batch.end();
    }

    public void dispose() {
        fonte.dispose();
    }
}
