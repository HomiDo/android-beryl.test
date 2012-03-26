package org.beryl.test.android.caching;

import org.beryl.cache.CachedItem;

import junit.framework.TestCase;

public class CachedItemTest extends TestCase {

	public void testConstructor() {
		CachedItem<Object> ci = new CachedItem<Object>(new Object());
		assertNotNull(ci.get());
	}
	
	public void testGetReturnsOriginalObject() {
		Object t = new Object();
		CachedItem<Object> ci = new CachedItem<Object>(t);
		assertSame(t, ci.get());
	}
	
	public void testGetReturnsNull() {
		CachedItem<Object> ci = new CachedItem<Object>(null);
		assertNull(ci.get());
	}
	
	public void testGetReturnsNullWhenCleared() {
		Object t = new Object();
		CachedItem<Object> ci = new CachedItem<Object>(t);
		ci.clear();
		assertNull(ci.get());
	}
	
	public void testIsValidReturnsTrueWhenHoldingObject() {
		Object t = new Object();
		CachedItem<Object> ci = new CachedItem<Object>(t);
		assertTrue(ci.isValid());
	}
	
	public void testIsValidReturnsFalseWhenHoldingNull() {
		CachedItem<Object> ci = new CachedItem<Object>(null);
		assertFalse(ci.isValid());
	}
	
	public void testIsValidReturnsFalseWhenCleared() {
		Object t = new Object();
		CachedItem<Object> ci = new CachedItem<Object>(t);
		ci.clear();
		assertFalse(ci.isValid());
	}
	
	public void testToStringIsSameToOriginal() {
		Object t = new Object();
		CachedItem<Object> ci = new CachedItem<Object>(t);
		assertEquals(t.toString(), ci.toString());
	}
	
	public void testToStringForNullValues() {
		Object t = new Object();
		CachedItem<Object> ci = new CachedItem<Object>(t);
		ci.clear();
		assertEquals("<null>, cached value dropped.", ci.toString());
	}
}
