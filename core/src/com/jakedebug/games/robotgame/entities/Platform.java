package com.jakedebug.games.robotgame.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jakedebug.games.robotgame.Assets.Assets;

public class Platform {

    public static final String TAG = Platform.class.getName();

    public float top;
    public float bottom;
    public float left;
    public float right;
    public float width;
    public float height;

    public Platform(float left, float top, float width, float height) {
        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;
        this.width = this.right - this.left;
        this.height = this.top - this.bottom;
    }

    public void render(SpriteBatch batch, ShapeRenderer renderer){
//        //Debug Platform
//        renderer.setColor(Color.BLUE);
//        renderer.set(ShapeRenderer.ShapeType.Filled);
//        renderer.rect(
//                left,
//                bottom,
//                width,
//                height);

        //TODO: Create atlas and ninepatch platform
        Assets.instance.platformAssets.ninePatchPlatform.draw(batch,left,bottom,width,height);
    }

}
