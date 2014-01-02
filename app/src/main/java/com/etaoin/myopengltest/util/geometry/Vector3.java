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

	@Override
	public Vector3 clone() {
		return new Vector3(x, y, z);
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

	public Vector3 addTo(Vector3 v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		return this;
	}

	public Vector3 add(Vector3 v) {
		Vector3 result = clone();
		return result.addTo(v);
	}

	public Vector3 subTo(Vector3 v) {
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		return this;
	}

	public Vector3 sub(Vector3 v) {
		Vector3 result = clone();
		return result.subTo(v);
	}

	public Vector3 mulTo(float scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
		return this;
	}

	public Vector3 mul(float scalar) {
		Vector3 result = clone();
		return result.mulTo(scalar);
	}

	public float dot(Vector3 v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}

	public Vector3 crossTo(Vector3 v) {
		this.x = y * v.z - z * v.y;
		this.y = z * v.x - x * v.z;
		this.z = x * v.y - y * v.x;
		return this;
	}

	public Vector3 cross(Vector3 v) {
		Vector3 result = clone();
		return result.crossTo(v);
	}

	public float norm() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public Vector3 normalizeTo() {
		float norm = norm();
		x /= norm;
		y /= norm;
		z /= norm;
		return this;
	}

	public Vector3 normalize() {
		Vector3 result = clone();
		return result.normalizeTo();
	}
}
