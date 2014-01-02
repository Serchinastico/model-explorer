package com.etaoin.myopengltest.core.main.context;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for swapping between contexts and send events to the right context.
 */
public class ContextManager {

	private List<Context> contexts;

	private Context currentContext;

	public ContextManager() {
		contexts = new ArrayList<Context>();
	}

	public void addContext(Context context) {
		contexts.add(context);
	}

	public void addCurrentContext(Context context) {
		contexts.add(context);
		currentContext = context;
	}

	public void initialize() {
		for (Context context : contexts) {
			context.initialize();
		}
	}

	public void draw() {
		currentContext.draw();
	}

	public Context getCurrentContext() {
		return currentContext;
	}

}
