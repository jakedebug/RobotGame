package com.jakedebug.games.robotgame.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jakedebug.games.robotgame.levels.Level;

public class Hud {

    private static final String TAG = Hud.class.getName();

    public final Viewport viewport;
    final BitmapFont font;
    public Level currentLevel;

    public Hud(Level currentLevel) {
        this.viewport = new ExtendViewport(600,600);
        font = new BitmapFont(Gdx.files.internal("fonts/bmFonts/customFont.fnt"),Gdx.files.internal("fonts/bmFonts/customFont.png"),false);
        font.getData().setScale(0.5F);
        this.currentLevel = currentLevel;
    }

    public void render(SpriteBatch batch, int lives, int ammo, int score){
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        if(Level.debugMode){
            font.draw(batch, "DEBUG MODE", 25,viewport.getWorldHeight()-20);
            font.draw(batch,
                    "Player.position: "
                            + Math.round(currentLevel.getPlayer().getPosition().x)
                            + ", "
                            + Math.round(currentLevel.getPlayer().getPosition().y),
                    25,
                    viewport.getWorldHeight()-45);
            font.draw(batch,
                    "Player.velocity: "
                            + Math.round(currentLevel.getPlayer().getVelocity().x)
                            + ", "
                            + Math.round(currentLevel.getPlayer().getVelocity().y),
                    25,
                    viewport.getWorldHeight()-70);
            font.draw(batch,
                    "Facing: "
                            + currentLevel.getPlayer().getFacing(),
                    25,
                    viewport.getWorldHeight()-95);
            font.draw(batch,
                    "JumpState: "
                            + currentLevel.getPlayer().getJumpState(),
                    25,
                    viewport.getWorldHeight()-120);
        } else {
            font.draw(batch, "FPS: " + score + "\nLIVES: " + lives + "\nAMMO:  " + ammo, 25,viewport.getWorldHeight()-20);

        }
        batch.end();

    }

    public void dispose(){
        font.dispose();
    }
}
