package com.etaoin.myopengltest.util.io.obj;

import com.etaoin.myopengltest.util.geometry.Vector3;
import com.etaoin.myopengltest.util.io.Face;
import com.etaoin.myopengltest.util.io.ParsedModel;

import java.util.List;

/**
 * Parsed model created through a .obj description.
 */
public class ObjParsedModel implements ParsedModel {

	private Vector3[] vertices;
	private Face[] faces;

	public ObjParsedModel(List<Vector3> vertices, List<Face> faces) {
		this.vertices = new Vector3[vertices.size()];
		this.faces = new Face[faces.size()];
		vertices.toArray(this.vertices);
		faces.toArray(this.faces);
	}

	@Override
	public Vector3[] getVertices() {
		return vertices;
	}

	@Override
	public Face[] getFaces() {
		return faces;
	}

	@Override
	public int getVerticesPerFace() {
		return faces[0].getVertexIndicesCount();
	}
}
