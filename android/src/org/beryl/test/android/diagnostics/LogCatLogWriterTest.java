package org.beryl.test.android.diagnostics;

import org.beryl.diagnostics.LogCatLogWriter;

import junit.framework.Assert;
import junit.framework.TestCase;

public class LogCatLogWriterTest extends TestCase {

	public void test_DefaultConstructor_NoExceptionOrNull() {
		LogCatLogWriter logcat = null;
		try {
			logcat = new LogCatLogWriter();
		} catch(Exception e) {
			Assert.fail();
		} finally {
			Assert.assertNotNull(logcat);
		}
	}
	
	public void test_d_NullTagAndMsg() {
		LogCatLogWriter logcat = null;
		try {
			logcat = new LogCatLogWriter();
			logcat.d(null, null);
		} catch(Exception e) {
			Assert.fail();
		} finally {
			Assert.assertNotNull(logcat);
		}
	}
	
	public void test_e_NullTagAndException() {
		LogCatLogWriter logcat = null;
		try {
			logcat = new LogCatLogWriter();
			Exception e = null;
			logcat.e(null, e);
		} catch(Exception e) {
			Assert.fail();
		} finally {
			Assert.assertNotNull(logcat);
		}
	}
	
	public void test_e_NullTagAndMsg() {
		LogCatLogWriter logcat = null;
		try {
			logcat = new LogCatLogWriter();
			String s = null;
			logcat.e(null, s);
		} catch(Exception e) {
			Assert.fail();
		} finally {
			Assert.assertNotNull(logcat);
		}
	}
	
	public void test_i_NullTagAndMsg() {
		LogCatLogWriter logcat = null;
		try {
			logcat = new LogCatLogWriter();
			logcat.i(null, null);
		} catch(Exception e) {
			Assert.fail();
		} finally {
			Assert.assertNotNull(logcat);
		}
	}
	
	public void test_w_NullTagAndMsg() {
		LogCatLogWriter logcat = null;
		try {
			logcat = new LogCatLogWriter();
			logcat.w(null, null);
		} catch(Exception e) {
			Assert.fail();
		} finally {
			Assert.assertNotNull(logcat);
		}
	}
}
