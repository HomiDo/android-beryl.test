package org.beryl.test.android.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.beryl.util.Reflection;

public class ReflectionTest extends TestCase {

	public void test_findMethod_MethodWithZeroParameters() {
		Method method = tryFindDeclaredMethod(Object.class, "hashCode", null);
		assertNotNull(method);
	}
	
	public void test_findMethod_MethodWithParameters() {
		List<Class<?>> params = new ArrayList<Class<?>>();
		params.add(String.class);
		Method method = tryFindDeclaredMethod(String.class, "indexOf", params);
		assertNotNull(method);
	}
	
	public void test_findMethod_MethodWithParameters_InvokeYieldsExpectedResult() {
		List<Class<?>> params = new ArrayList<Class<?>>();
		params.add(String.class);
		Method method = tryFindDeclaredMethod(String.class, "indexOf", params);
		String testString = "unit-test";
		try {
			Integer intVal = (Integer)method.invoke(testString, "test");
			assertEquals(intVal.intValue(), 5);
		} catch (Exception e) {
			fail(e.getMessage());
		}
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
