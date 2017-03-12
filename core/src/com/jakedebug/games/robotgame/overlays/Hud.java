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
    public String hudString;

    public Hud() {
        this.viewport = new ExtendViewport(480,480);
        font = new BitmapFont(Gdx.files.internal("fonts/bmFonts/customFont.fnt"),Gdx.files.internal("fonts/bmFonts/customFont.png"),false);
        font.getData().setScale(0.5F);
        hudString = "DEBUG MODE";
    }

    public void render(SpriteBatch batch, int lives, int ammo, int score){
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        if(Level.debugMode){
            setHudString("DEBUG MODE");
        } else {
            setHudString("NORMAL MODE");
        }

        font.draw(batch, hudString, 25,viewport.getWorldHeight()-20);
        font.draw(batch, "player.position: " + Math.round(Level.getPlayer().getPosition().x) + ", " + Math.round(Level.getPlayer().getPosition().y), 25,viewport.getWorldHeight()-40);


        batch.end();

    }

    public void setHudString(String hudString) {
        this.hudString = hudString;
    }
}
