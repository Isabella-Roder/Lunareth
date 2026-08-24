package com.isabella.lunareth;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.isabella.lunareth.input.ControlesInput;
import com.isabella.lunareth.mundo.Mundo;
import com.isabella.lunareth.player.Inventario;
import com.isabella.lunareth.player.Player;
import com.isabella.lunareth.ui.Hud;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Lunareth extends ApplicationAdapter {

    private OrthographicCamera camera;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;

    private Player player;
    private Inventario inventario;
    private Mundo mundo;

    private ControlesInput controles;
    private Hud hud;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 720);

        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();

        player = new Player(100, 100);
        inventario = new Inventario();
        mundo = new Mundo();

        controles = new ControlesInput();
        hud = new Hud();
    }

    @Override
    public void render() {
        camera.position.set(player.getX() + 16, player.getY() + 16, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        float delta = Gdx.graphics.getDeltaTime();
        player.update(delta, mundo.getMapa());
        mundo.atualizar(delta, player, inventario);
        controles.processar(player, inventario, mundo);

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.begin();
        mundo.render(batch);
        player.render(batch);
        batch.end();

        hud.render(batch, shapeRenderer, player, inventario, mundo, controles.getFalaAtual());
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        mundo.dispose();
        hud.dispose();
    }
}
