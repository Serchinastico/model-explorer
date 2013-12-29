package com.etaoin.myopengltest.util.geometry;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;

public class TriangleList extends ArrayList<Face> {

	public static final int VERTICES_PER_FACE = 3;

	// TODO This constant is repeated a few times...
	private static final int BYTES_PER_SHORT = 3;

	public ShortBuffer toShortBuffer() {
		int verticesCount = size() * VERTICES_PER_FACE;
		short[] vertexIndices = new short[verticesCount];
		for (int i = 0; i < size(); i++) {
			List<Integer> faceVertexIndices = get(i).getVertexIndices();
			for (int j = 0; j < VERTICES_PER_FACE; j++) {
				vertexIndices[3 * i + j] = faceVertexIndices.get(j).shortValue();
			}
		}
		ByteBuffer drawListByteBuffer = ByteBuffer.allocateDirect(vertexIndices.length * BYTES_PER_SHORT);
		drawListByteBuffer.order(ByteOrder.nativeOrder());
		ShortBuffer drawListBuffer = drawListByteBuffer.asShortBuffer();
		drawListBuffer.put(vertexIndices);
		drawListBuffer.position(0);

		return drawListBuffer;
	}
}
