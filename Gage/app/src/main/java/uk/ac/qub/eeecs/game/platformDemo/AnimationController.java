package uk.ac.qub.eeecs.game.platformDemo;

import java.util.ArrayList;

import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.world.GameObject;

public class AnimationController {

    double mLastUpdate;
    int mCurrentFrame;
    ArrayList<AnimationFrame> mAnimation;
    GameObject mParentGameObject;

    public AnimationController(GameObject gameObject, ElapsedTime elapsedTime, ArrayList<AnimationFrame> animation) {
        mParentGameObject = gameObject;
        mLastUpdate = elapsedTime.totalTime;
        mAnimation = animation;
        mCurrentFrame = 0;
    }

    public void update(ElapsedTime elapsedTime) {
        if (mLastUpdate + mAnimation.get(mCurrentFrame).getDuration() > elapsedTime.totalTime) {
            nextFrame();
            mLastUpdate = elapsedTime.totalTime;
        }
    }

    private void nextFrame() {
        mCurrentFrame++;
        if (mCurrentFrame == mAnimation.size()) {
            mCurrentFrame = 0;
        }
        mParentGameObject.setBitmapResource(mAnimation.get(mCurrentFrame).getResource());
    }
}
