package com.jakedebug.games.robotgame.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jakedebug.games.robotgame.entities.Platform;
import com.jakedebug.games.robotgame.entities.Player;

public class Level {

    private Viewport viewport;
    private Array<Platform> platformArray;
    private Player player;

    public Level(Viewport viewport) {
        this.viewport = viewport;
        this.platformArray = new Array<Platform>();
        initDebugLevel();
    }

    private void initDebugLevel(){
        platformArray.add(new Platform(30,70,150,35));
        player = new Player(new Vector2(100,120), this);


        //Assets.instance.audioAssets.powerUp.loop();
        //Assets.instance.audioAssets.gameOver.loop();
        //Assets.instance.audioAssets.music.play();
        //Assets.instance.audioAssets.music.isLooping();
    }

    public void update(float delta){
        player.update(delta);
    }

    public void render(SpriteBatch batch, ShapeRenderer renderer){
        for(Platform p : platformArray){
            p.render(batch,renderer);
        }

        player.render(batch,renderer);

        //TODO: Add player class, position, velocity, gravity
//        Utils.drawTextureRegion(batch, Assets.instance.debugPlayer.debugPlayerRegionSkeleton,50,100, 1.0F);
//        Utils.drawTextureRegion(batch, Assets.instance.debugPlayer.debugPlayerRegionWarlord,100,115, 1.0F);
//        Utils.drawTextureRegion(batch, Assets.instance.debugPlayer.debugPlayerRegionGoblin,85,115, 1.0F);
//        Utils.drawTextureRegion(batch, Assets.instance.debugPlayer.debugPlayerRegionBlob,160,115, 1.0F);
//        Utils.drawTextureRegion(batch, Assets.instance.debugPlayer.debugPlayerRegionEye,30,90, 1.0F);
//        Utils.drawTextureRegion(batch, Assets.instance.debugPlayer.debugPlayerRegionTurtle,125,75, 1.0F);
    }

    public Array<Platform> getPlatformArray() {
        return platformArray;
    }
}
