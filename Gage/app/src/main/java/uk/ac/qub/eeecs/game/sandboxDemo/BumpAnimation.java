package uk.ac.qub.eeecs.game.sandboxDemo;

import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.gage.world.LayerViewport;

public class BumpAnimation {

    LayerViewport mLayerViewport;
    SandboxGameScreen mGameScreen;
    double originalX;
    double originalY;
    double timeCreated;
    final double bumpDuration = 0.1;

    public BumpAnimation(ElapsedTime elapsedTime, LayerViewport layerViewport, SandboxGameScreen gameScreen) {
        mGameScreen = gameScreen;
        mLayerViewport = layerViewport;
        originalX = layerViewport.x;
        originalY = layerViewport.y;
        timeCreated = elapsedTime.totalTime;
    }

    public void Update(ElapsedTime elapsedTime) {
        mLayerViewport.x = (float)(originalX + Math.random()*4.0);
        mLayerViewport.y = (float)(originalY + Math.random()*4.0);
        if (elapsedTime.totalTime > timeCreated + bumpDuration) {
            mLayerViewport.x = (float)originalX;
            mLayerViewport.y = (float)originalY;
            mGameScreen.endBumpAnimation();
        }
    }
}
