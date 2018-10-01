package uk.ac.qub.eeecs.game.sandboxDemo;

import android.graphics.Bitmap;

import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;

public class Cat extends GameObject {

    CatState mCatState;

    int mEnergy = 0;

    final int MAX_ENERGY = 1000;

    public Cat(float x, float y, Bitmap bitmap, GameScreen gameScreen) {
        super(x, y, bitmap, gameScreen);
        mCatState = CatState.CAT_SLEEPING;
    }

    @Override
    public void update(ElapsedTime elapsedTime) {
        if (mCatState == CatState.CAT_SLEEPING) {
            mEnergy += 1;
            if (mEnergy >= MAX_ENERGY) {
                mEnergy = MAX_ENERGY;
            }
        } else {
            mEnergy -= 1;
        }

        if (mEnergy <= 0) {
            fallAsleep();
        }
    }

    public CatState getState() {
        return mCatState;
    }

    public void nudgeCat() {
        if (mCatState == CatState.CAT_SLEEPING) {
            if (Math.random() < 0.15) {
                wakeUp();
            }
        }
    }

    public void wakeUp() {
        mCatState = CatState.CAT_AWAKE;
        Bitmap awakeBitmap = mGameScreen.getGame().getAssetManager().getBitmap("AwakeCat");
        if (awakeBitmap != null) {
            mBitmap = awakeBitmap;
        }
    }

    public void fallAsleep() {
        mCatState = CatState.CAT_SLEEPING;
        Bitmap sleepBitmap = mGameScreen.getGame().getAssetManager().getBitmap("SleepingCat");
        if (sleepBitmap != null) {
            mBitmap = sleepBitmap;
        }
    }

}
