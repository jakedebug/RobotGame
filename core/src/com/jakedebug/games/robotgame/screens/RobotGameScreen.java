package com.jakedebug.games.robotgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jakedebug.games.robotgame.assets.Assets;
import com.jakedebug.games.robotgame.levels.Level;
import com.jakedebug.games.robotgame.overlays.Hud;
import com.jakedebug.games.robotgame.utils.ChaseCam;
import com.jakedebug.games.robotgame.utils.Constants;

public class RobotGameScreen extends ScreenAdapter{

    private static final String TAG = RobotGameScreen.class.getName();

    public static SpriteBatch batch;
    public Level level;
    public Hud hud;
    public ChaseCam chaseCam;

    public RobotGameScreen() {

    }

    @Override
    public void show() {
        AssetManager am = new AssetManager();
        Assets.instance.init(am);
        batch = new SpriteBatch();


        chaseCam = new ChaseCam();

        startNewLevel();
    }

    public void startNewLevel(){
        level = new Level();
        hud = new Hud(level);

        chaseCam.camera = level.viewport.getCamera();
        chaseCam.target = level.getPlayer();

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {

        /*
        UPDATE
         */
        level.update(delta);
        chaseCam.update(delta);

        Gdx.gl.glClearColor(
                Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //batch.setProjectionMatrix(level.viewport.getCamera().combined);

        /*
        RENDER
         */
        level.render(batch);

        hud.render(batch,10,5,Gdx.graphics.getFramesPerSecond());
//        Gdx.graphics.setVSync(true);
//        Gdx.graphics.setContinuousRendering(true);
    }

    @Override
    public void resize(int width, int height) {
        hud.viewport.update(width, height, true);
        level.viewport.update(width, height, true);
        chaseCam.camera = level.viewport.getCamera();
    }

    @Override
    public void dispose() {
        batch.dispose();
        hud.dispose();
    }

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
