package com.etaoin.myopengltest.core.main.renderers;

import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import com.etaoin.myopengltest.core.main.context.ContextManager;
import com.etaoin.myopengltest.util.gl.MyGLES20;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * GL Renderer for the main activity.
 */
public class MainGLRenderer implements GLSurfaceView.Renderer {

	private ContextManager contextManager;

	private MyGLES20 gles20;

	private float[] projectionMatrix = new float[16];
	private float[] viewMatrix = new float[16];
	private float angle = 0f;

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
		Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 1f, 10f);
	}

	@Override
	public void onDrawFrame(GL10 gl10) {
		// TODO Create camera object
		float[] vpMatrix = new float[16];
		Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, -5f, 0f, 0f, 0f, 0f, 1.0f, 0f);
		Matrix.multiplyMM(vpMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

		float[] rotationMatrix = new float[16];
		Matrix.setRotateM(rotationMatrix, 0, angle, 0f, 1f, 0f);
		Matrix.multiplyMM(vpMatrix, 0, vpMatrix, 0, rotationMatrix, 0);
		angle += 0.5f;

		contextManager.draw(vpMatrix);
	}
}
