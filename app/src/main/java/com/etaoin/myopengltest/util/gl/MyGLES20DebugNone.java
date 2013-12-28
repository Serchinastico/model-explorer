package com.etaoin.myopengltest.util.gl;

import android.opengl.GLES20;

/**
 * Wrapper for all the OpenGL ES 2.0 calls with no debug information. Thought to be used in a release environment.
 */
public class MyGLES20DebugNone implements MyGLES20 {

	@Override
	public void glClear(int mask) {
		GLES20.glClear(mask);
	}

	@Override
	public void glClearColor(float red, float green, float blue, float alpha) {
		GLES20.glClearColor(red, green, blue, alpha);
	}

	@Override
	public void glCompileShader(int shader) {
		GLES20.glCompileShader(shader);
	}

	@Override
	public int glCreateShader(int type) {
		return GLES20.glCreateShader(type);
	}

	@Override
	public void glShaderSource(int shader, String source) {
		GLES20.glShaderSource(shader, source);
	}

	@Override
	public void glViewport(int x, int y, int width, int height) {
		GLES20.glViewport(x, y, width, height);
	}
}
