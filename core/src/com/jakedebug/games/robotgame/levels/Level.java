package com.jakedebug.games.robotgame.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jakedebug.games.robotgame.entities.Platform;

public class Level {

    private Viewport viewport;
    private Array<Platform> platformArray;

    public Level(Viewport viewport) {
        this.viewport = viewport;
        this.platformArray = new Array<Platform>();
        initDebugLevel();
    }

    private void initDebugLevel(){
        platformArray.add(new Platform(30,70,150,20));
    }

    public void update(float delta){}

    public void render(SpriteBatch batch, ShapeRenderer renderer){
        for(Platform p : platformArray){
            p.render(batch,renderer);
        }
    }
}
