package com.jakedebug.games.robotgame.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.jakedebug.games.robotgame.utils.Constants;

public class Assets implements Disposable, AssetErrorListener{

    public static final String TAG = Assets.class.getName();

    public static final Assets instance = new Assets();

    private AssetManager assetManager;

    public PlatformAssets platformAssets;

    private Assets(){

    }

    public void init(AssetManager am){
        assetManager = am;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);

        platformAssets = new PlatformAssets(atlas);
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

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Error loading asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {

    }
}
