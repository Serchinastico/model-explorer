package com.etaoin.myopengltest.core.main.context;

import com.etaoin.myopengltest.util.light.PointLight;
import com.etaoin.myopengltest.util.shapes.Drawable;

import java.util.HashMap;
import java.util.Map;

public class GameContext implements Context {

	// TODO Add priorities for canvas rendering
	private Map<Integer, Drawable> drawables;

	// TODO Use Light interface
	private Map<Integer, PointLight> pointLights;

	private int nextDrawableId;
	private int nextPointLightId;

	public GameContext() {
		drawables = new HashMap<Integer, Drawable>();
		pointLights = new HashMap<Integer, PointLight>();
		nextDrawableId = 0;
		nextPointLightId = 0;
	}

	public void addDrawable(Drawable drawable) {
		drawables.put(nextDrawableId++, drawable);
	}

	public void addPointLight(PointLight pointLight) {
		pointLights.put(nextPointLightId++, pointLight);
	}

	@Override
	public void initialize() {
		for (Drawable drawable : drawables.values()) {
			drawable.initialize();
		}
	}

	@Override
	public void draw(float[] vpMatrix) {
		for (Drawable drawable : drawables.values()) {
			drawable.draw(vpMatrix);
		}
	}

	public Map<Integer, Drawable> getDrawables() {
		return drawables;
	}

	public Map<Integer, PointLight> getPointLights() {
		return pointLights;
	}
}
