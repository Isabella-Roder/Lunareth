package com.isabella.lunareth;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;
import com.isabella.lunareth.coletaveis.catalogo.Item;
import com.isabella.lunareth.coletaveis.catalogo.Itens;
import com.isabella.lunareth.coletaveis.comida.Comida;
import com.isabella.lunareth.mundo.Mapa;
import com.isabella.lunareth.npc.Npc;
import com.isabella.lunareth.npc.NpcPatrulha;
import com.isabella.lunareth.npc.Npcs;
import com.isabella.lunareth.player.Inventario;
import com.isabella.lunareth.player.ItemInventario;
import com.isabella.lunareth.player.Player;
import com.isabella.lunareth.save.SaveData;
import com.isabella.lunareth.save.SaveManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Lunareth extends ApplicationAdapter {

    private OrthographicCamera camera;

    private OrthographicCamera cameraUI;
    private BitmapFont fonte;
    
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;


    private Player player;
    private Mapa mapa;
    private List<Item> itensNoMundo;
    private Inventario inventario;

    private List<Npc> npcs;
    private String dialogoAtivo = null;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 720);

        cameraUI = new OrthographicCamera();
        cameraUI.setToOrtho(false, 1080, 720);
        fonte = new BitmapFont();

        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();

        mapa = new Mapa();

        player = new Player(100, 100);
        inventario = new Inventario();

        npcs = new ArrayList<>();
        npcs.add(Npcs.ISABELLA);

        itensNoMundo = new ArrayList<>();

        //armas
        itensNoMundo.add( new Item(150, 150, Itens.FOICE_MISTICA));

        //comidas
        itensNoMundo.add(new Item(250, 250, Itens.MACA));
        itensNoMundo.add(new Item(250, 150, Itens.MACA_VERDE));
        itensNoMundo.add(new Item(300, 250, Itens.MORANGO));
    }

    @Override
    public void render() {
        camera.position.set(player.getX() + 16, player.getY() + 16, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        float delta = Gdx.graphics.getDeltaTime();
        player.update(delta, mapa);

        for (Npc npc : npcs) {
            npc.atualizar(delta);
        }

        if (Gdx.input.isKeyJustPressed(Keys.F5)) {
            SaveData dados = new SaveData();

            dados.playerX = player.getX();
            dados.playerY = player.getY();

            for (var itemInv : inventario.getItens()) {
                dados.itensColetados.add(itemInv.getTipo().getNome());
            }

            SaveManager.salvar(dados);
        }

        if (Gdx.input.isKeyJustPressed(Keys.F9)) {
            SaveData dados = SaveManager.carregar();

            if (dados != null) {
                player.setPosicao(dados.playerX, dados.playerY);

                inventario.getItens().clear();
                
                for (Item itemMundo : itensNoMundo) {

                    if (dados.itensColetados.contains(itemMundo.getTipo().getNome())) {
                        itemMundo.coletar();
                        inventario.adicionar(itemMundo.getTipo());
                    }
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Keys.E)) {
            
            for (ItemInventario itemInv : inventario.getItens()) {

                if (itemInv.getTipo() instanceof Comida comida) {
                    player.getAtributos().aplicarEfeito(comida.comer());
                    itemInv.removerUnidade();
                    break;
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Keys.F)) {
            dialogoAtivo = null;

            for (Npc npc : npcs) {
                if (npc.pertoDoPlayer(player.getX(), player.getY(), 32f)) {
                    dialogoAtivo = npc.getDialogo();
                    break;
                }
            }

        }

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        for (Item itemMundo : itensNoMundo) {

            if (!itemMundo.isColetado() && itemMundo.colideCom(player.getX(), player.getY(), 32f)) {
                itemMundo.coletar();
                inventario.adicionar(itemMundo.getTipo());
            }
        }

        batch.begin();
        mapa.render(batch);
        player.render(batch);
        
        for (Npc npc : npcs) {
            npc.render(batch);
        }

        for (Item itemMundo : itensNoMundo) {
            itemMundo.render(batch);
        }

        batch.end();

        shapeRenderer.setProjectionMatrix(cameraUI.combined);
        shapeRenderer.begin(ShapeType.Filled);

        float yBarra = 700;
        desenharBarra(10, yBarra, 150, 12, player.getAtributos().getVida(), 100, Color.RED);
        yBarra -= 18;
        desenharBarra(10, yBarra, 150, 12, player.getAtributos().getFome(), 100, Color.ORANGE);
        yBarra -= 18;
        desenharBarra(10, yBarra, 150, 12, player.getAtributos().getSede(), 100, Color.BLUE);
        yBarra -= 18;
        desenharBarra(10, yBarra, 150, 12, player.getAtributos().getEnergia(), 100, Color.YELLOW);

        shapeRenderer.end();

        batch.setProjectionMatrix(cameraUI.combined);
        batch.begin();
        
        float y = 610;
        for (ItemInventario itemInv : inventario.getItens()) {
            batch.draw(itemInv.getTipo().getTextura(), 10, y - 16, 16, 16);
            fonte.draw(batch, itemInv.getTipo().getNome() + " x" + itemInv.getQuantidade(), 30, y);
            y -= 20;
        }

        if (dialogoAtivo != null) {
            fonte.draw(batch, dialogoAtivo, 400, 400);
        }

        batch.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        mapa.dispose();
        
        for (Item itemMundo : itensNoMundo) {
            itemMundo.dispose();
        }

        for (Npc npc : npcs) {
            npc.dispose();
        }
    }

    private void desenharBarra(float x, float y, float largura, float altura, float valorAtual, float valorMaximo, Color cor) {

        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x, y, largura, altura);

        float larguraPreenchida = largura * (valorAtual / valorMaximo);
        shapeRenderer.setColor(cor);
        shapeRenderer.rect(x, y, larguraPreenchida, altura);

    }
}
