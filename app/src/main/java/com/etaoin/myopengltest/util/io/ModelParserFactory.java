package com.etaoin.myopengltest.util.io;

import com.etaoin.myopengltest.util.io.obj.ObjParser;

import java.io.BufferedReader;
import java.io.IOException;

public class ModelParserFactory {

	public static final int OBJ_MODEL_TYPE = 0;

	private FileReader fileReader;

	public ModelParserFactory(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	public ModelParser createModelParser(int type, String path) throws IOException {
		ModelParser modelParser = null;

		switch (type) {
			case OBJ_MODEL_TYPE:
				BufferedReader reader = fileReader.toBufferedReader(path);
				modelParser = new ObjParser(reader);
				break;
			default:
				// TODO Throw exception!
		}

		return modelParser;
	}
}
