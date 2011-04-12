package org.beryl.test.android.util;

import java.lang.reflect.Method;
import java.util.List;

import org.beryl.util.Reflection;

import android.test.AndroidTestCase;

public class ReflectionTest extends AndroidTestCase {

	public void test_findMethod_Object_HashCode() {
		Method method = tryFindDeclaredMethod(Object.class, "hashCode", null);
		assertNotNull(method);
	}
	
	public static Method tryFindDeclaredMethod(final Class<?> clazz, final String methodName, final List<Class<?>> paramTypes) {
		Method result = null;
		try {
			result = Reflection.findDeclaredMethod(clazz, methodName, paramTypes);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
}
