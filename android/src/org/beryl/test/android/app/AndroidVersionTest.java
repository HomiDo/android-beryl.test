package org.beryl.test.android.app;

import junit.framework.TestCase;

import org.beryl.app.AndroidVersion;

public class AndroidVersionTest extends TestCase {

	public AndroidVersionTest() {
		super();
	}
	
	public void testPrecondition() {
		assertTrue(AndroidVersion.getSdkVersion() >= 3);
	}
	
	public void testDonut() {
		assertEquals(AndroidVersion.getSdkVersion() >= android.os.Build.VERSION_CODES.DONUT,
				AndroidVersion.isDonutOrHigher());
	}
	
	public void testEclair() {
		assertEquals(AndroidVersion.getSdkVersion() >= android.os.Build.VERSION_CODES.ECLAIR,
					AndroidVersion.isEclairOrHigher());
	}
	
	public void testFroyo() {
		assertEquals(AndroidVersion.getSdkVersion() >= android.os.Build.VERSION_CODES.FROYO,
				AndroidVersion.isFroyoOrHigher());
	}
	
	public void testGingerbread() {
		assertEquals(AndroidVersion.getSdkVersion() >= android.os.Build.VERSION_CODES.GINGERBREAD,
				AndroidVersion.isFroyoOrHigher());
	}
	
	public void testHoneycomb() {
		assertEquals(AndroidVersion.getSdkVersion() >= android.os.Build.VERSION_CODES.HONEYCOMB,
				AndroidVersion.isHoneycombOrHigher());
	}
	
	public void testBeforeDonut() {
		assertEquals(AndroidVersion.getSdkVersion() < android.os.Build.VERSION_CODES.DONUT,
				AndroidVersion.isBeforeDonut());
	}
	
	public void testBeforeEclair() {
		assertEquals(AndroidVersion.getSdkVersion() < android.os.Build.VERSION_CODES.ECLAIR,
				AndroidVersion.isBeforeEclair());
	}
	
	public void testBeforeFroyo() {
		assertEquals(AndroidVersion.getSdkVersion() < android.os.Build.VERSION_CODES.FROYO,
				AndroidVersion.isBeforeFroyo());
	}
	
	public void testBeforeGingerbread() {
		assertEquals(AndroidVersion.getSdkVersion() < android.os.Build.VERSION_CODES.GINGERBREAD,
				AndroidVersion.isBeforeGingerbread());
	}
	
	public void testBeforeHoneycomb() {
		assertEquals(AndroidVersion.getSdkVersion() < android.os.Build.VERSION_CODES.HONEYCOMB,
				AndroidVersion.isBeforeHoneycomb());
	}
}
