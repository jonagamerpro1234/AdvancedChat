package jss.advancedchat.nms;

import java.lang.reflect.Constructor;

public class ReflectionConstructor {

	private final Constructor<?> constructor;

	public Constructor<?> getRealConstructor() {
		return constructor;
	}

	public ReflectionClass getRefClass() {
		return new ReflectionClass(constructor.getDeclaringClass());
	}

	ReflectionConstructor(Constructor<?> constructor) {
		this.constructor = constructor;
		constructor.setAccessible(true);
	}

	public Object create(Object... params) {
		try {
			return constructor.newInstance(params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
