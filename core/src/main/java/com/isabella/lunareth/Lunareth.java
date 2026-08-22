package com.isabella.lunareth;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;
import com.isabella.lunareth.mundo.Mapa;
import com.isabella.lunareth.player.Player;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Lunareth extends ApplicationAdapter {

    private OrthographicCamera camera;
    
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;


    private Player player;
    private Mapa mapa;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 720);

        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();

        mapa = new Mapa();
        player = new Player(100, 100);
    }

    @Override
    public void render() {
        camera.position.set(player.getX() + 16, player.getY() + 16, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        float delta = Gdx.graphics.getDeltaTime();
        player.update(delta);

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.begin();
        mapa.render(batch);
        batch.end();

        shapeRenderer.begin(ShapeType.Filled);
        player.render(shapeRenderer);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        mapa.dispose();
    }
}
