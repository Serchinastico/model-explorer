package com.etaoin.myopengltest.core.main.activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import com.etaoin.myopengltest.core.main.context.ContextManager;
import com.etaoin.myopengltest.core.main.context.GameContext;
import com.etaoin.myopengltest.core.main.renderers.MainGLRenderer;
import com.etaoin.myopengltest.core.main.views.MainGLSurfaceView;
import com.etaoin.myopengltest.util.camera.Camera;
import com.etaoin.myopengltest.util.geometry.Vector3;
import com.etaoin.myopengltest.util.gl.MyGLES20;
import com.etaoin.myopengltest.util.gl.MyGLES20Factory;
import com.etaoin.myopengltest.util.io.FileReader;
import com.etaoin.myopengltest.util.io.ModelParser;
import com.etaoin.myopengltest.util.io.ModelParserFactory;
import com.etaoin.myopengltest.util.light.PointLight;
import com.etaoin.myopengltest.util.shapes.Axis;
import com.etaoin.myopengltest.util.shapes.Background;
import com.etaoin.myopengltest.util.shapes.Drawable;

import java.io.IOException;

/**
 * Main Activity for this test project.
 */
public class MainGLActivity extends Activity {

	private ContextManager contextManager;
	private GLSurfaceView.Renderer renderer;
	private GLSurfaceView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// OpenGL ES 2.0 wrapper
		MyGLES20Factory myGLES20Factory = new MyGLES20Factory();
		MyGLES20 gles20 = myGLES20Factory.createGLES20(MyGLES20Factory.DEBUG_LEVEL_ALL);

		// Context manager
		contextManager = new ContextManager();
		GameContext gameContext = new GameContext();
		ModelParserFactory modelParserFactory = new ModelParserFactory(new FileReader(), gles20, contextManager);

		gameContext.setCamera(new Camera(new Vector3(3f, 3f, 3f), new Vector3(0f, 0f, 0f)));
		gameContext.addDrawable(new Background(gles20));
		gameContext.addDrawable(new Axis(gles20));
		gameContext.addDrawable(createTeapot(modelParserFactory));
		gameContext.addPointLight(new PointLight(new Vector3(3f, 3f, 3f)));

		contextManager.addCurrentContext(gameContext);

		// Renderer & view
		renderer = new MainGLRenderer(contextManager, gles20);
		view = new MainGLSurfaceView(this, renderer, contextManager);
		setContentView(view);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	private Drawable createTeapot(ModelParserFactory modelParserFactory) {
		Drawable teapot = null;
		try {
			ModelParser modelParser = modelParserFactory.createModelParser(ModelParserFactory.OBJ_MODEL_TYPE,
					"teapot.obj");
			teapot = modelParser.parse();
		} catch (IOException exception) {
			// TODO Handle exception
		} catch (ModelParserFactory.InvalidModelParserTypeException exception) {
			// TODO Handle exception
		}

		return teapot;
	}
}
