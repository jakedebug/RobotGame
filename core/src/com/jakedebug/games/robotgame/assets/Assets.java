package com.jakedebug.games.robotgame.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
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

    private Assets(){

    }

    public void init(AssetManager am){
        assetManager = am;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();
        //TODO: potential switch to a gradle build task
        try{
            //android/assets/..
            TexturePacker.process("rawAssets","images","packedAtlas");
        } catch (Exception e){
            Gdx.app.error(TAG, "Error packing assets" + e.getMessage());
        }

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);

        platformAssets = new PlatformAssets(atlas);
        debugPlayer = new DebugPlayer(atlas);
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
        public final TextureAtlas.AtlasRegion debugPlayerRegion;

        public DebugPlayer(TextureAtlas atlas) {
            debugPlayerRegion = atlas.findRegion(Constants.WARLORD);
        }
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Error loading asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {

    }
}
