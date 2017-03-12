package com.jakedebug.games.robotgame.utils;

import com.badlogic.gdx.graphics.Camera;
import com.jakedebug.games.robotgame.entities.Player;

public class ChaseCam {
    public Camera camera;
    public Player target;
    public Boolean following;

    public ChaseCam(){
        following = true;
    }

    public void update(){
        if(following){
            camera.position.x = target.getPosition().x;
            camera.position.y = target.getPosition().y;
        }
    }
}
