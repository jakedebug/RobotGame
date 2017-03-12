package com.jakedebug.games.robotgame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jakedebug.games.robotgame.assets.Assets;
import com.jakedebug.games.robotgame.enums.Enums;
import com.jakedebug.games.robotgame.levels.Level;
import com.jakedebug.games.robotgame.screens.RobotGameScreen;
import com.jakedebug.games.robotgame.utils.Constants;
import com.jakedebug.games.robotgame.utils.Utils;

public class Player {
    private Vector2 spawnLocation;
    private Vector2 position;
    private Vector2 velocity;

    private Level currentlevel;

    private Enums.Facing facing;
    private Enums.JumpState jumpState;

    public Player(Vector2 spawnLocation, Level level) {
        this.spawnLocation = spawnLocation;
        position = new Vector2();
        this.velocity = new Vector2(0.0F,0.0F);
        this.currentlevel = level;
        init();
    }

    public void init(){
        respawn();
    }

    public void respawn(){
        position.set(spawnLocation);
        velocity.setZero();
        facing = Enums.Facing.RIGHT;
        jumpState = Enums.JumpState.FALLING;
    }

    public void update(float delta){
        velocity.y -= 10;
        position.mulAdd(velocity, delta);

        checkInput(delta);
        checkCollisions();
    }

    private void checkInput(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            facing = Enums.Facing.LEFT;
            position.x -= delta * Constants.PLAYER_MOVE_SPEED;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            facing = Enums.Facing.RIGHT;
            position.x += delta * Constants.PLAYER_MOVE_SPEED;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
            respawn();
        }
    }

    public void render(SpriteBatch batch){

        TextureRegion region;

        if(facing == Enums.Facing.RIGHT){
            region = Assets.instance.debugPlayer.debugPlayerRegionWarlord_R;
        } else {
            region = Assets.instance.debugPlayer.debugPlayerRegionWarlord_L;
        }

        //draw offset for cape to trail below platform
        Utils.drawTextureRegion(batch, region ,position.x,position.y-1, 1.0F);
    }

    public void checkCollisions(){
        for(Platform platform : currentlevel.getPlatformArray()){
            if(position.y < platform.top){
                position.y = platform.top;
                velocity.y = 0;
                jumpState = Enums.JumpState.GROUNDED;
            }
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void drawBounds(){
        RobotGameScreen.getRenderer().rect(getBounds().x, getBounds().y, getBounds().width,getBounds().height);
    }

    public Rectangle getBounds(){
        return new Rectangle(position.x, position.y, 32,33);
    }

    public Enums.Facing getFacing() {
        return facing;
    }

    public Enums.JumpState getJumpState() {
        return jumpState;
    }
}
