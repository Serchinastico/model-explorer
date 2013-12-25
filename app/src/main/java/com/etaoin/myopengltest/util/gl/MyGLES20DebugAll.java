package com.etaoin.myopengltest.util.gl;

import android.opengl.GLES20;

public class MyGLES20DebugAll implements MyGLES20 {

	@Override
	public void glClear(int mask) {
		GLES20.glClear(mask);
		checkGlError("glClear");
	}

	@Override
	public void glClearColor(float red, float green, float blue, float alpha) {
		GLES20.glClearColor(red, green, blue, alpha);
		checkGlError("glClearColor");
	}

	@Override
	public void glCompileShader(int shader) {
		GLES20.glCompileShader(shader);
		checkGlError("glCompileShader");
	}

	@Override
	public int glCreateShader(int type) {
		int shader = GLES20.glCreateShader(type);
		checkGlError("glCreateShader");
		return shader;
	}

	@Override
	public void glShaderSource(int shader, String source) {
		GLES20.glShaderSource(shader, source);
		checkGlError("glShaderSource");
	}

	@Override
	public void glViewport(int x, int y, int width, int height) {
		GLES20.glViewport(x, y, width, height);
		checkGlError("glViewport");
	}

	private void checkGlError(String glOperation) {
		int error;
		if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
			throw new RuntimeException(glOperation + ": glError " + error);
		}
	}
}
