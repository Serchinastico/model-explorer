package com.etaoin.myopengltest.core.main.context;

import com.etaoin.myopengltest.util.camera.Camera;
import com.etaoin.myopengltest.util.light.PointLight;

import java.util.Map;

// TODO Drawable and Context have the same method signatures...
public interface Context {

	public void initialize();

	public void draw();

	public Map<Integer, PointLight> getPointLights();

	public Camera getCamera();

}
