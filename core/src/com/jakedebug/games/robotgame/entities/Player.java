package com.jakedebug.games.robotgame.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jakedebug.games.robotgame.assets.Assets;
import com.jakedebug.games.robotgame.levels.Level;
import com.jakedebug.games.robotgame.utils.Utils;

public class Player {

    private Vector2 position;
    private Vector2 velocity;
    private Level currentlevel;

    public Player(Vector2 position, Level level) {
        this.position = position;
        this.velocity = new Vector2(0.0F,0.0F);
        this.currentlevel = level;
    }

    public void update(float delta){
        velocity.y += 150 *delta;
        position.y -= velocity.y * delta;
        checkCollisions();

    }

    public void render(SpriteBatch batch, ShapeRenderer renderer){
        Utils.drawTextureRegion(batch, Assets.instance.debugPlayer.debugPlayerRegionWarlord,position.x,position.y, 1.0F);
        //renderer.rect(getBounds().x,getBounds().y,getBounds().width,getBounds().height);
    }

    public void checkCollisions(){
        for(Platform platform : currentlevel.getPlatformArray()){
            if(position.y < platform.top){
                position.y = platform.top-1;
            }
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Rectangle getBounds(){
        return new Rectangle(position.x, position.y, 32, 33);
    }
}
