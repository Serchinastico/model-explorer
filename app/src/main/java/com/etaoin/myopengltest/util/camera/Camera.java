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
	private boolean viewMatrixChanged;
	private boolean projectionMatrixChanged;
	private Vector3 eye;
	private Vector3 lookAt;
	private Vector3 up;
	private float xRotation;
	private float yRotation;
	private float zRotation;

	public Camera(Vector3 eye, Vector3 lookAt) {
		viewMatrix = new float[16];
		projectionMatrix = new float[16];
		viewProjectionMatrix = new float[16];
		this.eye = eye;
		this.lookAt = lookAt;
		xRotation = 0f;
		yRotation = 0f;
		zRotation = 0f;

		// Force to create the view & projection matrices
		viewMatrixChanged = true;
		projectionMatrixChanged = true;
	}

	public void move(Vector3 direction) {
		eye.add(direction);
		viewMatrixChanged = true;
	}

	public void moveTo(Vector3 position) {
		eye = position;
		viewMatrixChanged = true;
	}

	public void lookAt(Vector3 position) {
		lookAt = position;
		viewMatrixChanged = true;
	}

	public void rotateX(float angle) {
		xRotation += angle;
		viewMatrixChanged = true;
	}

	public void rotateY(float angle) {
		yRotation += angle;
		viewMatrixChanged = true;
	}

	public void rotateZ(float angle) {
		zRotation += angle;
		viewMatrixChanged = true;
	}

	public void setProjectionMatrix(float left, float right, float bottom, float top, float near, float far) {
		Matrix.frustumM(projectionMatrix, 0, left, right, bottom, top, near, far);
		projectionMatrixChanged = true;
	}

	public Vector3 getPosition() {
		return eye;
	}

	public Vector3 getLookAt() {
		return lookAt;
	}

	public float[] getViewMatrix() {
		return viewMatrix;
	}

	public float[] getProjectionMatrix() {
		return projectionMatrix;
	}

	public float[] getViewProjectionMatrix() {
		if (viewMatrixChanged) {
			updateViewMatrix();
		}

		if (viewMatrixChanged || projectionMatrixChanged) {
			Matrix.multiplyMM(viewProjectionMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
			viewMatrixChanged = false;
			projectionMatrixChanged = false;
		}

		return viewProjectionMatrix;
	}

	private void updateViewMatrix() {
		updateUp();
		Matrix.setLookAtM(viewMatrix, 0, eye.getX(), eye.getY(), eye.getZ(), lookAt.getX(), lookAt.getY(),
				lookAt.getZ(), up.getX(), up.getY(), up.getZ());
		Matrix.rotateM(viewMatrix, 0, xRotation, 1f, 0f, 0f);
		Matrix.rotateM(viewMatrix, 0, yRotation, 0f, 1f, 0f);
		Matrix.rotateM(viewMatrix, 0, zRotation, 0f, 0f, 1f);
		projectionMatrixChanged = true;
	}

	private void updateUp() {
		Vector3 lookAtVector = Vector3.sub(lookAt, eye);
		Vector3 right = Vector3.cross(lookAtVector, new Vector3(0f, 0f, 1f));
		up = right.cross(lookAtVector).normalize();
	}
}
