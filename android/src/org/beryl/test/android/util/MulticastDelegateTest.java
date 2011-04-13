package org.beryl.test.android.util;

import junit.framework.TestCase;

import org.beryl.util.MulticastDelegate;
import org.beryl.util.WrappedException;

public class MulticastDelegateTest extends TestCase {

	public void testConstructor() {
		MulticastDelegate delegate = new MulticastDelegate();
		assertNotNull(delegate);
	}
	
	public void test_Invoke_ZeroParameters() {
		MulticastDelegate delegate = new MulticastDelegate();
		delegate.add(new ZeroParametersTester(1));
		try {
			delegate.invoke(ZeroParametersTester.class, "onZeroParameters");
			fail("onZeroParameters was not invoked.");
		} catch(WrappedException wrapped) {
			InvokedIndicator indicator = (InvokedIndicator)WrappedException.unwrap(wrapped);
			indicator.check(ZeroParametersTester.class, 1);
		}
	}
	
	static class ZeroParametersTester implements OnZeroParametersListener {
		final InvokedIndicator indicator;
		public ZeroParametersTester(int flag) {
			indicator = new InvokedIndicator(this, flag);
		}
		public void onZeroParameters() {
			throw indicator;
		}
	}
	
	static interface OnZeroParametersListener {
		void onZeroParameters();
	}
	
	static interface OnPrimitiveListener {
		void onOnePrimitiveParameter(int param);
	}
	
	static interface OnObjectListener {
		void onOneObjectParameter(String param);
		void onTwoObjectParameters(String param, Object randomObj);
	}
	
	@SuppressWarnings("serial")
	static class InvokedIndicator extends RuntimeException {
		public final int Flag;
		public final Class<?> OriginatorClass;
		
		public InvokedIndicator(Object obj, int flag) {
			this.Flag = flag;
			OriginatorClass = obj.getClass();
		}
		
		public void check(Class<?> clazz, int flag) {
			assertEquals(flag, this.Flag);
			assertEquals(clazz, OriginatorClass);
		}
	}
}
