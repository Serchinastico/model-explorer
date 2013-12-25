package com.etaoin.myopengltest.util.shaders;

public class ShaderFactory {

	public static final int SAMPLE_VERTEX_SHADER = 0;
	public static final int SAMPLE_FRAGMENT_SHADER = 1;

	public Shader create(int type) throws InvalidShaderTypeException{
		Shader shader;
		switch (type) {
			case SAMPLE_VERTEX_SHADER:
				shader = new SampleVertexShader();
				break;
			case SAMPLE_FRAGMENT_SHADER:
				shader = new SampleFragmentShader();
				break;
			default:
				throw new InvalidShaderTypeException(type);
		}
		return shader;
	}
}

class InvalidShaderTypeException extends Exception {
	public InvalidShaderTypeException(int type) {
		super("Trying to create a shader with an invalid type: " + type);
	}
}