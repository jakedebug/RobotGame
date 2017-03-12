package com.jakedebug.games.robotgame.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.jakedebug.games.robotgame.assets.Assets;
import com.jakedebug.games.robotgame.screens.RobotGameScreen;

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

    public void render(SpriteBatch batch){
        //offset to account for border
        Assets.instance.platformAssets.ninePatchPlatform.draw(batch,left-1,bottom-1,width+2,height+2);
    }

    public Rectangle getBounds(){
        return new Rectangle(left, bottom, width, height);
    }

    public void drawBounds(){
        RobotGameScreen.getRenderer().rect(getBounds().x, getBounds().y, getBounds().width,getBounds().height);
    }

}
