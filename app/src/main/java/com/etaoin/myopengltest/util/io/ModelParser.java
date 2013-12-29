package com.etaoin.myopengltest.util.io;

import com.etaoin.myopengltest.util.shapes.Drawable;

import java.io.IOException;

/**
 * Interface 3D model parsers.
 */
public interface ModelParser {

	public Drawable parse() throws IOException;

}