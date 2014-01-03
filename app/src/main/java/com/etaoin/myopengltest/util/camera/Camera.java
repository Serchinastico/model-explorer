package com.etaoin.myopengltest.util.camera;

import android.opengl.Matrix;
import com.etaoin.myopengltest.util.geometry.Vector3;

/**
 * Class that represents the camera. Contains both, the view and the projection matrices.
 */
public class Camera {

	private float[] viewMatrix;
	private float[] projectionMatrix;
	private float[] viewProjectionMatrix;
	private boolean viewProjectionMatrixChanged;
	private Vector3 eye;
	private Vector3 lookAt;
	private Vector3 up;

	public Camera(Vector3 eye, Vector3 lookAt, Vector3 up) {
		viewMatrix = new float[16];
		projectionMatrix = new float[16];
		viewProjectionMatrix = new float[16];
		viewProjectionMatrixChanged = false;
		this.eye = eye;
		this.lookAt = lookAt;
		this.up = up;

		updateViewMatrix();
	}

	public void move(Vector3 direction) {
		eye.add(direction);
		updateViewMatrix();
	}

	public void moveTo(Vector3 position) {
		eye = position;
		updateViewMatrix();
	}

	public void lookAt(Vector3 position) {
		lookAt = position;
		updateViewMatrix();
	}

	public void setProjectionMatrix(float left, float right, float bottom, float top, float near, float far) {
		Matrix.frustumM(projectionMatrix, 0, left, right, bottom, top, near, far);
		viewProjectionMatrixChanged = true;
	}

	public float [] getViewMatrix() {
		return viewMatrix;
	}

	public float[] getProjectionMatrix() {
		return projectionMatrix;
	}

	public float[] getViewProjectionMatrix() {
		if (viewProjectionMatrixChanged) {
			Matrix.multiplyMM(viewProjectionMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
			viewProjectionMatrixChanged = false;
		}
		return viewProjectionMatrix;
	}

	private void updateViewMatrix() {
		updateUp();
		Matrix.setLookAtM(viewMatrix, 0, eye.getX(), eye.getY(), eye.getZ(), lookAt.getX(), lookAt.getY(),
				lookAt.getZ(), up.getX(), up.getY(), up.getZ());
		viewProjectionMatrixChanged = true;
	}

	private void updateUp() {
		Vector3 lookAtVector = Vector3.sub(lookAt, eye);
		Vector3 right = Vector3.cross(lookAtVector, new Vector3(0f, 0f, 1f));
		up = right.cross(lookAtVector).normalize();
	}
}
