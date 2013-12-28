package com.etaoin.myopengltest.util.io.obj;

import com.etaoin.myopengltest.util.geometry.Vector3;
import com.etaoin.myopengltest.util.io.Face;
import com.etaoin.myopengltest.util.io.ModelParser;
import com.etaoin.myopengltest.util.io.ParsedModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser for .obj models
 */
public class ObjParser implements ModelParser {

	private BufferedReader content;
	private List<Vector3> vertices = new ArrayList<Vector3>();
	private List<Vector3> normals = new ArrayList<Vector3>();
	private List<Vector3> textureCoordinates = new ArrayList<Vector3>();
	private List<Face> faces = new ArrayList<Face>();
	private ObjParsedModel model;
	private boolean isSmoothEnabled = false; // TODO Use?

	public ObjParser(BufferedReader content) {
		this.content = content;
	}

	public ParsedModel parse() throws IOException {
		String line;
		while ((line = content.readLine()) != null) {
			line = line.trim();
			if (isVertexDefinition(line)) {
				parseVertex(line);
			} else if (isNormalDefinition(line)) {
				parseNormal(line);
			} else if (isTextureCoordinateDefinition(line)) {
				parseTextureCoordinate(line);
			} else if (isGroupNameDefinition(line)) {
				// Empty
			} else if (isSmoothGroupDefinition(line)) {
				// Empty
			} else if (isFaceDefinition(line)) {
				parseFace(line);
			}
		}

		model = new ObjParsedModel(vertices, faces);
		return model;
	}

	private boolean isVertexDefinition(String line) {
		return line.startsWith("v ");
	}

	private boolean isNormalDefinition(String line) {
		return line.startsWith("vn ");
	}

	private boolean isTextureCoordinateDefinition(String line) {
		return line.startsWith("vt ");
	}

	private boolean isGroupNameDefinition(String line) {
		return line.startsWith("g ");
	}

	private boolean isSmoothGroupDefinition(String line) {
		return line.startsWith("s ");
	}

	private boolean isFaceDefinition(String line) {
		return line.startsWith("f ");
	}

	private void parseVertex(String line) {
		vertices.add(parseVector(line));
	}

	private void parseNormal(String line) {
		normals.add(parseVector(line));
	}

	private void parseTextureCoordinate(String line) {
		textureCoordinates.add(parseVector(line));
	}

	private void parseFace(String line) {
		Face face = new Face();
		String[] faceParts = line.split(" ");
		for (int i = 1; i < faceParts.length; i++) {
			String[] vertexParts = faceParts[i].split("/");
			int vertexIndex = Integer.parseInt(vertexParts[0]) - 1;
			int normalIndex = Integer.parseInt(vertexParts[1]) - 1;
			int textureCoordinateIndex = Integer.parseInt(vertexParts[2]) - 1;

			face.addVertex(vertexIndex, normalIndex, textureCoordinateIndex);
		}
		faces.add(face);
	}

	private Vector3 parseVector(String line) {
		String[] parts = line.split(" ");
		float xCoordinate = Float.parseFloat(parts[parts.length - 3]);
		float yCoordinate = Float.parseFloat(parts[parts.length - 2]);
		float zCoordinate = Float.parseFloat(parts[parts.length - 1]);
		return new Vector3(xCoordinate, yCoordinate, zCoordinate);
	}
}
