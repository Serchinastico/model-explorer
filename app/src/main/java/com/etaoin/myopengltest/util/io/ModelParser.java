package com.etaoin.myopengltest.util.io;

import java.io.IOException;

public interface ModelParser {

	public ParsedModel parse() throws IOException;

	public ParsedModel getModel();
}
