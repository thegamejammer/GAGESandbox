package uk.ac.qub.eeecs.game.sandboxDemo;

public enum CatState {
    CAT_SLEEPING("img/Sleeping Cat.png"),
    CAT_AWAKE("img/Sleeping Cat.png");

    private String mBitmapPath;

    CatState(String bitmapPath) {
        this.mBitmapPath = bitmapPath;
    }

    public String getBitmapPath() {
        return this.mBitmapPath;
    }
}
