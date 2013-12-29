package com.etaoin.myopengltest.core.main.renderers;

import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.etaoin.myopengltest.util.geometry.Vector3;
import com.etaoin.myopengltest.util.gl.MyGLES20;
import com.etaoin.myopengltest.util.io.ModelParser;
import com.etaoin.myopengltest.util.io.ModelParserFactory;
import com.etaoin.myopengltest.util.light.PointLight;
import com.etaoin.myopengltest.util.shapes.Drawable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

// TODO Implement drawable manager and listener manager or move that to a subclass

/**
 * GL Renderer for the main activity.
 */
public class MainGLRenderer implements GLSurfaceView.Renderer {

	private Map<Integer, Drawable> drawables = new HashMap<Integer, Drawable>();
	private PointLight light;
	private int nextDrawableId = 0;

	private ModelParserFactory modelParserFactory;
	private MyGLES20 gles20;

	private float[] projectionMatrix = new float[16];
	private float[] viewMatrix = new float[16];
	private float blue = 0f;
	private boolean isBlueIncrementing = true;
	private float angle = 0f;

	public MainGLRenderer(ModelParserFactory modelParserFactory, MyGLES20 gles20) {
		this.modelParserFactory = modelParserFactory;
		this.gles20 = gles20;
	}

	@Override
	public void onSurfaceCreated(GL10 gl10, EGLConfig config) {
		gles20.glClearColor(0.0f, 0.2f, blue, 1.0f);

		try {
			ModelParser modelParser = modelParserFactory.createModelParser(ModelParserFactory.OBJ_MODEL_TYPE,
				"teapot.obj");
			addDrawable(modelParser.parse());
		} catch (IOException exception) {
			// TODO Handle exception
		} catch (ModelParserFactory.InvalidModelParserTypeException exception) {
			// TODO Handle exception
		}

		this.light = new PointLight(new Vector3(3f, 3f, 3f));
	}

	@Override
	public void onSurfaceChanged(GL10 gl10, int width, int height) {
		gles20.glViewport(0, 0, width, height);

		float ratio = (float) width / height;
		Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 1f, 10f);
	}

	@Override
	public void onDrawFrame(GL10 gl10) {
		gles20.glClear(android.opengl.GLES20.GL_COLOR_BUFFER_BIT);
		gles20.glClearColor(0.0f, 0.2f, blue, 1.0f);
		calculateNextBackgroundColor();

		float[] vpMatrix = new float[16];
		Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, -5f, 0f, 0f, 0f, 0f, 1.0f, 0f);
		Matrix.multiplyMM(vpMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

		float[] rotationMatrix = new float[16];
		Matrix.setRotateM(rotationMatrix, 0, angle, 0f, 1f, 0f);
		Matrix.multiplyMM(vpMatrix, 0, vpMatrix, 0, rotationMatrix, 0);
		angle += 0.5f;

		for (Map.Entry pair : drawables.entrySet()) {
			Drawable drawable = (Drawable) pair.getValue();
			drawable.draw(vpMatrix);
		}
	}

	public int addDrawable(Drawable drawable) {
		drawables.put(nextDrawableId, drawable);
		nextDrawableId += 1;
		return nextDrawableId;
	}

	private void calculateNextBackgroundColor() {
		blue += (isBlueIncrementing) ? 0.01f : -0.01f;
		if (blue >= 1f && isBlueIncrementing) {
			blue = 1f;
			isBlueIncrementing = false;
		} else if (blue <= 0f && !isBlueIncrementing) {
			blue = 0f;
			isBlueIncrementing = true;
		}
	}
}
