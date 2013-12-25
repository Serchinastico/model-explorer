package com.etaoin.myopengltest.util.shapes;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.etaoin.myopengltest.core.main.renderers.MainGLRenderer;
import com.etaoin.myopengltest.util.geometry.Vector3;
import com.etaoin.myopengltest.util.io.Face;
import com.etaoin.myopengltest.util.io.ParsedModel;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.List;

public class Model implements Drawable {

	private FloatBuffer vertexBuffer;
	private ShortBuffer drawListBuffer;
	/**
	 * Number of vertices in total (not optimizing vertices in the same position)
	 */
	private int verticesCount;

	private static final int BYTES_PER_FLOAT = 4;
	private static final int BYTES_PER_SHORT = 2;
	private static final int COORDS_PER_VERTEX = 3;

	private float[] modelMatrix = new float[16];

	private float[] vertices;
	private short[] vertexIndices;
	private final int program;

	private float color[] = {
		0.63671875f, 0.76953125f, 0.22265625f, 1.0f
	};

	public Model() {
		Matrix.setIdentityM(modelMatrix, 0);

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

	public Model(ParsedModel parsedModel) {
		this();

		// TODO Create VectorList class to avoid calculating this
		Vector3[] vectorList = parsedModel.getVertices();
		vertices = new float[vectorList.length * 3];
		for (int i = 0; i < vectorList.length; i++) {
			vertices[3 * i] = vectorList[i].getX();
			vertices[3 * i + 1] = vectorList[i].getY();
			vertices[3 * i + 2] = vectorList[i].getZ();
		}
		ByteBuffer vertexByteBuffer = ByteBuffer.allocateDirect(vertices.length * BYTES_PER_FLOAT);
		vertexByteBuffer.order(ByteOrder.nativeOrder());
		vertexBuffer = vertexByteBuffer.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

		// TODO Create FaceList class to avoid calculating this
		Face[] faceList = parsedModel.getFaces();
		verticesCount = parsedModel.getVerticesPerFace() * faceList.length;
		vertexIndices = new short[verticesCount];
		for (int i = 0; i < faceList.length; i++) {
			List<Integer> faceVertexIndices = faceList[i].getVertexIndices();
			for (int j = 0; j < parsedModel.getVerticesPerFace(); j++) {
				vertexIndices[3 * i + j] = faceVertexIndices.get(j).shortValue();
			}
		}
		ByteBuffer drawListByteBuffer = ByteBuffer.allocateDirect(vertexIndices.length * BYTES_PER_SHORT);
		drawListByteBuffer.order(ByteOrder.nativeOrder());
		drawListBuffer = drawListByteBuffer.asShortBuffer();
		drawListBuffer.put(vertexIndices);
		drawListBuffer.position(0);
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

		GLES20.glDrawElements(GLES20.GL_TRIANGLES, verticesCount, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
	}
}
