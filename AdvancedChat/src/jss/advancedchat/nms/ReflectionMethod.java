package jss.advancedchat.nms;

import java.lang.reflect.Method;

public class ReflectionMethod {

	private final Method method;

	public Method getRealMethod() {
		return method;
	}

	public ReflectionClass getRefClass() {
		return new ReflectionClass(method.getDeclaringClass());
	}

	public ReflectionClass getReturnRefClass() {
		return new ReflectionClass(method.getReturnType());
	}

	ReflectionMethod(Method method) {
		this.method = method;
		method.setAccessible(true);
	}

	public ReflectionExecutor of(Object e) {
		return new ReflectionExecutor(e);
	}

	public Object call(Object... params) {
		try {
			return method.invoke(null, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public class ReflectionExecutor {
		Object e;

		public ReflectionExecutor(Object e) {
			this.e = e;
		}

		public Object call(Object... params) {
			try {
				return method.invoke(e, params);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
