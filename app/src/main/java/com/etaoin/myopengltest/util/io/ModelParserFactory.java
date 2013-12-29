package com.etaoin.myopengltest.util.io;

import com.etaoin.myopengltest.util.io.obj.ObjParser;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Factory that creates the correct ModelParser from a file path.
 */
public class ModelParserFactory {

	public static final int OBJ_MODEL_TYPE = 0;

	private FileReader fileReader;

	public ModelParserFactory(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	public ModelParser createModelParser(int type, String path) throws IOException, InvalidModelParserTypeException {
		ModelParser modelParser;
		switch (type) {
			case OBJ_MODEL_TYPE:
				BufferedReader reader = fileReader.toBufferedReader(path);
				modelParser = new ObjParser(reader);
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
