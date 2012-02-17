package org.beryl.test.android.concurrent;

import org.beryl.concurrent.ReferenceCounter;

import junit.framework.TestCase;

public class ReferenceCounterTest extends TestCase {

	static class RunnableCheck implements Runnable {
		public int timesRun = 0;
		public void run() {
			timesRun++;
		}
	}
	public void testConstructor() {
		RunnableCheck c = new RunnableCheck();
		@SuppressWarnings("unused")
		final ReferenceCounter rc = new ReferenceCounter(c);
		assertEquals(0, c.timesRun);
	}
	
	public void testDownOnly() {
		RunnableCheck c = new RunnableCheck();
		final ReferenceCounter rc = new ReferenceCounter(c);
		rc.down();
		assertEquals(1, c.timesRun);
	}
	
	public void testUpOnly() {
		RunnableCheck c = new RunnableCheck();
		final ReferenceCounter rc = new ReferenceCounter(c);
		rc.up();
		assertEquals(0, c.timesRun);
	}
	
	public void testUpDown() {
		RunnableCheck c = new RunnableCheck();
		final ReferenceCounter rc = new ReferenceCounter(c);
		rc.up();
		rc.down();
		assertEquals(1, c.timesRun);
	}
	
	public void testUpUpDownDown() {
		RunnableCheck c = new RunnableCheck();
		final ReferenceCounter rc = new ReferenceCounter(c);
		rc.up();
		rc.up();
		rc.down();
		assertEquals(0, c.timesRun);
		rc.down();
		assertEquals(1, c.timesRun);
	}
}
