package com.etaoin.myopengltest.util.shaders;

/**
 * The most basic vertex shader. It only renders every object in it's position.
 */
public class SampleVertexShader implements Shader {

	@Override
	public int getType() {
		return ShaderFactory.SAMPLE_VERTEX_SHADER;
	}

	@Override
	public String getCode() {
		return
			"uniform mat4 uMVPMatrix;" +
			"attribute vec4 vPosition;" +
			"void main() {" +
			"	gl_Position = uMVPMatrix * vPosition;" +
			"}";
	}
}
