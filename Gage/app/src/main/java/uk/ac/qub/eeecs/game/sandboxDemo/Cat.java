package uk.ac.qub.eeecs.game.sandboxDemo;

import android.graphics.Bitmap;

import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;

public class Cat extends GameObject {

    CatState mCatState;

    public Cat(float x, float y, Bitmap bitmap, GameScreen gameScreen) {
        super(x, y, bitmap, gameScreen);
        mCatState = CatState.CAT_SLEEPING;
    }

    public CatState getState() {
        return mCatState;
    }

}
