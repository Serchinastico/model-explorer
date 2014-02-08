package com.etaoin.myopengltest.util.shapes;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.etaoin.myopengltest.util.geometry.Face;
import com.etaoin.myopengltest.util.geometry.Vector3;
import com.etaoin.myopengltest.util.geometry.Vector3List;
import com.etaoin.myopengltest.util.gl.MyGLES20;
import com.etaoin.myopengltest.util.shaders.SampleFragmentShader;
import com.etaoin.myopengltest.util.shaders.SampleVertexShader;
import com.etaoin.myopengltest.util.shaders.Shader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Collection;

public class Axis implements Drawable {

	private static final int BYTES_PER_FLOAT = 4;
	private static final int BYTES_PER_SHORT = 2;
	private static final int COORDS_PER_VERTEX = 3;
	private MyGLES20 gles20;
	private int program;
	private FloatBuffer vertexBuffer;
	private ShortBuffer drawOrderBuffer;
	private Shader vertexShader;
	private Shader fragmentShader;
	private float[] modelMatrix;
	private float[] mvpMatrix;
	// TODO Configurable!
	private Collection<Vector3> coordinates;
	private short[] drawOrder;
	private float[] color;

	public Axis(MyGLES20 gles20) {
		this.gles20 = gles20;
		// TODO Creation of objects here *facepalm*...
		vertexShader = new SampleVertexShader();
		fragmentShader = new SampleFragmentShader();
		modelMatrix = new float[16];
		mvpMatrix = new float[16];
		coordinates = new ArrayList<Vector3>();
		coordinates.add(new Vector3(0f, 0f, 0f));
		coordinates.add(new Vector3(5f, 0f, 0f));
		coordinates.add(new Vector3(0f, 5f, 0f));
		coordinates.add(new Vector3(0f, 0f, 5f));
		drawOrder = new short[]{
			0, 1, 0, 2, 0, 3
		};
		color = new float[]{1f, 0f, 0f, 1.0f};

		Matrix.setIdentityM(modelMatrix, 0);
	}

	@Override
	public void initialize() {
		int vertexShaderHandler = gles20.loadShader(GLES20.GL_VERTEX_SHADER, vertexShader.getCode());
		int fragmentShaderHandler = gles20.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader.getCode());

		Vector3List vector3List = new Vector3List();
		vector3List.addAll(coordinates);
		vertexBuffer = vector3List.toFloatBuffer();

		ByteBuffer drawOrderByteBuffer = ByteBuffer.allocateDirect(drawOrder.length * BYTES_PER_SHORT);
		drawOrderByteBuffer.order(ByteOrder.nativeOrder());
		drawOrderBuffer = drawOrderByteBuffer.asShortBuffer();
		drawOrderBuffer.put(drawOrder);
		drawOrderBuffer.position(0);

		program = gles20.glCreateProgram();
		gles20.glAttachShader(program, vertexShaderHandler);
		gles20.glAttachShader(program, fragmentShaderHandler);
		gles20.glLinkProgram(program);
	}

	@Override
	public void draw(float[] vpMatrix) {
		Matrix.multiplyMM(mvpMatrix, 0, modelMatrix, 0, vpMatrix, 0);
		gles20.glUseProgram(program);

		int positionHandle = gles20.glGetAttribLocation(program, "vPosition");
		gles20.glEnableVertexAttribArray(positionHandle);
		gles20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false,
				COORDS_PER_VERTEX * BYTES_PER_FLOAT, vertexBuffer);

		int colorHandle = gles20.glGetUniformLocation(program, "vColor");
		gles20.glUniform4fv(colorHandle, 1, color, 0);

		int mvpMatrixHandle = gles20.glGetUniformLocation(program, "uMVPMatrix");
		gles20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mvpMatrix, 0);

		gles20.glDrawElements(GLES20.GL_LINES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawOrderBuffer);
	}
}
