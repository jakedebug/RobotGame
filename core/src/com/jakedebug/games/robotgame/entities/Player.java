package com.jakedebug.games.robotgame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.jakedebug.games.robotgame.assets.Assets;
import com.jakedebug.games.robotgame.enums.Enums;
import com.jakedebug.games.robotgame.levels.Level;
import com.jakedebug.games.robotgame.screens.RobotGameScreen;
import com.jakedebug.games.robotgame.utils.Constants;
import com.jakedebug.games.robotgame.utils.Utils;

public class Player {
    private Vector2 spawnLocation;
    private Vector2 position;
    private Vector2 positionLastFrame;
    private Vector2 velocity;

    private Level currentlevel;

    private Enums.Facing facing;
    private Enums.JumpState jumpState;

    private long jumpStartTime;

    public Player(Vector2 spawnLocation, Level level) {
        this.spawnLocation = spawnLocation;
        position = new Vector2();
        this.positionLastFrame = new Vector2();
        this.velocity = new Vector2(0.0F,0.0F);
        this.currentlevel = level;
        init();
    }

    public void init(){
        respawn();
    }

    public void respawn(){
        position.set(spawnLocation);
        positionLastFrame.set(position);
        velocity.setZero();
        facing = Enums.Facing.RIGHT;
        jumpState = Enums.JumpState.FALLING;
    }

    public void update(float delta){
        positionLastFrame.set(position);
        velocity.y -= 10;
        position.mulAdd(velocity, delta);

        if(position.y < -50){
            respawn();
        }

        if(jumpState != Enums.JumpState.JUMPING){
            jumpState = Enums.JumpState.FALLING;
            for(Platform p : currentlevel.getPlatformArray()){
                if(landedOnPlatform(p)){
                    jumpState = Enums.JumpState.GROUNDED;
                    velocity.y = 0;
                    velocity.x = 0;
                    position.y = p.top;
                }
            }
        }
        checkInput(delta);
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

        if(Gdx.input.isKeyJustPressed(Input.Keys.J)){
            switch (jumpState){
                case GROUNDED:
                    startJump();
                    break;
                case JUMPING:
                    continueJump();
                    break;
                case FALLING:
                    break;
            }
        } else{
            endJump();
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

    public boolean landedOnPlatform(Platform platform){
        boolean leftFootIn = false;
        boolean rightFootIn = false;
        boolean stradle = false;
        if(positionLastFrame.y >= platform.top && position.y < platform.top){
            float leftFoot = position.x;
            float rightFoot = position.x+32;
            leftFootIn = (platform.left < leftFoot && platform.right > leftFoot);
            rightFootIn = (platform.left < rightFoot && platform.right > rightFoot);
            stradle = (platform.left > leftFoot && platform.right < rightFoot);
        }
        return leftFootIn || rightFootIn || stradle;
    }

    private void startJump(){
        jumpState = Enums.JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        continueJump();
    }

    private void continueJump(){
        if(jumpState == Enums.JumpState.JUMPING){
            float jumpDuration = (MathUtils.nanoToSec * (TimeUtils.nanoTime() - jumpStartTime));
            if(jumpDuration < 0.5){
                velocity.y = 100;
            } else {
                endJump();
            }
        }
    }

    private void endJump(){
        if(jumpState == Enums.JumpState.JUMPING){
            jumpState = Enums.JumpState.FALLING;
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
