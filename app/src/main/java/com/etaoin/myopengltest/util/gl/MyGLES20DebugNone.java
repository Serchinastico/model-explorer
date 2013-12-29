package com.etaoin.myopengltest.util.gl;

import java.nio.Buffer;
import java.nio.FloatBuffer;

/**
 * Wrapper for all the OpenGL ES 2.0 calls with no debug information. Thought to be used in a release environment.
 */
public class MyGLES20DebugNone extends MyGenericGLES20 {

	@Override
	public void glAttachShader(int program, int shader) {
		android.opengl.GLES20.glAttachShader(program, shader);
	}

	@Override
	public void glClear(int mask) {
		android.opengl.GLES20.glClear(mask);
	}

	@Override
	public void glClearColor(float red, float green, float blue, float alpha) {
		android.opengl.GLES20.glClearColor(red, green, blue, alpha);
	}

	@Override
	public void glCompileShader(int shader) {
		android.opengl.GLES20.glCompileShader(shader);
	}

	@Override
	public int glCreateProgram() {
		return android.opengl.GLES20.glCreateProgram();
	}

	@Override
	public int glCreateShader(int type) {
		return android.opengl.GLES20.glCreateShader(type);
	}

	@Override
	public void glDrawElements(int mode, int count, int type, Buffer indices) {
		android.opengl.GLES20.glDrawElements(mode, count, type, indices);
	}

	@Override
	public void glDrawElements(int mode, int count, int type, int offset) {
		android.opengl.GLES20.glDrawElements(mode, count, type, offset);
	}

	@Override
	public void glEnableVertexAttribArray(int index) {
		android.opengl.GLES20.glEnableVertexAttribArray(index);
	}

	@Override
	public int glGetAttribLocation(int program, String name) {
		return android.opengl.GLES20.glGetAttribLocation(program, name);
	}

	@Override
	public int glGetUniformLocation(int program, String name) {
		return android.opengl.GLES20.glGetUniformLocation(program, name);
	}

	@Override
	public void glLinkProgram(int program) {
		android.opengl.GLES20.glLinkProgram(program);
	}

	@Override
	public void glShaderSource(int shader, String source) {
		android.opengl.GLES20.glShaderSource(shader, source);
	}

	@Override
	public void glUniform4fv(int location, int count, FloatBuffer v) {
		android.opengl.GLES20.glUniform4fv(location, count, v);
	}

	@Override
	public void glUniform4fv(int location, int count, float[] value, int offset) {
		android.opengl.GLES20.glUniform4fv(location, count, value, offset);
	}

	@Override
	public void glUniformMatrix4fv(int location, int count, boolean transpose, FloatBuffer value) {
		android.opengl.GLES20.glUniformMatrix4fv(location, count, transpose, value);
	}

	@Override
	public void glUniformMatrix4fv(int location, int count, boolean transpose, float[] value, int offset) {
		android.opengl.GLES20.glUniformMatrix4fv(location, count, transpose, value, offset);
	}

	@Override
	public void glUseProgram(int program) {
		android.opengl.GLES20.glUseProgram(program);
	}

	@Override
	public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, Buffer ptr) {
		android.opengl.GLES20.glVertexAttribPointer(index, size, type, normalized, stride, ptr);
	}

	@Override
	public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int offset) {
		android.opengl.GLES20.glVertexAttribPointer(index, size, type, normalized, stride, offset);
	}

	@Override
	public void glViewport(int x, int y, int width, int height) {
		android.opengl.GLES20.glViewport(x, y, width, height);
	}
}
