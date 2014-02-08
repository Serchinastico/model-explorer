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

	public Vector3 setX(float x) {
		this.x = x;
		return this;
	}

	public Vector3 setY(float y) {
		this.y = y;
		return this;
	}

	public Vector3 setZ(float z) {
		this.z = z;
		return this;
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

	public Vector3 add(Vector3 v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		return this;
	}

	public static Vector3 add(Vector3 v1, Vector3 v2) {
		Vector3 result = v1.clone();
		return result.add(v2);
	}

	public Vector3 sub(Vector3 v) {
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		return this;
	}

	public static Vector3 sub(Vector3 v1, Vector3 v2) {
		Vector3 result = v1.clone();
		return result.sub(v2);
	}

	public Vector3 mul(float scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
		return this;
	}

	public static Vector3 mul(Vector3 v, float scalar) {
		Vector3 result = v.clone();
		return result.mul(scalar);
	}

	public float dot(Vector3 v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}

	public Vector3 cross(Vector3 v) {
		float x = this.y * v.z - this.z * v.y;
		float y = this.z * v.x - this.x * v.z;
		float z = this.x * v.y - this.y * v.x;
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public static Vector3 cross(Vector3 v1, Vector3 v2) {
		Vector3 result = v1.clone();
		return result.cross(v2);
	}

	public float norm() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public Vector3 normalize() {
		float norm = norm();
		x /= norm;
		y /= norm;
		z /= norm;
		return this;
	}

	public static Vector3 normalize(Vector3 v) {
		Vector3 result = v.clone();
		return result.normalize();
	}

	@Override
	public String toString() {
		return "[x: " + x + ", y: " + y + ", z: " + z + "]";
	}
}
