package com.etaoin.myopengltest.util.gl;

import android.opengl.GLES20;

import java.nio.Buffer;
import java.nio.FloatBuffer;

/**
 * Wrapper for all the OpenGL ES 2.0 calls with no debug information. Thought to be used in a release environment.
 */
public class MyGLES20DebugNone extends MyGenericGLES20 {

	@Override
	public void glAttachShader(int program, int shader) {
		GLES20.glAttachShader(program, shader);
	}

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
	public int glCreateProgram() {
		return GLES20.glCreateProgram();
	}

	@Override
	public int glCreateShader(int type) {
		return GLES20.glCreateShader(type);
	}

	@Override
	public void glDrawElements(int mode, int count, int type, Buffer indices) {
		GLES20.glDrawElements(mode, count, type, indices);
	}

	@Override
	public void glDrawElements(int mode, int count, int type, int offset) {
		GLES20.glDrawElements(mode, count, type, offset);
	}

	@Override
	public void glEnableVertexAttribArray(int index) {
		GLES20.glEnableVertexAttribArray(index);
	}

	@Override
	public int glGetAttribLocation(int program, String name) {
		return GLES20.glGetAttribLocation(program, name);
	}

	@Override
	public int glGetUniformLocation(int program, String name) {
		return GLES20.glGetUniformLocation(program, name);
	}

	@Override
	public void glLinkProgram(int program) {
		GLES20.glLinkProgram(program);
	}

	@Override
	public void glShaderSource(int shader, String source) {
		GLES20.glShaderSource(shader, source);
	}

	@Override
	public void glUniform3fv(int location, int count, FloatBuffer v) {
		GLES20.glUniform3fv(location, count, v);
	}

	@Override
	public void glUniform3fv(int location, int count, float[] value, int offset) {
		GLES20.glUniform3fv(location, count, value, offset);
	}

	@Override
	public void glUniform4fv(int location, int count, FloatBuffer v) {
		GLES20.glUniform4fv(location, count, v);
	}

	@Override
	public void glUniform4fv(int location, int count, float[] value, int offset) {
		GLES20.glUniform4fv(location, count, value, offset);
	}

	@Override
	public void glUniformMatrix4fv(int location, int count, boolean transpose, FloatBuffer value) {
		GLES20.glUniformMatrix4fv(location, count, transpose, value);
	}

	@Override
	public void glUniformMatrix4fv(int location, int count, boolean transpose, float[] value, int offset) {
		GLES20.glUniformMatrix4fv(location, count, transpose, value, offset);
	}

	@Override
	public void glUseProgram(int program) {
		GLES20.glUseProgram(program);
	}

	@Override
	public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, Buffer ptr) {
		GLES20.glVertexAttribPointer(index, size, type, normalized, stride, ptr);
	}

	@Override
	public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int offset) {
		GLES20.glVertexAttribPointer(index, size, type, normalized, stride, offset);
	}

	@Override
	public void glViewport(int x, int y, int width, int height) {
		GLES20.glViewport(x, y, width, height);
	}
}
