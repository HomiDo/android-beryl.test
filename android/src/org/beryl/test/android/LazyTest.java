package org.beryl.test.android;

import org.beryl.Lazy;

import junit.framework.TestCase;

public class LazyTest extends TestCase {

	public LazyTest() {
		super();
	}
	
	public void testAssignment() {
		final int expected = 5;
		Lazy<Integer> target = new Lazy<Integer>() {
			public Integer onSet() {
				return expected;
			}
		};
		assertEquals((Integer)expected, target.get());
	}
	
	static abstract class LazyWithValue extends Lazy<Integer> {
		public LazyWithValue(int someValue) {
			this.someValue = someValue;
		}
		public int someValue;
		public abstract Integer onSet();
	}
	
	public void testOnSetRunsWhenGet() {
		final int expected = 5;
		final int previewExpected = 1;
		LazyWithValue target = new LazyWithValue(previewExpected) {
			public Integer onSet() {
				someValue = expected;
				return expected;
			}
		};
		assertEquals(previewExpected, target.someValue);
		assertEquals((Integer)expected, target.get());
		assertEquals(expected, target.someValue);
	}
}
