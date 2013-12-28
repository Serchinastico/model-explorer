package com.etaoin.myopengltest.util.gl;

/**
 * Wrapper for all the OpenGL ES 2.0 calls.
 */
public interface MyGLES20 {

	public void glClear(int mask);
	public void glClearColor(float red, float green, float blue, float alpha);
	public void glCompileShader(int shader);
	public int glCreateShader(int type);
	public void glShaderSource(int shader, String source);
	public void glViewport(int x, int y, int width, int height);

}
