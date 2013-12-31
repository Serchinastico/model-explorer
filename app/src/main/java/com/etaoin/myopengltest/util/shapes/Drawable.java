package com.etaoin.myopengltest.util.shapes;

/**
 * Interface for objects that can be drawn in the canvas.
 */
public interface Drawable {

	public void initialize();

	public void draw(float[] vpMatrix);

}
