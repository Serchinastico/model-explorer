package com.etaoin.myopengltest.util.io;

import com.etaoin.myopengltest.util.geometry.Vector3;

/**
 * Interface for all the parsed models.
 */
public interface ParsedModel {

	public Vector3[] getVertices();

	public Face[] getFaces();

	public int getVerticesPerFace();
}
