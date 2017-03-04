package com.jakedebug.games.robotgame.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jakedebug.games.robotgame.entities.Platform;

public class Level {

    private Viewport viewport;
    Platform platform;

    public Level(Viewport viewport) {
        this.viewport = viewport;
        initDebugLevel();
    }

    private void initDebugLevel(){
        platform = new Platform(60,70,15,15);
    }

    public void update(float delta){

    }

    public void render(SpriteBatch batch, ShapeRenderer renderer){
        platform.render(batch,renderer);
    }
}
