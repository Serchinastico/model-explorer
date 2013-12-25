package com.etaoin.myopengltest.core.main.events;

import android.view.MotionEvent;

public class UserEvent {

	private MotionEvent nativeEvent;
	private float previousTouchX;
	private float previousTouchY;
	private int height;
	private int width;
	private float deltaX;
	private float deltaY;

	public UserEvent(MotionEvent event, float previousTouchX, float previousTouchY, int height, int width) {
		nativeEvent = event;
		this.previousTouchX = previousTouchX;
		this.previousTouchY = previousTouchY;
		this.height = height;
		this.width = width;

		computeDeltas();
	}

	public float getDeltaX() {
		return deltaX;
	}

	public float getDeltaY() {
		return deltaY;
	}

	private void computeDeltas() {
		float x = nativeEvent.getX();
		float y = nativeEvent.getY();

		deltaX = x - previousTouchX;
		deltaY = y - previousTouchY;

		if (y > height / 2) {
			deltaX = -deltaX;
		}

		if (x < width / 2) {
			deltaY = -deltaY;
		}
	}
}
