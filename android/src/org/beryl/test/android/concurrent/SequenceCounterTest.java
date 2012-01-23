package org.beryl.test.android.concurrent;

import org.beryl.concurrent.SequenceCounter;

import junit.framework.TestCase;

public class SequenceCounterTest extends TestCase {

	public void testDefaultConstructor() {
		SequenceCounter sc = new SequenceCounter();
		assertEquals(0, sc.peek());
	}
	
	public void testSetConstructor() {
		SequenceCounter sc = new SequenceCounter(20);
		assertEquals(20, sc.peek());
	}
	
	public void testNextIncrementsCounter() {
		SequenceCounter sc = new SequenceCounter();
		assertEquals(1, sc.next());
		assertEquals(1, sc.peek());
	}
	
	public void testSet() {
		SequenceCounter sc = new SequenceCounter();
		sc.set(20);
		assertEquals(20, sc.peek());
	}
	
	public void testSetAndNext() {
		SequenceCounter sc = new SequenceCounter();
		sc.set(20);
		assertEquals(20, sc.peek());
		sc.set(40);
		assertEquals(40, sc.peek());
		assertEquals(41, sc.next());
		assertEquals(41, sc.peek());
	}
}
