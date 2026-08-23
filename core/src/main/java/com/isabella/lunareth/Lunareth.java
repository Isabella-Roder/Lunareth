package com.isabella.lunareth;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.isabella.lunareth.coletaveis.Item;
import com.isabella.lunareth.coletaveis.Itens;
import com.isabella.lunareth.mundo.Mapa;
import com.isabella.lunareth.player.Inventario;
import com.isabella.lunareth.player.Player;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Lunareth extends ApplicationAdapter {

    private OrthographicCamera camera;

    private OrthographicCamera cameraUI;
    private BitmapFont fonte;
    
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;


    private Player player;
    private Mapa mapa;
    private Item item;
    private Itens itens;
    private Inventario inventario;

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

        item = new Item(150, 150, itens.FOICE_MISTICA);
    }

    @Override
    public void render() {
        camera.position.set(player.getX() + 16, player.getY() + 16, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        float delta = Gdx.graphics.getDeltaTime();
        player.update(delta, mapa);

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        if (!item.isColetado() && item.colideCom(player.getX(), player.getY(), 32f)) {
            item.coletar();
            inventario.adicionar(item.getTipo());
        }

        batch.begin();
        mapa.render(batch);
        player.render(batch);
        item.render(batch);
        batch.end();

        batch.setProjectionMatrix(cameraUI.combined);
        batch.begin();
        fonte.draw(batch, "Itens: " + inventario.getItens().size(), 10, 700);
        batch.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        mapa.dispose();
        item.dispose();
    }
}
