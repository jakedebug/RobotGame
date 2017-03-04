package com.jakedebug.games.robotgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.jakedebug.games.robotgame.utils.Constants;
import com.jakedebug.games.robotgame.levels.Level;

public class RobotGameScreen extends ScreenAdapter{

    private static final String TAG = RobotGameScreen.class.getName();

    public static ExtendViewport viewport;
    public static SpriteBatch batch;
    public static ShapeRenderer renderer; //for debug
    public Level level;

    public RobotGameScreen() {

    }

    @Override
    //Screen becomes current
    public void show() {
        //create new viewport
        viewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        level = new Level(viewport);
    }

    @Override
    public void render(float delta) {
        viewport.apply();

        /*
        UPDATE
         */
        level.update(delta);

        //Clear screen, set background color
        Gdx.gl.glClearColor(
                Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setAutoShapeType(true);

        batch.begin();
        renderer.begin();

        /*
        RENDER
         */
        level.render(batch,renderer);

        batch.end();
        renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    // Currently unused lifecycle methods

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
