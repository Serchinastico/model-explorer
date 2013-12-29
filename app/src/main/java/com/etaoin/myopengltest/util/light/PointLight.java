package com.etaoin.myopengltest.util.light;

import com.etaoin.myopengltest.util.geometry.Vector3;

public class PointLight {

	private Vector3 position;

	public PointLight(Vector3 position) {
		this.position = position;
	}

	public Vector3 getPosition() {
		return position;
	}
}
