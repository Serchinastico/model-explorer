package com.etaoin.myopengltest.util.shapes;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.etaoin.myopengltest.core.main.context.ContextManager;
import com.etaoin.myopengltest.util.geometry.Vector3;
import com.etaoin.myopengltest.util.gl.MyGLES20;
import com.etaoin.myopengltest.util.light.PointLight;
import com.etaoin.myopengltest.util.shaders.SampleFragmentShader;
import com.etaoin.myopengltest.util.shaders.SampleVertexShader;
import com.etaoin.myopengltest.util.shaders.Shader;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Map;

/**
 * Drawable model built using triangles.
 */
public class Model implements Drawable {

	private MyGLES20 gles20;
	private ContextManager contextManager;

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

	private Shader vertexShader;

	private Shader fragmentShader;

	private static final int BYTES_PER_FLOAT = 4;
	private static final int COORDS_PER_VERTEX = 3;

	private float[] modelMatrix;
	private float[] mvpMatrix;

	private int program;

	// TODO Configurable!
	private float color[] = {
		0.63671875f, 0.76953125f, 0.22265625f, 1.0f
	};

	// TODO Delete shader creation!
	public Model(FloatBuffer vertexBuffer, ShortBuffer drawListBuffer, int verticesCount, MyGLES20 gles20,
			ContextManager contextManager) {
		this(vertexBuffer, drawListBuffer, verticesCount, new SampleVertexShader(), new SampleFragmentShader(), gles20,
				contextManager);
	}

	public Model(FloatBuffer vertexBuffer, ShortBuffer drawListBuffer, int verticesCount, Shader vertexShader,
			Shader fragmentShader, MyGLES20 gles20, ContextManager contextManager) {
		this.vertexBuffer = vertexBuffer;
		this.drawListBuffer = drawListBuffer;
		this.verticesCount = verticesCount;
		this.gles20 = gles20;
		this.contextManager = contextManager;
		this.vertexShader = vertexShader;
		this.fragmentShader = fragmentShader;
		this.modelMatrix = new float[16];
		this.mvpMatrix = new float[16];

		// TODO Configurable!
		Matrix.setIdentityM(modelMatrix, 0);
	}

	public void initialize() {
		int vertexShaderHandler = gles20.loadShader(GLES20.GL_VERTEX_SHADER, vertexShader.getCode());
		int fragmentShaderHandler = gles20.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader.getCode());

		program = gles20.glCreateProgram();
		gles20.glAttachShader(program, vertexShaderHandler);
		gles20.glAttachShader(program, fragmentShaderHandler);
		gles20.glLinkProgram(program);
	}

	public void draw(float[] vpMatrix) {
		Matrix.multiplyMM(mvpMatrix, 0, modelMatrix, 0, vpMatrix, 0);

		gles20.glUseProgram(program);

		int positionHandle = gles20.glGetAttribLocation(program, "vPosition");
		gles20.glEnableVertexAttribArray(positionHandle);
		gles20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false,
			COORDS_PER_VERTEX * BYTES_PER_FLOAT, vertexBuffer);

		// TODO Nonsense calculations here...
		Map<Integer, PointLight> pointLights = contextManager.getCurrentContext().getPointLights();
		float[] pointLightsArray = new float[pointLights.size() * 3];
		int iPointLight = 0;
		for (PointLight pointLight : pointLights.values()) {
			Vector3 pointLightPosition = pointLight.getPosition();
			pointLightsArray[iPointLight] = pointLightPosition.getX();
			pointLightsArray[iPointLight + 1] = pointLightPosition.getY();
			pointLightsArray[iPointLight + 2] = pointLightPosition.getZ();
			iPointLight += 3;
		}
		int pointLightsHandle = gles20.glGetUniformLocation(program, "vPointLights");
		gles20.glUniform3fv(pointLightsHandle, pointLights.size(), pointLightsArray, 0);

		int colorHandle = gles20.glGetUniformLocation(program, "vColor");
		gles20.glUniform4fv(colorHandle, 1, color, 0);

		int mvpMatrixHandle = gles20.glGetUniformLocation(program, "uMVPMatrix");
		gles20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mvpMatrix, 0);

		gles20.glDrawElements(GLES20.GL_TRIANGLES, verticesCount, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
	}
}
