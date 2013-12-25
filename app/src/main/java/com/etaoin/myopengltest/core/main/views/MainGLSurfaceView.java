package com.etaoin.myopengltest.core.main.views;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Main View for the test application.
 */
public class MainGLSurfaceView extends GLSurfaceView {

	private static final int OPEN_GL_ES_VERSION = 2;


	public MainGLSurfaceView(Context context, Renderer renderer) {
		super(context);

		setEGLContextClientVersion(OPEN_GL_ES_VERSION);
		setRenderer(renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}
}
