package com.etaoin.myopengltest.util.io;

import com.etaoin.myopengltest.core.main.context.ContextManager;
import com.etaoin.myopengltest.util.gl.MyGLES20;
import com.etaoin.myopengltest.util.io.obj.ObjParser;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Factory that creates the correct ModelParser from a file path.
 */
public class ModelParserFactory {

	public static final int OBJ_MODEL_TYPE = 0;

	private FileReader fileReader;

	private MyGLES20 gles20;

	private ContextManager contextManager;

	public ModelParserFactory(FileReader fileReader, MyGLES20 gles20, ContextManager contextManager) {
		this.fileReader = fileReader;
		this.gles20 = gles20;
		this.contextManager = contextManager;
	}

	public ModelParser createModelParser(int type, String path) throws IOException, InvalidModelParserTypeException {
		ModelParser modelParser;
		switch (type) {
			case OBJ_MODEL_TYPE:
				BufferedReader reader = fileReader.toBufferedReader(path);
				modelParser = new ObjParser(reader, gles20, contextManager);
				break;
			default:
				throw new InvalidModelParserTypeException(type);
		}

		return modelParser;
	}

	public static class InvalidModelParserTypeException extends Exception {
		public InvalidModelParserTypeException(int type) {
			super("Trying to create a model parser with an invalid type: " + type);
		}
	}
}
