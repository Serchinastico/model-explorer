package com.etaoin.myopengltest.util.gl;

import java.nio.Buffer;
import java.nio.FloatBuffer;

/**
 * Wrapper for all the OpenGL ES 2.0 calls.
 */
public interface MyGLES20 {

	public void glAttachShader(int program, int shader);
	public void glClear(int mask);
	public void glClearColor(float red, float green, float blue, float alpha);
	public void glCompileShader(int shader);
	public int glCreateProgram();
	public int glCreateShader(int type);
	public void glDrawElements(int mode, int count, int type, Buffer indices);
	public void glDrawElements(int mode, int count, int type, int offset);
	public void glEnableVertexAttribArray(int index);
	public int glGetAttribLocation(int program, String name);
	public int glGetUniformLocation(int program, String name);
	public void glLinkProgram(int program);
	public void glShaderSource(int shader, String source);
	public void glUniform3fv(int location, int count, FloatBuffer v);
	public void glUniform3fv(int location, int count, float[] value, int offset);
	public void glUniform4fv(int location, int count, FloatBuffer v);
	public void glUniform4fv(int location, int count, float[] value, int offset);
	public void glUniformMatrix4fv(int location, int count, boolean transpose, FloatBuffer value);
	public void glUniformMatrix4fv(int location, int count, boolean transpose, float[] value, int offset);
	public void glUseProgram(int program);
	public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, Buffer ptr);
	public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int offset);
	public void glViewport(int x, int y, int width, int height);

	public int loadShader(int type, String shaderCode);

}
