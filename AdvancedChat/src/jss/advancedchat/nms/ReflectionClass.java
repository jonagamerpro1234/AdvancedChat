package jss.advancedchat.nms;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReflectionClass {

	private final Class<?> clazz;
	
	public Class<?> getRealClass(){
		return clazz;
	}
	
	public ReflectionClass(Class<?> clazz) {
		this.clazz = clazz;
	}
	
    public boolean isInstance(Object object){
        return clazz.isInstance(object);
    }
    
    public ReflectionMethod getMethod(String name, Object... types) {
        try {
            Class<?>[] classes = new Class[types.length];
            int i=0; for (Object e: types) {
                if (e instanceof Class) classes[i++] = (Class<?>)e;
                else if (e instanceof ReflectionClass) classes[i++] = ((ReflectionClass) e).getRealClass();
                else classes[i++] = e.getClass();
            }
            try {
                return new ReflectionMethod(clazz.getMethod(name, classes));
            } catch (NoSuchMethodException ignored) {
                return new ReflectionMethod(clazz.getDeclaredMethod(name, classes));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public ReflectionConstructor getConstructor(Object... types) {
        try {
            Class<?>[] classes = new Class[types.length];
            int i=0; for (Object e: types) {
                if (e instanceof Class) classes[i++] = (Class<?>)e;
                else if (e instanceof ReflectionClass) classes[i++] = ((ReflectionClass) e).getRealClass();
                else classes[i++] = e.getClass();
            }
            try {
                return new ReflectionConstructor(clazz.getConstructor(classes));
            } catch (NoSuchMethodException ignored) {
                return new ReflectionConstructor(clazz.getDeclaredConstructor(classes));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public ReflectionMethod findMethod(Object... types) {
        Class<?>[] classes = new Class[types.length];
        int t=0; for (Object e: types) {
            if (e instanceof Class) classes[t++] = (Class<?>)e;
            else if (e instanceof ReflectionClass) classes[t++] = ((ReflectionClass) e).getRealClass();
            else classes[t++] = e.getClass();
        }
        List<Method> methods = new ArrayList<>();
        Collections.addAll(methods, clazz.getMethods());
        Collections.addAll(methods, clazz.getDeclaredMethods());
        findMethod: for (Method m: methods) {
            Class<?>[] methodTypes = m.getParameterTypes();
            if (methodTypes.length != classes.length) continue;
            for (int i=0; i<classes.length;) {
                if (!classes.equals(methodTypes)) continue findMethod;
                return new ReflectionMethod(m);
            }
        }
        throw new RuntimeException("no such method");
    }
    
    public ReflectionMethod findMethodByName(String... names) {
        List<Method> methods = new ArrayList<>();
        Collections.addAll(methods, clazz.getMethods());
        Collections.addAll(methods, clazz.getDeclaredMethods());
        for (Method m: methods) {
            for (String name: names) {
                if (m.getName().equals(name)) {
                    return new ReflectionMethod(m);
                }
            }
        }
        throw new RuntimeException("no such method");
    }
    
    public ReflectionMethod findMethodByReturnType(Class<?> type) {
        if (type==null) type = void.class;
        List<Method> methods = new ArrayList<>();
        Collections.addAll(methods, clazz.getMethods());
        Collections.addAll(methods, clazz.getDeclaredMethods());
        for (Method m: methods) {
            if (type.equals(m.getReturnType())) {
                return new ReflectionMethod(m);
            }
        }
        throw new RuntimeException("no such method");
    }
    
    public ReflectionConstructor findConstructor(int number) {
        List<Constructor<?>> constructors = new ArrayList<>();
        Collections.addAll(constructors, clazz.getConstructors());
        Collections.addAll(constructors, clazz.getDeclaredConstructors());
        for (Constructor<?> m: constructors) {
            if (m.getParameterTypes().length == number) return new ReflectionConstructor(m);
        }
        throw new RuntimeException("no such constructor");
    }
    
    public ReflectionField getField(String name) {
        try {
            try {
                return new ReflectionField(clazz.getField(name));
            } catch (NoSuchFieldException ignored) {
                return new ReflectionField(clazz.getDeclaredField(name));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public ReflectionField findField(ReflectionClass type) {
        return findField(type.clazz);
    }
    
    public ReflectionField findField(Class<?> type) {
        if (type==null) type = void.class;
        List<Field> fields = new ArrayList<>();
        Collections.addAll(fields, clazz.getFields());
        Collections.addAll(fields, clazz.getDeclaredFields());
        for (Field f: fields) {
            if (type.equals(f.getType())) {
                return new ReflectionField(f);
            }
        }
        throw new RuntimeException("no such field");
    }
	
}
