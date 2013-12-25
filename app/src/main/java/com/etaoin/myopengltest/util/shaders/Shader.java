package com.etaoin.myopengltest.util.shaders;

public interface Shader {

	/**
	 * Returns one of the GLES20 constants used for defining types of shaders:
	 * 	GLES20.GL_VERTEX_SHADER
	 * 	GLES20.GL_FRAGMENT_SHADER
	 */
	public int getType();

	/**
	 * Returns the code of shader as a string.
	 */
	public String getCode();
}
