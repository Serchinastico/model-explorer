package com.etaoin.myopengltest.util.shaders;

public class SampleFragmentShader implements Shader {

	@Override
	public int getType() {
		return ShaderFactory.SAMPLE_FRAGMENT_SHADER;
	}

	@Override
	public String getCode() {
		return
			"precision mediump float;" +
			"uniform vec4 vColor;" +
			"void main() {" +
			"	gl_FragColor = vColor;" +
			"}";
	}
}
