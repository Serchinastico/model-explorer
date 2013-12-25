package com.etaoin.myopengltest.core.main.activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.etaoin.myopengltest.core.main.renderers.MainGLRenderer;
import com.etaoin.myopengltest.core.main.views.MainGLSurfaceView;
import com.etaoin.myopengltest.util.gl.MyGLES20;
import com.etaoin.myopengltest.util.gl.MyGLES20Factory;
import com.etaoin.myopengltest.util.io.FileReader;
import com.etaoin.myopengltest.util.io.ModelParserFactory;

/**
 * Main Activity for this test project.
 */
public class MainGLActivity extends Activity {

	private GLSurfaceView.Renderer renderer;
	private GLSurfaceView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MyGLES20Factory myGLES20Factory = new MyGLES20Factory();
		MyGLES20 myGLES20 = myGLES20Factory.createGLES20(MyGLES20Factory.DEBUG_LEVEL_ALL);
		ModelParserFactory modelParserFactory = new ModelParserFactory(new FileReader());
		renderer = new MainGLRenderer(modelParserFactory, myGLES20);
		view = new MainGLSurfaceView(this, renderer);
		setContentView(view);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}
}
