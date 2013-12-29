package com.etaoin.myopengltest.util.geometry;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

public class Vector3List extends ArrayList<Vector3> {

	// TODO This constant is repeated a few times...
	private static final int BYTES_PER_FLOAT = 4;

	public FloatBuffer toFloatBuffer() {
		FloatBuffer vertexBuffer;

		float[] vertices = new float[size() * 3];
		for (int i = 0; i < size(); i++) {
			vertices[3 * i] = get(i).getX();
			vertices[3 * i + 1] = get(i).getY();
			vertices[3 * i + 2] = get(i).getZ();
		}
		ByteBuffer vertexByteBuffer = ByteBuffer.allocateDirect(vertices.length * BYTES_PER_FLOAT);
		vertexByteBuffer.order(ByteOrder.nativeOrder());
		vertexBuffer = vertexByteBuffer.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

		return vertexBuffer;
	}
}
