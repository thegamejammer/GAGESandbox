package uk.ac.qub.eeecs.game.sandboxDemo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetStore;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.util.GraphicsHelper;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.gage.world.LayerViewport;
import uk.ac.qub.eeecs.gage.world.ScreenViewport;

public class SandboxGameScreen extends GameScreen {

    private ScreenViewport mScreenViewport;
    private LayerViewport mLayerViewport;

    private Cat mCat;
    private LinkedList<GameObject> mSnores;
    private double lastSnoreTime;
    private double snorePeriod;
    private double lastSnoreIndex;

    public SandboxGameScreen(Game game) {
        super("SandboxGame", game);
        mLayerViewport = new LayerViewport(240, 160, 240, 160);
        mScreenViewport = new ScreenViewport();
        GraphicsHelper.create3To2AspectRatioScreenViewport(game, mScreenViewport);

        AssetStore assetManager = mGame.getAssetManager();
        assetManager.loadAndAddBitmap("SleepingCat", CatState.CAT_SLEEPING.getBitmapPath());
        assetManager.loadAndAddBitmap("AwakeCat", CatState.CAT_AWAKE.getBitmapPath());
        assetManager.loadAndAddBitmap("Snore", "img/z.png");

        mCat = new Cat(100, 100, assetManager.getBitmap("SleepingCat"), this);

        mSnores = new LinkedList<GameObject>();
        lastSnoreTime = 0;
        snorePeriod = 2.0;
    }

    @Override
    public void update(ElapsedTime elapsedTime) {

        if (mGame.getInput().getTouchEvents().size() > 0) {
            if (mGame.getInput().getTouchEvents().get(0).type == TouchEvent.TOUCH_DOWN) {
                if (mCat.getState() == CatState.CAT_SLEEPING) {
                    if (Math.random() < 0.15) {
                        mCat.wakeUp();
                    }
                }
            }
        }

        if (mCat.getState() == CatState.CAT_SLEEPING) {
            if (elapsedTime.totalTime >= lastSnoreTime + snorePeriod) {
                if (mSnores.size() >= 3) {
                    mSnores.removeFirst();
                }
                mSnores.addLast(new GameObject(mCat.position.x, mCat.position.y,
                        mGame.getAssetManager().getBitmap("Snore"), this));
                lastSnoreTime = elapsedTime.totalTime;
            }
        }
        for (GameObject Snore : mSnores) {
            Snore.position.y += 1.0;
            double random = Math.random();
            random *= 10.0;
            random -= 3.0;
            Snore.position.x += random;
        }
    }

    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D) {
        mCat.draw(elapsedTime, graphics2D, mLayerViewport, mScreenViewport);
        for (GameObject Snore : mSnores) {
            Snore.draw(elapsedTime, graphics2D, mLayerViewport, mScreenViewport);
        }
    }
}
