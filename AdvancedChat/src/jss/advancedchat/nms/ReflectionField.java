package jss.advancedchat.nms;

import java.lang.reflect.Field;

public class ReflectionField {

	private Field field;

	public Field getRealField() {
		return field;
	}

	public ReflectionClass getReflectionClass() {
		return new ReflectionClass(field.getDeclaringClass());
	}

	public ReflectionClass getFieldReflectionClass() {
		return new ReflectionClass(field.getType());
	}

	ReflectionField(Field field) {
		this.field = field;
		field.setAccessible(true);
	}

	public RefExecutor of(Object e) {
		return new RefExecutor(e);
	}

	public class RefExecutor {
		Object e;

		public RefExecutor(Object e) {
			this.e = e;
		}

		public void set(Object param) {
			try {
				field.set(e, param);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		public Object get() {
			try {
				return field.get(e);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

}
