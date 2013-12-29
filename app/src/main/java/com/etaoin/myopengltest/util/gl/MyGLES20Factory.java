package com.etaoin.myopengltest.util.gl;

/**
 * Factory for all the OpenGL ES 2.0 wrappers.
 */
public class MyGLES20Factory {

	public static final int DEBUG_LEVEL_NONE = 0;
	public static final int DEBUG_LEVEL_BASIC = 1; // TODO Do if needed
	public static final int DEBUG_LEVEL_ALL = 2;

	public MyGLES20 createGLES20(int type) {
		MyGLES20 gles20;

		switch (type) {
			case DEBUG_LEVEL_NONE:
				gles20 = new MyGLES20DebugNone();
				break;
			case DEBUG_LEVEL_ALL:
				gles20 = new MyGLES20DebugAll();
				break;
			default:
				throw new UnkownGLES20TypeRuntimeException(type);
		}

		return gles20;
	}

	public static class UnkownGLES20TypeRuntimeException extends RuntimeException {

		public UnkownGLES20TypeRuntimeException(int type) {
			super("Unknown MyGLES20 type: " + type);
		}
	}
}