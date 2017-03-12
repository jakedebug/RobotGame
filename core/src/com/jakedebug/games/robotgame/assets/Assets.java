package com.jakedebug.games.robotgame.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.utils.Disposable;
import com.jakedebug.games.robotgame.utils.Constants;

public class Assets implements Disposable, AssetErrorListener{

    public static final String TAG = Assets.class.getName();

    public static final Assets instance = new Assets();

    private AssetManager assetManager;

    public PlatformAssets platformAssets;
    public DebugPlayer debugPlayer;
    public AudioAssets audioAssets;

    //TODO: add font loader

    private Assets(){

    }

    public void init(AssetManager am){
        assetManager = am;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        //TODO: potential switch to a gradle build task
        try{
            TexturePacker.process("rawAssets","images","packedAtlas");
        } catch (Exception e){
            Gdx.app.error(TAG, "Error packing assets" + e.getMessage());
        }

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);

        platformAssets = new PlatformAssets(atlas);
        debugPlayer = new DebugPlayer(atlas);
        audioAssets = new AudioAssets();
    }

    public class PlatformAssets{
        public final NinePatch ninePatchPlatform;

        public PlatformAssets(TextureAtlas atlas){
            ninePatchPlatform = new NinePatch(
                    atlas.findRegion(Constants.BASIC_PLATFORM),
                    Constants.BASIC_PLATFORM_NINEPATCH_OFFSET,
                    Constants.BASIC_PLATFORM_NINEPATCH_OFFSET,
                    Constants.BASIC_PLATFORM_NINEPATCH_OFFSET,
                    Constants.BASIC_PLATFORM_NINEPATCH_OFFSET);
        }
    }

    public class DebugPlayer{
        //TODO: add loadPlayer method with String parameter
        public final TextureAtlas.AtlasRegion debugPlayerRegionWarlord_R;
        public final TextureAtlas.AtlasRegion debugPlayerRegionWarlord_L;

        public final TextureAtlas.AtlasRegion debugPlayerRegionSkeleton;
        public final TextureAtlas.AtlasRegion debugPlayerRegionGoblin;
        public final TextureAtlas.AtlasRegion debugPlayerRegionEye;
        public final TextureAtlas.AtlasRegion debugPlayerRegionBlob;
        public final TextureAtlas.AtlasRegion debugPlayerRegionTurtle;

        public DebugPlayer(TextureAtlas atlas) {
            debugPlayerRegionWarlord_R = atlas.findRegion(Constants.WARLORD_R);
            debugPlayerRegionWarlord_L = atlas.findRegion(Constants.WARLORD_L);
            debugPlayerRegionSkeleton = atlas.findRegion(Constants.SKELETON);
            debugPlayerRegionGoblin = atlas.findRegion(Constants.GOBLIN);
            debugPlayerRegionEye = atlas.findRegion(Constants.EYE);
            debugPlayerRegionTurtle = atlas.findRegion(Constants.TURTLE);
            debugPlayerRegionBlob = atlas.findRegion(Constants.BLOB);
        }
    }

    public class AudioAssets{
        //TODO: add loadMusic method with String parameter
        public final Sound powerUp;
        public final Sound gameOver;
        public final Music music;

        public AudioAssets(){
            powerUp = Gdx.audio.newSound(Gdx.files.internal(Constants.SOUNDS_POWERUP));
            gameOver = Gdx.audio.newSound(Gdx.files.internal(Constants.SOUNDS_GAMEOVER));
            music = Gdx.audio.newMusic(Gdx.files.internal(Constants.MUSIC_BACKGROUND));
        }
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Error loading asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        audioAssets.music.dispose();
        audioAssets.powerUp.dispose();
        audioAssets.gameOver.dispose();
    }
}
