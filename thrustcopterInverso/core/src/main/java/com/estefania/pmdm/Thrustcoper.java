package com.estefania.pmdm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Thrustcoper extends Game implements InputProcessor {

	public static final float WIDTH= 800f;
	public static final float HEIGHT= 480f;
	public static final float TERRAIN_SPEED_PPS = 200f;
	public static final float BACKGROUND_SPEED_PPS = 0;
	private static final float PLANE_TAP_SPEED = 200f;
	private static final float GRAVITY = -4;
	private static final float NEW_PILAR_POSITION_THRESHOLD = WIDTH - 100;

	protected SpriteBatch batch;
	//clases de texturas : Texture PACKET
	protected TextureAtlas textureAtlas;

	protected FPSLogger fpsLogger;
	protected OrthographicCamera camera;

	protected FitViewport fitViewport;
	//	private ExtendViewport extendViewport;

	protected TextureRegion backgroundTextureRegion;
	protected TextureRegion aboveGrassTextureRegion;
	protected TextureRegion belowGrassTextureRegion;

	protected float terrainOffset =0f;
	protected float backgroundOffset =0f;

	private TextureRegion pillarUp;
	private TextureRegion pillarDown;
	private final Array<Vector2> pillars = new Array<>();
	private static final float MIN_PILAR_DISTANCE = WIDTH /2f;
	private static final float PILLAR_DISTANCE_RANGE = 100;
	private Vector2 lastPillarPosition;

	private Animation<TextureRegion> planeAnimation;
	private TextureRegion planeRegionTexture1;
	private TextureRegion planeRegionTexture2;
	private TextureRegion planeRegionTexture3;
	private float planeAnimTime;
	protected TextureRegion backgroundFlippedTextureRegion;

	private Vector2 defaultPlanePosition;
	private Vector2 planePosition;
	private Vector2 gravity;

	private Vector2 planeVelocity;
	private float damping = 0.99f;

	private final Rectangle planeBoundingBox = new Rectangle();
	private final Rectangle pillarBoundingBox = new Rectangle();

	private Sound crashSound;
	private Music music;


	@Override
	public void create() {

		setScreen(new Start(this));

		fpsLogger = new FPSLogger();
		camera = new OrthographicCamera();
		camera.position.set(WIDTH/2f,HEIGHT/2F,0);
		fitViewport = new FitViewport(WIDTH, HEIGHT, camera); //para que el tamaño no cambie
//		camera.setToOrtho(false, WIDTH, HEIGHT); //para que el tamaño no cambie
		batch = new SpriteBatch();

		textureAtlas = new TextureAtlas(Gdx.files.internal("thrustcopter.atlas"));
		backgroundTextureRegion = textureAtlas.findRegion("background");
		backgroundFlippedTextureRegion = new TextureRegion(backgroundTextureRegion);
		backgroundFlippedTextureRegion.flip(true,false);

		belowGrassTextureRegion = textureAtlas.findRegion("groundGrass");
		aboveGrassTextureRegion = new TextureRegion(belowGrassTextureRegion);
		aboveGrassTextureRegion.flip(true, true);

		pillarUp= textureAtlas.findRegion("rockGrassUp");
		pillarDown= textureAtlas.findRegion("rockGrassDown");

		planeRegionTexture1 = textureAtlas.findRegion("planeRed1");
		planeRegionTexture2 = textureAtlas.findRegion("planeRed2");
		planeRegionTexture3 = textureAtlas.findRegion("planeRed3");
		planeAnimation = new Animation<>(0.05f, planeRegionTexture1, planeRegionTexture2, planeRegionTexture3, planeRegionTexture2);
		planeAnimation.setPlayMode(Animation.PlayMode.LOOP);

		defaultPlanePosition = new Vector2(planeRegionTexture1.getRegionWidth(), HEIGHT/2f- planeRegionTexture1.getRegionHeight()/2f);
		planePosition = new Vector2(defaultPlanePosition);
		gravity = new Vector2(0,GRAVITY);

		planeVelocity = new Vector2();

		crashSound = Gdx.audio.newSound(Gdx.files.internal("sounds/crash.ogg"));
		music =Gdx.audio.newMusic(Gdx.files.internal("sounds/journey.mp3"));
		music.setLooping(true);
		music.play();

		Gdx.input.setInputProcessor(this);


	}

	@Override
	public void resize(int width, int height) {
		fitViewport.update(width, height);
	}

	@Override
	public void render() {
	fpsLogger.log();
	updateScene();
	drawScene();
	}

	private void updateScene(){

		terrainOffset+= TERRAIN_SPEED_PPS * Gdx.graphics.getDeltaTime();
		if (terrainOffset >= belowGrassTextureRegion.getRegionWidth())
			terrainOffset = 0f;

		backgroundOffset += BACKGROUND_SPEED_PPS * Gdx.graphics.getDeltaTime();
		if (backgroundOffset >= backgroundTextureRegion.getRegionWidth())
			backgroundOffset = 0f;


		planeVelocity.add(gravity);
		planeVelocity.scl(damping);
		planePosition.mulAdd(planeVelocity, Gdx.graphics.getDeltaTime());
		planePosition.x = defaultPlanePosition.x;
		planeAnimTime += Gdx.graphics.getDeltaTime();



	}



	private void drawScene(){

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(backgroundTextureRegion, backgroundOffset, 0);
		batch.draw(backgroundTextureRegion, backgroundOffset - backgroundTextureRegion.getRegionWidth(), 0);
		batch.draw(aboveGrassTextureRegion, terrainOffset, HEIGHT - aboveGrassTextureRegion.getRegionHeight());
		batch.draw(aboveGrassTextureRegion, terrainOffset - aboveGrassTextureRegion.getRegionWidth(), HEIGHT - aboveGrassTextureRegion.getRegionHeight());
		batch.draw(belowGrassTextureRegion, terrainOffset, 0);
		batch.draw(belowGrassTextureRegion, terrainOffset - belowGrassTextureRegion.getRegionWidth(), 0);
		batch.draw(planeAnimation.getKeyFrame(planeAnimTime), planePosition.x, planePosition.y);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		textureAtlas.dispose();
//		backgroundImage.dispose();
//		grassTexture.dispose();

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		planeVelocity.add(0f,PLANE_TAP_SPEED);
		return true;
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}