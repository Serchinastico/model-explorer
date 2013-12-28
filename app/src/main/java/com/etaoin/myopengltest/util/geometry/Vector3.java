package com.etaoin.myopengltest.util.geometry;

/**
 * Class that represents a vector of 3 coordinates with common util operations.
 */
public class Vector3 {

	private float x;
	private float y;
	private float z;

	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public float getZ() {
		return this.z;
	}

	public void add(Vector3 v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
	}

	public void sub(Vector3 v) {
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
	}

	public void mul(float scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
	}

	public float dot(Vector3 v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}

	public void cross(Vector3 v) {
		float newX = y * v.z - z * v.y;
		float newY = z * v.x - x * v.z;
		float newZ = x * v.y - y * v.x;
		this.x = newX;
		this.y = newY;
		this.z = newZ;
	}

	public float norm() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
}
