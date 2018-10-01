package uk.ac.qub.eeecs.game;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;

import java.util.List;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetStore;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.game.platformDemo.CollisionDemoGameScreen;
import uk.ac.qub.eeecs.game.sandboxDemo.SandboxGameScreen;
import uk.ac.qub.eeecs.game.spaceDemo.SteeringDemoGameScreen;

/**
 * An exceedingly basic menu screen with a couple of touch area
 * 
 * @version 1.0
 */
public class MenuScreen extends GameScreen {

	/**
	 * Define the trigger touch region for playing the 'games'
	 */
	private Rect mSpaceShipDemoBound;
	private Rect mPlatformDemoBound;
	private Rect mSandboxBound;

	/**
	 * Create a simple menu screen
	 * 
	 * @param game
	 *            Game to which this screen belongs
	 */
	public MenuScreen(Game game) {
		super("MenuScreen", game);

		// Load in the bitmap used on the menu screen
		AssetStore assetManager = mGame.getAssetManager();
		assetManager.loadAndAddBitmap("SpaceshipIcon", "img/Spaceship1.png");
		assetManager.loadAndAddBitmap("PlatformIcon", "img/Platform.png");
		assetManager.loadAndAddBitmap("PlayIcon", "img/play.jpg");

		// Load in the demo icons
		assetManager.loadAndAddBitmap("AlphaBlendingIcon", "img/demos/alphaBlending/Icon.png");
		assetManager.loadAndAddBitmap("GraphicsManipulationIcon", "img/demos/graphicsManipulation/Icon.png");

		// Define the rects what will be used to 'hold' the images
		int spacingX = game.getScreenWidth() / 7;
		int spacingY = game.getScreenHeight() / 3;
		mSpaceShipDemoBound = new Rect(spacingX, spacingY, 2 * spacingX, 2 * spacingY);
		mPlatformDemoBound = new Rect(3 * spacingX, spacingY, 4 * spacingX, 2 * spacingY);
		mSandboxBound = new Rect(5 * spacingX, spacingY, 6 * spacingX, 2 * spacingY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uk.ac.qub.eeecs.gage.world.GameScreen#update(uk.ac.qub.eeecs.gage.engine
	 * .ElapsedTime)
	 */
	@Override
	public void update(ElapsedTime elapsedTime) {

		// Process any touch events occurring since the update
		Input input = mGame.getInput();

		List<TouchEvent> touchEvents = input.getTouchEvents();
		if (touchEvents.size() > 0) {

			// Just check the first touch event that occurred in the frame.
			// It means pressing the screen with several fingers may not
			// trigger a 'button', but, hey, it's an exceedingly basic menu.
			TouchEvent touchEvent = touchEvents.get(0);

			if (mSpaceShipDemoBound.contains((int) touchEvent.x,
					(int) touchEvent.y)) {
				// If the play game area has been touched then swap screens
				mGame.getScreenManager().removeScreen(this.getName());
				SteeringDemoGameScreen steeringDemoGameScreen = new SteeringDemoGameScreen(mGame);
				// As it's the only added screen it will become active.
				mGame.getScreenManager().addScreen(steeringDemoGameScreen);
			}

			else if (mPlatformDemoBound.contains((int) touchEvent.x,
					(int) touchEvent.y)) {
				// If the play game area has been touched then swap screens
				mGame.getScreenManager().removeScreen(this.getName());
				CollisionDemoGameScreen collisionDemoGameScreen = new CollisionDemoGameScreen(mGame, elapsedTime);
				// As it's the only added screen it will become active.
				mGame.getScreenManager().addScreen(collisionDemoGameScreen);

			}

			else if (mSandboxBound.contains((int) touchEvent.x,
					(int) touchEvent.y)) {
				// If the play game area has been touched then swap screens
				mGame.getScreenManager().removeScreen(this.getName());
				SandboxGameScreen sandboxGameScreen = new SandboxGameScreen(mGame);
				// As it's the only added screen it will become active.
				mGame.getScreenManager().addScreen(sandboxGameScreen);

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uk.ac.qub.eeecs.gage.world.GameScreen#draw(uk.ac.qub.eeecs.gage.engine
	 * .ElapsedTime, uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D)
	 */
	@Override
	public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D) {

		// Get and draw the bitmaps into the defined rectangles
		Bitmap playSpaceShipGame = mGame.getAssetManager().getBitmap("SpaceshipIcon");
		Bitmap playPlatformGame = mGame.getAssetManager().getBitmap("PlatformIcon");
		Bitmap playSandboxGame = mGame.getAssetManager().getBitmap("PlayIcon");

		graphics2D.clear(Color.WHITE);
		graphics2D.drawBitmap(playSpaceShipGame, null, mSpaceShipDemoBound,null);
		graphics2D.drawBitmap(playPlatformGame, null, mPlatformDemoBound, null);
		graphics2D.drawBitmap(playSandboxGame, null, mSandboxBound, null);
	}
}
