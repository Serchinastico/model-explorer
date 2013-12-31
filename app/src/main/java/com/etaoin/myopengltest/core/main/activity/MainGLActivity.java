package com.etaoin.myopengltest.core.main.activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import com.etaoin.myopengltest.core.main.context.ContextManager;
import com.etaoin.myopengltest.core.main.context.GameContext;
import com.etaoin.myopengltest.core.main.renderers.MainGLRenderer;
import com.etaoin.myopengltest.core.main.views.MainGLSurfaceView;
import com.etaoin.myopengltest.util.geometry.Vector3;
import com.etaoin.myopengltest.util.gl.MyGLES20;
import com.etaoin.myopengltest.util.gl.MyGLES20Factory;
import com.etaoin.myopengltest.util.io.FileReader;
import com.etaoin.myopengltest.util.io.ModelParser;
import com.etaoin.myopengltest.util.io.ModelParserFactory;
import com.etaoin.myopengltest.util.light.PointLight;
import com.etaoin.myopengltest.util.shapes.Background;
import com.etaoin.myopengltest.util.shapes.Drawable;

import java.io.IOException;

/**
 * Main Activity for this test project.
 */
public class MainGLActivity extends Activity {

	private GLSurfaceView.Renderer renderer;
	private GLSurfaceView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// OpenGL ES 2.0 wrapper
		MyGLES20Factory myGLES20Factory = new MyGLES20Factory();
		MyGLES20 gles20 = myGLES20Factory.createGLES20(MyGLES20Factory.DEBUG_LEVEL_ALL);

		// Context manager
		ModelParserFactory modelParserFactory = new ModelParserFactory(new FileReader(), gles20);
		GameContext gameContext = new GameContext();

		gameContext.addDrawable(new Background(gles20));
		gameContext.addDrawable(createTeapot(modelParserFactory));
		gameContext.addPointLight(new PointLight(new Vector3(3f, 3f, 3f)));

		ContextManager contextManager = new ContextManager();
		contextManager.addCurrentContext(gameContext);

		// Renderer & view
		renderer = new MainGLRenderer(contextManager, gles20);
		view = new MainGLSurfaceView(this, renderer);
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
