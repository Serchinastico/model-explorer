package com.etaoin.myopengltest.util.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a single face of a model.
 */
public class Face {

	protected List<Integer> vertexIndices = new ArrayList<Integer>();
	protected List<Integer> normalIndices = new ArrayList<Integer>();
	protected List<Integer> textureCoordinateIndices = new ArrayList<Integer>();

	public void addVertex(int vertexIndex, int normalIndex, int textureCoordinateIndex) {
		vertexIndices.add(vertexIndex);
		normalIndices.add(normalIndex);
		textureCoordinateIndices.add(textureCoordinateIndex);
	}

	public List<Integer> getVertexIndices() {
		return vertexIndices;
	}
}
