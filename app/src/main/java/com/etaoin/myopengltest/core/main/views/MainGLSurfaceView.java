package com.etaoin.myopengltest.core.main.views;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import com.etaoin.myopengltest.core.main.context.ContextManager;
import com.etaoin.myopengltest.util.camera.Camera;
import com.etaoin.myopengltest.util.geometry.Vector3;

/**
 * Main View for the test application.
 */
public class MainGLSurfaceView extends GLSurfaceView {

	private static final int OPEN_GL_ES_VERSION = 2;
	private static final float ROTATION_FACTOR = 0.15f;
	private ContextManager contextManager;
	// TODO Move to it's own module/class
	private float previousX;
	private float previousY;

	public MainGLSurfaceView(Context context, Renderer renderer, ContextManager contextManager) {
		super(context);

		this.contextManager = contextManager;
		previousX = 0f;
		previousY = 0f;
		setEGLContextClientVersion(OPEN_GL_ES_VERSION);
		setRenderer(renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
			case MotionEvent.ACTION_MOVE:
				float dx = x - previousX;
				float dy = y - previousY;

				Camera camera = contextManager.getCurrentContext().getCamera();
				Vector3 cameraPosition = camera.getPosition();
				Vector3 cameraLookAt = camera.getLookAt();
				Vector3 crossX = Vector3.sub(cameraPosition, cameraLookAt)
						.setZ(0f)
						.normalize()
						.cross(new Vector3(1f, 0f, 0f));
				float crossXNorm = crossX.norm();

				camera.rotateX((crossXNorm) * -dy * ROTATION_FACTOR);
				camera.rotateY((1 - crossXNorm) * -dy * ROTATION_FACTOR);
				camera.rotateZ(dx * ROTATION_FACTOR);

				previousX = x;
				previousY = y;
				break;
			case MotionEvent.ACTION_DOWN:
				previousX = x;
				previousY = y;
				break;
		}

		return true;
	}
}
