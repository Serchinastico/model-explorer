package com.etaoin.myopengltest.util.shapes;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.etaoin.myopengltest.core.main.renderers.MainGLRenderer;
import com.etaoin.myopengltest.util.shaders.SampleFragmentShader;
import com.etaoin.myopengltest.util.shaders.SampleVertexShader;
import com.etaoin.myopengltest.util.shaders.Shader;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Drawable model built using triangles.
 */
public class Model implements Drawable {

	/**
	 * OpenGL Vertex buffer.
	 */
	private FloatBuffer vertexBuffer;

	/**
	 * OpenGL buffer for storing the order to draw the vertices.
	 */
	private ShortBuffer drawListBuffer;

	/**
	 * Number of vertices in total (not optimizing vertices in the same position)
	 */
	private int verticesCount;

	private static final int BYTES_PER_FLOAT = 4;
	private static final int COORDS_PER_VERTEX = 3;

	private float[] modelMatrix = new float[16];
	private float[] mvpMatrix = new float[16];

	private final int program;

	// TODO Configurable!
	private float color[] = {
		0.63671875f, 0.76953125f, 0.22265625f, 1.0f
	};

	// TODO Delete shader creation!
	public Model(FloatBuffer vertexBuffer, ShortBuffer drawListBuffer, int verticesCount) {
		this(vertexBuffer, drawListBuffer, verticesCount, new SampleVertexShader(), new SampleFragmentShader());
	}

	public Model(FloatBuffer vertexBuffer, ShortBuffer drawListBuffer, int verticesCount, Shader vertexShader,
			Shader fragmentShader) {
		this.vertexBuffer = vertexBuffer;
		this.drawListBuffer = drawListBuffer;
		this.verticesCount = verticesCount;

		// TODO Configurable!
		Matrix.setIdentityM(modelMatrix, 0);

		int vertexShaderHandler = MainGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShader.getCode());
		int fragmentShaderHandler = MainGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader.getCode());

		program = GLES20.glCreateProgram();
		GLES20.glAttachShader(program, vertexShaderHandler);
		GLES20.glAttachShader(program, fragmentShaderHandler);
		GLES20.glLinkProgram(program);
	}

	public void draw(float[] vpMatrix) {
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

		GLES20.glDrawElements(GLES20.GL_TRIANGLES, verticesCount, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
	}
}
