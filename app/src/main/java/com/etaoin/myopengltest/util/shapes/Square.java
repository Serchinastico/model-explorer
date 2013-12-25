package com.etaoin.myopengltest.util.shapes;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.etaoin.myopengltest.core.main.renderers.MainGLRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Square implements Drawable {

	private FloatBuffer vertexBuffer;
	private ShortBuffer drawListBuffer;

	private static final int BYTES_PER_FLOAT = 4;
	private static final int BYTES_PER_SHORT = 2;
	private static final int COORDS_PER_VERTEX = 3;
	private static float squareCoords[] = {
		-0.5f, 0.5f, 0.0f,
		-0.5f, -0.5f, 0.0f,
		0.5f, -0.5f, 0.0f,
		0.5f, 0.5f, 0.0f,
	};

	private short drawOrder[] = {
		0, 1, 2, 0, 2, 3
	};

	private float color[] = {
		1f, 1f, 0.2f, 1.0f
	};

	private float[] modelMatrix = new float[16];

	private final int program;

	public Square() {
		Matrix.setIdentityM(modelMatrix, 0);

		ByteBuffer vertexByteBuffer = ByteBuffer.allocateDirect(squareCoords.length * BYTES_PER_FLOAT);
		vertexByteBuffer.order(ByteOrder.nativeOrder());

		vertexBuffer = vertexByteBuffer.asFloatBuffer();
		vertexBuffer.put(squareCoords);
		vertexBuffer.position(0);

		ByteBuffer drawListByteBuffer = ByteBuffer.allocateDirect(drawOrder.length * BYTES_PER_SHORT);
		drawListByteBuffer.order(ByteOrder.nativeOrder());
		drawListBuffer = drawListByteBuffer.asShortBuffer();
		drawListBuffer.put(drawOrder);
		drawListBuffer.position(0);

		String vertexShaderCode =
			"uniform mat4 uMVPMatrix;" +
			"attribute vec4 vPosition;" +
			"void main() {" +
			"	gl_Position = uMVPMatrix * vPosition;" +
			"}";
		String fragmentShaderCode =
			"precision mediump float;" +
			"uniform vec4 vColor;" +
			"void main() {" +
			"	gl_FragColor = vColor;" +
			"}";

		int vertexShader = MainGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
		int fragmentShader = MainGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

		program = GLES20.glCreateProgram();
		GLES20.glAttachShader(program, vertexShader);
		GLES20.glAttachShader(program, fragmentShader);
		GLES20.glLinkProgram(program);
	}

	public void draw(float[] vpMatrix) {
		float[] mvpMatrix = new float[16];
		Matrix.multiplyMM(mvpMatrix, 0, vpMatrix, 0, modelMatrix, 0);

		GLES20.glUseProgram(program);

		int positionHandle = GLES20.glGetAttribLocation(program, "vPosition");
		GLES20.glEnableVertexAttribArray(positionHandle);
		GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false,
			COORDS_PER_VERTEX * BYTES_PER_FLOAT, vertexBuffer);

		int colorHandle = GLES20.glGetUniformLocation(program, "vColor");
		GLES20.glUniform4fv(colorHandle, 1, color, 0);

		int mvpMatrixHandle = GLES20.glGetUniformLocation(program, "uMVPMatrix");
		GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mvpMatrix, 0);

		GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
	}
}
