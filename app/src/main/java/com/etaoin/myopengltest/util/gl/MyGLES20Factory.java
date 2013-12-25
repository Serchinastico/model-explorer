package com.etaoin.myopengltest.util.gl;

public class MyGLES20Factory {

	public static final int DEBUG_LEVEL_NONE = 0;
	public static final int DEBUG_LEVEL_BASIC = 1; // TODO Do if needed
	public static final int DEBUG_LEVEL_ALL = 2;

	public MyGLES20 createGLES20(int type) {
		MyGLES20 myGLES20;

		switch (type) {
			case DEBUG_LEVEL_NONE:
				myGLES20 = new MyGLES20DebugNone();
				break;
			case DEBUG_LEVEL_ALL:
				myGLES20 = new MyGLES20DebugAll();
				break;
			default:
				throw new UnkownGLES20TypeRuntimeException(type);
		}

		return myGLES20;
	}
}

class UnkownGLES20TypeRuntimeException extends RuntimeException {

	public UnkownGLES20TypeRuntimeException(int type) {
		super("Unknown MyGLES20 type: " + type);
	}
}