package com.etaoin.myopengltest.util.io;

import java.io.IOException;

/**
 * Interface 3D model parsers.
 */
public interface ModelParser {

	public ParsedModel parse() throws IOException;

}