package com.etaoin.myopengltest.util.shapes;

import com.etaoin.myopengltest.util.gl.MyGLES20;

public class Background implements Drawable {

	private MyGLES20 gles20;
	private float blue;
	private boolean isBlueIncrementing;

	public Background(MyGLES20 gles20) {
		this.gles20 = gles20;
		this.blue = 0f;
		this.isBlueIncrementing = true;
	}

	@Override
	public void initialize() {
		// Empty
	}

	@Override
	public void draw(float[] vpMatrix) {
		gles20.glClear(android.opengl.GLES20.GL_COLOR_BUFFER_BIT);
		gles20.glClearColor(0.0f, 0.2f, blue, 1.0f);
		// TODO updating in a draw method *facepalm*
		calculateNextBackgroundColor();
	}

	private void calculateNextBackgroundColor() {
		blue += (isBlueIncrementing) ? 0.01f : -0.01f;
		if (blue >= 1f && isBlueIncrementing) {
			blue = 1f;
			isBlueIncrementing = false;
		} else if (blue <= 0f && !isBlueIncrementing) {
			blue = 0f;
			isBlueIncrementing = true;
		}
	}
}
