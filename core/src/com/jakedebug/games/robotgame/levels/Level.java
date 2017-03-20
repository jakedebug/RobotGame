package com.jakedebug.games.robotgame.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jakedebug.games.robotgame.assets.Assets;
import com.jakedebug.games.robotgame.entities.Platform;
import com.jakedebug.games.robotgame.entities.Player;
import com.jakedebug.games.robotgame.utils.Constants;

public class Level {

    private static final String TAG = Level.class.getName();

    public Viewport viewport;
    private Array<Platform> platformArray;
    private Player player;

    private Music music;

    public static boolean debugMode = false;

    public Level() {
        this.viewport = new ExtendViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        this.platformArray = new Array<Platform>();
        this.music = Assets.instance.audioAssets.music;
        initDebugLevel();
    }

    private void initDebugLevel(){
        platformArray.add(new Platform(30,70,150,35));
        platformArray.add(new Platform(180, 30, 150, 20));
        player = new Player(new Vector2(100,120), this);

        music.play();
        music.isLooping();
    }

    public void update(float delta){
        player.update(delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.X)){
            debugMode = !debugMode;
        }

        //toggle music
        if(Gdx.input.isKeyJustPressed((Input.Keys.M))){
            if(music.isPlaying()){
                music.pause();
            } else {
                music.play();
            }
        }
    }

    public void render(SpriteBatch batch) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        for (Platform p : platformArray) {
            p.render(batch);
        }

        player.render(batch);

        batch.end();
    }

    public Array<Platform> getPlatformArray(){
        return platformArray;
    }

    public Player getPlayer() {
        return player;
    }
}