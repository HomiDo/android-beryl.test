package org.beryl.test.android.util;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.beryl.util.WrappedException;

public class WrappedExceptionTest extends TestCase {

	public void test_wrap_ShouldWrap_Exception() {
		Exception exception = ExceptionsTest.createCheckedException();
		WrappedException wrapped = WrappedException.wrap(exception);
		
		Assert.assertNotSame(exception, wrapped);
	}
	
	public void test_wrap_ShouldNotWrap_WrappedException() {
		Exception e = ExceptionsTest.createCheckedException();
		WrappedException wrapped = WrappedException.wrap(e);
		WrappedException reWrapped = WrappedException.wrap(wrapped);
		
		Assert.assertSame(reWrapped, wrapped);
	}
	
	public void test_wrap_ShouldWrap_RuntimeException() {
		Exception exception = ExceptionsTest.createUncheckedException();
		WrappedException wrapped = WrappedException.wrap(exception);
		
		Assert.assertNotSame(exception, wrapped);
	}
	
	public void test_unwrap_WrappedException() {
		Exception exception = ExceptionsTest.createUncheckedException();
		WrappedException wrapped = WrappedException.wrap(exception);
		
		assertSame(WrappedException.unwrap(wrapped), exception);
	}
	
	public void test_unwrap_PreventRewrap() {
		Exception exception = ExceptionsTest.createUncheckedException();
		WrappedException wrapped = WrappedException.wrap(exception);
		WrappedException rewrapped = WrappedException.wrap(wrapped);
		
		assertSame(WrappedException.unwrap(rewrapped), exception);
	}
}
