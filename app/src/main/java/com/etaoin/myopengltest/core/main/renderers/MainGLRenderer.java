package com.etaoin.myopengltest.core.main.renderers;

import android.opengl.GLSurfaceView;
import com.etaoin.myopengltest.core.main.context.ContextManager;
import com.etaoin.myopengltest.util.camera.Camera;
import com.etaoin.myopengltest.util.gl.MyGLES20;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * GL Renderer for the main activity.
 */
public class MainGLRenderer implements GLSurfaceView.Renderer {

	private ContextManager contextManager;

	private MyGLES20 gles20;

	public MainGLRenderer(ContextManager contextManager, MyGLES20 gles20) {
		this.contextManager = contextManager;
		this.gles20 = gles20;
	}

	@Override
	public void onSurfaceCreated(GL10 gl10, EGLConfig config) {
		contextManager.initialize();
	}

	@Override
	public void onSurfaceChanged(GL10 gl10, int width, int height) {
		gles20.glViewport(0, 0, width, height);

		float ratio = (float) width / height;
		// TODO Send camera change through EventBus when ready
		Camera camera = contextManager.getCurrentContext().getCamera();
		camera.setProjectionMatrix(-ratio, ratio, -1f, 1f, 1f, 10f);
	}

	@Override
	public void onDrawFrame(GL10 gl10) {
		contextManager.draw();
	}

	public ContextManager getContextManager() {
		return contextManager;
	}
}
