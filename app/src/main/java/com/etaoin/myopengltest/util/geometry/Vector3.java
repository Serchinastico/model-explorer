package com.etaoin.myopengltest.util.geometry;

public class Vector3 {

	private static final int X_INDEX = 0;
	private static final int Y_INDEX = 1;
	private static final int Z_INDEX = 2;

	private float[] coordinates = new float[3];

	public Vector3(float x, float y, float z) {
		coordinates[X_INDEX] = x;
		coordinates[Y_INDEX] = y;
		coordinates[Z_INDEX] = z;
	}

	public float getX() {
		return coordinates[X_INDEX];
	}

	public float getY() {
		return coordinates[Y_INDEX];
	}

	public float getZ() {
		return coordinates[Z_INDEX];
	}
}
