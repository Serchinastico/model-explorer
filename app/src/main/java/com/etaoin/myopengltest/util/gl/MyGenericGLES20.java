package com.etaoin.myopengltest.util.gl;

public abstract class MyGenericGLES20 implements MyGLES20 {

	@Override
	public int loadShader(int type, String shaderCode) {
		int shader = glCreateShader(type);
		glShaderSource(shader, shaderCode);
		glCompileShader(shader);
		return shader;
	}
}
