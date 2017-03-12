package com.jakedebug.games.robotgame.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jakedebug.games.robotgame.entities.Platform;
import com.jakedebug.games.robotgame.entities.Player;
import com.jakedebug.games.robotgame.utils.Constants;

public class Level {

    private static final String TAG = Level.class.getName();

    public Viewport viewport;
    private Array<Platform> platformArray;
    private Player player;

    public static boolean debugMode = false;

    public Level() {
        this.viewport = new ExtendViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        this.platformArray = new Array<Platform>();
        initDebugLevel();
    }

    private void initDebugLevel(){
        platformArray.add(new Platform(30,70,150,35));
        platformArray.add(new Platform(180, 30, 150, 20));
        player = new Player(new Vector2(100,120), this);

        //Assets.instance.audioAssets.powerUp.loop();
        //Assets.instance.audioAssets.gameOver.loop();
        //Assets.instance.audioAssets.music.play();
        //Assets.instance.audioAssets.music.isLooping();
    }

    public void update(float delta){
        player.update(delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.X)){
            debugMode = !debugMode;
        }
    }

    public void render(SpriteBatch batch, ShapeRenderer renderer) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        renderer.begin();

        for (Platform p : platformArray) {
            p.render(batch);
        }

        player.render(batch);

        if (debugMode) {
            drawDebugModeCollisionBounds();
        }

        batch.end();
        renderer.end();

    }

    public Array<Platform> getPlatformArray(){
        return platformArray;
    }

    public void drawDebugModeCollisionBounds() {

        player.drawBounds();

        for (Platform p : platformArray) {
            p.drawBounds();
        }
    }

    public Player getPlayer() {
        return player;
    }
}