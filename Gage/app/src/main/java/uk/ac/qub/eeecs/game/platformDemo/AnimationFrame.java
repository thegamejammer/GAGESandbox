package uk.ac.qub.eeecs.game.platformDemo;

public class AnimationFrame {
    private String mBitmapResource;
    private float mDuration;

    public AnimationFrame(String bitmapResource, float duration) {
        mBitmapResource = bitmapResource;
        mDuration = duration;
    }

    public String getResource() {
        return mBitmapResource;
    }

    public float getDuration() {
        return mDuration;
    }
}
