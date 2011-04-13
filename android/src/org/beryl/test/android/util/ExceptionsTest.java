package org.beryl.test.android.util;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.beryl.util.Exceptions;
import org.beryl.util.WrappedException;

public class ExceptionsTest extends TestCase {

	public void test_wrapCauseAndThrow_PreventRewrap() {
		Exception exception = createCheckedException();
		WrappedException wrapped = WrappedException.wrap(exception);
		WrappedException caught = null;
		try {
			Exceptions.wrapCauseAndThrow(wrapped);
		} catch(WrappedException wex) {
			caught = wex;
		} finally {
			Assert.assertSame(caught, wrapped);
		}
	}
	
	public void test_wrapCauseAndThrow_CauseIsWrapped() {
		Exception exception = createCheckedException();
		WrappedException wrapped = WrappedException.wrap(exception);
		Exception reWrapped = new Exception(wrapped);
		
		WrappedException caught = null;
		try {
			Exceptions.wrapCauseAndThrow(reWrapped);
		} catch(WrappedException wex) {
			caught = wex;
		} finally {
			Assert.assertSame(caught, wrapped);
		}
	}
	
	public void test_wrapCauseAndThrow_NoCause_UseOriginalInstead() {
		Exception exception = createCheckedException();
		WrappedException caught = null;
		try {
			Exceptions.wrapCauseAndThrow(exception);
		} catch(WrappedException wex) {
			caught = wex;
		} finally {
			Assert.assertSame(caught.getCause(), exception);
		}
	}
	
	public void test_wrapCauseAndThrow_HasCause() {
		Exception cause = createCheckedException();
		Exception exception = new Exception(cause);
		WrappedException caught = null;
		try {
			Exceptions.wrapCauseAndThrow(exception);
		} catch(WrappedException wex) {
			caught = wex;
		} finally {
			Assert.assertSame(caught.getCause(), cause);
		}
	}
	
	public void test_wrapAndThrow_CheckedException() {
		Exception exception = createCheckedException();
		WrappedException caught = null;
		try {
			Exceptions.wrapCauseAndThrow(exception);
		} catch(WrappedException wex) {
			caught = wex;
		} finally {
			Assert.assertSame(caught.getCause(), exception);
		}
	}
	
	public void test_wrapAndThrow_UncheckedException() {
		Exception exception = createUncheckedException();
		WrappedException caught = null;
		try {
			Exceptions.wrapCauseAndThrow(exception);
		} catch(WrappedException wex) {
			caught = wex;
		} finally {
			Assert.assertSame(caught.getCause(), exception);
		}
	}
	
	public void test_wrapAndThrow_PreventRewrap() {
		Exception exception = createUncheckedException();
		WrappedException wrapped = WrappedException.wrap(exception);
		WrappedException caught = null;
		try {
			Exceptions.wrapCauseAndThrow(wrapped);
		} catch(WrappedException wex) {
			caught = wex;
		} finally {
			Assert.assertSame(caught.getCause(), exception);
		}
	}

	static Exception createCheckedException() {
		return new Exception("Checked");
	}
	
	static Exception createUncheckedException() {
		return new RuntimeException("Unchecked");
	}
}
